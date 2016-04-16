package Vue;

import java.awt.*;

/**
 * Created by arnaud.ibakuyumcu on 16/04/2016.
 */
class Bouton extends Button {
    public Bouton(String str, Font police){
        super(str);
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.BLACK);
        this.setFont(police);
    }
}
