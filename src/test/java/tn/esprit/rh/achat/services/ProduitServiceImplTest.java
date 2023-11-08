package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProduitServiceImplTest {

    @InjectMocks
    private ProduitServiceImpl produitService;

    @Mock
    private ProduitRepository produitRepository;

    @Mock
    private StockRepository stockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialiser les mocks

        // Définir le comportement attendu des mocks
        Produit produit = new Produit();
        produit.setIdProduit(1L);

        Mockito.when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void retrieveAllProduits() {
        // Définir le comportement attendu du mock
        Produit produit1 = new Produit();
        Produit produit2 = new Produit();
        List<Produit> produits = Arrays.asList(produit1, produit2);

        Mockito.when(produitRepository.findAll()).thenReturn(produits);

        // Appeler la méthode à tester
        List<Produit> retrievedProduits = produitService.retrieveAllProduits();

        // Vérifier le résultat
        assertNotNull(retrievedProduits);
        assertEquals(2, retrievedProduits.size());
    }



    @Test
    void deleteProduit() {
        // Appeler la méthode à tester
        produitService.deleteProduit(1L);

        // Vérifier que la méthode deleteById du repository a été appelée avec le bon argument
        Mockito.verify(produitRepository).deleteById(1L);
    }

    @Test
    void updateProduit() {
        Produit produit = new Produit();

        // Appeler la méthode à tester
        Produit updatedProduit = produitService.updateProduit(produit);

        // Vérifier que la méthode save du repository a été appelée
        Mockito.verify(produitRepository).save(produit);
    }

    @Test
    void retrieveProduit() {
        // Appeler la méthode à tester
        Produit retrievedProduit = produitService.retrieveProduit(1L);

        // Vérifier le résultat
        assertNotNull(retrievedProduit);
        assertEquals(1L, retrievedProduit.getIdProduit());
    }

    @Test
    void assignProduitToStock() {
        Stock stock = new Stock();
        stock.setIdStock(2L);

        // Appeler la méthode à tester
        produitService.assignProduitToStock(1L, 2L);

        // Vérifier que le produit a été associé au bon stock
        Mockito.verify(produitRepository).save(Mockito.any(Produit.class));
    }
}
