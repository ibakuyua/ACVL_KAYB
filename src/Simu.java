/**
 * \file Simu.java
 * \brief main of the simulation game
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 11 April 2016
 */

import java.util.Scanner;
import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * \class Simu
 * \brief Finance Game
 *
 * \details Main of our program
 */
public class Simu {


    /**
     * \fn void main(String[] args)
     * \brief main of the game
     *
     * \details Launch the menu : 1) Play ; 2) Test
     */
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n=========================================================\n" +
                           "\t\t SIMULATION BOURSIERE\n" +
                           "=========================================================\n");
        System.out.println("\n\n** Menu principale : \n");
        System.out.println("\n1) Jouer au jeu");
        System.out.println("\n2) Lancer les tests");
        System.out.println("\n0) Quitter le jeu");

        boolean mauvaisChoix = false;
        do {
            System.out.print("\n\n===> Choix : ");
            int choix = sc.nextInt();

            switch (choix) {
                case 1:
                    Simulation.jouer();
                    break;
                case 2:
                    Test.lancer();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\n !!!! Vous n'avez pas rentré un choix entre 1 et 2");
            }
        }while(mauvaisChoix);
        System.out.println("\n\n###### FIN DU JEU #######\n\n");
    }
}

/**
 * \class Simulation
 * \brief Permit to simulate the game
 */
class Simulation{

    //METHODS//

    /**
     * \fn void jouer()
     * \brief Principal function of the game
     */
    protected static void jouer(){
        // VARIABLE GLOBALE DE JEU //
        final int nombreTour = 12;
        final int nombreAction = 48;
        final double cash = 20000;

        // Connexion au VPN obligatoire
        Scanner sc = new Scanner(System.in);
        System.out.println("\n*** Veuillez vous connecter au VPN Ensimag pour utiliser la Base de Donnée\n"
                            +"*** Taper sur une touche une fois que c'est fait");
        sc.nextLine();

        // Affichage des règles pendant la création de la base
        rules();
        // Création du marché (unique en V1)
        Marche Market;
        Connection connection;
        try{
            connection = initBD(nombreAction,nombreTour);
            Market = new Marche("Market", 1, nombreAction,connection);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("\n\nProblème de connexion BD (VPN Activé ?)\n");
            return;
        }

        // Création de l'utilisateur (unique en V1)
        System.out.println("Appuyer sur ENTRER lorsque vous êtes prêt à démarrer ! \n");
        sc.nextLine();
        System.out.print("\n*** Veuillez entrer votre pseudo : ");
        String nom = sc.nextLine();
        Utilisateur user = new Utilisateur(1,nom,cash);
        System.out.println("\n\n+ Création du joueur : " + user.getNom() + " effectuée. Cash initial : " + user.getArgent() + " euros.");

        boolean exit = false;
        while(Marche.getTourCour() <= nombreTour && !exit){

            System.out.println("\n == MENU DES OPERATIONS TOUR(" + Marche.getTourCour() + "): \n\n" +
                    "1. Consulter mon portefeuille \n" +
                    "2. Consulter les actions surveillées\n" +
                    "3. Consulter les actions disponibles sur le marché\n" +
                    "4. Acheter une action\n" +
                    "5. Vendre une action \n" +
                    "6. Surveiller une action\n" +
                    "7. Ne plus surveiller une action\n" +
                    "8. Consulter son historique\n\n" +
                    "9. Passer au tour suivant\n\n" +
                    "0. Quitter le jeu\n");
            System.out.print("Votre choix : ");
            int a = sc.nextInt();

            switch (a){
                case 1:
                    System.out.println("\n\n" + user.toStringPortefeuille() + "\n\n" );
                    break;
                case 2:
                    System.out.println("\n\n" + user.toStringFavoris() + "\n\n");
                    break;
                case 3:
                    System.out.println("\n\n" + Market.toString() + "\n\n");
                    break;
                case 4:
                    achatAction(user);
                    break;
                case 5:
                    venteAction(user);
                    break;
                case 6:
                    surveillerAction(user);
                    break;
                case 7:
                    notSurveillerAction(user);
                    break;
                case 8:
                    consulterHist(user);
                    break;
                case 9:
                    Market.nextLap();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("\n\n!!!!!! Vous n'avez pas rentré un chiffre entre 0 et 9, veuillez recommencer.\n");
                    break;
            }
        }
        System.out.println("\n\n\t ## PLUS VALUE FINALE : \033[" + ((user.getArgent()-cash<0)?"31m":"33m")
                            +(user.getArgent()-cash) + "\033[m euros ##\n");

        // Fermeture de la BD //
        closeBD(connection);
    }

