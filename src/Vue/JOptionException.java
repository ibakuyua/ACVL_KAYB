package Vue;

import javax.swing.*;

/**
 * Created by arnaud.ibakuyumcu on 17/04/2016.
 */
public class JOptionException extends JOptionPane {
    public JOptionException(String title, String subtitle){
        super();
        showMessageDialog(null,subtitle,title,JOptionPane.ERROR_MESSAGE);
        setIcon(new ImageIcon("src/Vue/images/error.png"));
    }
}
