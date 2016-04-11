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
    protected LinkedList<Action> actions;

    // CONSTRUCTOR //
    public ListeDAction(){
        actions = new LinkedList<>();
    }

    // METHODS //
    @Override
    abstract public String toString();

    public int getNombreAction(){
        return actions.size();
    }

    public void ajout(int IDAction, int qte){
        Action a = new Action(IDAction,qte,Marche.getNom(IDAction),Marche.getValeur(IDAction));
        actions.add(a);
    }

    public double retirer(int position, int qte) throws Exception{
        // Cas où l'action a retirer n'existe pas
        if (position > actions.size()){
            throw new Exception("\n||Exception : Vous ne pouvez retirer une action à cette position");
        }
        double r = actions.get(position).getValeur()*qte;
        // Cas où on enlève toute l'action
        if(actions.get(position).getQuantite() == qte){
            actions.remove(position);
        }
        // Cas où on enlève qu'une partie des actions
        else if(actions.get(position).getQuantite() < qte){
            actions.get(position).rmvQuantite(qte);
        }
        // Cas où on enlève plus que ce que l'on possède
        else{
            throw new Exception("\n||Exception : Vous ne pouvez pas vendre à découvert\n");
        }
        return r;
    }
}
