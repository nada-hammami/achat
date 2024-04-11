package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.entities.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {
   public Reservation updateReservation (Reservation res);
    public Reservation retrieveReservation (long idReservation);

    public List<Reservation> getReservationParAnneeUniversitaire(Date anneeUniversitaire );

    public Reservation addReservation (Reservation res);
    public List<Reservation> retrieveReservations();

    public double statistiques();

    public void validerReservation(long idReservation);
}
