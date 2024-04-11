package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Chambre;
import tn.esprit.com.foyer.entities.Etudiant;
import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.repositories.ChambreRepository;
import tn.esprit.com.foyer.repositories.EtudiantRepository;
import tn.esprit.com.foyer.repositories.ReservationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Slf4j
@AllArgsConstructor
public class EtudiantService implements IEtudiantService{
    EtudiantRepository etudiantRepository;
    ReservationRepository reservationRepository;
    ReservationService reservationService;
    ChambreServices chambreServices;
ChambreRepository chambreRepository;
    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant addEtudiant(Etudiant e) {

        return etudiantRepository.save(e) ;
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        log.info("azedaze");
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(Long idEtudiant) {
        Optional<Etudiant> optionalEtudiant = etudiantRepository.findById(idEtudiant);
        return optionalEtudiant.orElse(null);
    }

    @Override
    public void removeEtudiant(Long idEtudiant) {
        etudiantRepository.deleteById(idEtudiant);
    }

    @Override
    public Etudiant affecterEtudiantAReservation(String nomEt, String prenomEt, Long idReservation) {
        Etudiant etudiant = etudiantRepository.findEtudiantByNomEtAndPrenomEt(nomEt, prenomEt);
        Reservation reservation = reservationService.retrieveReservation(idReservation);
        List<Reservation> reservations = new ArrayList<>();
        if (etudiant.getReservations() != null){
            reservations = etudiant.getReservations();
        }
        reservations.add(reservation);
        etudiant.setReservations(reservations);
        etudiantRepository.save(etudiant);
        return etudiant;
    }


    public void passerUneReservation2(long idEtudiant, Reservation res, Long numchambre) {
        Etudiant etudiant = etudiantRepository.findById(idEtudiant).orElse(null);
        if (!chambreServices.isChambreDisponible(numchambre, chambreRepository.findChambreByNumeroChambre(numchambre).getTypeC())) {
            // Gérer le cas où la chambre n'est pas disponible en lançant une exception
            throw new ChambreNonDisponibleException("La chambre n'est pas disponible pour une réservation de ce type.");
        }
        Reservation reservation = reservationService.addReservation( res);
        List<Reservation> reservations = new ArrayList<>();
        if (etudiant != null && etudiant.getReservations() != null) {
            reservations = etudiant.getReservations();
        }
        reservations.add(reservation);
        etudiant.setReservations(reservations);
        etudiantRepository.save(etudiant);

        Chambre chambre = chambreRepository.findChambreByNumeroChambre(numchambre);
        if (chambre != null) {
            Set<Reservation> chambreReservations = chambre.getReservations();
            chambreReservations.add(reservation);
            chambre.setReservations(chambreReservations);
            chambreRepository.save(chambre);
        }
    }
    public class ChambreNonDisponibleException extends RuntimeException {
        public ChambreNonDisponibleException(String message) {
            super(message);
        }
    }

    @Override
    public Etudiant findEtudiantwithemail(String email) {
        return etudiantRepository.findEtudiantByEmail(email);

    }


}
