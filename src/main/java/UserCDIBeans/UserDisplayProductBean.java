package UserCDIBeans;

import Beans.UserBeanLocal;
import Entitys.ProductTb;
import Entitys.WishlistTb;
import RestFullClient.RestClient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import loginBean.LoginBean;

@Named(value = "userDisplayProductBean")
@SessionScoped
public class UserDisplayProductBean implements Serializable {
    
    @EJB UserBeanLocal ul;
    private RestClient rc;
    Collection<WishlistTb> wishProducts;
    private Collection<ProductTb> products;
    private GenericType<Collection<ProductTb>> gp;
    private Response res;
    private GenericType<ProductTb> gpp;
    private ProductTb p;
    private String size;
    @Inject private LoginBean lb;
    private Integer qty;
    private String searchcat, searchval;
    
    // Pagination properties
    private int currentPage = 1;
    private int itemsPerPage = 16;
    private int totalPages;

    public UserDisplayProductBean() {
        rc = new RestClient();
        products = new ArrayList<>();
        gp = new GenericType<Collection<ProductTb>>() {};
        gpp = new GenericType<ProductTb>() {};
        p = new ProductTb();
        fetchAllProducts();
        wishProducts = new ArrayList<>();
    }

    public Collection<ProductTb> getProducts() {
        int fromIndex = (currentPage - 1) * itemsPerPage;
        int toIndex = Math.min(fromIndex + itemsPerPage, products.size());
        return new ArrayList<>(products).subList(fromIndex, toIndex);
    }

    public void setProducts(Collection<ProductTb> products) {
        this.products = products;
    }

    public ProductTb getP() {
        return p;
    }

    public void setP(ProductTb p) {
        this.p = p;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getSearchcat() {
        return searchcat;
    }

    public void setSearchcat(String searchcat) {
        this.searchcat = searchcat;
    }

    public String getSearchval() {
        return searchval;
    }

    public void setSearchval(String searchval) {
        this.searchval = searchval;
    }

    public Collection<WishlistTb> getWishProducts() {
        wishProducts = ul.getWishProductByUser(lb.getuId());
        return wishProducts;
    }

    public void setWishProducts(Collection<WishlistTb> wishProducts) {
        this.wishProducts = wishProducts;
    }

    public String getProductDetail(Integer pid) {
        System.out.println("Fetching Product Details for ID: " + pid);
        res = rc.getProductDetails(Response.class, String.valueOf(pid));
        this.p = res.readEntity(gpp);
        System.out.println("Product Name: " + p.getName());
        return "productDetail";
    }

    public String addToCart() {
        rc.addToCart(String.valueOf(lb.getuId()), String.valueOf(p.getId()), this.size, String.valueOf(this.qty), String.valueOf(p.getPrice()));
        return "Products";
    }

    public void searchProduct() {
        if (searchval == null || searchval.isEmpty()) {
            fetchAllProducts();
        } else {
            switch (this.searchcat) {
                case "product":
                    res = rc.getProductByName(Response.class, searchval);
                    break;
                case "movie":
                    res = rc.getProductByMovie(Response.class, searchval);
                    break;
                case "song":
                    res = rc.getProductBySong(Response.class, searchval);
                    break;
                case "celebrity":
                    res = rc.getProductByCelebrity(Response.class, searchval);
                    break;
                case "category":
                    res = rc.getProductByCategory(Response.class, searchval);
                    break;
                default:
                    fetchAllProducts();
                    return;
            }
            products = res.readEntity(gp);
            totalPages = (int) Math.ceil((double) products.size() / itemsPerPage);
        }
    }

    private void fetchAllProducts() {
        res = rc.getAllProduct(Response.class);
        products = res.readEntity(gp);
        totalPages = (int) Math.ceil((double) products.size() / itemsPerPage);
    }

    // Pagination methods
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

    public void goToPage(int page) {
        if (page >= 1 && page <= totalPages) {
            currentPage = page;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    // Method to check if a product is in the wishlist
    public boolean isProductInWishlist(int productId) {
        for (WishlistTb w : getWishProducts()) {
            if (w.getProductId().getId() == productId) {
                return true;
            }
        }
        return false;
    }
    
    public String removeWishlist(Integer pid)
    {
        System.out.println("Remove karo Wishlist mathi Product id = "+pid);
        ul.removeWishList(pid, lb.getuId());
        return "Products";
    }
    
    public String addWishlist(Integer pid)
    {
        ul.addWishList(pid, lb.getuId());
        return "Products";
    }
    
}
