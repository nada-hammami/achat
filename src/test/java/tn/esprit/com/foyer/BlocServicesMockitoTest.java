package tn.esprit.com.foyer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.Foyer;
import tn.esprit.com.foyer.entities.TypeChambre;
import tn.esprit.com.foyer.repositories.BlocRepository;
import tn.esprit.com.foyer.repositories.ChambreRepository;
import tn.esprit.com.foyer.repositories.FoyerRepository;
import tn.esprit.com.foyer.services.BlocServices;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BlocServicesMockitoTest {

    @Mock
    private BlocRepository blocRepository;

    @Mock
    private FoyerRepository foyerRepository;

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private BlocServices blocServices;

    @Test
    public void testObtenirInfos() {
        // ajout du foyer
        Foyer foyer = Foyer.builder()
                .nomFoyer("FoyerTest1")
                .capaciteFoyer(100L)
                .build();

        // ajout du bloc
        Bloc bloc = Bloc.builder()
                .nomBloc("BlocTest1")
                .capaciteBloc(50L)
                .foyer(foyer)
                .build();

        Chambre chambre1 = Chambre.builder()
                .numeroChambre(701L)
                .typeC(TypeChambre.DOUBLE)
                .build();

        Chambre chambre2 = Chambre.builder()
                .numeroChambre(702L)
                .typeC(TypeChambre.SIMPLE)
                .build();

        List<Chambre> chambres = new ArrayList<>();
        chambres.add(chambre1);
        chambres.add(chambre2);

        bloc.setChambre(chambres);

        when(foyerRepository.findAll()).thenReturn(Collections.singletonList(foyer));
        when(blocRepository.findByFoyer(foyer)).thenReturn(Collections.singletonList(bloc));
        when(chambreRepository.findByBloc(bloc)).thenReturn(chambres);

        // Call the method under test
        List<Map<String, Object>> actualFoyersInfos = blocServices.obtenirInfos();

        // Define expected structure of the returned list
        List<Map<String, Object>> expectedFoyersInfos = new ArrayList<>();
        Map<String, Object> expectedFoyerInfo = new HashMap<>();
        expectedFoyerInfo.put("nomFoyer", "FoyerTest1");
        expectedFoyerInfo.put("capaciteMaxFoyer", 100L);

        List<Map<String, Object>> expectedBlocsInfos = new ArrayList<>();
        Map<String, Object> expectedBlocInfo = new HashMap<>();
        expectedBlocInfo.put("nomBloc", "BlocTest1");
        expectedBlocInfo.put("capaciteMaxBloc", 50L);

        List<Map<String, Object>> expectedChambresInfos = new ArrayList<>();
        Map<String, Object> expectedChambreInfo1 = new HashMap<>();
        expectedChambreInfo1.put("numeroChambre", 701L);
        expectedChambreInfo1.put("capaciteChambre", 2);

        Map<String, Object> expectedChambreInfo2 = new HashMap<>();
        expectedChambreInfo2.put("numeroChambre", 702L);
        expectedChambreInfo2.put("capaciteChambre", 1);

        expectedChambresInfos.add(expectedChambreInfo1);
        expectedChambresInfos.add(expectedChambreInfo2);
        expectedBlocInfo.put("chambres", expectedChambresInfos);
        expectedBlocsInfos.add(expectedBlocInfo);
        expectedFoyerInfo.put("blocs", expectedBlocsInfos);
        expectedFoyersInfos.add(expectedFoyerInfo);

        // Assert that the actual result matches the expected structure

        Map<String, Object> actualFoyerInfo = actualFoyersInfos.get(0);
        assertEquals("FoyerTest1", actualFoyerInfo.get("nomFoyer"));
        assertEquals(100L, actualFoyerInfo.get("capaciteMaxFoyer"));

        List<Map<String, Object>> actualBlocsInfos = (List<Map<String, Object>>) actualFoyerInfo.get("blocs");
        assertEquals(1, actualBlocsInfos.size());

        Map<String, Object> actualBlocInfo = actualBlocsInfos.get(0);
        assertEquals("BlocTest1", actualBlocInfo.get("nomBloc"));
        assertEquals(50L, actualBlocInfo.get("capaciteMaxBloc"));

        List<Map<String, Object>> actualChambresInfos = (List<Map<String, Object>>) actualBlocInfo.get("chambres");
        assertEquals(2, actualChambresInfos.size());

        Map<String, Object> actualChambreInfo1 = actualChambresInfos.get(0);
        assertEquals(701L, actualChambreInfo1.get("numeroChambre"));
        assertEquals(2, actualChambreInfo1.get("capaciteChambre"));

        Map<String, Object> actualChambreInfo2 = actualChambresInfos.get(1);
        assertEquals(702L, actualChambreInfo2.get("numeroChambre"));
        assertEquals(1, actualChambreInfo2.get("capaciteChambre"));

        // Verify that repository methods were called
        verify(foyerRepository, times(1)).findAll();
        verify(blocRepository, times(1)).findByFoyer(foyer);
        verify(chambreRepository, times(1)).findByBloc(bloc);
        System.out.println("Actual Result:");
        System.out.println(actualFoyersInfos);
    }
}
