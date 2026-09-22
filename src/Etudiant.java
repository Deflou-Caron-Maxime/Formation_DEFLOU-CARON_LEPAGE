import java.util.ArrayList;
import java.util.HashMap;

public class Etudiant {

    // Declaration des attributs
    private Identite identite;
    private Formation formation;
    private HashMap<String, ArrayList<Double>> resultat;


    public Etudiant(Identite id, Formation f){
        this.identite = id;
        this.formation = f;
        this.resultat = new HashMap<>();
        for (String s : this.formation.getMatieres()){
            this.resultat.put(s, null);
        }
    }

    public void ajoutNote(String matiere, double note){
        // Si la note renseignée est incorrect, on la corrige pour la mettre dans l'intervalle autorisee
        if ((note < 0)||(note > 20)) throw new NoteException("La note saisie n'est pas comprise entre 0 et 20 !");
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
            // Resultat d'erreur
            return -1;
        };
        double moyenne = 0;
        for (double note : this.resultat.get(matiere)){
            moyenne += note;
        }
        // resultat d'erreur
        if(this.getResultat(matiere).isEmpty()) return -1;
        return moyenne / this.resultat.get(matiere).size();
    }

    public double calculerMoyenneGenerale(){
        double mg = 0;
        double mm;
        int cpte = 0;

        for (String m : this.resultat.keySet()){
            mm = calculerMoyenne(m);
            if (mm != -1) {
                mg += mm * this.formation.getCoeff(m);
                cpte += this.formation.getCoeff(m);
            }
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
