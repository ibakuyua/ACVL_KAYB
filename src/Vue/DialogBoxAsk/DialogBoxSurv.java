package Vue.DialogBoxAsk;

import Vue.JOptionException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by arnaud.ibakuyumcu on 16/04/2016.
 */
public class DialogBoxSurv extends DialogBox{
    public DialogBoxSurv(JFrame parent, String title,
                         boolean modal, boolean isSurv){
        super(parent,title,modal);
        init(isSurv);
    }

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
