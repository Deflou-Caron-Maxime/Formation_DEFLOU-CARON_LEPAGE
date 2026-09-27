import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Groupe {
    private ArrayList<Etudiant> liste;
    private Formation formation;


    public Groupe(Formation f){
        this.formation = f;
        this.liste = new ArrayList<>();
    }

    public Formation getFormation() {
        return formation;
    }

    public ArrayList<Etudiant> getListe() {
        return this.liste;
    }

    @Override
    public boolean equals(Object obj) {
        return this.formation.equals((Formation) obj);
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

    public boolean appartinirGroupe(Etudiant e){
        return this.liste.contains(e);
    }

    public int getIndexEtudiant(Etudiant e){
        return this.liste.indexOf(e);
    }

    public void triAlpha(){
        Collections.sort(this.liste, new TriAlpha());
    }

    public void triAntiAlpha(){
        Collections.sort(this.liste, new TriAntiAlpha());
    }

    public void triMerite(){

    }



}
