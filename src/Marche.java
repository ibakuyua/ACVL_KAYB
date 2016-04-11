/**
 * \file Marche.java
 * \brief Implementation of the class Marche
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 10 April 2016
 */

/**
 * \class ListeDAction.Marche
 * \brief Representation of the market
 *
 * \details Contain the spot of each asset
 */
public class Marche {

    // ATTRIBUTS //

    private String nom; /**< Market's name */
    private int tourCour; /**< Time */
    private int nombreActions; /**< Number of assets in the market*/
    private static Action[] actions; /**< Assets contained in the market */
    private static double[] cours; /**< Asset's value at t=tourCour*/

    // CONSTRUCTOR //

    // TODO : Rajouter en argument les valeurs choppées dans la BD pour cours et actions
    public Marche(String nom, int tourCour,int nombreActions){
        this.nom = nom;
        this.nombreActions = nombreActions;
        actions = new Action[nombreActions];
        cours = new double[nombreActions];
        for (int i = 0; i < nombreActions; i++){
            actions[i] = new Action(i, 1, "test", 50.5);
            cours[i] = 50.5;
        }
        this.tourCour = tourCour;
    }

    // GETTERS //

    public String getNom() {
        return nom;
    }

    public int getNombreActions() {
        return nombreActions;
    }

    public int getTourCour() {
        return tourCour;
    }

    // METHODS //

    public static double getValeur(int IDAction){
        return cours[IDAction];
    }

    public static String getNom(int IDAction){
        return actions[IDAction].getNom();
    }

    @Override
    public String toString(){
        String s = nom + " : " + nombreActions + " actions\n";
        for (Action i : actions){
            s += "\nID : " + i.getIDAction();
            s += " : " + i.getNom();
            s += "\nValeur : " + i.getValeur();
            s += " : " + i.getEvolution();
            s += " => " + i.getPlusValue();
        }
        return s;
    }
}
