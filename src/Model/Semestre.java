package Model;

public class Semestre {
    private int idSemestre;
    private String intitule;
    private int annee;

    public Semestre(int idSemestre, String intitule, int annee) {
        this.idSemestre = idSemestre;
        this.intitule = intitule;
        this.annee = annee;
    }

    public Semestre() {
    }

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
