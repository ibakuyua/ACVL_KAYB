package Modele; /**
 * \file Modele.Utilisateur.java
 * \brief Permit to implement the Modele.Utilisateur class
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 10 April 2016
 */

import Modele.Actions.ListeDActionDetenus;
import Modele.Actions.ListeDActionSurveilles;
import Modele.Marche;

import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * \class Modele.Utilisateur
 * \brief Permit to represent an user
 *
 * \details A user has got an ID and a name
 * \details A user contains money (cash+bank loan) with which he can buy asset on the market
 * \details He has got a Portefeuille of detained assets with which he can sell assets
 * \details He has got a Portefeuille of bookmarks assets to control
 *
 */
public class Utilisateur {

    // ATTRIBUTS //
    private int ID; /**< User's ID*/
    private String nom; /**< User's name*/
    private double argent; /**< User's Cash */
    private ListeDActionDetenus portefeuille; /**< User's portfolio*/
    private ListeDActionSurveilles favoris; /**< User's list of favorite assets*/
    private static int numberTransaction;
    // CONSTRUCTOR //

    /**
     * \fn Modele.Utilisateur(int ID, String nom, double argent)
     * \brief Constructor of a Modele.Utilisateur
     *
     * \param int ID : The ID of the user
     * \param String nom : The name of the user
     * \param double argent : Initial mountant of the user
     */
    public Utilisateur(int ID, String nom, double argent){
        this.ID = ID;
        this.nom = nom;
        this.argent = argent;
        numberTransaction = 1;
        portefeuille = new ListeDActionDetenus();
        favoris = new ListeDActionSurveilles();
    }

    // GETTERS & SETTERS //

    public int getID() {
        return ID;
    }

    public String getNom() {
        return nom;
    }

    public double getArgent() {
        return argent;
    }

    // METHODS //

    /**
     * \fn void acheter(int IDAction, int quantite)
     * \brief Permit a user to buy an asset
     *
     * \param int IDAction : The ID of the asset
     * \param quantite : The quantity to buy
     * \throws Exception : If the user doesn't have enough money
     */
    public void acheter(int IDAction, int quantite) throws Exception{

        if (quantite*Marche.getValeur(IDAction) > argent){
            throw new Exception("\n\033[31m[FAIL]\033[m\n||Exception : Vous n'avez pas assez de fond pour faire cet achat\n");
        }
        // Achat des actions
        argent -= portefeuille.ajout(IDAction,quantite);
        // Mettre dans l'historique
        putHistorique(0,ID,IDAction,quantite);
        // Transaction suivante
        numberTransaction++;

    }

    /**
     * \fn void vendre(int position, int quantite)
     * \brief Permit a user to sell an asset
     *
     * \param int position : Position of the asset in the portfolio
     * \param int quantite : Quantity to sell
     */
    public void vendre(int position, int quantite) {
        // Vente de la quantite d'action donnée
        try{
            double [] arID = portefeuille.retirer(position-1,quantite);
            argent += arID[0];
            // Mettre dans l'historique
            putHistorique(ID,0,(int)arID[1],quantite);
            // Transaction suivante
            numberTransaction++;
        }// Cas de la vente à découvert ou d'une position trop grande
        catch (Exception e){
            System.out.println(e.getMessage() + " ==> Veuillez reformuler votre demande \n");
        }
    }

    /**
     * \fn void ajoutFav()
     * \brief Permit to add an asset in order to control it
     *
     * \param int IDAction : The asset to control
     */
    public void ajoutFav(int IDAction){
        favoris.ajout(IDAction,1);
    }

    /**
     * \fn void retirerFav(int position)
     * \brief Permit to remove an asset of the list of controling assets
     *
     * \param int position : Position of the asset to remove
     */
    public void retirerFav(int position){
        try{
            favoris.retirer(position-1,1);
        }// Cas d'une position trop grande
        catch (Exception e){
            System.out.println(e.getMessage() + "==> Veuillez reformuler votre demande\n");
        }
    }

    /**
     * \fn String toStringPortefeuille()
     * \brief Permit to describe the portfolio
     *
     * \return String : The description
     */
    public String toStringPortefeuille() {
        DecimalFormat df = new DecimalFormat("0.0##");
        return "\n ** " + nom + "\n" + portefeuille.toString() + "\n\n  Cash disponible : " + df.format(argent) + " euros";
    }

    /**
     * \fn String toStringFavoris()
     * \brief Permit to describe the controling assets
     *
     * \return String : The description
     */
    public String toStringFavoris() {
        return favoris.toString();
    }


    /**
     * \fn String toStringHistorique()
     * \brief Permit to describe the different event about the user
     *
     * \return String : The description
     */
    public String toStringHistorique() {
        String Res = "";
        try{
            java.sql.Statement stmt = Marche.getConnection().createStatement();
            String getHist = "Select * from HISTORIQUE WHERE (IDACHETEUR = " + ID + " OR IDVENDEUR = " + ID + ")";
            java.sql.ResultSet ResSet = stmt.executeQuery(getHist);
            while(ResSet.next()){
                try {
                    if(ResSet.getInt("IDACHETEUR") == ID) {
                        Res = Res + ResSet.getInt("NUMTRANSAC") + ". :\n" +
                                "  ACHAT de l'action : "
                                + "n° " + ResSet.getInt("IDACTION") + " --> "+ Marche.getNom(ResSet.getInt("IDACTION")) +"\n"
                                + "  Quantité d'action impliquée dans la vente : " + ResSet.getInt("QUANTITY") + "\n\n";
                    }else{
                        Res = Res + ResSet.getInt("NUMTRANSAC") + ". :\n" +
                                "  VENTE de l'action : "
                                + "n° " + ResSet.getInt("IDACTION") + " --> "+ Marche.getNom(ResSet.getInt("IDACTION")) +"\n"
                                + "  Quantité d'action impliquée dans la vente : " + ResSet.getInt("QUANTITY") + "\n\n";
                    }
                }catch (Exception e){

                }




            }

            return Res;
        }catch (SQLException e){
            System.out.println("\n\033[31m[FAIL]\033[m\n");
            System.out.println("||Exception : Problème lors de la lecture de l'historique");
        }
        return Res;
    }


    // PRIVATE FUNCTION //

    /**
     * \putHistorique(int IDVendeur,int IDAcheteur, int IDAction, int qte)
     * \brief Permit to put on the table Historique in the database a data
     *
     * \param int IDVendeur : Who sell (0 = Market)
     * \param int IDAcheteur : Who buy (0=Market)
     * \param IDAction : Which asset
     * \param int qte : How much
     *
     * \details private function used in Modele.Utilisateur::acheter and Modele.Utilisateur::vendre
     */
    private void putHistorique(int IDVendeur,int IDAcheteur, int IDAction, int qte){
        try {
            java.sql.Statement stmt = Marche.getConnection().createStatement();
            String setHist;
            setHist  = "insert into HISTORIQUE values (";
            setHist += IDVendeur + ", ";
            setHist += IDAcheteur + ", ";
            setHist += IDAction + ", ";
            setHist += qte + ",";
            setHist += numberTransaction + ")";
            stmt.executeQuery(setHist);
        }catch (Exception e){
            System.out.println("\n\033[31m[FAIL]\033[m\n");
            System.out.println("||Exception : Problème pour la mise à jour de l'historique");
        }
    }
}
