import java.util.Comparator;

public class TriMerite implements Comparator<Etudiant> {

    @Override
    public int compare(Etudiant o1, Etudiant o2) {
        double m1 = o1.calculerMoyenneGenerale();
        double m2 = o2.calculerMoyenneGenerale();

        int res = 0;
        if(m1>m2){
            res = 1;
        }
        if(m2>m1){
            res = -1;
        }
        return res;
    }
}
