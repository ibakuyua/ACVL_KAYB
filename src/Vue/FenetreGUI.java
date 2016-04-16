package Vue;

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

import Controleur.Controler;
import Vue.DialogBoxAsk.DialogBoxAV;
import Vue.DialogBoxAsk.DialogBoxSurv;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * \class FenetreGUI
 * \brief Implementation of the IHM for the game
 */
public class FenetreGUI extends JFrame{

    // ATTRIBUTS //

    // Conteneur Haut (Tab bar)
    JTabbedPane containerHaut = new JTabbedPane();
    // Conteneur Bas (Modele.Actions.Action utilisateur)
    JPanel containerBas = new JPanel();
    // Conteneur (Modele.Actions.Action du marché)
    JPanel containerDroit = new JPanel();
    // Icone principal
    Image icon;
    // Les différents onglets
    JPanel portefeuille,favoris,historique, cours;
    // Les affichages des onglets
    JTextArea portefeuilleTxt, favorisTxt, historiqueTxt, coursTxt, marcheTxt;
    // Boutons
    Bouton buttonJourSuiv, buttonAchat, buttonVente,
            buttonQuitter, buttonSurv, buttonNotSurv;
    // Titre du marche
    JLabel titleMarche;
    // Police du Jeu
    final Font police = new Font("Comics sans MS", Font.BOLD,16);


    // CONSTRUCTOR //
    public FenetreGUI(String marketName, String userName){

        // Définition de la fenetre //

        this.setTitle("ACVL : Simulation boursière (Joueur : " + userName + ")");
        this.setSize(1200,800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        try {
            icon = ImageIO.read(new File("Vue/images/icon.jpeg"));
        }catch(IOException e){
            e.printStackTrace();
        }
        this.setIconImage(icon);

        // Définitiond de la liste d'action (droite)
        containerDroit.setBackground(Color.BLACK);
        containerDroit.setLayout(new BorderLayout());
        JPanel containerDroitHaut = new JPanel();
        containerDroitHaut.setBackground(Color.BLACK);
        containerDroitHaut.setLayout(new BorderLayout());
        titleMarche = new JLabel();
        titleMarche.setForeground(Color.GREEN);
        titleMarche.setHorizontalAlignment(JLabel.CENTER);
        titleMarche.setFont(police);
        titleMarche.setPreferredSize(new Dimension(30,50));
        containerDroitHaut.add(titleMarche,BorderLayout.NORTH);
        buttonJourSuiv = new Bouton("Tour Suivant",police);
        buttonJourSuiv.setBackground(Color.BLACK);
        buttonJourSuiv.setPreferredSize(new Dimension(30,50));
        containerDroitHaut.add(buttonJourSuiv,BorderLayout.SOUTH);
        containerDroit.add(containerDroitHaut,BorderLayout.NORTH);
        buttonQuitter = new Bouton("Quitter",police);
        buttonQuitter.setPreferredSize(new Dimension(30,40));
        marcheTxt = new JTextArea(35,10);
        marcheTxt.setBackground(Color.BLACK);
        marcheTxt.setForeground(Color.green);
        marcheTxt.setEditable(false);
        JScrollPane scrollMarche = new JScrollPane(marcheTxt);
        containerDroit.add(scrollMarche,BorderLayout.CENTER);
        containerDroit.add(buttonQuitter,BorderLayout.SOUTH);

        // Definition de la tab bar
        containerHaut.setBackground(Color.LIGHT_GRAY);
        portefeuille = new JPanel();
        portefeuilleTxt = new JTextArea(35,60);
        portefeuilleTxt.setBackground(Color.LIGHT_GRAY);
        portefeuilleTxt.setEditable(false);
        JScrollPane scrollPort = new JScrollPane(portefeuilleTxt);
        scrollPort.setBackground(Color.LIGHT_GRAY);
        portefeuille.add(scrollPort);
        containerHaut.addTab("Portefeuille",new ImageIcon("Vue/images/portfolioIcon.png"),portefeuille,
                            "Actions présentes dans votre portefeuille");
        favoris = new JPanel();
        favorisTxt = new JTextArea(35,60);
        favorisTxt.setBackground(Color.LIGHT_GRAY);
        favorisTxt.setEditable(false);
        JScrollPane scrollFav = new JScrollPane(favorisTxt);
        favoris.add(scrollFav);
        containerHaut.addTab("Surveillées",new ImageIcon("Vue/images/lunetteIcon.png"),favoris,"Actions surveillées");
        historique = new JPanel();
        historiqueTxt = new JTextArea(35,60);
        historiqueTxt.setBackground(Color.LIGHT_GRAY);
        historiqueTxt.setEditable(false);
        JScrollPane scrollHist = new JScrollPane(historiqueTxt);
        historique.add(scrollHist);
        containerHaut.addTab("Historique",new ImageIcon("Vue/images/transactionIcon.png"),historique,"Historique de transaction de l'utilisateur");
        cours = new JPanel();
        coursTxt = new JTextArea(35,30);
        coursTxt.setBackground(Color.LIGHT_GRAY);
        coursTxt.setEditable(false);
        JScrollPane scrollCours = new JScrollPane(coursTxt);
        cours.add(scrollCours);
        containerHaut.addTab("Cours",new ImageIcon("Vue/images/coursIcon.png"),cours,"Cours d'une action depuis t=0");

        // Definition du container bas
        containerBas.setBackground(Color.DARK_GRAY);
        containerBas.setLayout(new BoxLayout(containerBas,BoxLayout.Y_AXIS));
        buttonAchat = new Bouton("Acheter",police);
        buttonVente = new Bouton("Vendre",police);
        JPanel containerButtonAV = new JPanel();
        containerButtonAV.setLayout(new BoxLayout(containerButtonAV,BoxLayout.X_AXIS));
        containerButtonAV.add(buttonAchat);
        containerButtonAV.add(buttonVente);
        containerButtonAV.setBackground(Color.DARK_GRAY);
        buttonSurv = new Bouton("Suivre",police);
        buttonNotSurv = new Bouton("Ne plus suivre",police);
        buttonSurv.setPreferredSize(new Dimension(168,27));
        JPanel containerButtonSurv = new JPanel();
        containerButtonSurv.setLayout(new BoxLayout(containerButtonSurv,BoxLayout.X_AXIS));
        containerButtonSurv.add(buttonSurv);
        containerButtonSurv.add(buttonNotSurv);
        containerButtonSurv.setBackground(Color.DARK_GRAY);
        containerBas.add(containerButtonAV);
        containerBas.add(containerButtonSurv);

        // Split des différents écrans
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                                            containerHaut,containerBas);
        split.setDividerLocation(685);
        split.setEnabled(false);
        JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                            split,containerDroit);
        split2.setDividerLocation(900);
        split2.setEnabled(false);

