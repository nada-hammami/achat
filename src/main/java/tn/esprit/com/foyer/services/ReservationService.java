package tn.esprit.com.foyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.repositories.ReservationRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class ReservationService implements IReservationService{
    ReservationRepository reservationRepository;
    @Override
    public Reservation updateReservation(Reservation res) {

        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(long idReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(idReservation);
        return  optionalReservation.orElse(null);
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaire(Date anneeUniversitaire) {
        return reservationRepository.findReservationByAnneeUniversitaire(anneeUniversitaire);

    }

    @Override
    public Reservation addReservation(Reservation res) {
        return reservationRepository.save(res);

    }

    @Override
    public List<Reservation> retrieveReservations() {
        return reservationRepository.findAll();

    }

    @Override
    public double statistiques() {
        List<Reservation> reservations = reservationRepository.findAll();
        long nombreReservationsValides = reservations.stream()
                .filter(reservation -> reservation.getEstValid() != null && !Boolean.FALSE.equals(reservation.getEstValid()))                .count();
        return(nombreReservationsValides * 100.0) / reservations.size();

    }

    @Override
    public void validerReservation(long idReservation) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(idReservation);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setEstValid(true);
            reservationRepository.save(reservation);
        } else {
            log.info("Reservation not found with ID: {}", idReservation);
        }
    }




}
