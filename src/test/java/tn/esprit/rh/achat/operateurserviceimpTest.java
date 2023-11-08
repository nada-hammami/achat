package tn.esprit.rh.achat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class operateurserviceimpTest {
    @Mock
    OperateurRepository operateurRepository;

    @Mock
    StockRepository stockRepository;


    @InjectMocks
    OperateurServiceImpl operateurService;

    private Operateur operateur;

    @BeforeEach
    void setUp() {
        operateur = new Operateur();
        operateur.setIdOperateur(1L);
        operateur.setNom("test Bettaieb");
        operateur.setPrenom("Nabil");

        // Initialize date properties


        Stock stock = new Stock();
        stock.setIdStock(2L);
        stock.setLibelleStock("test Stock Operateur");
        stock.setQte(100);
        stock.setQteMin(10);


    }



    @Test
    void retrieveAllOperateursTest() {
        // Create a list using ArrayList
        List<Operateur> operateursList = new ArrayList<>();
        operateursList.add(operateur);

        // Mock the behavior of operateurRepository.findAll()
        when(operateurRepository.findAll()).thenReturn(operateursList);

        // Call the method under test
        List<Operateur> retrievedOperateursList = operateurService.retrieveAllOperateurs();

        // Use assertThat(actual).hasSize(expected)
        assertThat(retrievedOperateursList).hasSize(1);
        verify(operateurRepository).findAll();
    }

    @Test
    void addOperateurTest() {
        // Mock the behavior of operateurRepository.save()
        when(operateurRepository.save(Mockito.any(Operateur.class))).thenReturn(operateur);

        // Call the method under test
        Operateur savedOperateur = operateurService.addOperateur(operateur);

        // Assertions
        assertThat(savedOperateur).isNotNull();
        verify(operateurRepository).save(Mockito.any(Operateur.class));
    }

    @Test
    void deleteOperateurTest() {
        Long operateurId = 1L;
        // Mock the behavior of operateurRepository.deleteById()
        doNothing().when(operateurRepository).deleteById(operateurId);

        // Call the method under test
        operateurService.deleteOperateur(operateurId);

        // Verify that deleteById was called once
        verify(operateurRepository, times(1)).deleteById(operateurId);
    }

    @Test
    void updateOperateurTest() {
        // Mock the behavior of operateurRepository.save()
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        // Modify some properties of the operateur
        operateur.setPrenom("Modif Operateur prenom");

        // Call the method under test
        Operateur updatedOperateur = operateurService.updateOperateur(operateur);

        // Assertions
        assertThat(updatedOperateur).isNotNull();
        assertThat(updatedOperateur.getPrenom()).isEqualTo("Modif Operateur prenom");
        verify(operateurRepository).save(operateur);
    }

    @Test
    void retrieveOperateurTest() {
        Long operateurId = 1L;
        // Mock the behavior of operateurRepository.findById()
        when(operateurRepository.findById(operateurId)).thenReturn(Optional.of(operateur));

        // Call the method under test
        Operateur retrievedOperateur = operateurService.retrieveOperateur(operateurId);

        // Assertions
        assertThat(retrievedOperateur).isNotNull();
        verify(operateurRepository).findById(operateurId);
    }




}

