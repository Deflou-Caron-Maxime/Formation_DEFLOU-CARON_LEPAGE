import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTris {
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

        groupe.ajouterEtudiant(e);
        groupe.ajouterEtudiant(e1);
        groupe.ajouterEtudiant(e2);
    }

    @Test
    public void testTriAlpha(){
        groupe.triAlpha();

        assertEquals(0, groupe.getIndexEtudiant(e2));
        assertEquals(1, groupe.getIndexEtudiant(e));
        assertEquals(2, groupe.getIndexEtudiant(e1));
    }

    @Test
    public void testTriAlphaMemeNomEtu(){
        Identite idd3 = new Identite("lg", "GRENNER", "Michelle");
        Etudiant e3 = new Etudiant(idd3, formation);
        this.groupe.ajouterEtudiant(e3);

        System.out.println("Avant Tri (ordre alphabétique) : ");
        System.out.println();
        for(Etudiant e : groupe.getListe()){
            System.out.println(e.getIdentite().getNom() + " " + e.getIdentite().getPrenom());
        }

        groupe.triAlpha();
        System.out.println();
        System.out.println("Apres Tri : ");
        System.out.println();
        for(Etudiant e : groupe.getListe()){
            System.out.println(e.getIdentite().getNom() + " " + e.getIdentite().getPrenom());
        }
    }


    @Test
    public void testTriAntiAlpha(){
        groupe.triAntiAlpha();

        assertEquals(0, groupe.getIndexEtudiant(e1));
        assertEquals(1, groupe.getIndexEtudiant(e));
        assertEquals(2, groupe.getIndexEtudiant(e2));
    }

    @Test
    public void testTriAntiAlphaMemeNomEtu(){
        Identite idd3 = new Identite("lg", "GRENNER", "Michelle");
        Etudiant e3 = new Etudiant(idd3, formation);
        this.groupe.ajouterEtudiant(e3);

        System.out.println("Avant Tri (ordre anti-alphabétique) : ");
        System.out.println();
        for(Etudiant e : groupe.getListe()){
            System.out.println(e.getIdentite().getNom() + " " + e.getIdentite().getPrenom());
        }

        groupe.triAntiAlpha();

        System.out.println();
        System.out.println("Apres Tri : ");
        System.out.println();
        for(Etudiant e : groupe.getListe()){
            System.out.println(e.getIdentite().getNom() + " " + e.getIdentite().getPrenom());
        }
    }

    //Test de la méthode triParMerite
    @Test
    public void testTriParMerite(){
        //web
        //reseau
        //algo

        for(Etudiant e : groupe.getListe()){
            System.out.println(e.calculerMoyenneGenerale());
        }

        e.ajoutNote("web", 10);
        e.ajoutNote("web", 12);
        e.ajoutNote("reseau", 10);
        e.ajoutNote("reseau", 12);
        e.ajoutNote("algo", 10);
        e.ajoutNote("algo", 12);

        e1.ajoutNote("web", 10);
        e1.ajoutNote("web", 14);
        e1.ajoutNote("reseau", 10);
        e1.ajoutNote("reseau", 14);
        e1.ajoutNote("algo", 10);
        e1.ajoutNote("algo", 14);

        e2.ajoutNote("web", 8);
        e2.ajoutNote("web", 12);
        e2.ajoutNote("reseau", 8);
        e2.ajoutNote("reseau", 12);
        e2.ajoutNote("algo", 8);
        e2.ajoutNote("algo", 12);

        groupe.triParMerite();

        for(Etudiant e : groupe.getListe()){
            System.out.println(e.calculerMoyenneGenerale());
        }

        assertEquals(0, groupe.getIndexEtudiant(e2));
        assertEquals(1, groupe.getIndexEtudiant(e));
        assertEquals(2, groupe.getIndexEtudiant(e1));
    }
    //On estime avoir fait tous les tests pour la méthode triParMerite
    //Tester autre chose reviendrait à tester les autres méthodes déjà testées.
}
