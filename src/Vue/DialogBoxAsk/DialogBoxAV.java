package Vue.DialogBoxAsk;
/**
 * \file Vue.DialogBoxAV.java
 * \brief Implementation of the DialogBoxAsk interface for buying/selling
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
 * \class DialogBoxAV
 * \brief DialogBoxAsk for buying/selling
 *
 * \details Extends DialogBox
 */
public class DialogBoxAV extends DialogBox{

    private JTextField qteField; /**<The quantity fields*/
    private int qte; /**<The quantity asking*/

    /**
     * \fn DialogBoxAV(JFrame parent, String title, boolean modal,boolean isAchat)
     * \brief Constructor
     *
     * \param JFrame parent
     * \param String title
     * \param boolean modal
     * \param boolean isAchat : If the window is for buying
     */
    public DialogBoxAV(JFrame parent, String title, boolean modal,boolean isAchat){
        super(parent,title,modal);
        init(isAchat);
    }

    /**
     * \fn void init(boolean isAchat)
     * \brief Permit to init the window
     *
     * \param boolean isAchat : If the window is for buying
     */
    private void init(boolean isAchat){
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
        panID.setBorder(BorderFactory.createTitledBorder(((isAchat)?"ID de l'action":"Position de l'action")));
        panID.add(new JLabel("Saisir " +((isAchat)?"l'ID : ":"la position : ")));
        panID.add(IDfield);
        // MISE EN PLACE DE LA QTE
        JPanel panQte = new JPanel();
        panQte.setBackground(Color.GRAY);
        panQte.setPreferredSize(new Dimension(250,60));
        qteField = new JTextField();
        qteField.setPreferredSize(new Dimension(100,25));
        panQte.setBorder(BorderFactory.createTitledBorder("Quantité à "+((isAchat)?"acheter":"vendre")));
        panQte.add(new JLabel("Saisir la quantité : "));
        panQte.add(qteField);
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
                    try {
                        qte = Integer.parseInt(qteField.getText());
                        sendData = true;
                    }catch (NumberFormatException ne){
                        new JOptionException("Erreur Quantité","Veuillez entrer une quantité valide");
                        sendData = false;
                    }
                }catch(NumberFormatException ne){
                    new JOptionException("Erreur ID","Veuillez entrer un ID valide");
                            sendData = false;
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
        content.add(panQte);
        this.getContentPane().setBackground(Color.GRAY);
        this.getContentPane().add(control,BorderLayout.SOUTH);
        this.getContentPane().add(panIcon,BorderLayout.WEST);
        this.getContentPane().add(content,BorderLayout.CENTER);

        setVisible(true);

    }

    // GETTER //

    public int getQte() {
        return qte;
    }
}
