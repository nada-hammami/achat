package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.*;
import tn.esprit.rh.achat.repositories.ProduitRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.ProduitServiceImpl;
import tn.esprit.rh.achat.repositories.ReglementRepository;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.services.ReglementServiceImpl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;




@ExtendWith(MockitoExtension.class)
class ReglementserviceimpTest  {
    @Mock
    ReglementRepository reglementRepository;

    @Mock
    FactureRepository factureRepository;


    @InjectMocks
    ReglementServiceImpl reglementService;

    private Reglement reglement;

    @BeforeEach
    void setUp() {
        reglement = new Reglement();
        reglement.setIdReglement(1L);
        reglement.setPayee(true);
        reglement.setMontantPaye(50);
        reglement.setMontantRestant(30);


        // Initialize date properties
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            reglement.setDateReglement(dateFormat.parse("2023-10-24"));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Facture facture = new Facture();
        facture.setIdFacture(2L);
        facture.setMontantFacture(50);
        facture.setMontantRemise(100);
        facture.setArchivee(false);

        try {
            facture.setDateCreationFacture(dateFormat.parse("2023-10-24"));
            facture.setDateDerniereModificationFacture(dateFormat.parse("2023-10-24"));

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }



    @Test
    void retrieveAllreglementTest() {
        // Create a list using ArrayList
        List<Reglement> reglementlist = new ArrayList<>();
        reglementlist.add(reglement);

        // Mock the behavior of produitRepository.findAll()
        when(reglementRepository.findAll()).thenReturn(reglementlist);

        // Call the method under test
        List<Reglement> retrievedProduitsList = reglementService.retrieveAllReglements();

        // Use assertThat(actual).hasSize(expected)
        assertThat(retrievedProduitsList).hasSize(1);
        verify(reglementRepository).findAll();
    }

    @Test
    void addProduitTest() {
        // Mock the behavior of produitRepository.save()
        when(reglementRepository.save(Mockito.any(Reglement.class))).thenReturn(reglement);

        // Call the method under test
        Reglement savedReglement = reglementService.addReglement(reglement);

        // Assertions
        assertThat(savedReglement).isNotNull();
        verify(reglementRepository).save(Mockito.any(Reglement.class));
    }





    @Test
    void retrieveReglementTest() {
        Long reglementId = 1L;
        // Mock the behavior of produitRepository.findById()
        when(reglementRepository.findById(reglementId)).thenReturn(Optional.of(reglement));

        // Call the method under test
        Reglement retrievedreglement = reglementService.retrieveReglement(reglementId);

        // Assertions
        assertThat(retrievedreglement).isNotNull();
        verify(reglementRepository).findById(reglementId);
    }



}
