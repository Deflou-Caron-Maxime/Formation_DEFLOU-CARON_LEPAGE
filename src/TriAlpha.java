import java.util.Comparator;

public class TriAlpha implements Comparator<Etudiant> {

    @Override
    public int compare(Etudiant o1, Etudiant o2) {

        return ((Etudiant) o1).getIdentite().getNom().compareToIgnoreCase(((Etudiant) o2).getIdentite().getNom());
    }
}
