/**
 * \file Utilisateur.java
 * \brief Permit to implement the Utilisateur class
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 10 April 2016
 */

/**
 * \class Utilisateur
 * \brief Permit to represent an user
 *
 * \details A user has got an ID and a name
 * \details A user contains money (cash+bank loan) with which he can buy asset on the market
 * \details He has got a Portefeuille of detained assets with which he can sell assets
 * \details He has got a Portefeuille of bookmarks assets to control
 *
 */
public class Utilisateur {

    // ATTRIBUTS //
    private int ID; /**< User's ID*/
    private String nom; /**< User's name*/
    private int argent; /**< User's Cash */
    private ListeDActionDetenus portefeuille; /**< User's portfolio*/
    private ListeDActionFavoris favoris; /**< User's list of favorite assets*/

    // GETTERS & SETTERS //

    public int getID() {
        return ID;
    }

    public String getNom() {
        return nom;
    }

    public int getArgent() {
        return argent;
    }

    // METHODS //

    public void acheter(int IDAction, int quantite) {

    }

    public void vendre(int IDAction, int quantite) {

    }

    public void afficherPortefeuille() {

    }

    public void afficherFavoris() {

    }

    public void afficherHistorique() {

    }

}
