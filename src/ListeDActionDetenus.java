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

    /**
     * \fn ListeDActionDetenus()
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
        String s = "Portefeuille utilisateur : " + getNombreAction() + " actions.\n";
        int pos = 1;
        for (Action i : actions){
            s += "\n" + pos + ") ID " + i.getIDAction() + " : " + i.getNom();
            s += "\n----------------------------------------------";
            s += "\n\tQte : " + i.getQuantite() + " à " + i.getInitial() + " €";
            s += "\n\tCours : " + i.getValeur();
            s += " EVOLUTION : " + i.getEvolution() + "%";
            s += "\n\tPlus-Value : " + i.getPlusValue() + "\n";
            pos++;
        }
        s += "\n\nValeur portefeuille : " + valeur();
        s += "\nPlus-Value : " + plusValue();
        return s;
    }

    /**
     * \fn double valeur()
     * \brief Permit to know the value of the list
     *
     * \return double : value of the assets list
     */
    private double valeur(){
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
