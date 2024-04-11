package tn.esprit.com.foyer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.com.foyer.entities.*;
import tn.esprit.com.foyer.services.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlocServicesTest {

    @Autowired
    private BlocServices blocServices;
    @Autowired
    private FoyerServices foyerServices;
    @Autowired
    private ChambreServices chambreServices;

    @Test
    public void testObtenirInfos() {
        // Create Foyer
        Foyer foyer = Foyer.builder().nomFoyer("FoyerTest2").capaciteFoyer(111L).build();
        Foyer savedFoyer = foyerServices.addFoyer(foyer);

        // Create Bloc and associate with Foyer
        Bloc bloc = Bloc.builder()
                .nomBloc("BlocTest2")
                .capaciteBloc(50L) // Set the capaciteBloc value here
                .foyer(savedFoyer)
                .build();
        Bloc savedBloc = blocServices.addBloc(bloc);

        // Create Chambres associated with the Bloc
        chambreServices.addChambre(Chambre.builder().numeroChambre(701L).typeC(TypeChambre.DOUBLE).bloc(savedBloc).build());
        chambreServices.addChambre(Chambre.builder().numeroChambre(702L).typeC(TypeChambre.SIMPLE).bloc(savedBloc).build());

        // Call the method under test
        List<Map<String, Object>> actualFoyersInfos = blocServices.obtenirInfos();

        // Define expected structure of the returned list
        List<Map<String, Object>> expectedFoyersInfos = new ArrayList<>();
        Map<String, Object> expectedFoyerInfo = new HashMap<>();
        expectedFoyerInfo.put("nomFoyer", "FoyerTest2");
        expectedFoyerInfo.put("capaciteMaxFoyer", 111L);

        List<Map<String, Object>> expectedBlocsInfos = new ArrayList<>();
        Map<String, Object> expectedBlocInfo = new HashMap<>();
        expectedBlocInfo.put("nomBloc", "BlocTest2");
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
        assertEquals("FoyerTest2", actualFoyerInfo.get("nomFoyer"));
        assertEquals(111L , actualFoyerInfo.get("capaciteMaxFoyer"));

        List<Map<String, Object>> actualBlocsInfos = (List<Map<String, Object>>) actualFoyerInfo.get("blocs");
        assertEquals(1, actualBlocsInfos.size());

        Map<String, Object> actualBlocInfo = actualBlocsInfos.get(0);
        assertEquals("BlocTest2", actualBlocInfo.get("nomBloc"));
        assertEquals(50L, actualBlocInfo.get("capaciteMaxBloc"));

        List<Map<String, Object>> actualChambresInfos = (List<Map<String, Object>>) actualBlocInfo.get("chambres");
        assertEquals(2, actualChambresInfos.size());

        Map<String, Object> actualChambreInfo1 = actualChambresInfos.get(0);
        assertEquals(701L, actualChambreInfo1.get("numeroChambre"));
        assertEquals(2, actualChambreInfo1.get("capaciteChambre"));

        Map<String, Object> actualChambreInfo2 = actualChambresInfos.get(1);
        assertEquals(702L, actualChambreInfo2.get("numeroChambre"));
        assertEquals(1, actualChambreInfo2.get("capaciteChambre"));

    }
}
