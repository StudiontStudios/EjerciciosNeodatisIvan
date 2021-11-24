package neodatis;

public class Pais {

    private int id;
    private String nombrepais;

    Pais() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Pais(int id, String nombrepais) {
        this.nombrepais = nombrepais;
        this.id = id;
    }

    public String getNombrepais() {
        return this.nombrepais;
    }

    public void setNombrepais(String nombrepais) {
        this.nombrepais = nombrepais;
    }

    public String toString() {
        return nombrepais;
    }
}
