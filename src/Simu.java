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
    }
}

class Simulation{


    //Methodes
    protected static void jouer(){
        // TODO : Mettre la fonction de jeu ici
        System.out.println("\n\033[31m[Fonction jeu à implémenter]\033[m\n");

        Marche Market = new Marche("Market", 0, 48);
        ListeDActionDetenus L = new ListeDActionDetenus();

        Rules();
        //initBD();
        while(Market.getTourCour() < 52){

            boolean b = true;
            while(b == true){
                System.out.println("Quelle action voulez vous faire ? \n" +
                        "1. Regarder mon portefeuille \n" +
                        "2. Regarder les actions disponibles\n" +
                        "3. Acheter une action\n" +
                        "4. Vendre une action \n" +
                        "5. Passer au tour suivant\n");
                Scanner sc = new Scanner(System.in);
                int a = sc.nextInt();

                switch (a){
                    case 1:
                        System.out.println("\n\n" + L.toString() + "\n\n" );
                        break;
                    case 2:
                        System.out.println("\n\n" + Market.toString() + "\n\n");
                        break;
                    case 3:
                        //TODO Implémenter l'achat
                        System.out.println("\n\n" + "Fonction NON IMPLEMENTEE" + "\n\n");
                        break;
                    case 4:
                        System.out.println("\n\n" + "Fonction NON IMPLEMENTEE" + "\n\n");
                        break;
                    case 5:
                        b = false;
                        break;
                    default:
                        break;
                }
            }

            Market.nextLap();
        }






    }



    public static void initBD(){
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


        int a = 0;
        try{
            Statement stmt = connection.createStatement();
            String setCours;
            for(int i = 1; i < 52; i++){
                for(int j = 0; j < 48; j++){
                    a = (int)(Math.random()*100);
                    setCours = "UPDATE ACTION SET VALUE" + i + " = " + a + " WHERE IDACTION = " + j ;
                    stmt.executeQuery(setCours);
                }
            }


        } catch (Exception e){
            System.err.println("FAIL");
            e.printStackTrace();
        }




        try{
            connection.close();
        } catch (Exception e){
            System.err.println("FAIL");
            e.printStackTrace();
        }


    }


    protected static void Rules(){
        System.out.println("\n" +
                "033[31m[RULES]033[m\n");
    }




    //TODO : Mettre les fonctions nécessaires à la fonction jouer ici en private
}

class Test{
    // TODO : Mettre dans le package test et faire plusieurs petits fichiers à lancer ici !
    protected static void lancer(){
        System.out.println("\n\n################## BATTERIE DE TESTS #####################");
        // Instance d'un marché
        System.out.print("\n\n 1) Instance d'un marché : ");
        Marche m = new Marche("AllMarket", 0, 48);
        Simulation s = new Simulation(); 
        assert m != null : "La création d'un objet Marche n'a pas fonctionné" + failed();
        assert m.getNom().equals("AllMarket") : "Le constructeur de Marche ne rentre pas le nom" + failed();
        assert m.getNombreActions() == 10 : "Le constructeur de Marche ne rentre pas le bon nombre d'action" + failed();
        assert m.getTourCour() == 0 : "Le constructeur de Marche ne rentre pas le bon tour courant" + failed();
        System.out.print(check());

        s.initBD();
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
