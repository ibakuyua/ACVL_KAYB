//package Vue;

/**
 * \file FenetreGUI.java
 * \brief Implementation of the class FenetreGUI
 * \author Ibakuyumcu Arnaud
 * \author Voong Kwan
 * \author Ayutaya Rattanatray
 * \author Ruimy Benjamin
 * \version 1.0
 * \date 16 April 2016
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * \class FenetreGUI
 * \brief Implementation of the IHM for the game
 */
public class FenetreGUI extends JFrame{

    // Conteneur Haut (Tab bar)
    JTabbedPane containerHaut;
    // Conteneur Bas (Action utilisateur)
    JPanel containerBas;
    // Conteneur (Action du marché)
    JPanel containerDroit;
    // Icone principal
    Image icon;
    // Les différents onglets
    JPanel portefeuille,favoris,historique, cours;
    // La liste d'action
    MarcheLabel marche;
    // Boutons
    Button buttonJourSuiv;
    public FenetreGUI(){
        this.setTitle("ACVL : Simulation boursière");
        this.setSize(1200,800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            icon = ImageIO.read(new File("icon.jpeg"));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.setIconImage(icon);

        // Définitiond de la liste d'action (droite)
        containerDroit = new JPanel();
        containerDroit.setBackground(Color.BLACK);
        containerDroit.setLayout(new BorderLayout());
        JLabel titleMarche = new JLabel();
        titleMarche.setText("MARCHE DES ACTIONS");
        titleMarche.setForeground(Color.GREEN);
        titleMarche.setHorizontalAlignment(JLabel.CENTER);
        Font police = new Font("Comics sans MS", Font.BOLD,16);
        titleMarche.setFont(police);
        containerDroit.add(titleMarche,BorderLayout.NORTH);
        buttonJourSuiv = new Button("Tour Suivant");
        buttonJourSuiv.setBackground(Color.DARK_GRAY);
        buttonJourSuiv.setFont(police);
        buttonJourSuiv.setForeground(Color.BLACK);
        containerDroit.add(buttonJourSuiv,BorderLayout.SOUTH);

        // Definition de la tab bar
        containerHaut = new JTabbedPane();
        containerHaut.setBackground(Color.LIGHT_GRAY);
        portefeuille = new JPanel();
        containerHaut.addTab("Portefeuille",new ImageIcon("images/portfolioIcon.png"),portefeuille,
                            "Actions présentes dans votre portefeuille");
        favoris = new JPanel();
        containerHaut.addTab("Surveillées",new ImageIcon("images/lunetteIcon.png"),favoris,"Actions surveillées");
        historique = new JPanel();
        containerHaut.addTab("Historique",new ImageIcon("images/transactionIcon.png"),historique,"Historique de transaction de l'utilisateur");
        cours = new JPanel();
        containerHaut.addTab("Cours",new ImageIcon("images/coursIcon.png"),cours,"Cours d'une action depuis t=0");

        // Definition du container bas
        containerBas = new JPanel();
        containerBas.setBackground(Color.DARK_GRAY);
        containerBas.setLayout(new BorderLayout());

        // Split des différents écrans
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                            containerHaut,containerBas);
        split.setDividerLocation(700);
        split.setEnabled(false);
        JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                            split,containerDroit);
        split2.setDividerLocation(900);
        split2.setEnabled(false);

        // Affichage
        this.setContentPane(split2);
        this.setVisible(true);
    }
}

class MarcheLabel extends JLabel{

}
