package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entities.Bloc;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IBlocService {
    List<Bloc> retrieveAllBlocs();

    Bloc addBloc(Bloc b);

    Bloc updateBloc(Bloc b);

    Bloc retrieveBloc(Long idBloc);

    void removeBloc(Long idBloc);
    byte[] generatePdfForBloc(Bloc bloc) throws IOException;
    List<Map<String, Object>> obtenirInfosBlocsParFoyer();
    List<Map<String, Object>> obtenirInfos();

}
