package Modele.Actions; /**
 * \file Modele.Actions.ListeDAction.java
 * \brief Implementation of the class Modele.Actions.ListeDAction
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
 * \class Modele.Actions.ListeDAction
 * \brief Representation of a list of favorite assets
 */
public class ListeDActionSurveilles extends ListeDAction {

    // CONSTRUCTOR //

    /**
     * \fn Modele.Actions.ListeDActionSurveilles()
     * \brief Constructor
     *
     * \details Call super()
     */
    public ListeDActionSurveilles(){
        super();
    }

    // METHODS //

    /**
     * \fn String toString()
     * \brief toString of a control list of assets
     * \return String : The description of the list of favorite assets
     */
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.0##");
        String s = "\n  ** Actions surveillées : " + actions.size() + " actions.\n";
        int pos = 1;
        for(Action i : actions){
            s += "\n\n  " + pos + ") ID " + i.getIDAction();
            s += " : " + i.getNom();
            s += "\n----------------------------------------------";
            s += "\n  Valeur : " + df.format(i.getValeur());
            s += " Evolution : " + df.format(i.getEvolution()) + " %";
            s += "\n  Plus-Value => "  + df.format(i.getPlusValue()) + " \n";
            pos++;
        }
        return s;
    }
}
