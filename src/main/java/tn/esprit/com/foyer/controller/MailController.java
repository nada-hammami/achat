package tn.esprit.com.foyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.com.foyer.mailmodel.MailStructure;
import tn.esprit.com.foyer.services.IMailService;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200")

@RequestMapping("/mailer")
public class MailController {
    IMailService mailService;

    @PostMapping("/sendMail/{e-mail}")
    public String sendMail(@PathVariable("e-mail") String mail, @RequestBody MailStructure mailStructure){
        mailService.sendMail(mail,mailStructure);
        return "mail send with success !!";
    }

    @GetMapping("/sendMailToStudentWithValidReservation")
    public String sendMailToStudentWithValidReservation(){
        mailService.sendMailToStudentWithValidReservation();
        return "this student has a valid reservation";
    }

    @PostMapping ("/sendMailToStudentWithValidReservation2")
    public String sendMailToStudentWithValidReservation2(@RequestBody MailStructure mailStructure){
        mailService.sendMailToStudentWithValidReservation2(mailStructure);
        return "mail sent to student succeffully!!";
    }

}
