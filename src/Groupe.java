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


    public double calculerMoyenneMatiere(String matiere) {
        double moy = 0;
        double me;
        int nbe = 0;
        for (Etudiant e : this.liste) {
            me = e.calculerMoyenne(matiere);
            if (me != -1) {
                moy += me;
                nbe++;
            }

        }
        return (nbe != 0) ? (moy / nbe) : -1;
    }

    public double calculerMoyenneGenerale(){
        double moy = 0;
        for (Etudiant e : this.liste) {
            moy += e.calculerMoyenneGenerale();
        }
        return (this.liste.size() != 0) ? (moy / this.liste.size()) : 0;

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




}
