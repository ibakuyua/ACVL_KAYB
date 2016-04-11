/**
 * \file Action.java
 * \brief Implementation of the class Action
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 10 April 2016
 */

/**
 * \class Action
 * \brief Representation of assets
 */
public class Action {
    // ATTRIBUTS //
    private int IDAction; /**< Asset's ID*/
    private String nom; /**< Asset's name*/
    private int quantite; /**< */
    private double initial; /**< Asset's initial value (when the object was created)*/

    // CONSTRUCTOR //

    public Action(int IDAction, int quantite, String nom, double valeurIni){
        this.IDAction = IDAction;
        this.quantite = quantite;
        this.nom = nom;
        this.initial = valeurIni;
    }

    // GETTERS && SETTERS//

    public int getIDAction() {
        return IDAction;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void rmvQuantite(int qte){
        quantite = quantite-qte;
    }

    public double getInitial() {
        return initial;
    }
    // METHODS //

    public double getValeur(){
        return Marche.getValeur(IDAction);
    }

    public double getEvolution() {
        return (Marche.getValeur(IDAction)-initial)*100/initial;
    }

    public double getPlusValue() {
        return (getEvolution()*initial/100)*quantite;
    }
}
