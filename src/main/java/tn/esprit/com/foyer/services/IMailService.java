package tn.esprit.com.foyer.services;

import tn.esprit.com.foyer.mailmodel.MailStructure;

public interface IMailService {
    public void sendMail(String mail , MailStructure mailStructure);
    public void sendMailToStudentWithValidReservation();
    public void sendMailToStudentWithValidReservation2(MailStructure mailStructure);
}
