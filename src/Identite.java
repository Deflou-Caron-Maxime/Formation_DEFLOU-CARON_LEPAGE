public class Identite {
    private String nip, nom, prenom;

    public Identite(String ni, String n, String p){
        this.nip = ni;
        this.nom = n;
        this.prenom = p;
    }

    public String getNIP(){
        return this.nip;
    }
    public String getNom(){
        return this.nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
