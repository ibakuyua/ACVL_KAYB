/**
 * \file ListeDActionDetenus.java
 * \brief Implementation of the class ListeDActionDetenus
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 10 April 2016
 */

/**
 * \class ListeDActionDetenus
 * \brief Represent a user portfolio
 *
 * \details inherite from ListeDAction
 */
public class ListeDActionDetenus extends ListeDAction{

    // CONSTRUCTOR //

    public ListeDActionDetenus(){
        super();
    }

    // METHODS //

    @Override
    public String toString(){
        String s = "Portefeuille utilisateur : " + getNombreAction() + " actions.\n";
        int pos = 1;
        for (Action i : actions){
            s += "\n" + pos + ") ID : " + i.getIDAction() + " : " + i.getNom();
            s += "\nQte : " + i.getQuantite() + " à " + i.getInitial() + " €";
            s += "\nCours : " + i.getValeur() + " : " + i.getEvolution() + "%";
            s += "Plus-Value : " + i.getPlusValue() + "\n";
            pos++;
        }
        s += "\n\nValeur portefeuille : " + valeur();
        s += "\nPlus-Value : " + plusValue();
        return s;
    }

    private double valeur(){
        double r = 0;
        for(Action i : actions){
            r += i.getValeur()*i.getQuantite();
        }
        return r;
    }

    private double plusValue(){
        double r = 0;
        for(Action i : actions){
            r += i.getPlusValue();
        }
        return r;
    }
}
