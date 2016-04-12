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








    }


    protected void Rules(){
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
