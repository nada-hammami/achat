package tn.esprit.com.foyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.Universite;
import tn.esprit.com.foyer.repositories.UniversteRepository;
import tn.esprit.com.foyer.services.UniversiteServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceTest {

    @Mock
    UniversteRepository universiteRepository;

    @InjectMocks
    UniversiteServices universiteService;

    private Universite universite;

    @BeforeEach
    void setUp() {
        universite = new Universite();
        universite.setIdUniversite(1L);
        universite.setNomUniversite("Test University");
        universite.setAdresse("Test Address");
        // Assuming Foyer entity is already mocked
        Foyer foyer = new Foyer();
        universite.setFoyer(foyer);
    }

    @Test
    void retrieveUniversiteTest() {
        Long universiteId = 1L;
        // Mock the behavior of universiteRepository.findById()
        when(universiteRepository.findById(universiteId)).thenReturn(Optional.of(universite));

        // Call the method under test
        Universite retrievedUniversite = universiteService.retrieveUniversity(universiteId);

        // Assertions
        assertThat(retrievedUniversite).isNotNull();
        verify(universiteRepository).findById(universiteId);
    }

    @Test
    void retrieveAllUniversitesTest() {
        // Create a list of universities for testing
        List<Universite> universites = new ArrayList<>();
        universites.add(universite);

        // Mock the behavior of universiteRepository.findAll()
        when(universiteRepository.findAll()).thenReturn(universites);

        // Call the method under test
        List<Universite> retrievedUniversites = universiteService.retrieveAllUniversities();

        // Assertions
        assertThat(retrievedUniversites).isNotNull();
        assertThat(retrievedUniversites).hasSize(1);
        verify(universiteRepository).findAll();
    }



}
