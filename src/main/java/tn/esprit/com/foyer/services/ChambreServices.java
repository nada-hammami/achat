package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Bloc;
import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.TypeChambre;
import tn.esprit.com.foyer.entities.TypeChambrePourcentage;
import tn.esprit.com.foyer.repositories.BlocRepository;
import tn.esprit.com.foyer.repositories.ChambreRepository;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ChambreServices implements IChambreService{
    ChambreRepository chambreRepository;
    BlocRepository blocRepository;
    @Override
    public List<Chambre> retrieveAllChambre() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    public Chambre updateChambre(Chambre chambre) {
        Chambre existingChambre = chambreRepository.findById(chambre.getIdChambre()).orElse(null);
        if (existingChambre == null) {
            throw new RuntimeException("Chambre not found");
        }
        else{

        existingChambre.setNumeroChambre(chambre.getNumeroChambre());
        existingChambre.setTypeC(chambre.getTypeC());
        existingChambre.setBloc(chambre.getBloc());
        existingChambre.setReservations(chambre.getReservations());}
        return chambreRepository.save(existingChambre);
    }



    @Override
    public Chambre retrieveChambre(Long idChambre) {
        Optional<Chambre> optionalChambre = chambreRepository.findById(idChambre);
        return  optionalChambre.orElse(null);
    }

    @Override
    public void removeChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);
    }

    @Override
    public HashSet<TypeChambrePourcentage> calculerPourcentageChambreParTypeChambre1(boolean estValid) {
        Long totalChambres = chambreRepository.count();
        HashSet<TypeChambrePourcentage> resultSet = new HashSet<>();

        for (TypeChambre typeChambre : TypeChambre.values()) {
            Float total = chambreRepository.nbrChambreParType(typeChambre, estValid);
            float pourcentage = (total / totalChambres) * 100;

            TypeChambrePourcentage chambreInfo = new TypeChambrePourcentage(typeChambre, pourcentage);
            resultSet.add(chambreInfo);
        }

        return resultSet;
    }


    public void affecterChambresABloc(List<Long> numChambres, String nomBloc) {
        Bloc bloc = blocRepository.findByNomBloc(nomBloc);
        for (Long chambreNumber : numChambres) {
            log.info("Your message here: {}", chambreNumber);
            Chambre chambre = chambreRepository.findById(chambreNumber).orElse(null);
            if (chambre != null) {
                chambre.setBloc(bloc);
                chambreRepository.save(chambre);
            }
        }
    }

    void nbPlacesDisponibleParChambreAnneeEnCours() {
    log.info("nbPlacesDisponibleParChambreAnneeEnCours");
        chambreRepository.findAll().stream().forEach(

                chambre -> {
                    int max;
                    int nbReservation;
                   if (chambre.getTypeC().toString().equals("SIMPLE"))
                   {
                       max=1;
                   }
                   else if (chambre.getTypeC().toString().equals("DOUBLE"))
                   {
                       max=2;
                   }
                   else {
                       max =3;
                   }

                   nbReservation=chambre.getReservations().size();
                   int placeDispo = max-nbReservation;
                   log.info("nombre de places disponibles  en " + Year.now().getValue() +" dans la chambre numero " + chambre.getNumeroChambre() + "est : " + placeDispo);

                }
        );

    }

    public boolean isChambreDisponible(Long numeroChambre, TypeChambre typeC) {
        Chambre chambre = chambreRepository.findChambreByNumeroChambre(numeroChambre);
        if (chambre == null || chambre.getTypeC() != typeC) {
            return false; // La chambre n'existe pas ou le type ne correspond pas
        }

        int nombreMaxReservations = 0;
        switch (typeC) {
            case SIMPLE:
                nombreMaxReservations = 1;
                break;
            case DOUBLE:
                nombreMaxReservations = 2;
                break;
            case TRIPLE:
                nombreMaxReservations = 3;
                break;
        }

        return chambre.getReservations().size() < nombreMaxReservations;
    }

}