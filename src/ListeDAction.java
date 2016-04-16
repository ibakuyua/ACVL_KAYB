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

import java.util.LinkedList;

/**
 * \class ListeDAction
 * \brief Representation of a list of assets
 *
 * \details abstract class
 */

abstract public class ListeDAction {
    // ATTRIBUTS //
    protected LinkedList<Action> actions; /**<List of assets*/

    // CONSTRUCTOR //

    /**
     * \fn ListeDAction()
     * \brief Constructor
     */
    public ListeDAction(){
        actions = new LinkedList<>();
    }

    // METHODS //

    /**
     * \fn abstract String toString()
     * \brief Description of the list
     *
     * \return String : The description
     *
     * \details Depends on the list
     */
    @Override
    abstract public String toString();

    /**
     * \fn int getNombreAction()
     * \brief Permit to know how many asset the list contains
     *
     * \return int : Number of assets in the list
     */
    public int getNombreAction(){
        return actions.size();
    }

    /**
     * \fn double ajout(int IDAction, int qte)
     * \brief Permit to add an asset in the list
     *
     * \param int IDAction : The asset to add
     * \param int qte : The quantity to add
     *
     * \return double : The amount of this operation
     */
    public double ajout(int IDAction, int qte){
        try{
            Action a = new Action(IDAction,qte,Marche.getNom(IDAction),Marche.getValeur(IDAction));
            actions.add(a);
        }catch (Exception e){

        }

        return qte*Marche.getValeur(IDAction);
    }

    /**
     * \fn double retirer(int position, int qte)
     * \brief Permit to remove a quantity of asset out of the list
     *
     * \param int position : position in the list
     * \param int qte : quantity of removing
     *
     * \return double : The value of this removing in euros
     *
     * \throws Exception in the case of removing too much
     */
    public double[] retirer(int position, int qte) throws Exception{
        // Cas où l'action a retirer n'existe pas
        if (position >= actions.size() || position < 0){
            throw new Exception("\n||Exception : Vous ne pouvez retirer une action à cette position");
        }
        double [] r = new double[2];
        r[0] = actions.get(position).getValeur()*qte;
        r[1] = actions.get(position).getIDAction();
        // Cas où on enlève toute l'action
        if(actions.get(position).getQuantite() == qte){
            actions.remove(position);
        }
        // Cas où on enlève qu'une partie des actions
        else if(actions.get(position).getQuantite() > qte){
            actions.get(position).rmvQuantite(qte);
        }
        // Cas où on enlève plus que ce que l'on possède
        else{
            throw new Exception("\n\033[31m[FAIL]\033[m\n||Exception : Vous ne pouvez pas vendre à découvert\n");
        }
        return r;
    }
}
