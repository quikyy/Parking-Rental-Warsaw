package com.quikyy.Controller;

import com.quikyy.Order.Order;
import com.quikyy.Order.OrderDTO;
import com.quikyy.Order.OrderRepository;
import com.quikyy.UTILS.PDF.PDFGeneratorService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class PdfController {
    private final OrderRepository orderRepository;
    private final PDFGeneratorService pdfGeneratorService;

    private static final String pdfContent = "application/pdf";
    private static final String headerKey = "Content-Disposition";
    private static String headerValue = "attachment; filename=rezerwacja_";

    @GetMapping("/summary-reservation/pdf/{id}")
    public void generatePDFSummary(HttpServletResponse response, @PathVariable(value = "id") String referenceNumber){
        Order order = orderRepository.findOrderByReferenceNumberEquals(referenceNumber);

        response.setContentType(pdfContent);
        response.setHeader(headerKey, headerValue + order.getReferenceNumber() + ".pdf");

        try {
            pdfGeneratorService.exportPdf(response, order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/check-reservation/pdf/{id}")
    public void generatePDFCheck(HttpServletResponse response, @PathVariable(value = "id") String referenceNumber){
        Order order = orderRepository.findOrderByReferenceNumberEquals(referenceNumber);
        response.setContentType(pdfContent);
        response.setHeader(headerKey, headerValue + order.getReferenceNumber() + ".pdf");

        try {
            pdfGeneratorService.exportPdf(response, order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
