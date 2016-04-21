import Controleur.Controler;
/**
 * \file Jeu.java
 * \brief Main of the game
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 16 April 2016
 */

/**
 * \class Jeu
 * \brief Main of the game
 */
public class Jeu {
    public static void main(String [] args){
        try{
            Controler c = new Controler();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
