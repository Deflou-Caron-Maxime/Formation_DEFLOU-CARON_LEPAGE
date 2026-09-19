import java.util.ArrayList;
import java.util.HashMap;

public class Etudiant {
    private Identite identite;
    private Formation formation;
    private HashMap<String, ArrayList<Double>> resultat;

    public Etudiant(Identite id, Formation f){
        this.identite = id;
        this.formation = f;
        this.resultat = new HashMap<>();
    }

    public void ajoutNote(String matiere, double note){
        if (note < 0) note = 0;
        if (note > 20) note = 20;
        if (!this.resultat.containsKey(matiere)) this.resultat.put(matiere, new ArrayList<>());
        this.resultat.get(matiere).add(note);
    }

    public ArrayList<Double> getResultat(String matiere) throws MatiereException{
        if(!resultat.containsKey(matiere)){
            throw new MatiereException("La matiere n'existe pas dans la " +
                    "formation de l'étudiant.");
        }
        return resultat.get(matiere);

    }

    public double calculerMoyenne(String matiere) throws MatiereException {
        if (!this.resultat.containsKey(matiere)){
            throw new MatiereException("La matiere n'existe pas dans la formation de" +
                    "l'etudiant.");
        };
        double moyenne = 0;
        for (double note : this.resultat.get(matiere)){
            moyenne += note;
        }
        if(this.getResultat(matiere).isEmpty()) return 0.0;
        return moyenne / this.resultat.get(matiere).size();
    }

    public double calculerMoyenneGenerale(){
        double mg = 0;
        double mm = 0;
        int cpte = 0;

        for (String m : this.resultat.keySet()){
            mm = calculerMoyenne(m);
            mg += mm * this.formation.getCoeff(m);
            cpte += this.formation.getCoeff(m);
        }
        if(cpte == 0){
            return 0.0;
        }
        return mg/cpte;
    }

    public Formation getFormation() {
        return formation;
    }
}
