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
    private static final String BASE_URI = "http://localhost:8080/SGA/resources";

    public RestClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("rest");
    }

    public void addDesigner(String name, String password, String email, String mobileno, String gender, String image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addDesigner/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{name, password, email, mobileno, gender, image})).request().post(null);
    }

    public void deleteMovieDesigner(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteMovieDesigner/{0}", new Object[]{id})).request().delete();
    }

    public void addMovieCelebrity(String mid, String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addMovieCelebrity/{0}/{1}", new Object[]{mid, cid})).request().post(null);
    }

    public void updateCategory(String id, String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateCategory/{0}/{1}", new Object[]{id, name})).request().post(null);
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

    public void updateDesigner(String id, String name, String password, String email, String mobileno, String gender, String image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateDesigner/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{id, name, password, email, mobileno, gender, image})).request().post(null);
    }

    public void deleteSong(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteSong/{0}", new Object[]{id})).request().delete();
    }

    public void updateDesignerProducts(String name, String price, String stock, String size, String image, String cid, String mid, String cbid, String sid, String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateDesignerProducts/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{name, price, stock, size, image, cid, mid, cbid, sid, did})).request().post(null);
    }

    public void addCategory(String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addCategory/{0}", new Object[]{name})).request().post(null);
    }

    public <T> T displayMovieCategorys(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayMovieCategorys");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateMovieCategory(String id, String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateMovieCategory/{0}/{1}", new Object[]{id, name})).request().post(null);
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

    public void deleteDesigner(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteDesigner/{0}", new Object[]{id})).request().delete();
    }

    public <T> T displayCategorys(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayCategorys");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addMovie(String name, String date, String mcid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addMovie/{0}/{1}/{2}", new Object[]{name, date, mcid})).request().post(null);
    }

    public void addMovieCategory(String name) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addMovieCategory/{0}", new Object[]{name})).request().post(null);
    }

    public void addSong(String name, String mid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addSong/{0}/{1}", new Object[]{name, mid})).request().post(null);
    }

    public void updateMovieDesigner(String id, String mid, String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateMovieDesigner/{0}/{1}/{2}", new Object[]{id, mid, did})).request().post(null);
    }

    public void updateSong(String id, String name, String mid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("UpdateSong/{0}/{1}/{2}", new Object[]{id, name, mid})).request().post(null);
    }

    public <T> T displayDesignersProducts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayDesignersProducts");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateCelebrity(String id, String name, String dob, String gender, String image) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateCelebrity/{0}/{1}/{2}/{3}/{4}", new Object[]{id, name, dob, gender, image})).request().post(null);
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

    public void deleteMovieCelebrity(String id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteMovieCelebrity/{0}", new Object[]{id})).request().delete();
    }

    public void updateMovie(String id, String name, String date, String mcid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateMovie/{0}/{1}/{2}/{3}", new Object[]{id, name, date, mcid})).request().post(null);
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

    public void updateMovieCelebrity(String id, String mid, String cid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateMovieCelebrity/{0}/{1}/{2}", new Object[]{id, mid, cid})).request().post(null);
    }

    public <T> T displayDesigners(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayDesigners");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addDesignerProducts(String name, String price, String stock, String size, String image, String cid, String mid, String cbid, String sid, String did) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addDesignerProducts/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}/{9}", new Object[]{name, price, stock, size, image, cid, mid, cbid, sid, did})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
