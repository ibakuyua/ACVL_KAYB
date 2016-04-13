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

    // CONSTRUCTOR //
    public Utilisateur(int ID, String nom, int argent){
        this.ID = ID;
        this.nom = nom;
        this.argent = argent;

        portefeuille = new ListeDActionDetenus();
        favoris = new ListeDActionFavoris();
    }

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

    public void acheter(int IDAction, int quantite) throws Exception{
        // Cas où il n'a pas suffisament de fond
        if (quantite*Marche.getValeur(IDAction) > argent){
            throw new Exception("\n||Exception : Vous n'avez pas assez de fond pour faire cet achat\n");
        }
        // Achat des actions
        portefeuille.ajout(IDAction,quantite);
    }

    public void vendre(int position, int quantite) {
        // Vente de la quantite d'action donnée
        try{
            argent += portefeuille.retirer(position,quantite);
        }// Cas de la vente à découvert ou d'une position trop grande
        catch (Exception e){
            System.out.println(e.getMessage() + "==> Veuillez reformuler votre demande \n");
        }
    }

    public void ajoutFav(int IDAction){
        favoris.ajout(IDAction,0);
    }

    public void retirerFav(int position){
        try{
            favoris.retirer(position,0);
        }// Cas d'une position trop grande
        catch (Exception e){
            System.out.println(e.getMessage() + "==> Veuillez reformuler votre demande\n");
        }
    }

    public String toStringPortefeuille() {
        return portefeuille.toString();
    }

    public String toStringFavoris() {
        return favoris.toString();
    }

    public void afficherHistorique() {
        //TODO : REQUETE SGBD
    }

}
