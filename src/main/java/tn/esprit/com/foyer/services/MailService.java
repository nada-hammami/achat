package tn.esprit.com.foyer.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import tn.esprit.com.foyer.entities.Etudiant;
import tn.esprit.com.foyer.entities.Reservation;
import tn.esprit.com.foyer.mailmodel.MailStructure;
import tn.esprit.com.foyer.repositories.EtudiantRepository;

import java.util.List;


@Service
public class MailService implements IMailService {

    EtudiantRepository etudiantRepository;

    EtudiantService etudiantService;
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;
    @Override
    public void sendMail(String mail, MailStructure mailStructure) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail); //votreuniversite@gmail.com
        simpleMailMessage.setSubject(mailStructure.getSubject());
        simpleMailMessage.setText(mailStructure.getMessage());
        simpleMailMessage.setTo(mail);

        mailSender.send(simpleMailMessage);

    }

    @Override
    public void sendMailToStudentWithValidReservation() {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        for (Etudiant etudiant: etudiants){
            List<Reservation> reservations = etudiant.getReservations();
            for (Reservation reservation: reservations){
                if ( reservation.getEstValid()){
                     etudiant.getEmail();

                }
            }
        }

    }

    @Override
    public void sendMailToStudentWithValidReservation2(MailStructure mailStructure) {
        List<Etudiant> etudiants = etudiantService.retrieveAllEtudiants();

        for (Etudiant etudiant: etudiants){
            List<Reservation> reservations = etudiant.getReservations();
            for (Reservation reservation: reservations){
                if ( reservation.getEstValid()){
                    String gmail = etudiant.getEmail();
                    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setFrom(fromMail); //votreuniversite@gmail.com
                    simpleMailMessage.setSubject(mailStructure.getSubject());
                    simpleMailMessage.setText(mailStructure.getMessage());
                    simpleMailMessage.setTo(gmail);

                    mailSender.send(simpleMailMessage);

                }
            }
        }
    }


}
