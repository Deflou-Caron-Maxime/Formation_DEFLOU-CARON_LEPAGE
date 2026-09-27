import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        formation.ajoutMatiere("reseau", 1.0);
        formation.ajoutMatiere("algo", 1.0);
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
}
