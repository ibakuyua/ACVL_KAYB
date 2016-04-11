/**
 * \file ListeDAction.java
 * \brief Implementation of the class ListeDAction
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 10 April 2016
 */

/**
 * \class ListeDAction
 * \brief Representation of a list of favorite assets
 */
public class ListeDActionFavoris extends ListeDAction{

    // CONSTRUCTOR //

    public ListeDActionFavoris(){
        super();
    }

    // METHODS //

    @Override
    public String toString(){
        String s = "Actions surveillées : \n";
        int pos = 1;
        for(Action i : actions){
            s += "\n" + pos + ") ID : " + i.getIDAction();
            s += " : " + i.getNom();
            s += "\nValeur : " + i.getValeur();
            s += " : " + i.getEvolution();
            s += " => " + i.getPlusValue() + "\n";
            pos++;
        }
        return s;
    }
}
