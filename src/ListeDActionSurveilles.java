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
public class ListeDActionSurveilles extends ListeDAction{

    // CONSTRUCTOR //

    /**
     * \fn ListeDActionSurveilles()
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
        String s = "Actions surveillées : " + actions.size() + " actions.\n";
        int pos = 1;
        for(Action i : actions){
            s += "\n\n" + pos + ") ID " + i.getIDAction();
            s += " : " + i.getNom();
            s += "\n----------------------------------------------";
            s += "\nValeur : " + i.getValeur();
            s += " Evolution : \033[" + ((i.getEvolution()<0)?"31m":"33m") + i.getEvolution() + "\033[m %";
            s += "\nPlus-Value => " + i.getPlusValue();
            pos++;
        }
        return s;
    }
}
