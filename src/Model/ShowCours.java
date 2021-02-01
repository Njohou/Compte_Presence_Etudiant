package Model;

public class ShowCours {
    private int idCours;
    private String nomCours;
    private String intitule;
    private int annee;

    public ShowCours(int idCours, String nomCours, String intitule, int annee) {
        this.idCours = idCours;
        this.nomCours = nomCours;
        this.intitule = intitule;
        this.annee = annee;
    }

    public ShowCours() {
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
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
