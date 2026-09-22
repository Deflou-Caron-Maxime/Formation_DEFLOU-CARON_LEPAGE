import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEtudiant {
    //Attributs

    private Identite identite = new Identite("1234", "LEPAGE", "Thomas");
    private Identite idd2 = new Identite("1244", "DEFLOU-CARON", "Maxime");
    private Formation formation = new Formation(1);
    private Formation formation2 = new Formation(2);
    public Etudiant etu1 = new Etudiant(identite, formation);
    public Etudiant etu2 = new Etudiant(idd2, formation2);

    String qdev = "Qdev";
    String algo = "Algo";
    String reseaux = "Reseaux";
    String cryptographie = "Cryptographie";
    String anglais = "Anglais";

    //Mis en place des tests
    @BeforeEach
    public void setUp(){
        formation.ajoutMatiere("Cryptographie", 1.0);
        formation.ajoutMatiere("Qdev", 1.0);
        formation.ajoutMatiere("Algo", 1.0);
        formation.ajoutMatiere("Reseaux", 1.0);



        etu1.ajoutNote(qdev, 11.0);
        etu1.ajoutNote(qdev, 17.0);
        etu1.ajoutNote(algo, 15.0);
        etu1.ajoutNote(algo, 18.0);
        etu1.ajoutNote(reseaux, 13.0);
        etu1.ajoutNote(reseaux, 16.0);
    }

    //Test l'ajout d'une note dans une matière existante.
    @Test
    public void testAjoutNote(){
        etu1.ajoutNote(cryptographie, 16.0);
        assertEquals(16.0, etu1.getResultat(cryptographie).getFirst());
    }

    //Test l'ajout d'une note dans une matière inexistante.
    @Test
    public void testAjoutNoteMatiereInexistante(){
        etu1.ajoutNote(anglais, 20.0);
        assertEquals(20.0, etu1.getResultat(anglais).getFirst());
        //Quand la matière n'existe pas la matière est ajoutée
    }

    //Test l'ajout d'une note négative.
    @Test
    public void testAjoutNoteNegative(){
        etu1.ajoutNote(anglais, -2);
        assertEquals(0.0, etu1.getResultat(anglais).getFirst());
        //La note devient 0.0.
    }

    //Test l'ajout d'une note supérieur à 20.
    @Test
    public void testAjoutNoteSupVingt(){
        etu1.ajoutNote(anglais, 22);
        assertEquals(20.0, etu1.getResultat(anglais).getFirst());
        //La note devient 20.0.
    }



    @Test
    public void testGetResultatMatiereInexistante(){
        assertThrows(MatiereException.class, () -> {
            etu1.getResultat("Mathematique");
        });
    }

    @Test
    public void testCalculerMoyenne(){
        assertEquals(14, etu1.calculerMoyenne(qdev));
    }

    @Test
    public void testCalculerMoyenneMatiereInexistante(){
        boolean test = false;
        try{
            etu1.calculerMoyenne("Mathematique");
            test = true;
        } catch (MatiereException e){
            System.out.println(e.getMessage());
            test = false;
        }
        assertFalse(test);
    }

    @Test
    public void testCalculerMoyenneGenerale(){
        assertEquals(15, etu1.calculerMoyenneGenerale());
    }

    @Test
    public void testCalculerMoyennerGeneraleEtudiantVide(){
        assertEquals(0, etu2.calculerMoyenneGenerale());
    }
}