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
import java.util.Set;


@Service
public class PdfService {

    @Value("${movie.path}")
    private String pathImage;

    public InputStream createPdfFromPost(Movie m, List<WorkerMovieResponse> w, Set<String> g, Set<String> l) throws IOException {
        int glunghezza =g.size();
        int llunghezza =l.size();
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
        PdfFont fontTableTitle = PdfFontFactory.createFont(StandardFonts.TIMES_BOLDITALIC);

        //title
        Paragraph paraTitle = new Paragraph().
                setPadding(10).
                setHeight(50).
                setFixedPosition(200,746,337).
                setBackgroundColor(ColorConstants.LIGHT_GRAY);
        paraTitle.add(text.setBold().setFontColor(ColorConstants.BLUE).setFont(font).setFontSize(20));

        //image
        img.setHeight(210).setMarginBottom(20);
        img.setHorizontalAlignment(HorizontalAlignment.LEFT);

        //body
        Paragraph paraPlot = new Paragraph(plot).
                setPadding(10).
                setMarginBottom(10).
                setHeight(100).
                setFixedPosition(200,606,337).
                setBackgroundColor(ColorConstants.LIGHT_GRAY);
        Paragraph paraDuration = new Paragraph (duration);
        Paragraph paraProduYear = new Paragraph (productionYear);
        Paragraph paraNat = new Paragraph(nationality);



        //develop
        document.add(paraTitle);
        document.add(img);
        document.add(paraPlot);

        //TABLE 1 SECTION -------------------------------------------------------------------
        Table table1a = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth().
                setBackgroundColor(ColorConstants.LIGHT_GRAY).setFontColor(ColorConstants.BLUE).setFont(fontTableTitle);
        Table table1b = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth().
                setMarginBottom(20).setBackgroundColor(ColorConstants.LIGHT_GRAY);

        table1a.addCell("DURATA");
        table1a.addCell("ANNO DI PRODUZIONE");
        table1a.addCell("PRODOTTO IN:");
        table1b.addCell(paraDuration);
        table1b.addCell(paraProduYear);
        table1b.addCell(paraNat);

        // TABLE 2 SECTION -------------------------------------------------------------------
        Table table2a = new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth().
                setBackgroundColor(ColorConstants.LIGHT_GRAY).setFontColor(ColorConstants.BLUE).setFont(fontTableTitle);
        Table table2b= new Table(UnitValue.createPercentArray(3)).useAllAvailableWidth().
                setBackgroundColor(ColorConstants.LIGHT_GRAY).setMarginBottom(20);
        Table table2c= new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth().setBackgroundColor(ColorConstants.LIGHT_GRAY).
                setFontColor(ColorConstants.BLUE).setFont(fontTableTitle).setTextAlignment(TextAlignment.CENTER);

        table2c.addCell("PROFESSIONISTI");
        table2a.addCell("NOME");
        table2a.addCell("COGNOME");
        table2a.addCell("RUOLO");

        for (WorkerMovieResponse value: w) {
            table2b.addCell(value.getFirstName());
            table2b.addCell(value.getLastName());
            table2b.addCell(value.getRole().toString());
        }
        // TABLE 3 SECTION -------------------------------------------------------------------
        Table table3a = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth().setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setFontColor(ColorConstants.BLUE).setFont(fontTableTitle).setTextAlignment(TextAlignment.CENTER);
        Table table3b = new Table(UnitValue.createPercentArray(glunghezza)).useAllAvailableWidth().
                setBackgroundColor(ColorConstants.LIGHT_GRAY).setMarginBottom(20);
        table3a.addCell("RUOLO");
        for (String value: g) {
            table3b.addCell(value.toString());
        }
        // TABLE 4 SECTION -------------------------------------------------------------------
        Table table4a = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth().setBackgroundColor(ColorConstants.LIGHT_GRAY)
                .setFontColor(ColorConstants.BLUE).setFont(fontTableTitle).setTextAlignment(TextAlignment.CENTER);
        Table table4b = new Table(UnitValue.createPercentArray(llunghezza)).useAllAvailableWidth().
                setBackgroundColor(ColorConstants.LIGHT_GRAY).setMarginBottom(20);
        table4a.addCell("LINGUE");
        for (String value: l) {
            table4b.addCell(value.toString());
        }

        document.add(table1a);
        document.add(table1b);
        document.add(table2c);
        document.add(table2a);
        document.add(table2b);
        document.add(table3a);
        document.add(table3b);
        document.add(table4a);
        document.add(table4b);

        // CHIUSURA DOCUMENTO
        document.close();

        InputStream in = new ByteArrayInputStream(out.toByteArray());

        return in;
    }
    
}
