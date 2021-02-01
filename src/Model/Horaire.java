package Model;

import java.util.Date;

public class Horaire {
    private int idCours;
    private int idEtudiant;
    private int idHoraire;
    private String type;
    private String dateCours;

    public Horaire(int idCours, int idEtudiant, int idHoraire, String type, String dateCours) {
        this.idCours = idCours;
        this.idEtudiant = idEtudiant;
        this.idHoraire = idHoraire;
        this.type = type;
        this.dateCours = dateCours;
    }

    public Horaire() {
    }

    public int getIdCours() {
        return idCours;
    }

    public void setIdCours(int idCours) {
        this.idCours = idCours;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public int getIdHoraire() {
        return idHoraire;
    }

    public void setIdHoraire(int idHoraire) {
        this.idHoraire = idHoraire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateCours() {
        return dateCours;
    }

    public void setDateCours(String dateCours) {
        this.dateCours = dateCours;
    }
}