    // FONCTIONNALITES DU JEU //
    /**
     * \fn void achatAction(Utilisateur user)
     * \brief Permit an user to buy an asset
     *
     * \param Utilisateur user : The user who wants to buy an asset
     */
    private static void achatAction(Utilisateur user){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nQuelle action voulait vous acheter ? ID : ");
        int ID = sc.nextInt();
        System.out.print("\nQuantité de " + Marche.getNom(ID) + " à " + Marche.getValeur(ID) + " euros ? : ");
        int qte = sc.nextInt();
        try {
            user.acheter(ID, qte);
        }catch(Exception e){
            System.out.println("\n\033[31m[FAILED]\033[m " + e.getMessage());
        }
    }

    /**
     * \fn void venteAction(Utilisateur user)
     * \brief Permit an user to sell an asset
     *
     * \param Utilisateur user : The user who wants to sell an asset
     */
    private static void venteAction(Utilisateur user){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nQuelle action voulez vous vendre ? Position : ");
        int pos = sc.nextInt();
        System.out.print("\nQuantité ? : ");
        int qte = sc.nextInt();
        user.vendre(pos, qte);
    }

    /**
     * \fn void surveillerAction(Utilisateur user)
     * \brief Permit an user to control an asset
     *
     * \param Utilisateur user : The user who wants to control an asset
     */
    private static void surveillerAction(Utilisateur user){
        Scanner sc = new Scanner(System.in);
        System.out.print("\nQuelle action voulait vous surveiller ? ID : ");
        int ID = sc.nextInt();
        user.ajoutFav(ID);
        System.out.println("\nAction surveillée : " + Marche.getNom(ID));
    }

    /**
     * \fn void notSurveillerAction(Utilisateur user)
     * \brief Permit to remove a controling asset
     *
     * \param Utilisateur user : The user who wants to remove a controling asset
     */
     private static void notSurveillerAction(Utilisateur user){
         Scanner sc = new Scanner(System.in);
         System.out.print("\nQuelle action voulait vous arreter de surveiller ? Position : ");
         int pos = sc.nextInt();
         user.retirerFav(pos);
     }

    /**
     * \fn void consulterHist(Utilisateur user)
     * \brief Permit to consult the historic of a user
     *
     * \param Utilisateur user : The user who wants to consult his historic
     */
    private static void consulterHist(Utilisateur user){
        System.out.println("\nHistorique de " + user.getNom() + " : \n");
        System.out.println(user.toStringHistorique());
    }



