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

    @GetMapping("/summary-reservation/pdf/{id}")
    public void generatePDF(HttpServletResponse response, @PathVariable(value = "id") String referenceNumber){
        Order order = orderRepository.findOrderByReferenceNumberEquals(referenceNumber);

        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=rezerwacja_" + order.getReferenceNumber() + ".pdf";
        response.setHeader(headerKey, headerValue);

        try {
            pdfGeneratorService.exportPdf(response, order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
