import java.util.HashMap;
import java.util.Objects;

public class Formation {
    private int identifiant;
    private HashMap<String, Double> matieres;

    public Formation(int idd){

        this.identifiant = idd;
        matieres = new HashMap<>();
    }
    public void ajoutMatiere(String matiere, Double coef){
        this.matieres.put(matiere, coef);
    }
    public void supprimerMatiere(String matiere){
        if (this.matieres.containsKey(matiere)){
            this.matieres.remove(matiere);
        }
    }

    public int getIdentifiant(){
        return this.identifiant;
    }

    public Double getCoeff(String matiere){
        if(!this.matieres.containsKey(matiere)){
            //que faire si la matière n’est pas dans la
            //collection attribut de la formation ?
            //Lever une exception
            throw new MatiereException("La matière n'existe pas dans la formation," +
                    "veuillez l'ajouter d'abord.");
        }
        return this.matieres.get(matiere);
    }


    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj.getClass() != this.getClass()){
            return false;
        }

        return((this.identifiant == ((Formation)obj).identifiant)
                && (this.matieres == ((Formation)obj).matieres));
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifiant, matieres);
    }


}
