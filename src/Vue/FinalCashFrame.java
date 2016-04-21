package Vue;
/**
 * \file Vue.FinalCashFrame
 * \brief Implementation of the final window of the game
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 16 April 2016
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * \class Vue.FinalCashFrame
 * \brief Permit to display the final gain of the game
 *
 * \details Extends JDialog
 */
public class FinalCashFrame extends JDialog{
    /**
     * \fn FinalCashFrame(JFrame parent, String title, boolean modal,double cash)
     * \brief Constructor
     *
     * \param JFrame parent
     * \param String title : Window's title
     * \param boolean modal
     * \param double cash : Final gain
     */
    public FinalCashFrame(JFrame parent, String title, boolean modal,double cash){
        super(parent,title,modal);
        this.setSize(500,300);
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        DecimalFormat df = new DecimalFormat("0.0##");
        JLabel cashTxt = new JLabel("## PLUS-VALUE FINALE : " + df.format(cash) + " ##");
        cashTxt.setHorizontalAlignment(JLabel.CENTER);
        Button okButton = new Button("Terminer");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.add(cashTxt,BorderLayout.CENTER);
        this.add(okButton,BorderLayout.SOUTH);

        this.setVisible(true);

    }
}
