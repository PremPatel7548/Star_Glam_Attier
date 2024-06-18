
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import loginBean.LoginBean;

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
                .setBackgroundColor(ColorConstants.GREEN)
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
        Paragraph total = new Paragraph("Total Amount: $" + totalAmt)
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

        return "Products"; // Navigate to the products page after processing payment
    }
}
