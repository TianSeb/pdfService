package com.yoiki.pdfservice.generator;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;

@Service
public class PDFService {

    public ByteArrayInputStream generatePDF() {
        try {
            // Load the HTML template
            File htmlFile = ResourceUtils.getFile("classpath:templates/sample.html");
            String htmlContent = new String(Files.readAllBytes(htmlFile.toPath()));

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(os);
            builder.run();

            return new ByteArrayInputStream(os.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