        // Evenement lié aux boutons //
        buttonQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controler.closeGUI();
            }
        });

        buttonAchat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogBoxAV db = new DialogBoxAV(null,"Acheter un actif",true,true);
                // Dans le cas où l'utilisateur a envoyé des datas
                if (db.isSendData()){
                    Controler.achat(db.getID(),db.getQte());
                }
                db.dispose();
            }
        });

        buttonVente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogBoxAV db = new DialogBoxAV(null,"Vendre un actif",true,false);
                // Dans le cas où l'utilisateur a envoyé des datas
                if (db.isSendData()){
                    Controler.vente(db.getID(),db.getQte());
                }
                db.dispose();
            }
        });

        buttonSurv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogBoxSurv db = new DialogBoxSurv(null,"Suivre un actif",true,true);
                // Dans le cas où l'utilisateur a envoyé des datas
                if (db.isSendData()){
                    Controler.surveiller(db.getID());
                }
                db.dispose();
            }
        });

        buttonNotSurv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogBoxSurv db = new DialogBoxSurv(null,"Ne plus suivre un actif",true,false);
                // Dans le cas où l'utilisateur a envoyé des datas
                if (db.isSendData()){
                    Controler.notSurveiller(db.getID());
                }
                db.dispose();
            }
        });

        buttonJourSuiv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controler.nextStep();
            }
        });

        // Affichage final
        this.setContentPane(split2);
        this.setVisible(true);
    }

    // METHODE D'UPDATE //
    public void updatePortfolio(String str){
        portefeuilleTxt.setText(str);
    }
    public void updateFavoris(String str){
        favorisTxt.setText(str);

    }
    public void updateHistorique(String str){
        historiqueTxt.setText(str);
    }
    public void updateCours(String str){
        coursTxt.setText(str);
    }
    public void updateMarche(String str){
        marcheTxt.setText(str);
    }
    public void updateTitleMarche(String str){
        titleMarche.setText(str);
    }
}

