/**
 * \file Simu.java
 * \brief main of the simulation game
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 11 April 2016
 */

import java.util.Scanner;
import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * \class Simu
 * \brief Finance Game
 *
 * \details Main of our program
 */
public class Simu {


    /**
     * \fn void main(String[] args)
     * \brief main of the game
     *
     * \details Launch the menu : 1) Play ; 2) Test
     */
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=========================================================\n" +
                           "\t\t SIMULATION BOURSIERE\n" +
                           "=========================================================\n");
        System.out.println("\n\n** Menu principale : \n");
        System.out.println("\n1) Jouer au jeu");
        System.out.println("\n2) Lancer les tests");
        System.out.println("\n0) Quitter le jeu");

        boolean mauvaisChoix = false;
        do {
            System.out.print("\n\n===> Choix : ");
            int choix = sc.nextInt();

            switch (choix) {
                case 1:
                    Simulation.jouer();
                    break;
                case 2:
                    Test.lancer();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n !!!! Vous n'avez pas rentré un choix entre 1 et 2");
            }
        }while(mauvaisChoix);
        System.out.println("\n\n###### FIN DU JEU #######\n\n");
    }
}

/**
 * \class Simulation
 * \brief Permit to simulate the game
 */
class Simulation{

    //METHODS//

    /**
     * \fn void jouer()
     * \brief Principal function of the game
     */
    protected static void jouer(){
        // VARIABLE GLOBALE DE JEU //
        final int nombreTour = 12;
        final int nombreAction = 48;
        // Connexion au VPN obligatoire
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*** Veuillez vous connecter au VPN Ensimag pour utiliser la Base de Donnée\n"
                            +"*** Taper sur une touche une fois que c'est fait");
        sc.nextLine();

        // Affichage des règles pendant la création de la base
        rules();
        // Création du marché (unique en V1)
        Marche Market;
        try{
            Market = new Marche("Market", 0, nombreAction,initBD(nombreAction,nombreTour));
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("\n\nProblème de connexion BD (VPN Activé ?)\n");
            return;
        }

        // Création de l'utilisateur (unique en V1)
        System.out.print("\n*** Veuillez entrer votre pseudo : ");
        String nom = sc.nextLine();
        Utilisateur user = new Utilisateur(1,nom,20000);
        System.out.println("\n\n+ Création du joueur : " + user.getNom() + " effectuée. Cash initial : " + user.getArgent() + " euros.");

