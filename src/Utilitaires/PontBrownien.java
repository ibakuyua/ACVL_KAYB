package Utilitaires;
/**
 * \file Utilitaires.PontBrownien.java
 * \brief Implementation of the utilitary PontBrownien
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 10 April 2016
 */

import java.util.Random;

/**
 * \class Utilitaires.PontBrownien
 * \brief Permit to simulate a Brownian Motion for assets value
 */
public class PontBrownien{

    private final static double[] s0 = spotValue(); /**<Values of each asset at 14 April morning*/

    /**
     * \fn double[][] simuler(int nbreTour, int nbreAction)
     * \brief Permit to simulate the price of an asset
     *
     * \param int nbreTour : Number of step in the game
     * \param int nbreAction : Number of asset in the market
     *
     * \return double[nbreAction][nbreTour] : Price of each share at each step
     */
    public static double [][] simuler(int nbreTour, int nbreAction){
        double[][] r = new double[nbreAction][nbreTour];
        for (int i = 0; i<nbreAction; i++){
            // Traitement de chaque action
            // Initialisation des deux extrémités
            r[i][0] = s0[i];
            r[i][nbreTour-1] = s0[i] + normal(0,2.5*nbreTour);
            recPB(0,nbreTour-1,r,i);
        }
        return r;
    }

    /**
     * \fn double normal(double m, double v)
     * \brief Simulation of a N(m,v)
     * \param double m : Mean
     * \param double v : Variance
     *
     * \return double FN(m,v)
     */
    private static double normal(double m, double v){
        Random rand = new Random();
        double r = rand.nextGaussian();
        return m + Math.sqrt(v)*r;
    }

    /**
     * \fn void recPB(int ta, int tb, double[][] r, int i )
     * \brief Recursion function for the simulation of a Brownian motion
     *
     * \param int ta : Left time
     * \param int tb : Right time
     * \param double[][] r : The matrix to fill
     * \param int i : The current share
     */
    private static void recPB(int ta, int tb, double[][] r, int i ){
        //S'il reste un temps à remplir entre ta et tb (condition d'arret de recursion)
        if(tb-ta > 1){
            int ti = (ta+tb)/2;
            // Remplissage du point du milieu par pont brownien
            double m= r[i][ta] + (((double)ti-(double)ta)/((double)tb-(double)ta))*(r[i][tb]-r[i][ta]);
            double v= (((double)tb-(double)ti)*((double)ti-(double)ta))/((double)tb-(double)ta);
            r[i][ti] = normal(m,v);
            // Parcours à gauche
            recPB(ta,ti,r,i);
            // Parcours à droite
            recPB(ti,tb,r,i);
        }
    }

    /**
     * \fn double[] spotValue()
     * \brief Fill the field s0
     *
     * \return double[] : Price of each asset at t=0
     */
    private static double[] spotValue(){
        double [] r = new double[48];
        r[0] = 57;
        r[1] = 75.64; r[2] = 22.17; r[3] = 30.68; r[4] = 82.10; r[5] = 71.03; r[6] = 15.06; r[7] = 61.28;
        r[8] = 77.21; r[9] = 56.56; r[10] = 34.17; r[11] = 93.27; r[12] = 104.70; r[13] = 26.58; r[14] = 11.35;
        r[15] = 75.13; r[16] = 116.33; r[17] = 25.04; r[18] = 40.84; r[19] = 141.79; r[20] = 28.5; r[21] = 54.81;
        r[22] = 112.55; r[23] = 49.1; r[24] = 52.85; r[25] = 28.86;
        r[26] = 72.53; r[27] = 102.86;
        r[28] = 92.79; r[29] = 113.32; r[30] = 61.34; r[31] = 31.19; r[32] = 83.34; r[33] = 45.94; r[34] = 666.83;
        r[35] = 545.34; r[36] = 142.04; r[37] =7.47; r[38] = 99.41; r[39] = 18.92; r[40] = 12.86; r[41] = 51.19;
        r[42] = 80.04; r[43] = 172.06; r[44] = 42.5; r[45] = 64.69; r[46] = 240.98; r[47] = 70.68;

        return r;
    }
}
