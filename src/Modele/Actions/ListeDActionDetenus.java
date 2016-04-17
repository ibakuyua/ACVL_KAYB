package Modele.Actions; /**
 * \file Modele.Actions.ListeDActionDetenus.java
 * \brief Implementation of the class Modele.Actions.ListeDActionDetenus
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 10 April 2016
 */

import Modele.Actions.Action;
import Modele.Actions.ListeDAction;

import java.text.DecimalFormat;

/**
 * \class Modele.Actions.ListeDActionDetenus
 * \brief Represent a user portfolio
 *
 * \details inherite from Modele.Actions.ListeDAction
 */
public class ListeDActionDetenus extends ListeDAction {

    // CONSTRUCTOR //

    /**
     * \fn Modele.Actions.ListeDActionDetenus()
     * \brief Constructor
     *
     * \details Call the super()
     */
    public ListeDActionDetenus(){
        super();
    }

    // METHODS //

    /**
     * \fn String toString()
     * \brief Permit to describe the list of detained assets
     *
     * \return String : The description
     */
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.0##");
        String s = " ** Portefeuille : " + getNombreAction() + " actions.\n";
        int pos = 1;
        for (Action i : actions){
            s += "\n  " + pos + ") ID " + i.getIDAction() + " : " + i.getNom();
            s += "\n----------------------------------------------";
            s += "\n\tQte : " + i.getQuantite() + " à " + df.format(i.getInitial()) + " €";
            s += "\n\tCours : " + df.format(i.getValeur());
            s += " Evolution : " + df.format((i.getEvolution())) + " %";
            s += "\n\tPlus-Value : " + df.format(i.getPlusValue()) + " \n";
            pos++;
        }
        s += "\n\n  Valeur portefeuille : " + df.format(valeur());
        s += "\n  Plus-Value : " + df.format(plusValue());
        return s;
    }

    /**
     * \fn double valeur()
     * \brief Permit to know the value of the list
     *
     * \return double : value of the assets list
     */
    public double valeur(){
        double r = 0;
        for(Action i : actions){
            r += i.getValeur()*i.getQuantite();
        }
        return r;
    }

    /**
     * \fn double plusValue()
     * \brief Permit to know the plusValue of the list
     *
     * \return double : plus-value of the assets list
     */
    private double plusValue(){
        double r = 0;
        for(Action i : actions){
            r += i.getPlusValue();
        }
        return r;
    }
}
