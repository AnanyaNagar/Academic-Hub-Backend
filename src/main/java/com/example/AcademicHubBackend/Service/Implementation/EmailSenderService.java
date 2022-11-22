package com.example.AcademicHubBackend.Service.Implementation;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import com.example.AcademicHubBackend.Service.AdminStduentInfoService;


@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

//    @Autowired
//    private UserRepo userRepo;

    @Autowired
    private AdminStudentInfoRepo adminStudentInfoRepo;


    public void sendEmail (String toEmail,String password , String studentId, String subject) {
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom("muniversity055@gmail.com");
            mimeMessageHelper.setTo(toEmail);
//            int length=cartItems.size();
            Date date =new Date();
//            //   body.setContent(getContentFromTemplate(body.getModel()));
//            // mimeMessageHelper.setText(body.getContent(), true);
            StringBuffer sb = new StringBuffer("<html><body>");
            sb.append("<h1>Your Student profile is generated</h1>\n" +
                    "<h3>Your student Id is "+ studentId+" </h3>\n" +
                    "<p>Details are:</p>\n");
            sb.append("<table style=\"border-color:white\">\n" +
                    "    <tr>\n" +
                    "<th style=\"margin:10px;padding:5px\">UserId</th>" +
                    "<th style=\"margin:10px;padding:5px\">Password</th>" +
                    "<th style=\"margin:10px;padding:5px\">Link :</th>" +
                    "      \n" +
                    "    </tr>");

            sb.append("<tr>\n" +
                        "        <td style=\"margin:10px;padding:5px\">");
            sb.append(toEmail+"\n");
            sb.append("</td><td style=\"margin:10px;padding:5px\">");
            sb.append(password);
            sb.append("</td><td style=\"margin:10px;pdding:5px\">");
//            sb.append(link);
//            sb.append("</td></tr>");



//            sb.append("</table><br>");
//            sb.append("<strong>Total Price = </strong><strong>Rs."+totalPrice+"</strong>");
//            sb.append("<hr style=\"width:30%;align:center;border:1 px solid black\">\n" +
//                    "<footer>\n" +
//                    "    <p style=\"color:grey\"><i>From <br> HealFitNest Team</i></p>\n" +
//                    "\n" +
//                    "</footer>");
//            sb.append("</body></html>");
            mimeMessageHelper.setText("Plain message", sb.toString() );


            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("MAil sent successfully");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }




}
