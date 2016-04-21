package Vue;
/**
 * \file Vue.JOptionException.java
 * \brief Implementation of the GUI for exception
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 16 April 2016
 */

import javax.swing.*;

/**
 * \class Vue.JOptionException
 * \brief Permit to display a window if there is an exception
 *
 * \details Extends JOptionPane
 */
public class JOptionException extends JOptionPane {
    /**
     * \fn JOptionException(String title, String subtitle)
     * \brief The window for an exception
     *
     * \param String title : Title of the window
     * \param String subtitle : Text in the window
     */
    public JOptionException(String title, String subtitle){
        super();
        showMessageDialog(null,subtitle,title,JOptionPane.ERROR_MESSAGE);
        setIcon(new ImageIcon("src/Vue/images/error.png"));
    }
}
