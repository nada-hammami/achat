package tn.esprit.rh.achat;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class produitserviceimp{

    @InjectMocks
    ProduitServiceImpl ProduitService;

    @Mock
    ProduitRepository ProduitRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // initialize mocks

        // Test data setup
        Produit Produit1 = new Produit();

        Produit1.setCodeProduit("Informatique");

        Produit Produit2 = new Produit();

        Produit2.setCodeProduit("Mathématiques");

        // Mock behavior for the repository
        when(ProduitRepository.findAll()).thenReturn(Arrays.asList(Produit1, Produit2));
        when(ProduitRepository.save(any(Produit.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testRetrieveAllProduits() {
        List<Produit> Produits = ProduitService.retrieveAllProduits();

        assertEquals(2, Produits.size());
        assertTrue(Produits.stream().anyMatch(d -> d.getCodeProduit().equals("Informatique")));
        assertTrue(Produits.stream().anyMatch(d -> d.getCodeProduit().equals("Mathématiques")));
    }

    @Test
    public void testAddProduit() {
        Produit newProduit = new Produit();
        newProduit.setCodeProduit("Physics");

        Produit savedProduit = ProduitService.addProduit(newProduit);

        assertNotNull(savedProduit);
        assertEquals("Physics", savedProduit.getCodeProduit());

        // Verify that the save method was called once
        verify(ProduitRepository, times(1)).save(newProduit);
    }
}