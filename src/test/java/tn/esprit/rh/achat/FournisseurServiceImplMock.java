package tn.esprit.rh.achat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplMockTest {

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Mock
    private FournisseurRepository fournisseurRepository;

    @BeforeEach
    public void setup() {
        // Mock behavior for the repository
        Fournisseur fournisseur1 = new Fournisseur();
        Fournisseur fournisseur2 = new Fournisseur();

        Mockito.when(fournisseurRepository.findAll()).thenReturn(Arrays.asList(fournisseur1, fournisseur2));
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testRetrieveAllFournisseurs() {
        // Call the method you want to test
        List<Fournisseur> result = fournisseurService.retrieveAllFournisseurs();

        // Perform assertions to verify the behavior
        assertEquals(2, result.size());
    }

    @Test
    public void testAddFournisseur() {
        // Create a sample Fournisseur object
        Fournisseur fournisseur = new Fournisseur();
        DetailFournisseur df = new DetailFournisseur();
        df.setDateDebutCollaboration(new Date());
        fournisseur.setDetailFournisseur(df);

        // Call the method you want to test
        Fournisseur result = fournisseurService.addFournisseur(fournisseur);

        // Perform assertions to verify the behavior
        assertNotNull(result);
        assertEquals(df, result.getDetailFournisseur());
    }
}
