package Controleur;
/**
 * \file Controler.java
 * \brief Implementation of the class controler
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 16 April 2016
 * \details MVC conception
 */

import Modele.Marche;
import Modele.Utilisateur;
import Utilitaires.DataBase.DataBase;
import Vue.FenetreGUI;
import Vue.FinalCashFrame;
import Vue.JOptionException;

import java.sql.Connection;
import java.util.Scanner;

/**
 * \class Controler
 * \brief Permit to control the event of the Vue (GUI) and modify the Modele
 */
public class Controler {
    // VARIABLES GLOBALES DU JEU //
    final static int nbreAction = 48; // Doit être <= 48
    final static int nbreTour = 15; // Doit être < 52
    final static double cash = 20000; // Doit être > 0

    // ATTRIBUTS //
    private static FenetreGUI gui; /**< Vue part, Graphic Interface*/
    private static Marche market; /**< Model part, the market*/
    private static Utilisateur user; /**< Model part, the user*/
    private static Connection connection; /**< DataBase part, the connection*/

    // CONSTRUCTOR //

    /**
     * \fn Controler()
     * \brief Constructor
     *
     * \throws Exception if there is a problem with database connection
     */
    public Controler()throws Exception{
        // Connexion au VPN obligatoire
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*** Veuillez vous connecter au \033[31mVPN Ensimag\033[m pour utiliser la Base de Donnée\n"
                +"*** Taper sur une touche une fois que c'est fait");
        sc.nextLine();

        // Affichage des règles pendant la création de la base
        rules();
        // Initialisation de la BD //
        connection = DataBase.initBD(nbreAction,nbreTour);
        // Initialisation du marché
        try {
            market = new Marche("MARCHÉ DES ACTIONS",1,nbreAction,connection);
        }catch (Exception e){
            throw new Exception(e.getMessage() +
                        "Problème de connexion BD (VPN Activé ?)\n");
        }
        // Initialisation de l'utilisateur
        System.out.println("Appuyer sur ENTRER lorsque vous êtes prêt à démarrer ! \n");
        sc.nextLine();
        System.out.print("\n*** Veuillez entrer votre pseudo : ");
        String nom = sc.nextLine();
        user = new Utilisateur(1,nom,cash);
        System.out.println("\n\n+ Création du joueur : " +
                user.getNom() + " effectuée. Cash initial : " +
                user.getArgent() + " euros.");
        // Ouverture de la fenetre
        gui = new FenetreGUI(user.getNom());
        // Update de tous les onglets
        gui.updatePortfolio(user.toStringPortefeuille());
        gui.updateFavoris(user.toStringFavoris());
        gui.updateHistorique(user.toStringHistorique());
        gui.updateCours("");
        gui.updateMarche(market.toString());
        gui.updateTitleMarche(market.getNom() + " (" + getTour() + " )");
    }

    // CONTROL METHODS //

    /**
     * \fn closeGui()
     * \brief Permit to close the GUI window clearly
     */
    public static void closeGUI(){
        DataBase.closeBD(connection);
        FinalCashFrame guiFinal = new FinalCashFrame(null,"  FIN DU JEU",true,(user.getArgent()+user.getArgentPortfolio()-cash));
        gui.dispose();
    }

    /**
     * \fn void achat(int ID, int qte)
     * \brief Permit to buy an asset
     *
     * \param int ID : The asset's ID
     * \param qte : The asset's quantity
     */
    public static void achat(int ID, int qte){
        if (ID >= nbreAction){
            new JOptionException("Erreur ID","L'ID est inexistant !");
        }else {
            try {
                user.acheter(ID, qte);
                gui.updatePortfolio(user.toStringPortefeuille());
                gui.updateHistorique(user.toStringHistorique());
            } catch (Exception e) {
                new JOptionException("Erreur d'achat", e.getMessage());
            }
        }
    }

    /**
     * \fn void vente(int pos, int qte)
     * \brief Permit to sell an asset
     *
     * \param int pos : The asset's position
     * \param int qte : The asset's quantity
     */
    public static void vente(int pos, int qte){
        try {
            user.vendre(pos, qte);
            gui.updatePortfolio(user.toStringPortefeuille());
            gui.updateHistorique(user.toStringHistorique());
        }catch (Exception e){
            new JOptionException("Erreur de Vente", e.getMessage());
        }
    }

    /**
     * \fn void surveiller(int ID)
     * \brief Permit to control an asset
     *
     * \param int ID : The asset's ID
     */
    public static void surveiller(int ID){
        if (ID >= nbreAction){
            new JOptionException("Erreur ID","L'ID est inexistant !");
        }else {
            user.ajoutFav(ID);
            gui.updateFavoris(user.toStringFavoris());
        }
    }

    /**
     * \fn void notSurveiller(int pos)
     * \brief Permit to remove a controlled asset
     *
     * \param int pos : The asset's position
     */
    public static void notSurveiller(int pos){
        try {
            user.retirerFav(pos);
            gui.updateFavoris(user.toStringFavoris());
        }catch (Exception e){
            new JOptionException("Erreur position", e.getMessage());
        }
    }

    /**
     * \fn void nextStep()
     * \brief Permit to go to the next step
     *
     * \details : Close the GUI if it is the end of the game
     */
    public static void nextStep(){
        // Si on a fini le jeu
        if (getTour() >= nbreTour){
            closeGUI();
            return;
        }
        // Sinon on update
        market.nextLap();
        gui.updatePortfolio(user.toStringPortefeuille());
        gui.updateHistorique(user.toStringHistorique());
        gui.updateFavoris(user.toStringFavoris());
        gui.updateMarche(market.toString());
        gui.updateTitleMarche(market.getNom() + " (" + getTour() + ")");
    }

    /**
     * \fn void consulterCours(int ID)
     * \brief Permit to consult the price of an asset during all the game
     *
     * \param int ID : The asset's ID
     */
    public static void consulterCours(int ID){
        String res = "";
        try {
            String nomAction = Marche.getNom(ID);
            res += "\n  L'Historique de l'action " + nomAction + " est le suivant : \n\n";
            res += DataBase.consulterCours(ID,Marche.getTourCour(),connection);
            gui.updateCours(res);
        }catch (Exception e){
            new JOptionException("Erreur ID", e.getMessage());
        }
    }

    // AUTRES //

    /**
     * \int getTour()
     * \brief Permit to know in which lap the user are
     *
     * \return int : The current lap
     */
    public static int getTour(){
        return Marche.getTourCour();
    }

    /**
     * \void rules()
     * \brief Display of the game's rules
     */
    private void rules(){
        System.out.println("\n" +
                "\033[31m[RULES]\033[m\n");
    }
}
