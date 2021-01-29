package Model;

public class Semestre {
    private int idSemestre;
    private String intitule;
    private String annee;

    public Semestre(int idSemestre, String intitule, String annee) {
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

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
}
