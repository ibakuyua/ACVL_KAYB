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
 * \brief Simulation Game
 *
 * \details Main of our program
 */
public class Simu {


    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=========================================================\n" +
                           "\t SIMULATION BOURSIERE\n" +
                           "=========================================================\n");
        System.out.println("\n\n** Menu principale : \n");
        System.out.println("\n1) Jouer au jeu");
        System.out.println("\n2) Lancer les tests");

        boolean bonChoix = false;
        while(!bonChoix) {
            System.out.print("\n\n===> Choix : ");
            int choix = sc.nextInt();

            switch (choix) {
                case 1:
                    Simulation.jouer();
                    bonChoix = true;
                    break;
                case 2:
                    Test.lancer();
                    bonChoix = true;
                    break;
                default:
                    System.out.println("\n !!!! Vous n'avez pas rentré un choix entre 1 et 2");
            }
        }
        System.out.println("\n\n###### FIN DU JEU #######\n\n");
    }
}

class Simulation{


    //Methodes
    protected static void jouer(){
        // Connexion au VPN obligatoire
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*** Veuillez vous connecter au VPN Ensimag pour utiliser la Base de Donnée\n"
                            +"*** Taper sur une touche une fois que c'est fait");
        sc.nextLine();

        // Création du marché (unique en V1)
        Marche Market;
        try{
            Market = new Marche("Market", 0, 48,initBD());
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("\n\nProblème de connexion BD (VPN Activé ?)\n");
            return;
        }

        // Création de l'utilisateur (unique en V1)
        System.out.print("\n*** Veuillez entrer votre pseudo : ");
        String nom = sc.nextLine();
        Utilisateur user = new Utilisateur(1,nom,20000);
        System.out.println("\n\n+ Création du joueur : " + user.getNom() + "effectuée. Cash initial : " + user.getArgent());

        Rules();
        while(Market.getTourCour() < 52){

            boolean exit = false;
            while(!exit){
                System.out.println("\n MENU DES OPERATIONS : \n\n" +
                        "1. Regarder mon portefeuille \n" +
                        "2. Regarder les actions surveillées" +
                        "3. Regarder les actions disponibles\n" +
                        "4. Acheter une action\n" +
                        "5. Vendre une action \n" +
                        "6. Passer au tour suivant\n" +
                        "7. Quitter le jeu\n");
                System.out.print("Votre choix : ");
                int a = sc.nextInt();

                switch (a){
                    case 1:
                        System.out.println("\n\n" + user.toStringPortefeuille() + "\n\n" );
                        break;
                    case 2:
                        System.out.println("\n\n" + user.toStringFavoris() + "\n\n");
                    case 3:
                        System.out.println("\n\n" + Market.toString() + "\n\n");
                        break;
                    case 4:
                        //TODO Implémenter l'achat
                        System.out.println("\n\n" + "\033[31m[Fonction NON IMPLEMENTEE]\033[m" + "\n\n");
                        break;
                    case 5:
                        System.out.println("\n\n" + "033[31m[Fonction NON IMPLEMENTEE]033[m" + "\n\n");
                        break;
                    case 6:
                        Market.nextLap();
                        break;
                    case 7:
                        exit = true;
                    default:
                        System.out.println("\n\n!!!!!! Vous n'avez pas rentré un chiffre entre 1 et 7, veuillez recommencer.\n");
                        break;
                }
            }
        }
    }

    public static Connection initBD(){
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
            System.err.println("FAILED");
            e.printStackTrace();
        }

        // Initialisation
        int a = 0;
        try{
            Statement stmt = connection.createStatement();
            String setCours;
            for(int i = 1; i < 52; i++){
                for(int j = 0; j < 48; j++){
                    a = (int)(Math.random()*100);
                    setCours = "UPDATE ACTION SET VALUE" + i + " = " + a + " WHERE IDACTION = " + j ;
                    System.out.println(setCours);
                    stmt.executeQuery(setCours);
                }
            }


        } catch (Exception e){
            System.err.println("FAIL");
            e.printStackTrace();
        }

        return connection;
    }


    protected static void Rules(){
        System.out.println("\n" +
                "\033[31m[RULES]\033[m\n");
    }

}

class Test{
    // TODO : Mettre dans le package test et faire plusieurs petits fichiers à lancer ici !
    protected static void lancer(){
        System.out.println("\n\n################## BATTERIE DE TESTS #####################");
        // Instance d'un marché
        System.out.print("\n\n 1) Instance d'un marché : ");
        Marche m;
        try {
            m = new Marche("AllMarket", 0, 48, Simulation.initBD());
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("\n\n Probleme connexion BD (VPN Activé ?)");
            return;
        }
        Simulation s = new Simulation(); 
        assert m != null : "La création d'un objet Marche n'a pas fonctionné" + failed();
        assert m.getNom().equals("AllMarket") : "Le constructeur de Marche ne rentre pas le nom" + failed();
        assert m.getNombreActions() == 10 : "Le constructeur de Marche ne rentre pas le bon nombre d'action" + failed();
        assert m.getTourCour() == 0 : "Le constructeur de Marche ne rentre pas le bon tour courant" + failed();
        System.out.print(check());

        m.nextLap();
        m.nextLap();
        m.nextLap();

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
