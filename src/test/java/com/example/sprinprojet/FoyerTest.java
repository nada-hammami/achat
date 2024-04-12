package com.example.sprinprojet;

import com.example.sprinprojet.entity.Bloc;
import com.example.sprinprojet.entity.Foyer;
import com.example.sprinprojet.entity.Universite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FoyerTest {

    @InjectMocks
    Foyer foyer;

    @Mock
    Universite universite;

    @Mock
    List<Bloc> blocs;

    @BeforeEach
    void setUp() {
        // Initialize the foyer object
        foyer = new Foyer();
        foyer.setIdFoyer(1L);
        foyer.setNomFoyer("Test Foyer");
        foyer.setCapaciteFoyer(100);
        foyer.setArchived(false);

        // Mocking behavior for associated objects
        when(universite.getNomUniversite()).thenReturn("Test University");

        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Test Bloc");
        List<Bloc> blocList = new ArrayList<>();
        blocList.add(bloc);
        when(blocs.size()).thenReturn(1);
    }

    @Test
    void testFoyerAttributes() {
        assertThat(foyer.getIdFoyer()).isEqualTo(1L);
        assertThat(foyer.getNomFoyer()).isEqualTo("Test Foyer");
        assertThat(foyer.getCapaciteFoyer()).isEqualTo(100);

    }

    @Test
    void testUniversiteAssociation() {
        foyer.setUniversite(universite);
        assertThat(foyer.getUniversite().getNomUniversite()).isEqualTo("Test University");
    }

    @Test
    void testBlocsAssociation() {
        foyer.setBlocs(blocs);
        assertThat(foyer.getBlocs().size()).isEqualTo(1);
    }
}
