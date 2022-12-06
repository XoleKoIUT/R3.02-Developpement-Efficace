package partie1;

import javax.swing.*;
import java.awt.*;


public class FrameCarte extends JFrame {
    PanelCarte panelCarte;

    public FrameCarte(Controlable ctrl) {
        this.panelCarte   = new PanelCarte(ctrl);

        this.add (panelCarte,  BorderLayout.CENTER);
        this.panelCarte   = new PanelCarte(ctrl);
        this.panelCarte.setPreferredSize( new Dimension( 1740,1217) );

        JScrollPane jsp = new JScrollPane( this.panelCarte );

        this.add (jsp, BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void majIHM() {
        this.panelCarte.repaint();
    }
}