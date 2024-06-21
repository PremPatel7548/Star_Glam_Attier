/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package RestFullClient;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:JakartaEE8Resource [rest]<br>
 * USAGE:
 * <pre>
 *        RestClient client = new RestClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class RestClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Star_Glam_Attier/resources";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest");
    }

    public <T> T getProductByCelebrity(Class<T> responseType, String celebrity) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProductByCelebrity/{0}", new Object[]{celebrity}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addMovieCelebrity(String mid, String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addMovieCelebrity/{0}/{1}", new Object[]{mid, cid})).request().post(null);
    }

    public void cancelOrder(String oid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("cancelOrder/{0}", new Object[]{oid})).request().delete();
    }

    public void updateCategory(String id, String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateCategory/{0}/{1}", new Object[]{id, name})).request().post(null);
    }

    public void editUser(String id, String name, String password, String email, String mobileno, String gender, String address, String image, String dob) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("editUser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{id, name, password, email, mobileno, gender, address, image, dob})).request().post(null);
    }

    public void updateDesigner(String id, String name, String password, String email, String mobileno, String gender, String image, String isapprove) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateDesigner/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{id, name, password, email, mobileno, gender, image, isapprove})).request().post(null);
    }

    public void removefromCart(String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removefromCart/{0}", new Object[]{cid})).request().delete();
    }

    public <T> T getProductByMovie(Class<T> responseType, String movie) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProductByMovie/{0}", new Object[]{movie}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateDesignerProducts(String id, String name, String price, String stock, String image, String cid, String mid, String cbid, String sid, String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateDesignerProducts/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{id, name, price, stock, image, cid, mid, cbid, sid, did})).request().post(null);
    }

    public <T> T getCartProducts(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCartProducts/{0}", new Object[]{uid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateMovieCategory(String id, String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateMovieCategory/{0}/{1}", new Object[]{id, name})).request().post(null);
    }

    public String path() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("p1");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void deleteDesigner(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteDesigner/{0}", new Object[]{id})).request().delete();
    }

    public <T> T getProductByCategory(Class<T> responseType, String category) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProductByCategory/{0}", new Object[]{category}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayCategorys(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayCategorys");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateSong(String id, String name, String mid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("UpdateSong/{0}/{1}/{2}", new Object[]{id, name, mid})).request().post(null);
    }

    public void updateCelebrity(String id, String name, String dob, String gender, String image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateCelebrity/{0}/{1}/{2}/{3}/{4}", new Object[]{id, name, dob, gender, image})).request().post(null);
    }

    public void afterRegister(String email) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("afterRegister/{0}", new Object[]{email})).request().post(null);
    }

    public void increasecartProductQuantity(String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("increasecartProductQuantity/{0}", new Object[]{cid})).request().post(null);
    }

    public void deleteMovieCelebrity(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteMovieCelebrity/{0}", new Object[]{id})).request().delete();
    }

    public void updateMovie(String id, String name, String date, String mcid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateMovie/{0}/{1}/{2}/{3}", new Object[]{id, name, date, mcid})).request().post(null);
    }

    public void registerUser(String name, String password, String email, String mobileno, String gender, String address, String image, String dob) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("registerUser/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{name, password, email, mobileno, gender, address, image, dob})).request().post(null);
    }

    public void deleteUser(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteUser/{0}", new Object[]{id})).request().delete();
    }

    public void updateMovieCelebrity(String id, String mid, String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateMovieCelebrity/{0}/{1}/{2}", new Object[]{id, mid, cid})).request().post(null);
    }

    public <T> T getAllProduct(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAllProducts");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getProductDetails(Class<T> responseType, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProductDetails/{0}", new Object[]{pid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addDesigner(String name, String password, String email, String mobileno, String gender, String image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addDesigner/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{name, password, email, mobileno, gender, image})).request().post(null);
    }

    public void deleteMovieDesigner(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteMovieDesigner/{0}", new Object[]{id})).request().delete();
    }

    public <T> T getUserById(Class<T> responseType, String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getUserById/{0}", new Object[]{id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displaySongs(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displaySongs");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayMovie(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayMovie");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void decreasecartProductQuantity(String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("decreasecartProductQuantity/{0}", new Object[]{cid})).request().post(null);
    }

    public void deleteSong(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteSong/{0}", new Object[]{id})).request().delete();
    }

    public void addCategory(String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addCategory/{0}", new Object[]{name})).request().post(null);
    }

    public <T> T displayMovieCategorys(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayMovieCategorys");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayMovieCelebrity(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayMovieCelebrity");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayMovieDesigner(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayMovieDesigner");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayCelebrity(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayCelebrity");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addMovie(String name, String date, String mcid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addMovie/{0}/{1}/{2}", new Object[]{name, date, mcid})).request().post(null);
    }

    public <T> T getProductByName(Class<T> responseType, String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProductByName/{0}", new Object[]{name}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addToCart(String uid, String pid, String size, String qty, String price) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addToCart/{0}/{1}/{2}/{3}/{4}", new Object[]{uid, pid, size, qty, price})).request().post(null);
    }

    public <T> T addOrder(Class<T> responseType, String uid, String pid, String size, String qty, String price) throws ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("addOrder/{0}/{1}/{2}/{3}/{4}", new Object[]{uid, pid, size, qty, price})).request().post(null, responseType);
    }

    public void addMovieCategory(String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addMovieCategory/{0}", new Object[]{name})).request().post(null);
    }

    public void addSong(String name, String mid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addSong/{0}/{1}", new Object[]{name, mid})).request().post(null);
    }

    public <T> T displayUsers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayUsers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateMovieDesigner(String id, String mid, String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateMovieDesigner/{0}/{1}/{2}", new Object[]{id, mid, did})).request().post(null);
    }

    public <T> T getProductByDesigner(Class<T> responseType, String did) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProductByDesigner/{0}", new Object[]{did}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getOrderHistory(Class<T> responseType, String uid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getOrderHistory/{0}", new Object[]{uid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getProductBySong(Class<T> responseType, String song) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getProductBySong/{0}", new Object[]{song}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayDesignersProducts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayDesignersProducts");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addCancelOrder(String uid, String pid, String size, String qty, String price, String total, String order) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addCancelOrder/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{uid, pid, size, qty, price, total, order})).request().post(null);
    }

    public void deleteMovie(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteMovie/{0}", new Object[]{id})).request().delete();
    }

    public void addCelebrity(String name, String dob, String gender, String image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addCelebrity/{0}/{1}/{2}/{3}", new Object[]{name, dob, gender, image})).request().post(null);
    }

    public void addMovieDesigner(String mid, String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addMovieDesigner/{0}/{1}", new Object[]{mid, did})).request().post(null);
    }

    public void deleteCategory(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteCategory/{0}", new Object[]{id})).request().delete();
    }

    public String path2() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("p2");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public void deleteMovieCategory(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteMovieCategory/{0}", new Object[]{id})).request().delete();
    }

    public void deleteDesignerProduct(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteDesignerProducts/{0}", new Object[]{id})).request().delete();
    }

    public void deleteCelebrity(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteCelebrity/{0}", new Object[]{id})).request().delete();
    }

    public <T> T displayDesigners(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayDesigners");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addDesignerProducts(String name, String price, String stock, String image, String cid, String mid, String cbid, String sid, String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addDesignerProducts/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{name, price, stock, image, cid, mid, cbid, sid, did})).request().post(null);
    }

    public void addPayment(String uid, String oid, String mode) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addPayment/{0}/{1}/{2}", new Object[]{uid, oid, mode})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
