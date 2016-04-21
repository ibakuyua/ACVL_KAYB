package Vue;
/**
 * \file Vue.Bouton.java
 * \brief Implementation of a personal button
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 16 April 2016
 */

import java.awt.*;

/**
 * \class Vue.Bouton
 * \brief Personnal button used in FenetreGUI
 *
 * \details Extends Button
 */
class Bouton extends Button {
    /**
     * \fn Bouton(String str, Font police)
     * \brief Constructor
     *
     * \param String str : The title of the button
     * \param Font police : The font of the button
     */
    public Bouton(String str, Font police){
        super(str);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.BLACK);
        this.setFont(police);
    }
}
