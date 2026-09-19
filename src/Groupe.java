import java.util.HashSet;

public class Groupe {
    private HashSet<Etudiant> liste;
    private Formation formation;


    public Groupe(Formation f){
        this.formation = f;
        this.liste = new HashSet<>();
    }

    public Formation getFormation() {
        return formation;
    }

    public HashSet<Etudiant> getListe() {
        return liste;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void ajouterEtudiant(Etudiant e){
        if (e.getFormation().equals(this.formation)){
            this.liste.add(e);
        }
    }

    public void supprimerEtudiant(Etudiant e){
        if (this.liste.contains(e)){
            this.liste.remove(e);
        }
    }

    public void triAlpha(){

    }

    public void triAntiAlpha(){

    }

    public void triMerite(){

    }



}
