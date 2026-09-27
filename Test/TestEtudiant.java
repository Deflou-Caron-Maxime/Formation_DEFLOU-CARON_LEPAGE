import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.Normalizer;

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

    //Test le calcul de la moyenne d'une matière qui existe
    @Test
    public void testCalculerMoyenne(){
        assertEquals(14, etu1.calculerMoyenne(qdev));
    }

    //Test le calcul de la moyenne d'une matière qui n'existe pas
    @Test
    public void testCalculerMoyenneMatiereInexistante(){
        assertEquals(-1,etu1.calculerMoyenne("Mathematique"));
    }
    //Test le calcul de la moyenne general d'un étudiant
    @Test
    public void testCalculerMoyenneGenerale(){
        assertEquals(15, etu1.calculerMoyenneGenerale());
    }

    //Test le calcul de la moyenne general d'un étudiant qui n'a pas de note
    @Test
    public void testCalculerMoyennerGeneraleEtudiantVide(){
        assertEquals(0, etu2.calculerMoyenneGenerale());
    }

    //Test le calcul de la moyenne general d'un étudiant qui n'a pas de note dans une matière
    @Test
    public void testCalculerMoyenneGeneraleUneMatiereVide(){
        Identite id3 = new Identite("toto", "tata", "e83033u");
        Formation f3 = new Formation(3);
        f3.ajoutMatiere(anglais, 1.0);
        f3.ajoutMatiere(algo, 1.0);

        Etudiant etu3 = new Etudiant(id3, f3);
        etu3.ajoutNote(anglais, 10);

        assertEquals(10, etu3.calculerMoyenneGenerale());
    }
}