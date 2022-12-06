package partie1;

public class Dept implements Comparable<Dept> {
    private int numero;
    private String nomDept;
    private String nomPrefecture;
    private String nomRegion;
    private double superficie;
    private int population;
    private int x;
    private int y;


    public Dept(int numero, String nomDept, String nomPrefecture, String nomRegion, double superficie, int population) {
        this.numero = numero;
        this.nomDept = nomDept;
        this.nomPrefecture = nomPrefecture;
        this.nomRegion = nomRegion;
        this.superficie = superficie;
        this.population = population;
        this.x = 0;
        this.y = 0;
    }


    public int getNumero() {
        return this.numero;
    }

    public String getNomDept() {
        return this.nomDept;
    }

    public String getNomPrefecture() {
        return this.nomPrefecture;
    }

    public String getNomRegion() {
        return this.nomRegion;
    }

    public double getSuperficie() {
        return this.superficie;
    }

    public int getPopulation() {
        return this.population;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public double getDensite() {
        return this.population / this.superficie;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return this.numero + " " + this.nomDept + " " + this.nomPrefecture + " " + this.nomRegion + " " + this.superficie + " " + this.population;
    }

    public int compareTo(Dept autreDept) {
        return this.numero - autreDept.numero;
    }

}