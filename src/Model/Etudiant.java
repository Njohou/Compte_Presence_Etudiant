package Model;

public class Etudiant {
    private int idEtud;
    private String nom;
    private String prenom;
    private String sexe;
    private int telephone;

    public Etudiant(int idEtud, String nom, String prenom, String sexe, int telephone) {
        this.idEtud = idEtud;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.telephone = telephone;
    }

    public Etudiant() {}

    public int getIdEtud() {
        return idEtud;
    }

    public void setIdEtud(int idEtud) {
        this.idEtud = idEtud;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }
}
