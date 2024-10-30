package com.erp.service;

import com.erp.dto.CheckoutDataDTO;
import com.erp.dto.CheckoutPaymentDTO;
import com.erp.dto.CheckoutValidityResultDTO;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PDFGenerator {

    @Autowired
    private OrderService orderService;

    public byte[] generatePDF(CheckoutPaymentDTO checkoutPayment, long tenantId, String token) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            // Fetch order details
            CheckoutValidityResultDTO pdfData = orderService.GetPdfData(checkoutPayment.getOrderId(), token);


            document.add(new Paragraph("Official Shop Invoice")
                    .setBold()
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Order Date: " + pdfData.getDate())
                    .setTextAlignment(TextAlignment.RIGHT).setMarginRight(20));

            document.add(new Paragraph("Checkout Date: " + LocalDate.now())
                    .setTextAlignment(TextAlignment.RIGHT).setMarginRight(20));

            // Common PDF content
            addCommonContent(document, pdfData);

            // Add additional information if required

            document.add(new Paragraph(String.format("Paid: %.2f", checkoutPayment.getReceptAmount()))
                    .setBold().setFontSize(14).setTextAlignment(TextAlignment.RIGHT).setMarginRight(20));
            document.add(new Paragraph(String.format("Due: %.2f", pdfData.getTotalPrice()-checkoutPayment.getReceptAmount()))
                    .setBold().setFontSize(14).setTextAlignment(TextAlignment.RIGHT).setMarginRight(20));

            document.add(new Paragraph("Thank you for your business!").setTextAlignment(TextAlignment.CENTER).setMarginTop(30));
            document.add(new Paragraph("Page 1").setTextAlignment(TextAlignment.CENTER).setMarginTop(10));
            document.close();
            String a=orderService.checkoutNow(checkoutPayment, tenantId,token);
            return baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public byte[] generatePDF(long orderId, String token, boolean includeAdditionalInfo) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            // Fetch order details
            CheckoutValidityResultDTO pdfData = orderService.GetPdfData(orderId, token);

            document.add(new Paragraph("Official Shop Invoice")
                    .setBold()
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Date: " + pdfData.getDate())
                    .setTextAlignment(TextAlignment.RIGHT).setMarginRight(20));

            // Common PDF content
            addCommonContent(document, pdfData);

            // Add additional information if required
            if (includeAdditionalInfo) {
                document.add(new Paragraph("Additional Line 1").setMarginTop(10).setBold());
                document.add(new Paragraph("Additional Line 2").setMarginTop(5).setItalic());
            }
            document.add(new Paragraph("Thank you for your business!").setTextAlignment(TextAlignment.CENTER).setMarginTop(30));
            document.add(new Paragraph("Page 1").setTextAlignment(TextAlignment.CENTER).setMarginTop(10));
            document.close();
            return baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addCommonContent(Document document, CheckoutValidityResultDTO pdfData) throws IOException {


        Table table = new Table(new float[]{4, 2, 2, 2});
        table.setWidth(500).setMarginTop(20).setMarginBottom(20).setBorder(null);

        // Add table headers
        addTableHeader(table);
        // Add product details
        addProductDetails(table, pdfData);

        document.add(table);
        document.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------------"));
        document.add(new Paragraph(String.format("Total: %.2f", pdfData.getTotalPrice()))
                .setBold().setFontSize(14).setTextAlignment(TextAlignment.RIGHT).setMarginRight(20));

    }

    private void addTableHeader(Table table) {
        table.addCell(new Cell().add(new Paragraph("Product Name").setBold())
                .setBackgroundColor(new DeviceRgb(230, 230, 230)).setBorder(null));
        table.addCell(new Cell().add(new Paragraph("Unit").setBold())
                .setBackgroundColor(new DeviceRgb(230, 230, 230)).setBorder(null));
        table.addCell(new Cell().add(new Paragraph("Quantity").setBold())
                .setBackgroundColor(new DeviceRgb(230, 230, 230)).setBorder(null));
        table.addCell(new Cell().add(new Paragraph("Price").setBold())
                .setBackgroundColor(new DeviceRgb(230, 230, 230)).setBorder(null));
    }

    private void addProductDetails(Table table, CheckoutValidityResultDTO pdfData) {
        for (CheckoutDataDTO item : pdfData.getDetails()) {
            table.addCell(new Cell().add(new Paragraph(item.getProductName())).setBorder(null));
            table.addCell(new Cell().add(new Paragraph(item.getUnitName())).setBorder(null));
            table.addCell(new Cell().add(new Paragraph(item.getQuantity() + " pcs")).setBorder(null));
            table.addCell(new Cell().add(new Paragraph(String.format("%.2f", item.getPrice())))
                    .setTextAlignment(TextAlignment.RIGHT).setBorder(null));
        }
    }
}

