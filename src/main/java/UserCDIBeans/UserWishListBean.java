package UserCDIBeans;

import Beans.UserBeanLocal;
import Entitys.ProductTb;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import loginBean.LoginBean;

/**
 *
 * @author Admin
 */
@Named(value = "userWishListBean")
@RequestScoped
public class UserWishListBean {

    @Inject LoginBean lb;
    @EJB UserBeanLocal ul;
    private Collection<ProductTb> wishProducts;
    
    private int currentPage = 1;
    private int pageSize = 12; // Number of products per page
    private int totalProducts;
    private int totalPages;

    /**
     * Creates a new instance of UserWishListBean
     */
    public UserWishListBean() {
        wishProducts = new ArrayList<>();
    }

    public Collection<ProductTb> getWishProducts() {
        int userId = lb.getuId();
        List<ProductTb> allProducts = new ArrayList<>(ul.getWishListProduct(userId));
        totalProducts = allProducts.size();
        totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        int start = (currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, totalProducts);

        wishProducts = allProducts.subList(start, end);
        return wishProducts;
    }

    public void setWishProducts(Collection<ProductTb> wishProducts) {
        this.wishProducts = wishProducts;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void nextPage() {
        if (currentPage < totalPages) {
            currentPage++;
        }
    }

    public void prevPage() {
        if (currentPage > 1) {
            currentPage--;
        }
    }
}
