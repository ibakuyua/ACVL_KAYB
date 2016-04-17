package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Created by arnaud.ibakuyumcu on 17/04/2016.
 */
public class FinalCashFrame extends JDialog{
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
