
package tn.esprit.rh.achat;


        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.MockitoAnnotations;
        import tn.esprit.rh.achat.entities.Fournisseur;
        import tn.esprit.rh.achat.repositories.FournisseurRepository;
        import tn.esprit.rh.achat.services.FournisseurServiceImpl;

        import java.util.Arrays;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class Fournisseurtestimpl {

    @InjectMocks
    FournisseurServiceImpl FournisseurService;

    @Mock
    FournisseurRepository FournisseurRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // initialize mocks

        // Test data setup
        Fournisseur Fournisseur1 = new Fournisseur();

        Fournisseur1.setCode("Informatique");

        Fournisseur Fournisseur2 = new Fournisseur();

        Fournisseur2.setCode("Mathématiques");

        // Mock behavior for the repository
        when(FournisseurRepository.findAll()).thenReturn(Arrays.asList(Fournisseur1, Fournisseur2));
        when(FournisseurRepository.save(any(Fournisseur.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testRetrieveAllFournisseurs() {
        List<Fournisseur> Fournisseurs = FournisseurService.retrieveAllFournisseurs();

        assertEquals(2, Fournisseurs.size());
        assertTrue(Fournisseurs.stream().anyMatch(d -> d.getCode().equals("Informatique")));
        assertTrue(Fournisseurs.stream().anyMatch(d -> d.getCode().equals("Mathématiques")));
    }

    @Test
    public void testAddFournisseur() {
        Fournisseur newFournisseur = new Fournisseur();
        newFournisseur.setCode("Physics");

        Fournisseur savedFournisseur = FournisseurService.addFournisseur(newFournisseur);

        assertNotNull(savedFournisseur);
        assertEquals("Physics", savedFournisseur.getCode());

        // Verify that the save method was called once
        verify(FournisseurRepository, times(1)).save(newFournisseur);
    }
}