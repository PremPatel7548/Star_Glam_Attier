
import Beans.UserBeanLocal;
import Entitys.UserCartTb;
import Entitys.UserTb;
import RestFullClient.RestClient;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import loginBean.LoginBean;
import java.nio.file.Path;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

@Named(value = "userCartBean")
@SessionScoped
public class UserCartBean implements Serializable {

    @EJB
    UserBeanLocal ub;
    RestClient rc;
    Collection<UserCartTb> carts;
    GenericType<Collection<UserCartTb>> gc;
    GenericType<UserTb> gut;
    GenericType<Integer> gi;
    Response res;
    @Inject
    LoginBean lb;
    Integer cartProductCount;
    Integer totalPrice;
    UserCartTb uc = new UserCartTb();
    UserTb ut = new UserTb();

    public UserCartBean() {
        rc = new RestClient();
        carts = new ArrayList<>();
        gc = new GenericType<Collection<UserCartTb>>() {
        };
        gi = new GenericType<Integer>() {
        };
        gut = new GenericType<UserTb>() {
        };
    }

    public Collection<UserCartTb> getCarts() {
        res = rc.getCartProducts(Response.class, String.valueOf(lb.getuId()));
        carts = res.readEntity(gc);
        return carts;
    }

    public Integer getCartProductCount() {
        cartProductCount = ub.countOfCartProduct(lb.getuId());
        return cartProductCount;
    }

    public void setCartProductCount(Integer cartProductCount) {
        this.cartProductCount = cartProductCount;
    }

    public UserCartTb getUc() {
        return uc;
    }

    public void setUc(UserCartTb uc) {
        this.uc = uc;
    }

    public Integer getTotalPrice() {
        totalPrice = 0;
        for (UserCartTb c : getCarts()) {
            totalPrice += c.getTotal();
        }
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCarts(Collection<UserCartTb> carts) {
        this.carts = carts;
    }

    public UserTb getUt() {
        res = rc.getUserById(Response.class, String.valueOf(lb.getuId()));
        ut = res.readEntity(gut);
        return ut;
    }

    public void setUt(UserTb ut) {
        this.ut = ut;
    }

    public String removeCart(Integer cid) {
        rc.removefromCart(String.valueOf(cid));
        // Recalculate totals after removal
        getCarts();
        return "Shoppingcart";
    }

    public void addQuantity(Integer cid) {
        System.out.println("Quantity ++");
        rc.increasecartProductQuantity(String.valueOf(cid));
        // Recalculate totals after quantity change
        getCarts();
    }

    public void decreaseQuantity(Integer cid) {
        System.err.println("Quantity --");
        rc.decreasecartProductQuantity(String.valueOf(cid));
        // Recalculate totals after quantity change
        getCarts();
    }

    public String processPayment(String mode) throws IOException {
        for (UserCartTb c : carts) {
            res = rc.addOrder(Response.class, String.valueOf(lb.getuId()), String.valueOf(c.getProductId().getId()), c.getSize(), String.valueOf(c.getQty()), String.valueOf(c.getPrice()));
            Integer orderId = res.readEntity(Integer.class);
            rc.addPayment(String.valueOf(lb.getuId()), String.valueOf(orderId), mode);
        }

        String home = System.getProperty("user.home");
        Path downloadsDir = Paths.get(home, "Downloads");
        if (!Files.exists(downloadsDir)) {
            Files.createDirectories(downloadsDir);
        }
        Path pdfPath = downloadsDir.resolve("order_details.pdf");

        PdfWriter writer = new PdfWriter(pdfPath.toString());
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Integer totalAmt = 0;

        // Add title with background color and padding
        Paragraph title = new Paragraph("Order Details")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.WHITE)
                .setBackgroundColor(ColorConstants.BLACK)
                .setPadding(10)
                .setMarginBottom(20);
        document.add(title);

        // Add date with a smaller font size and a different color
        Paragraph date = new Paragraph("Date: " + LocalDate.now().toString())
                .setFontSize(12)
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontColor(ColorConstants.GRAY)
                .setMarginBottom(10);
        document.add(date);

        // Define a table with 4 columns
        float[] columnWidths = {4, 1, 2, 2};
        Table table = new Table(columnWidths);
        table.setWidth(UnitValue.createPercentValue(100));

        // Add table header with bold text and background color
        table.addHeaderCell(new Paragraph("Product Name").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Paragraph("Quantity").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Paragraph("Price").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));
        table.addHeaderCell(new Paragraph("Total Price").setBold().setBackgroundColor(ColorConstants.LIGHT_GRAY));

