package Model;

import java.util.Date;

public class ShowHoraire {
    private int idHoraire;
    private String nomEtud;
    private String prenomEtud;
    private String dateEtat;
    private String nomCours;
    private String nomSemestre;
    private int annee;
    private String type;

    public ShowHoraire(int idHoraire, String nomEtud, String prenomEtud, String dateEtat, String nomCours, String nomSemestre, int annee, String type) {
        this.idHoraire = idHoraire;
        this.nomEtud = nomEtud;
        this.prenomEtud = prenomEtud;
        this.dateEtat = dateEtat;
        this.nomCours = nomCours;
        this.nomSemestre = nomSemestre;
        this.annee = annee;
        this.type = type;
    }

    public ShowHoraire() {
    }

    public int getIdHoraire() {
        return idHoraire;
    }

    public void setIdHoraire(int idHoraire) {
        this.idHoraire = idHoraire;
    }

    public String getNomEtud() {
        return nomEtud;
    }

    public void setNomEtud(String nomEtud) {
        this.nomEtud = nomEtud;
    }

    public String getPrenomEtud() {
        return prenomEtud;
    }

    public void setPrenomEtud(String prenomEtud) {
        this.prenomEtud = prenomEtud;
    }

    public String getDateEtat() {
        return dateEtat;
    }

    public void setDateEtat(String dateEtat) {
        this.dateEtat = dateEtat;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public String getNomSemestre() {
        return nomSemestre;
    }

    public void setNomSemestre(String nomSemestre) {
        this.nomSemestre = nomSemestre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }
}
