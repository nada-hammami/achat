package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class operateurserviceimpl {
    @InjectMocks
    OperateurServiceImpl operateurService;

    @Mock
    OperateurRepository operateurRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // initialize mocks

        // Test data setup
        Operateur operateu1 = new Operateur();

        operateu1.setNom("Bettaieb");

        Operateur operateur2 = new Operateur();

        operateur2.setNom("Nabill");

        // Mock behavior for the repository
        when(operateurRepository.findAll()).thenReturn(Arrays.asList(operateu1, operateur2));
        when(operateurRepository.save(any(Operateur.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testRetrieveAllOperateurs() {
        List<Operateur> Produits = operateurService.retrieveAllOperateurs();

        assertEquals(2, Produits.size());
        assertTrue(Produits.stream().anyMatch(d -> d.getNom().equals("Bettaieb")));
        assertTrue(Produits.stream().anyMatch(d -> d.getNom().equals("Nabill")));
    }

    @Test
    public void testAddOperateur() {
        Operateur op = new Operateur();
        op.setNom("BettaiebAdd");

        Operateur savedProduit = operateurService.addOperateur(op);

        assertNotNull(savedProduit);
        assertEquals("BettaiebAdd", savedProduit.getNom());

        // Verify that the save method was called once
        verify(operateurRepository, times(1)).save(op);
    }
}


