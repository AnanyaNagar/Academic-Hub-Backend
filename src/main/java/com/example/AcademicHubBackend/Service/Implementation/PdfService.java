package com.example.AcademicHubBackend.Service.Implementation;

import com.example.AcademicHubBackend.model.AdminStudentInfo;
import com.example.AcademicHubBackend.model.ProjectDescriptionModel;
import com.example.AcademicHubBackend.repository.AdminStudentInfoRepo;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;



@Service
public class PdfService {

    @Autowired
    AdminStudentInfoRepo adminStudentInfoRepo;

    private Logger logger = LoggerFactory.getLogger(PdfService.class);
    public ByteArrayInputStream createPdf(String enrollment){
        logger.info("Downloading Resume");

        AdminStudentInfo studentInfo = adminStudentInfoRepo.findById(enrollment).get();

        String title = studentInfo.getFirstName() + " " + studentInfo.getLastName();


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, out);

        document.open();

        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 22);
        Font headingFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 16);
        Font contentFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14);

        //title
        Paragraph titlePara = new Paragraph(title, titleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        document.add(titlePara);

        // student details
        Paragraph heading = new Paragraph("About", headingFont);
        Paragraph content = new Paragraph("", contentFont);
        content.setIndentationLeft(10);
        content.add("Branch:" + "\t\t" + studentInfo.getBranch() + "\n");
        content.add("Email:" + "\t\t" + studentInfo.getEmail() + "\n");
        content.add("Year:" + "\t\t" + studentInfo.getYear().toString() + "\n");
        content.add("Department:" + "\t\t" + studentInfo.getDepartment() + "\n\n\n");
        document.add(heading);
        document.add(content);

        //skills
        heading = new Paragraph("Skills", headingFont);
        content = new Paragraph(studentInfo.getSkills() + "\n\n", contentFont);
        content.setIndentationLeft(10);
        document.add(heading);
        document.add(content);

        //Projects
        heading = new Paragraph("Projects", headingFont);
        content = new Paragraph("", contentFont);
        content.setIndentationLeft(10);
        for (ProjectDescriptionModel project:studentInfo.getProjects()){
            content.add(project.getProjectName() + "\n");
            content.add(project.getProjectDescription() + "\n\n\n");
        }
        document.add(heading);
        document.add(content);

        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
