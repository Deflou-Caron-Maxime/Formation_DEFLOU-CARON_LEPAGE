import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFormation {
    // Initialisation des attributs
    private Formation formation = new Formation(1);
    private Formation formation2 = new Formation(1);
    private Formation formation3 = new Formation(2);
    private Formation formation4 = new Formation(2);


    @BeforeEach
    public void setUp(){
        // Ajout des matieres dans formation
        formation.ajoutMatiere("Cryptographie", 16.0);
        formation.ajoutMatiere("Qdev", 1.0);
        formation.ajoutMatiere("Algo", 1.0);
        formation.ajoutMatiere("Reseaux", 1.0);
    }

    @Test
    public void testAjoutMatiereInexistante(){
        // Les ajouts sont realises dans le setup
        // Verification
        assertTrue(formation.getMatieres().contains("Cryptographie"));
        assertTrue(formation.getMatieres().contains("Qdev"));
        assertTrue(formation.getMatieres().contains("Algo"));
        assertTrue(formation.getMatieres().contains("Reseaux"));
    }

    @Test
    public void testAjoutMatiereExistante(){
        // Ajout de la meme matiere Reseaux mais avec un coef different
        formation.ajoutMatiere("Reseaux", 2.0);
        // Cela change la valeur (géré par le put)
        // Verification
        assertEquals(2.0, formation.getCoeff("Reseaux"));
    }

    @Test
    public void supprimerMatiereExistante(){
        // Suppression de la matiere Reseaux
        formation.supprimerMatiere("Reseaux");

        // Verification
        assertFalse(formation.getMatieres().contains("Reseaux"));
    }

    @Test
    public void supprimerMatiereInexistante(){
        // Suppression d'une matiere inexistante dans la liste des matieres
        formation.supprimerMatiere("Anglais");
        // Verification
        assertFalse(formation.getMatieres().contains("Anglais"));
    }


    @Test
    public void testEquals(){
        // Ajout des memes matieres dans formation2
        formation2.ajoutMatiere("Cryptographie", 16.0);
        formation2.ajoutMatiere("Qdev", 1.0);
        formation2.ajoutMatiere("Algo", 1.0);
        formation2.ajoutMatiere("Reseaux", 1.0);

        // Ajout des memes matieres dans formation3 qui comporte un identifiant different
        formation3.ajoutMatiere("Cryptographie", 16.0);
        formation3.ajoutMatiere("Qdev", 1.0);
        formation3.ajoutMatiere("Algo", 1.0);
        formation3.ajoutMatiere("Reseaux", 1.0);

        // Ajout de matieres differentes dans formation4
        formation4.ajoutMatiere("Computer Science", 16.0);
        formation4.ajoutMatiere("Qdev", 5.0);
        formation4.ajoutMatiere("Faux", 2.4);
        formation4.ajoutMatiere("Banane", 1.0);

        // Verification
        assertTrue(formation.equals(formation2));
        assertFalse(formation.equals(formation3));
        assertFalse(formation.equals(formation4));
    }


    @Test
    public void testException(){
        // On initialise le verificateur
        boolean except = false;
        try {
            // On teste le getter avec une matiere inexistante
            formation.getCoeff("inexistant");
        } catch (MatiereException e){
            System.out.println(e.getMessage());
            // On change la valeur pour dire que l'exception est bien catchee
            except = true;
        }
        // Verification
        assertTrue(except);
    }
}