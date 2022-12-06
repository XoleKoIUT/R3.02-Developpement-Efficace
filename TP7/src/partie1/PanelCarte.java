package partie1; /**
 * Ce panneau représente l'aire de dessin de la grille ou plateau de jeu
 *
 * @Author Philippe Le Pivert.
 */


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;

class PanelCarte extends JPanel implements ActionListener {
    private Controlable ctrl;
    private Graphics2D g2;
    private JButton btnXML;
    private Image img;

    public PanelCarte(Controlable ctrl) {
        this.setLayout(null);      // Suppression de la gestion des layouts,
        // pas très conseillée par la littérature, mais bien pratique
        this.ctrl = ctrl;

        this.btnXML = new JButton("Generer Fichier XML");
        this.btnXML.setBounds(10, 900, 190, 50);
        this.add(btnXML);

        this.btnXML.addActionListener(this);

        this.img = getToolkit().getImage("./images/carte_de_france.png");
        // L'image sera dessiner sur le Panel dans la méthode paintComponent


        // Instanciation du Gestionnaire d'événement Souris
        // ------------------------------------------------
        GestionSouris ecouteurSouris = new GestionSouris();
        this.addMouseListener(ecouteurSouris);
        this.setFocusable(true);

    }

    /*--------------------------------------------------*/
    /* Méthode permettant de redessinner entièrement    */
    /* le contenu de la fenêtre.                        */
    /* Cette méthode est appelée par la méthode repaint */
    /*--------------------------------------------------*/
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2 = (Graphics2D) g;
        g2.drawImage(img, 0, 0, this);
    }

    public void actionPerformed(ActionEvent e) {
        this.ctrl.ecrireXml();
    }


    // Classe interne pour la gestion des événements Souris
    private class GestionSouris extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            ctrl.cliquer(event.getX(), event.getY());
        }
    }


}