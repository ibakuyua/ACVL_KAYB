package Utilitaires.DataBase;

import Utilitaires.PontBrownien;

import java.sql.*;

/**
 * Created by arnaud.ibakuyumcu on 16/04/2016.
 */
public class DataBase {
    /**
     * \fn Connection initBD(int nbreAction, int nbreTour)
     * \brief Permit to connect with the Data Base and initialize it
     *
     * \param int nbreAction : The number of Modele.Actions.Action in the market (<48)
     * \param int nbreTour : The number of round (<52)
     * \return Connection : The connection to the Data Base
     */
    public static Connection initBD(int nbreAction,int nbreTour){
        System.out.println("\n\n Initialisation de la BD : Cette étape peux prendre du temps");
        Connection connection = null;
        //Connection BD
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@ensioracle1.imag.fr:"
                    + "1521:ensioracle1";
            String user = "ruimyb";
            String passwd = "ruimyb";

            connection = DriverManager.getConnection(url, user, passwd);
            System.out.println("\n\033[34m[CONNECTION CHECK]\033[m");

        } catch (SQLException e){
            System.err.println("\n\033[31m[CONNECTION FAILED]\033[m\n");
            e.printStackTrace();
        }

        // Initialisation
        try{
            Statement stmt = connection.createStatement();
            if (stmt==null){
                System.err.println("\n\n\033[31m[INITIALISATION FAILED]\033[m");
                System.out.println("\nProblem to create a statement : Initialization");
                return null;
            }


            //Suppression de la table HISTORIQUE pour qu'il n'yait pas de réécriture
            String getHist;
            getHist = "CREATE TABLE HISTORIQUE( IDVENDEUR INTEGER, IdAcheteur INTEGER," +
                    " IdAction INTEGER references Action , Quantity INTEGER, NUMTRANSAC INTEGER )";
            stmt.executeQuery(getHist);

            System.out.println("\nTIME /" + nbreTour + " : ");

            String setCours;
            double[][] a = PontBrownien.simuler(nbreTour,nbreAction);
            for(int i = 1; i <= nbreTour; i++){
                for(int j = 0; j < nbreAction; j++){
                    setCours = "UPDATE ACTION SET VALUE" + i + " = " + a[j][i-1] + " WHERE IDACTION = " + j ;
                    stmt.executeQuery(setCours);
                }
                System.out.print(i+"..");
            }
            System.out.println("\n\n\033[34m[INITIALISATION DONE]\033[m\n");


        } catch (Exception e){
            System.err.println("\n\033[31m[INITIALISATION FAIL]\033[m\n");
            e.printStackTrace(System.err);
        }

        return connection;
    }

    public static String consulterCours(int ID,int tourCour,Connection connection){
        String s="";
        try{
            Statement stmt = connection.createStatement();
            String getHist = "Select * FROM ACTION WHERE IDACTION = " + ID;
            ResultSet res = stmt.executeQuery(getHist);
            res.next();
            for(int j = 1; j <= tourCour; j++){
                s += "\n  Valeur au tour " + j + " : " + res.getFloat("VALUE" + j) + "\n";
            }
            return s;
        }catch(Exception e){
            System.out.println("Exception " + e.getMessage());
            return "";
        }
    }

    /**
     * \fn void closeBD(Connection connection)
     * \brief Permit to close properly the connection with the dataBase
     *
     * \param Connection connection : The connection
     */
    public static void closeBD(Connection connection){
        try{
            Statement stmt = connection.createStatement();

            String getHist = "DROP TABLE HISTORIQUE";
            stmt.executeQuery(getHist);
            connection.close();
            System.out.println("\n\033[34m[DISCONNECTION CHECK]\033[m\n");
        } catch (SQLException e){
            System.err.println("\n\033[31m[DISCONNECTION FAIL]\033[m\n");
            e.printStackTrace(System.err);
        }
    }

}