    // PARTIE DATABASE //
    /**
     * \fn Connection initBD(int nbreAction, int nbreTour)
     * \brief Permit to connect with the Data Base and initialize it
     *
     * \param int nbreAction : The number of Action in the market (<48)
     * \param int nbreTour : The number of round (<52)
     * \return Connection : The connection to the Data Base
     */
    protected static Connection initBD(int nbreAction,int nbreTour){
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
                    " IdAction INTEGER references Action , Quantity INTEGER, NUMTRANSAC INTEGER" +
                    ")";
            stmt.executeQuery(getHist);

            System.out.println("\nTIME /" + nbreTour + " : ");

            String setCours;
            for(int i = 1; i <= nbreTour; i++){
                for(int j = 0; j < nbreAction; j++){
                    double[][] a = PontBrownien.simuler(nbreTour,nbreAction);
                    // TODO : Pont Brownien
                    //setCours = "UPDATE ACTION SET VALUE" + i + " = " + a[j][i-1] + " WHERE IDACTION = " + j ;
                    setCours = "UPDATE ACTION SET VALUE" + i + " = " + Math.random()*100 + " WHERE IDACTION = " + j ;
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

    protected static void closeBD(Connection connection){
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


    /**
     * \fn rules()
     * \brief rules of the game
     *
     * \details Launched at the begining of the game in order to wait for the initialization
     */
    private static void rules(){
        System.out.println("\n" +
                "\033[31m[RULES]\033[m\n");
    }

}

/**
 * \class PontBrownien
 * \brief Permit to simulate a Brownian Motion for assets value
 */
class PontBrownien{
    private static double[] s0 = spotValue();
    public static double [][] simuler(int nbreTour, int nbreAction){
        double[][] r = new double[nbreAction][nbreTour];
        for (int i = 0; i<nbreAction; i++){
            // Traitement de chaque action
            // Initialisation des deux extrémités
            r[i][0] = s0[i];
            r[i][nbreTour-1] = s0[i] + normal(0,nbreTour);
            recPB(0,nbreTour-1,r,i);
        }
        return r;
    }

    private static double normal(double m, double v){
        Random rand = new Random();
        double r = rand.nextGaussian();
        return m + Math.sqrt(v)*r;
    }

    private static void recPB(int ta, int tb, double[][] r, int i ){
        //S'il reste un temps à remplir entre ta et tb (condition d'arret de recursion)
        if(tb-ta > 1){
            int ti = (ta+tb)/2;
            // Remplissage du point du milieu par pont brownien
            double m= r[i][ta] + ((ti-ta)/(tb-ta))*(r[i][tb]-r[i][ta]);
            double v= ((tb-ti)*(ti-ta))/(tb-ta);
            r[i][ti] = normal(m,v);
            // Parcours à gauche
            recPB(ta,ti,r,i);
            // Parcours à droite
            recPB(ti,tb,r,i);
        }
    }

    private static double[] spotValue(){
        double [] r = new double[48];
        r[0] = 57;
        r[1] = 75.64; r[2] = 22.17; r[3] = 30.68; r[4] = 82.10; r[5] = 71.03; r[6] = 15.06; r[7] = 61.28;
        r[8] = 77.21; r[9] = 56.56; r[10] = 34.17; r[11] = 93.27; r[12] = 104.70; r[13] = 26.58; r[14] = 11.35;
        r[15] = 75.13; r[16] = 116.33; r[17] = 25.04; r[18] = 40.84; r[19] = 141.79; r[20] = 28.5; r[21] = 54.81;
        r[22] = 112.55; r[23] = 49.1; r[24] = 52.85; r[25] = 28.86; r[26] = 0; r[27] = 0;
        r[28] = 0; r[29] = 0; r[30] = 0; r[31] = 0; r[32] = 0; r[33] = 0; r[34] = 0;
        r[35] = 0; r[36] = 0; r[37] = 0; r[38] = 0; r[39] = 0; r[40] = 0; r[41] = 0;
        r[42] = 0; r[43] = 0; r[44] = 0; r[45] = 0; r[46] = 0; r[47] = 0;
        return r;
    }
}

/**
 * \class Test
 * \brief Permit to test the class of our game
 */
class Test{
    /**
     * \fn void lancer()
     * \brief Launch of the test
     */
    protected static void lancer(){
        final int nombreAction = 20;
        final int nombreTour = 12;
        System.out.println("\n\n################## BATTERIE DE TESTS #####################");
        // Instance d'un marché
        System.out.print("\n\n 1) Instance d'un marché : ");
        Marche m;
        Connection connection;
        try {
            connection = Simulation.initBD(nombreAction,nombreTour);
            m = new Marche("AllMarket", 0, nombreAction, connection) ;
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("\n\n Probleme connexion BD (VPN Activé ?)");
            return;
        }
        assert m != null : "La création d'un objet Marche n'a pas fonctionné" + failed();
        assert m.getNom().equals("AllMarket") : "Le constructeur de Marche ne rentre pas le nom" + failed();
        assert m.getNombreActions() == 10 : "Le constructeur de Marche ne rentre pas le bon nombre d'action" + failed();
        assert m.getTourCour() == 0 : "Le constructeur de Marche ne rentre pas le bon tour courant" + failed();
        System.out.print(check());

        // Affichage marché
        System.out.println("\n\n 2) Affichage du marché créé : \n");
        System.out.println(m);
        System.out.print("\n : " + check());
        System.out.println("\n\n");

        Simulation.closeBD(connection);

    }

    private static String check(){
        return "\033[34m[CHECK]\033[m";
    }
    private static String failed(){
        return "\033[31m[FAILED]\033[m";
    }
}
