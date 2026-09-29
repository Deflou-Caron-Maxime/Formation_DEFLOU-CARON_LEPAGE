import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestGroupe {
    Identite idd = new Identite("ar", "RENNES", "Arthur");
    Identite idd1 = new Identite("as", "SED", "Astrid");
    Identite idd2 = new Identite("lg", "GRENNER", "Lucie");
    Formation formation = new Formation(1);

    Etudiant e = new Etudiant(idd, formation);
    Etudiant e1 = new Etudiant(idd1, formation);
    Etudiant e2 = new Etudiant(idd2, formation);

    Groupe groupe = new Groupe(formation);

    @BeforeEach
    public void setUp(){
        formation.ajoutMatiere("web", 1.0);
        formation.ajoutMatiere("reseau", 2.0);
        formation.ajoutMatiere("algo", 0.5);

        e.ajoutNote("reseau", 10);
        e.ajoutNote("algo", 20);

        e1.ajoutNote("reseau", 13);

        e2.ajoutNote("reseau", 6);
        e2.ajoutNote("algo", 5.5);
    }


    @Test
    public void testAjoutEtudiant(){
        groupe.ajouterEtudiant(e);
        groupe.ajouterEtudiant(e1);
        groupe.ajouterEtudiant(e2);

        assertTrue(groupe.appartinirGroupe(e));

    }

    @Test
    public void testAjoutEtudiantDiffFormation(){
        Identite idd3 = new Identite("e17022u", "LEPAGE", "Thomas");
        Formation formation1 = new Formation(2);
        Etudiant e3 = new Etudiant(idd3, formation1);

        groupe.ajouterEtudiant(e3);

        assertFalse(groupe.appartinirGroupe(e3));
    }

    @Test
    public void testSuppressionEtudiant(){
        groupe.ajouterEtudiant(e);
        groupe.ajouterEtudiant(e1);
        groupe.ajouterEtudiant(e2);

        groupe.supprimerEtudiant(e1);

        assertTrue(groupe.appartinirGroupe(e));
        assertFalse(groupe.appartinirGroupe(e1));
    }

    @Test
    public void testMoyenneMatiereExistante(){
        groupe.ajouterEtudiant(e);
        groupe.ajouterEtudiant(e1);
        groupe.ajouterEtudiant(e2);

        double mm = groupe.calculerMoyenneMatiere("reseau");

        double moy = e.calculerMoyenne("reseau");
        moy += e1.calculerMoyenne("reseau");
        moy += e2.calculerMoyenne("reseau");
        assertEquals(moy/3, mm);
    }

    @Test
    public void testMoyenneMatiereInexistanteTous(){
        groupe.ajouterEtudiant(e);
        groupe.ajouterEtudiant(e1);
        groupe.ajouterEtudiant(e2);

        double mm = groupe.calculerMoyenneMatiere("web");
        // Le code d'erreur associé à une matière inexistante est -1
        assertEquals(-1, mm);
    }

    @Test
    public void testMoyenneMatiereInexistanteUn(){
        groupe.ajouterEtudiant(e);
        groupe.ajouterEtudiant(e1);
        groupe.ajouterEtudiant(e2);

        double mm = groupe.calculerMoyenneMatiere("algo");
        double moy = e.calculerMoyenne("algo");
        moy += e2.calculerMoyenne("algo");
        assertEquals(moy/2, mm);
    }

    @Test
    public void testMoyenneGenerale(){
        groupe.ajouterEtudiant(e);
        groupe.ajouterEtudiant(e1);
        groupe.ajouterEtudiant(e2);

        double mg = groupe.calculerMoyenneGenerale();
        // Comme la classe TestEtudiant a été vérifiée, on peut utiliser les méthodes de la classe Etudiant
        double moyg = e.calculerMoyenneGenerale() + e1.calculerMoyenneGenerale() + e2.calculerMoyenneGenerale();
        assertEquals(moyg/3, mg);
    }
}
