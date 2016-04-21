package Vue.DialogBoxAsk;
/**
 * \file Vue.DialogBoxAsk.java
 * \brief Implementation of the DialogBoxAsk interface
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 16 April 2016
 */


import javax.swing.*;
import java.awt.*;

/**
 * \class Vue.DialogBoxAsk
 * \brief Permit to make appear a dialogBox to ask the user
 * \details Extends JDialog
 */
public class DialogBox extends JDialog{
    // ATTRIBUTS //
    protected final JLabel icon  = new JLabel(new ImageIcon("images/interrogation.png"));/**<Icon for the window*/
    protected JTextField IDfield;/**<The ID field*/
    protected int ID;/**<The ID contain in the IDField*/
    protected boolean sendData;/**<If the user write data in the fields*/

    // CONSTRUCTOR //

    /**
     * \fn DialogBox(JFrame parent, String title, boolean modal)
     * \brief Constructor
     * \param Jframe parent
     * \param String title : Title of the window
     * \param boolean modal
     */
    public DialogBox(JFrame parent, String title, boolean modal){
        super(parent,title,modal);
        this.setSize(500,300);
        this.setBackground(Color.GRAY);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    // GETTER //

    public int getID() {
        return ID;
    }

    public boolean isSendData() {
        return sendData;
    }
}