        // Add content to the table with cell borders and padding
        for (UserCartTb c : carts) {
            table.addCell(new Paragraph(c.getProductId().getName()).setPadding(5));
            table.addCell(new Paragraph(String.valueOf(c.getQty())).setPadding(5));
            table.addCell(new Paragraph(String.valueOf(c.getPrice())).setPadding(5));
            table.addCell(new Paragraph(String.valueOf(c.getQty() * c.getPrice())).setPadding(5));

            totalAmt += c.getQty() * c.getPrice();
        }

        // Add table to the document
        document.add(table);

        // Add total amount with bold text and larger font size
        Paragraph total = new Paragraph("Total Amount: " + totalAmt + "/-")
                .setFontSize(14)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT)
                .setMarginTop(20);
        document.add(total);

        // Add order status with italic text and color
        Paragraph status1 = new Paragraph("Order Verified: Pending")
                .setFontSize(10)
                .setItalic()
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontColor(ColorConstants.ORANGE)
                .setMarginTop(5);
        document.add(status1);

        document.close();

        for (UserCartTb c : carts) {
            rc.removefromCart(String.valueOf(c.getId()));
        }
        // Send email after processing payment
        sendOrderConfirmationEmail(getUt(), pdfPath);
        return "Products"; // Navigate to the products page after processing payment
    }

     private void sendOrderConfirmationEmail(UserTb user, Path pdfPath) {
        String toEmail = user.getEmail();
        String subject = "Order Confirmation";

        // HTML content of the email
      String emailBody = "<html><body>"
        + "<div style='width: 100%; display: flex; justify-content: center;'>"
        + "<div style='background-color: #ffffff; width: 350px; border: 1px solid #dcdcdc;'>"
        + "<div style='background-color: #142850; height:40px;'>"
        + "<h2 style='color: #ffffff; text-align: center; margin: 0; padding: 5px 0;'>Star Glam Attire</h2>"
        + "</div>"
        + "<div style='padding: 20px;'>"
        + "<h2 style='color: #212a47;'>Dear " + user.getName() + ",</h2>"
        + "<p style='font-size: 14px; color: #6c757d;'>Your order has been placed successfully.</p>"
        + "<p style='font-size: 14px; color: #6c757d;'>Thank you for shopping with us!</p>"
        + "<p style='font-size: 14px; color: #6c757d;'>Please find the order details attached.</p>"
        + "<br/>"
        + "<p style='font-size: 14px; color: #6c757d;'>"
        + "Thank you once again for your order. If you have any questions or concerns, feel free to contact us.</p>"
        + "<p style='font-size: 14px; color: #6c757d;'>"
        + "Best regards,<br/></p>"
        + "<p style='font-size: 14px; color: #212a47;'>The Star Glam Attire Team</p>"
        + "</div>"
        + "<div style='background-color: #142850; height:40px; text-align: center;'>"
        + "</div>"
        + "</div>"
        + "</div>"
        + "</body></html>";


        // If using Gmail for SMTP
        final String SMTP_HOST = "smtp.gmail.com";
        final String SMTP_PORT = "587";
        final String SMTP_USERNAME = "";
        final String SMTP_PASSWORD = "";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);

            // Create a multipart message for combining text and attachment
            Multipart multipart = new MimeMultipart();

            // Create a body part for the HTML content
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(emailBody, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlPart);

            // Create a body part for the PDF attachment
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(pdfPath.toFile());
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(pdfPath.getFileName().toString());
            multipart.addBodyPart(attachmentBodyPart);

            // Set the content of the message to the multipart message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
