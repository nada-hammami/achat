package tn.esprit.rh.achat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class FactureServiceImplTest {




        @InjectMocks
        FactureServiceImpl factureService;

        @Mock
        FactureRepository factureRepository;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this); // initialize mocks

            // Test data setup
            Facture facture1 = new Facture();

            facture1.setMontantFacture(20);

            Facture facture2 = new Facture();

            facture2.setMontantRemise(40);

            // Mock behavior for the repository
            when(factureRepository.findAll()).thenReturn(Arrays.asList(facture1, facture2));
            when(factureRepository.save(any(Facture.class))).thenAnswer(invocation -> invocation.getArgument(0));
        }

        @Test
        public void testRetrieveAllFactures() {
            List<Facture> factures = factureService.retrieveAllFactures();

            assertEquals(2, factures.size());
            assertTrue(factures.stream().anyMatch(d -> d.getMontantFacture() == (20)));
            assertTrue(factures.stream().anyMatch(d -> d.getMontantRemise() == (40)));
        }


    }

