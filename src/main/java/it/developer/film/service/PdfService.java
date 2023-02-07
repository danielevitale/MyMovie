package it.developer.film.service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import it.developer.film.entity.Movie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


@Service
public class PdfService {

    @Value("${movie.path}")
    private String pathImage;

    public InputStream createPdfFromPost(Movie m) throws IOException {

        String title = m.getTitle().toUpperCase();
        String plot = m.getPlot();
        String productionYear = String.valueOf(m.getProductionYear().getYear()); // (yyyy-MM-dd)
        String image = pathImage.concat(m.getPoster());
        ImageData data = ImageDataFactory.create(image);
        Image img = new Image(data);

        String duration = "Durata: ".concat(String.valueOf(m.getDuration()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // GENERAZIONE PDF
        // creazione pdf vuoto e impostazioni di pagina
        PdfDocument pdf = new PdfDocument(new PdfWriter(out));
        Document document = new Document(pdf, PageSize.A4); // Il formato A4 corrisponde a (595.0F, 842.0F).
        // the 595.0F - the x coordinate of lower left point
        // the 842.0F - the y coordinate of lower left point

        // title, image, content, updatedAt (yyyy-MM-dd), author (username)
        // ...
        Text text = new Text(title);
        Paragraph paraTitle = new Paragraph().setTextAlignment(TextAlignment.CENTER).setMarginBottom(10);
        paraTitle.add(text.setBold().setFontColor(ColorConstants.RED));


        img.setHeight(400).setWidth(PageSize.A4.getWidth()/2).setMarginBottom(20);
        img.setMarginLeft((PageSize.A4.getWidth()/4)-document.getLeftMargin());

        Paragraph paraPlot = new Paragraph(plot).setBorder(new SolidBorder(1)).setPadding(10);;

        Paragraph paraDuration = new Paragraph (duration);
        Paragraph paraProduYear = new Paragraph (productionYear);



        document.add(paraTitle);
        document.add(img);
        document.add(paraPlot);
        document.add(paraDuration);
        document.add(paraProduYear);

        // CHIUSURA DOCUMENTO
        document.close();

        InputStream in = new ByteArrayInputStream(out.toByteArray());

        return in;
    }
}
