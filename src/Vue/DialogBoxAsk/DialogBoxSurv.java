package Vue.DialogBoxAsk;
/**
 * \file Vue.DialogBoxSurv.java
 * \brief Implementation of the DialogBoxAsk interface for controling
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 16 April 2016
 */

import Vue.JOptionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * \class DialogBoxSurv
 * \brief DialogBoxAsk for controling
 *
 * \details Extends DialogBox
 */
public class DialogBoxSurv extends DialogBox{
    /**
     * \fn DialogBoxSurv(JFrame parent, String title,boolean modal, boolean isSurv)
     * \brief Constructor
     *
     * \param JFrame parent
     * \param String title
     * \param boolean modal
     * \param boolean isSurv : If the window is for controling or removing controlled asset
     */
    public DialogBoxSurv(JFrame parent, String title, boolean modal, boolean isSurv){
        super(parent,title,modal);
        init(isSurv);
    }

    /**
     * \fn void init(boolean isSurv)
     * \brief Permit to init the window
     *
     * \param boolean isSurv : If the window is for controling or removing controlled asset
     */
    private void init(boolean isSurv){
        // MISE EN PLACE DE L'ICONE
        JPanel panIcon = new JPanel();
        panIcon.setBackground(Color.GRAY);
        panIcon.add(icon);
        // MISE EN PLACE DE l'ID
        JPanel panID = new JPanel();
        panID.setBackground(Color.GRAY);
        panID.setPreferredSize(new Dimension(250,60));
        IDfield = new JTextField();
        IDfield.setPreferredSize(new Dimension(100,25));
        panID.setBorder(BorderFactory.createTitledBorder(((isSurv)?"ID de l'action":"Position de l'action")));
        panID.add(new JLabel("Saisir " +((isSurv)?"l'ID : ":"la position : ")));
        panID.add(IDfield);

        // BOUTONS //
        JPanel control = new JPanel();
        control.setBackground(Color.GRAY);
        JButton okButton = new JButton("Confirmer");
        okButton.setBackground(Color.GRAY);
        JButton cancButton = new JButton("Annuler");
        cancButton.setBackground(Color.GRAY);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ID = Integer.parseInt(IDfield.getText());
                    sendData = true;
                }catch (NumberFormatException ne){
                    sendData = false;
                    new JOptionException("Erreur ID","Veuillez entrer un ID valide");
                }
                setVisible(false);
            }
        });

        cancButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData = false;
                setVisible(false);
            }
        });

        control.add(okButton);
        control.add(cancButton);

        // MISE EN PLACE DES LAYOUTS //
        JPanel content = new JPanel();
        content.setBackground(Color.GRAY);
        content.add(panID);
        this.getContentPane().setBackground(Color.GRAY);
        this.getContentPane().add(control,BorderLayout.SOUTH);
        this.getContentPane().add(panIcon,BorderLayout.WEST);
        this.getContentPane().add(content,BorderLayout.CENTER);

        setVisible(true);

    }
}
