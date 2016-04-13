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

    /**
     * \fn Action(int IDAction, int quantite, String nom, double valeurIni)
     * \brief Constructor
     *
     * \param int IDAction : Identifiant of the asset
     * \param int quantite : Quantity of asset
     * \param String nom : Asset's name
     * \param int valeurIni : Initial value of the asset
     */
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

    public int getQuantite()
    {
        return quantite;
    }

    public double getInitial() {
        return initial;
    }

    /**
     * \fn void rmvQuantite(int qte)
     * \brief When a user sell a quantity of this asset but not all
     *
     * \param int qte : The quantity to remove
     *
     * \details Change the quantity
     */
    public void rmvQuantite(int qte){
        quantite = quantite-qte;
    }

    // METHODS //

    /**
     * \fn double getValeur()
     * \brief Permit to know the value of the asset
     *
     * \return double : asset's value
     */
    public double getValeur(){
        return Marche.getValeur(IDAction);
    }

    /**
     * \fn double getEvolution()
     * \brief Permit to know it's evolution compared to the initial value
     *
     * \return double : the evolution
     */
    public double getEvolution() {
        return (Marche.getValeur(IDAction)-initial)*100/initial;
    }

    /**
     * \fn double getPlusValue()
     * \brief Permit to know the plus-value of the asset
     *
     * \return double : The plus-value
     */
    public double getPlusValue() {
        return (getEvolution()*initial/100)*quantite;
    }
}
