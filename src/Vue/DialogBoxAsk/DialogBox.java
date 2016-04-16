package Vue.DialogBoxAsk;


import javax.swing.*;
import java.awt.*;

/**
 * Created by arnaud.ibakuyumcu on 16/04/2016.
 */
public class DialogBox extends JDialog{
    // ATTRIBUTS //
    protected final JLabel icon  = new JLabel(new ImageIcon("images/interrogation.png"));
    protected JTextField IDfield;
    protected int ID;
    protected boolean sendData;

    // CONSTRUCTOR //
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

