package tn.esprit.com.foyer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.Universite;
import tn.esprit.com.foyer.repositories.UniversiteRepository;

@ExtendWith(MockitoExtension.class)
public class UniversiteServiceTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @InjectMocks
    private UniversiteService universiteService;

    private Universite universite;

    @BeforeEach
    public void setUp() {
        universite = new Universite();
        universite.setIdUniversite(1L);
        universite.setNomUniversite("ESPRIT");
        universite.setAdresse("Tunis");
        universite.setFoyer(new Foyer()); // Assuming a default Foyer object
    }

    @Test
    public void testAddUniversite() {
        // Mock behavior of UniversiteRepository.save()
        when(universiteRepository.save(universite)).thenReturn(universite);

        // Call the method under test
        Universite savedUniversite = universiteService.addUniversite(universite);

        // Assertions
        assertThat(savedUniversite).isNotNull();
        assertThat(savedUniversite.getIdUniversite()).isEqualTo(1L);
        assertThat(savedUniversite.getNomUniversite()).isEqualTo("ESPRIT");
        assertThat(savedUniversite.getAdresse()).isEqualTo("Tunis");
        verify(universiteRepository).save(universite);
    }

    @Test
    public void testFindUniversiteById() {
        Long id = 1L;

        // Mock behavior of UniversiteRepository.findById()
        when(universiteRepository.findById(id)).thenReturn(Optional.of(universite));

        // Call the method under test
        Universite retrievedUniversite = universiteService.findUniversiteById(id);

        // Assertions
        assertThat(retrievedUniversite).isNotNull();
        assertThat(retrievedUniversite.getIdUniversite()).isEqualTo(1L);
        verify(universiteRepository).findById(id);
    }

    // You can add similar test methods for updateUniversite, deleteUniversite, etc., 
    // following the same pattern of mocking repository behavior and asserting results.
}
