package com.quikyy.UTILS.PDF;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import com.quikyy.Order.Order;
import com.quikyy.Order.OrderDTO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PDFGeneratorService {

    public void exportPdf(HttpServletResponse response, Order order) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Paragraph paragraph = new Paragraph("Numer rezerwacji: " + order.getReferenceNumber() + "\n" +
                "Imie i nazwisko: " + order.getFirstName() + " " + order.getLastName() + "\n" +
                "Telefon: " + order.getTelNum() + "\n" +
                "E-mail: " + order.getEmailAddress() + "\n" +
                "Samochod: " + order.getCarMark() + "\n" +
                "Numer rejestracji: "+ order.getCarPlate() + "\n" +
                "Data rozpoczecia: " + order.getStartDate() + "\n" +
                "Data zakonczenia:" + order.getLastName() + "\n " +
                "Ilosc dni: " + order.getDays() + "\n" +
                "Cena: " + order.getPrice() + " PLN");
        document.add(paragraph);
        document.close();
    }
}
