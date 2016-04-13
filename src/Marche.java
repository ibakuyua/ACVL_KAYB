/**
 * \file Marche.java
 * \brief Implementation of the class Marche
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 10 April 2016
 */

import java.sql.*;

/**
 * \class ListeDAction.Marche
 * \brief Representation of the market
 *
 * \details Contain the spot of each asset
 */
public class Marche {

    // ATTRIBUTS //

    private String nom; /**< Market's name */
    private int tourCour; /**< Time */
    private int nombreActions; /**< Number of assets in the market*/
    private static Action[] actions; /**< Assets contained in the market */
    private static double[] cours; /**< Asset's value at t=tourCour*/
    private Connection connection;

    // CONSTRUCTOR //

    // TODO : Rajouter en argument les valeurs choppées dans la BD pour cours et actions
    public Marche(String nom, int tourCour,int nombreActions,Connection connection)throws Exception{

        int i = 0;
        this.connection = connection;
        if (this.connection == null){
            throw new Exception("|| Exception ");
        }
        try{
            actions = new Action[nombreActions];
            cours = new double[nombreActions];
            Statement stmt = connection.createStatement();
            String getActions = "SELECT * FROM ACTION";
            ResultSet rsActions = stmt.executeQuery(getActions);
            while(rsActions.next()) {
                cours[i] = rsActions.getInt("VALUE1");
                actions[i] = new Action(rsActions.getInt("IDACTION"),rsActions.getInt("QUANTITY"),
                                                                rsActions.getString("NAME"),rsActions.getInt("VALUE1"));
                i++;
            }
            this.nombreActions = nombreActions;
            this.tourCour = tourCour;
            this.nom = nom;
        } catch (Exception e){
            System.err.println("FAIL");
            e.printStackTrace();
        }
    }



    //SETTER

    public void nextLap(){
        tourCour++;
        int i = 0;
        try{
            Statement stmt = connection.createStatement();
            String getActions = "SELECT * FROM ACTION";
            ResultSet rsActions = stmt.executeQuery(getActions);
            while(rsActions.next()) {
                cours[i] = rsActions.getInt("VALUE" + (this.getTourCour() + 1));
                i++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    // GETTERS //

    public String getNom() {
        return nom;
    }

    public int getNombreActions() {
        return nombreActions;
    }

    public int getTourCour() {
        return tourCour;
    }

    // METHODS //

    public static double getValeur(int IDAction){
        return cours[IDAction];
    }

    public static String getNom(int IDAction){
        return actions[IDAction].getNom();
    }

    @Override
    public String toString(){
        String s = nom + " : " + nombreActions + " actions\n";
        for (Action i : actions){
            s += "\nID : " + i.getIDAction();
            s += " : " + i.getNom();
            s += "\nValeur : " + i.getValeur();
            s += " Evolution : " + i.getEvolution();
            s += "\nPlus-Value => " + i.getPlusValue();
        }
        return s;
    }
}
