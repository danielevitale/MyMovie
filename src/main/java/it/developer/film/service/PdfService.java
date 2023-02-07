package it.developer.film.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import it.developer.film.entity.Movie;

import it.developer.film.payload.response.WorkerMovieResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Service
public class PdfService {

    @Value("${movie.path}")
    private String pathImage;

    public InputStream createPdfFromPost(Movie m, List<WorkerMovieResponse> w) throws IOException {

        String title = m.getTitle().toUpperCase();
        String plot = m.getPlot();
        String productionYear = String.valueOf(m.getProductionYear().getYear()); // (yyyy-MM-dd)
        String image = pathImage.concat(m.getPoster());
        ImageData data = ImageDataFactory.create(image);
        Image img = new Image(data);
        String nationality = m.getNationality().getNationalityName();

        //String duration = "Durata: ".concat(String.valueOf(m.getDuration()));
        String duration = String.valueOf(m.getDuration());

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // GENERAZIONE PDF
        // creazione pdf vuoto e impostazioni di pagina
        PdfDocument pdf = new PdfDocument(new PdfWriter(out));
        Document document = new Document(pdf, PageSize.A4); // Il formato A4 corrisponde a (595.0F, 842.0F).
        // the 595.0F - the x coordinate of lower left point
        // the 842.0F - the y coordinate of lower left point

        // title, image, content, updatedAt (yyyy-MM-dd), author (username)
        Text text = new Text(title);
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLDOBLIQUE);

        //title
        Paragraph paraTitle = new Paragraph().setTextAlignment(TextAlignment.CENTER).setMarginBottom(20);
        paraTitle.add(text.setBold().setFontColor(ColorConstants.BLUE).setFont(font).setFontSize(20));

        //image
        img.setHeight(200).setMarginBottom(20);
        img.setHorizontalAlignment(HorizontalAlignment.CENTER);

        //body
        Paragraph paraPlot = new Paragraph(plot).setBorder(new SolidBorder(1)).setPadding(10).setMarginBottom(10);
        Paragraph paraDuration = new Paragraph (duration);
        Paragraph paraProduYear = new Paragraph (productionYear);
        Paragraph paraNat = new Paragraph(nationality);

        //table section
        Table table = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth().setMarginBottom(20);
        Table table1 = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();
        Table table2 = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth();

        //develop
        document.add(paraTitle);
        document.add(img);
        document.add(paraPlot);

        table.addCell(paraDuration).addHeaderCell("DURATA");
        table.addCell(paraProduYear).addHeaderCell("ANNO DI PRODUZIONE");
        table.addCell(paraNat).addHeaderCell("PRODOTTO IN:");

        table1.addCell("NOME").setFontColor(ColorConstants.MAGENTA);
        table1.addCell("COGNOME").setFontColor(ColorConstants.MAGENTA);
        table1.addCell("RUOLO").setFontColor(ColorConstants.MAGENTA);

        for (WorkerMovieResponse value: w) {
            table2.addCell(value.getFirstName()).setFontColor(ColorConstants.BLACK);
            table2.addCell(value.getLastName()).setFontColor(ColorConstants.BLACK);
            table2.addCell(value.getRole().toString()).setFontColor(ColorConstants.BLACK);
        }


        document.add(table);
        document.add(table1);
        document.add(table2);

        // CHIUSURA DOCUMENTO
        document.close();

        InputStream in = new ByteArrayInputStream(out.toByteArray());

        return in;
    }
}
