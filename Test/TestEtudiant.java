import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.classfile.instruction.CharacterRange;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestEtudiant {
    private Identite identite = new Identite("1234", "LEPAGE", "Thomas");
    private Identite idd2 = new Identite("1244", "DEFLOU-CARON", "Maxime");
    private Formation formation = new Formation(1);
    private Formation formation2 = new Formation(2);
    public Etudiant etu1 = new Etudiant(identite, formation);
    public Etudiant etu2 = new Etudiant(idd2, formation2);

    String Qdev = "Qdev";
    String Algo = "Algo";
    String Reseaux = "Reseaux";
    String Cryptographie = "Cryptographie";
    String Anglais = "Anglais";

    @BeforeEach
    public void setUp(){
        formation.ajoutMatiere("Cryptographie", 1.0);
        formation.ajoutMatiere("Qdev", 1.0);
        formation.ajoutMatiere("Algo", 1.0);
        formation.ajoutMatiere("Reseaux", 1.0);



        etu1.ajoutNote(Qdev, 11.0);
        etu1.ajoutNote(Qdev, 17.0);
        etu1.ajoutNote(Algo, 15.0);
        etu1.ajoutNote(Algo, 18.0);
        etu1.ajoutNote(Reseaux, 13.0);
        etu1.ajoutNote(Reseaux, 16.0);
    }

    @Test
    public void testAjoutNote(){
        etu1.ajoutNote(Cryptographie, 16.0);
        assertEquals(16.0, etu1.getResultat(Cryptographie).getFirst());
    }

    @Test
    public void testAjoutNoteMatiereInexistante(){
        etu1.ajoutNote(Anglais, 20.0);
        assertEquals(20.0, etu1.getResultat(Anglais).getFirst());
    }

    @Test
    public void testGetResultat(){
        System.out.println(etu1.getResultat(Qdev));
    }

    @Test
    public void testGetResultatMatiereInexistante(){
        assertThrows(MatiereException.class, () -> {
            etu1.getResultat("Mathematique");
        });
    }

    @Test
    public void testCalculerMoyenne(){
        assertEquals(14, etu1.calculerMoyenne(Qdev));
    }

    @Test
    public void testCalculerMoyenneMatiereInexistante(){
        assertThrows(MatiereException.class, () ->{
            etu1.calculerMoyenne("Mathematique");
        });
    }

    @Test
    public void testCalculerMoyenneGenerale(){
        assertEquals(15, etu1.calculerMoyenneGenerale());
    }

    @Test
    public void testCalculerMoyennerGeneraleEtudiantVide(){
        assertEquals(0, etu2.calculerMoyenneGenerale());
    }