        boolean exit = false;
        while(Market.getTourCour() < 52 && !exit){

            System.out.println("\n MENU DES OPERATIONS : \n\n" +
                    "1. Regarder mon portefeuille \n" +
                    "2. Regarder les actions surveillées\n" +
                    "3. Regarder les actions disponibles\n" +
                    "4. Acheter une action\n" +
                    "5. Vendre une action \n" +
                    "6. Surveiller une action\n" +
                    "7. Ne plus surveiller une action\n" +
                    "8. Consulter son historique\n\n" +
                    "9. Passer au tour suivant\n\n" +
                    "0. Quitter le jeu\n");
            System.out.print("Votre choix : ");
            int a = sc.nextInt();

            switch (a){
                case 1:
                    System.out.println("\n\n" + user.toStringPortefeuille() + "\n\n" );
                    break;
                case 2:
                    System.out.println("\n\n" + user.toStringFavoris() + "\n\n");
                    break;
                case 3:
                    System.out.println("\n\n" + Market.toString() + "\n\n");
                    break;
                case 4:
                    achatAction(user);
                    break;
                case 5:
                    venteAction(user);
                    break;
                case 6:
                    surveillerAction(user);
                    break;
                case 7:
                    notSurveillerAction(user);
                    break;
                case 8:
                    consulterHist(user);
                    break;
                case 9:
                    Market.nextLap();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("\n\n!!!!!! Vous n'avez pas rentré un chiffre entre 0 et 9, veuillez recommencer.\n");
                    break;
            }
        }
    }

    // FONCTIONNALITES DU JEU //
    /**
     * \fn void achatAction(Utilisateur user)
     * \brief Permit an user to buy an asset
     *
     * \param Utilisateur user : The user who wants to buy an asset
     */
    private static void achatAction(Utilisateur user){
        System.out.println("\n\n" + "\033[31m[Fonction NON IMPLEMENTEE]\033[m" + "\n\n");
    }

    /**
     * \fn void venteAction(Utilisateur user)
     * \brief Permit an user to sell an asset
     *
     * \param Utilisateur user : The user who wants to sell an asset
     */
    private static void venteAction(Utilisateur user){
        System.out.println("\n\n" + "\033[31m[Fonction NON IMPLEMENTEE]\033[m" + "\n\n");
    }

    /**
     * \fn void surveillerAction(Utilisateur user)
     * \brief Permit an user to control an asset
     *
     * \param Utilisateur user : The user who wants to control an asset
     */
    private static void surveillerAction(Utilisateur user){
        System.out.println("\n\n" + "\033[31m[Fonction NON IMPLEMENTEE]\033[m" + "\n\n");
    }

    /**
     * \fn void notSurveillerAction(Utilisateur user)
     * \brief Permit to remove a controling asset
     *
     * \param Utilisateur user : The user who wants to remove a controling asset
     */
     private static void notSurveillerAction(Utilisateur user){
         System.out.println("\n\n" + "\033[31m[Fonction NON IMPLEMENTEE]\033[m" + "\n\n");
     }

    /**
     * \fn void consulterHist(Utilisateur user)
     * \brief Permit to consult the historic of a user
     *
     * \param Utilisateur user : The user who wants to consult his historic
     */
    private static void consulterHist(Utilisateur user){
        System.out.println("\n\n" + "\033[31m[Fonction NON IMPLEMENTEE]\033[m" + "\n\n");
    }



    // PARTIE DATABASE //
    /**
     * \fn Connection initBD(int nbreAction, int nbreTour)
     * \brief Permit to connect with the Data Base and initialize it
     *
     * \param int nbreAction : The number of Action in the market (<48)
     * \param int nbreTour : The number of round (<52)
     * \return Connection : The connection to the Data Base
     */
    protected static Connection initBD(int nbreAction,int nbreTour){
        System.out.println("\n\n Initialisation de la BD : Cette étape peux prendre du temps");
        Connection connection = null;
        //Connection BD
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@ensioracle1.imag.fr:"
                    + "1521:ensioracle1";
            String user = "ruimyb";
            String passwd = "ruimyb";

            connection = DriverManager.getConnection(url, user, passwd);
        } catch (SQLException e){
            System.err.println("\n\033[31m[FAILED]\033[m");
            e.printStackTrace();
        }

        // Initialisation
        int a = 0;
        try{
            System.out.println("\nTIME /" + nbreTour + " : ");
            Statement stmt = connection.createStatement();
            if (stmt==null){
                System.err.println("\n\033[31m[FAILED]\033[m");
                System.out.println("\nProblem to create a statement : Initialization");
                return null;
            }
            String setCours;
            for(int i = 1; i < nbreTour; i++){
                for(int j = 0; j < nbreAction; j++){
                    a = (int)(Math.random()*100);
                    setCours = "UPDATE ACTION SET VALUE" + i + " = " + a + " WHERE IDACTION = " + j ;
                    stmt.executeQuery(setCours);
                }
                System.out.print(i+"..");
            }
            System.out.println("\033[34m[DONE]\033[m");


        } catch (Exception e){
            System.err.println("FAIL");
            e.printStackTrace();
        }

        return connection;
    }


    /**
     * \fn rules()
     * \brief rules of the game
     *
     * \details Launched at the begining of the game in order to wait for the initialization
     */
    private static void rules(){
        System.out.println("\n" +
                "\033[31m[RULES]\033[m\n");
    }

}

/**
 * \class Test
 * \brief Permit to test the class of our game
 */
class Test{
    /**
     * \fn void lancer()
     * \brief Launch of the test
     */
    protected static void lancer(){
        final int nombreAction = 20;
        final int nombreTour = 12;
        System.out.println("\n\n################## BATTERIE DE TESTS #####################");
        // Instance d'un marché
        System.out.print("\n\n 1) Instance d'un marché : ");
        Marche m;
        try {
            m = new Marche("AllMarket", 0, nombreAction, Simulation.initBD(nombreAction,nombreTour));
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("\n\n Probleme connexion BD (VPN Activé ?)");
            return;
        }
        assert m != null : "La création d'un objet Marche n'a pas fonctionné" + failed();
        assert m.getNom().equals("AllMarket") : "Le constructeur de Marche ne rentre pas le nom" + failed();
        assert m.getNombreActions() == 10 : "Le constructeur de Marche ne rentre pas le bon nombre d'action" + failed();
        assert m.getTourCour() == 0 : "Le constructeur de Marche ne rentre pas le bon tour courant" + failed();
        System.out.print(check());

        // Affichage marché
        System.out.println("\n\n 2) Affichage du marché créé : \n");
        System.out.println(m);
        System.out.print("\n : " + check());
        System.out.println("\n\n");

    }

    private static String check(){
        return "\033[34m[CHECK]\033[m";
    }
    private static String failed(){
        return "\033[31m[FAILED]\033[m";
    }
}
