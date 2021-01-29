package Model;

public class Cours {
    private int idCours;
    private String intitule;
    private int idSemestre;

    public Cours(int idCours, String intitule, int idSemestre) {
        this.idCours = idCours;
        this.intitule = intitule;
        this.idSemestre = idSemestre;
    }

    public Cours() {
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }
}
