package it.developer.film.service;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import it.developer.film.entity.Movie;
import it.developer.film.entity.Worker;
import it.developer.film.payload.response.WorkerMovieResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Service
public class WorkerPdfService {
    public InputStream createPdfFromWorker(Worker w) throws IOException {

        String firstName = w.getFirstName();
        String lastName = w.getLastName();
        LocalDate birthday = w.getBirthday();
        String nationality = w.getLocality().getLocalityId().getNationalityName().getNationalityName();
        String city = w.getLocality().getLocalityId().getCityName();

        //creazione font custom
        PdfFont font = null;
        final String GRAFITE = "src/main/resources/fonts/Happy Birthday.otf";
        try {
            font = PdfFontFactory.createFont(GRAFITE, PdfEncodings.WINANSI, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);
        } catch (Exception e) {
            System.out.println(e);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // GENERAZIONE PDF
        // creazione pdf vuoto e impostazioni di pagina
        PdfDocument pdf = new PdfDocument(new PdfWriter(out));
        Document document = new Document(pdf, PageSize.A4); // Il formato A4 corrisponde a (595.0F, 842.0F).
        // the 595.0F - the x coordinate of lower left point
        // the 842.0F - the y coordinate of lower left point

        // title, image, content, updatedAt (yyyy-MM-dd), author (username)
        Text text = new Text(firstName);
        Text text1 = new Text(lastName);
        Text text2 = new Text(nationality);

        //PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLDOBLIQUE);
        PdfFont fontTableTitle = PdfFontFactory.createFont(StandardFonts.TIMES_BOLDITALIC);
        Paragraph paraFirstName = new Paragraph(firstName).add(text);
        Paragraph paraLastName = new Paragraph(lastName).add(text1);
        Paragraph paraNat = new Paragraph(nationality).add(text2);

        //DEVELOPE
        document.add(paraFirstName);
        document.add(paraLastName);
        document.add(paraNat);

        // CHIUSURA DOCUMENTO
        document.close();

        InputStream in = new ByteArrayInputStream(out.toByteArray());

        return in;

    }
}
