package partie1;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;

// Pour la gestion de l'image
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.plaf.synth.Region;

public class GenererXml implements Controlable {

    private List<Dept> ensDept;

    private FrameCarte frame;

    private BufferedImage bi;

    private ArrayList<Dept> alDept = new ArrayList<Dept>();

    /* HashMap pour stocker les régions et les départements associés */
    private HashMap<String, ArrayList<Dept>> hashMap = new HashMap<>();

    public GenererXml() {
        this.ensDept = new ArrayList<Dept>();

        this.frame = new FrameCarte(this);

        this.frame.setSize(1400, 1200);
        this.frame.setLocation(10, 10);
        this.frame.setTitle("Définition des département");
        this.frame.setVisible(true);

        try {
            File file = new File("./images/carte_de_france_degrade.png");
            //System.out.println(file.getAbsolutePath());
            bi = ImageIO.read(file);
        } catch (Exception e) {
        }

    }

    public void lireFichier() {
        // Lecture du fichier list_dept.txt
        Scanner scFic, scLigne;

        int numDept, nbPop;
        String nomDept, nomPref, nomReg;
        double nbSuperf;

        /* Triage de la liste des départements par ordre alphabétique */
        RegionComparator regionComparator = new RegionComparator();

        try {
            scFic = new Scanner(new FileInputStream("./source/liste_dept.data"));

            while (scFic.hasNextLine()) {
                scLigne = new Scanner(scFic.nextLine());
                scLigne.useDelimiter("\t");

                numDept  = Integer.parseInt(scLigne.next());
                nomDept  = scLigne.next();
                nomPref  = scLigne.next();
                nomReg   = scLigne.next();
                nbSuperf = Double.parseDouble(scLigne.next().replace(",", "."));
                nbPop    = Integer.parseInt(scLigne.next());

                Dept d = new Dept(numDept, nomDept, nomPref, nomReg, nbSuperf, nbPop);
                alDept.add(d);
            }

            Collections.sort( this.alDept, regionComparator );

            scFic.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    public void ecrireXml() {

        /*      Remplissage de la HashMap     */
        for (Dept c : alDept) {
            String region = c.getNomRegion();
            if (this.hashMap.containsKey(region)) {
                this.hashMap.get(region).add(c);
            } else {
                ArrayList<Dept> alTemp = new ArrayList<Dept>();
                alTemp.add(c);
                this.hashMap.put(region, alTemp);
            }
        }
        /*-------------------------------------*/

        PrintWriter pw;
        try {
            pw = new PrintWriter("./sortie/liste_dept.xml");

            pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            pw.println("<liste>");

            for (String region : this.hashMap.keySet()) {
                pw.println("\t<region nom=\"" + region + "\">");

                for (Dept d : this.hashMap.get(region)) {
                    pw.println("\t\t<dept numero=\"" + d.getNumero() + "\">");
                    pw.println("\t\t<nomDept>" + d.getNomDept() + "</nomDept>");
                    pw.println("\t\t<preferecture>" + d.getNomPrefecture() + "</preferecture>");
                    pw.println("\t\t<population>" + d.getPopulation() + "</population>");
                    pw.println("\t\t<superficie>" + d.getSuperficie() + "</superficie>");
                    pw.println("\t\t</dept>");
                }
                pw.println("\t</region>");
            }

            pw.println("</liste>");
            pw.close();

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
    public void cliquer(int x, int y) {
        int couleur = bi.getRGB(x, y) & 0xFFFFFF;

        System.out.println(couleur + "  (" + x + ":" + y + ")");        // Conserver cette Ligne
        /* récupère les coordonnees du clic de la souris */

    }

    public String toString() {
        String s = "Liste des départements : \n";
        for (Dept d : alDept)
            s += d + "\n";
        return s;
    }


    public static void main(String[] a) {
        GenererXml generer = new GenererXml();

        generer.lireFichier();
        //System.out.println(generer.toString());
    }

}