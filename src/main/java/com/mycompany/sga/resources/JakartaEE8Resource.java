package com.mycompany.sga.resources;

import Beans.UserBeanLocal;
import Beans.adminBeanLocal;
import Beans.designerBeanLocal;
import Entitys.CategoryTb;
import Entitys.CelebrityTb;
import Entitys.DesignerTb;
import Entitys.MovieCategoryTb;
import Entitys.MovieCelebrity;
import Entitys.MovieDesigner;
import Entitys.MovieTb;
import Entitys.ProductTb;
import Entitys.UserTb;
import Entitys.SongTb;
import Entitys.UserCartTb;
import Entitys.UserOrderTb;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author
 */
@Path("rest")
@DeclareRoles({"Admin","User"})
public class JakartaEE8Resource {

    @EJB
    designerBeanLocal designer;
    @EJB
    adminBeanLocal admin;
    @EJB
    UserBeanLocal user;

//----------------------------------------------------------------------------------------------------------------   
//                                            Designer Module Rest Logic
//----------------------------------------------------------------------------------------------------------------
//   ================
//      Designer
//   ================
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayDesigners")
    public Collection<DesignerTb> displayDesigners() {
        return designer.getDesigners();
    }

    @POST
    @Path("addDesigner/{name}/{password}/{email}/{mobileno}/{gender}/{image}")
    public void addDesigner(@PathParam("name") String name, @PathParam("password") String password, @PathParam("email") String email, @PathParam("mobileno") String mobileno, @PathParam("gender") String gender, @PathParam("image") String image) {
        designer.addDesigner(name, password, email, mobileno, gender, image);
    }

    @DELETE
    @Path("deleteDesigner/{id}")
    public void deleteDesigner(@PathParam("id") Integer id) {
        designer.deleteDesigner(id);
    }

    @POST
    @Path("updateDesigner/{id}/{name}/{password}/{email}/{mobileno}/{gender}/{image}/{isapprove}")
    public void updateDesigner(@PathParam("id") Integer id, @PathParam("name") String name, @PathParam("password") String password, @PathParam("email") String email, @PathParam("mobileno") String mobileno, @PathParam("gender") String gender, @PathParam("image") String image,@PathParam("isapprove") Integer isapprove) {
        designer.editDesigner(id, name, password, email, mobileno, gender, image, isapprove);
    }

//   ================
//      Product
//   ================
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayDesignersProducts")
    public Collection<ProductTb> displayDesignersProducts() {
        return designer.getProducts();
    }

    @POST
    @Path("addDesignerProducts/{name}/{price}/{stock}/{image}/{cid}/{mid}/{cbid}/{sid}/{did}")
    public void addDesignerProducts(@PathParam("name") String name, @PathParam("price") Integer price, @PathParam("stock") Integer stock, @PathParam("image") String image, @PathParam("cid") Integer cid, @PathParam("mid") Integer mid, @PathParam("cbid") Integer cbid, @PathParam("sid") Integer sid, @PathParam("did") Integer did) {
        designer.addProduct(name, price, stock, image, cid, mid, cbid, sid, did);
    }

    @DELETE
    @Path("deleteDesignerProducts/{id}")
    public void deleteDesignerProduct(@PathParam("id") Integer id) {
        designer.deleteProduct(id);
    }

    @POST
    @Path("updateDesignerProducts/{id}/{name}/{price}/{stock}/{image}/{cid}/{mid}/{cbid}/{sid}/{did}")
    public void updateDesignerProducts(@PathParam("id") Integer id,@PathParam("name") String name, @PathParam("price") Integer price, @PathParam("stock") Integer stock, @PathParam("image") String image, @PathParam("cid") Integer cid, @PathParam("mid") Integer mid, @PathParam("cbid") Integer cbid, @PathParam("sid") Integer sid, @PathParam("did") Integer did) {
        designer.editProduct(id, name, price, stock, image, cid, mid, cbid, sid, did);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getProductByDesigner/{did}")
    public Collection<ProductTb> getProductByDesigner(@PathParam("did") Integer did)
    {
        return designer.getProductByDesigner(did);
    }

//---------------------------------------------------------------------------------------------------------------- 
//                                          Admin Module Rest Logic 
//----------------------------------------------------------------------------------------------------------------  
//   ================
//      Category
//   ================
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayCategorys")
    @RolesAllowed("Admin")
    public Collection<CategoryTb> displayCategorys() {
        return admin.getCategorys();
    }

    @POST
    @Path("addCategory/{name}")
    @RolesAllowed("Admin")
    public void addCategory(@PathParam("name") String name) {
        admin.addCategory(name);
    }

    @DELETE
    @Path("deleteCategory/{id}")
    @RolesAllowed("Admin")
    public void deleteCategory(@PathParam("id") Integer id) {
        admin.deleteCategory(id);
    }

    @POST
    @Path("updateCategory/{id}/{name}")
    @RolesAllowed("Admin")
    public void updateCategory(@PathParam("id") Integer id, @PathParam("name") String name) {
        admin.editCategory(id, name);
    }

//   ===================
//      Movie_category
//   ===================
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayMovieCategorys")
    @RolesAllowed("Admin")
    public Collection<MovieCategoryTb> displayMovieCategorys() {
        return admin.getMovieCategorys();
    }

    @POST
    @Path("addMovieCategory/{name}")
    @RolesAllowed("Admin")
    public void addMovieCategory(@PathParam("name") String name) {
        admin.addMovieCategory(name);
    }

    @DELETE
    @Path("deleteMovieCategory/{id}")
    @RolesAllowed("Admin")
    public void deleteMovieCategory(@PathParam("id") Integer id) {
        admin.deleteMovieCategory(id);
    }

    @POST
    @Path("updateMovieCategory/{id}/{name}")
    @RolesAllowed("Admin")
    public void updateMovieCategory(@PathParam("id") Integer id, @PathParam("name") String name) {
        admin.editMovieCategory(id, name);
    }

//   ===================
//      Celebrity
//   ===================
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayCelebrity")
    @RolesAllowed("Admin")
    public Collection<CelebrityTb> displayCelebrity() {
        return admin.getCelebritys();
    }

    @POST
    @Path("addCelebrity/{name}/{dob}/{gender}/{image}")
    @RolesAllowed("Admin")
    public void addCelebrity(@PathParam("name") String name, @PathParam("dob") String dob, @PathParam("gender") String gender, @PathParam("image") String image) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dobdata = sdf.parse(dob);
            admin.addCelebrity(name, dobdata, gender, image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DELETE
    @Path("deleteCelebrity/{id}")
    @RolesAllowed("Admin")
    public void deleteCelebrity(@PathParam("id") Integer id) {
        admin.deleteCelebrity(id);
    }

    @POST
    @Path("updateCelebrity/{id}/{name}/{dob}/{gender}/{image}")
    @RolesAllowed("Admin")
    public void updateCelebrity(@PathParam("id") Integer id, @PathParam("name") String name, @PathParam("dob") String dob, @PathParam("gender") String gender, @PathParam("image") String image) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dobdata = sdf.parse(dob);
            admin.editCelebrity(id, name, dobdata, gender, image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//   ===================
//        Movie
//   ===================
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayMovie")
    @RolesAllowed("Admin")
    public Collection<MovieTb> displayMovie() {
        return admin.getMovies();
    }

    @POST
    @Path("addMovie/{name}/{date}/{mcid}")
    @RolesAllowed("Admin")
    public void addMovie(@PathParam("name") String name, @PathParam("date") String date, @PathParam("mcid") Integer mcid) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datedata = sdf.parse(date);
            admin.addMovie(name, datedata, mcid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @DELETE
    @Path("deleteMovie/{id}")
    @RolesAllowed("Admin")
    public void deleteMovie(@PathParam("id") Integer id) {
        admin.deleteMovie(id);
    }

    @POST
    @Path("updateMovie/{id}/{name}/{date}/{mcid}")
    @RolesAllowed("Admin")
    public void updateMovie(@PathParam("id") Integer id, @PathParam("name") String name, @PathParam("date") String date, @PathParam("mcid") Integer mcid) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datedata = sdf.parse(date);
            admin.editMovie(id, name, datedata, mcid);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//   ===================
//        Song
//   ===================
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displaySongs")
    @RolesAllowed("Admin")
    public Collection<SongTb> displaySongs() {
        return admin.getSongs();
    }

    @POST
    @Path("addSong/{name}/{mid}")
    @RolesAllowed("Admin")
    public void addSong(@PathParam("name") String name, @PathParam("mid") Integer mid) {
        admin.addSong(name, mid);
    }

    @DELETE
    @Path("deleteSong/{id}")
    @RolesAllowed("Admin")
    public void deleteSong(@PathParam("id") Integer id) {
        admin.deleteSong(id);
    }

    @POST
    @Path("UpdateSong/{id}/{name}/{mid}")
    @RolesAllowed("Admin")
    public void updateSong(@PathParam("id") Integer id, @PathParam("name") String name, @PathParam("mid") Integer mid) {
        admin.editSong(id, name, mid);
    }

//   ===================
//     MovieCelebrity
//   ===================
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayMovieCelebrity")
    @RolesAllowed("Admin")
    public Collection<MovieCelebrity> displayMovieCelebrity() {
        return admin.getMovieCelebrity();
    }

    @POST
    @Path("addMovieCelebrity/{mid}/{cid}")
    @RolesAllowed("Admin")
    public void addMovieCelebrity(@PathParam("mid") Integer mid, @PathParam("cid") Integer cid) {
        admin.addMovieCelebrity(mid, cid);
    }

    @DELETE
    @Path("deleteMovieCelebrity/{id}")
    @RolesAllowed("Admin")
    public void deleteMovieCelebrity(@PathParam("id") Integer id) {
        admin.deleteMovieCelebrity(id);
    }

    @POST
    @Path("updateMovieCelebrity/{id}/{mid}/{cid}")
    @RolesAllowed("Admin")
    public void updateMovieCelebrity(@PathParam("id") Integer id, @PathParam("mid") Integer mid, @PathParam("cid") Integer cid) {
        admin.editMovieCelebrity(id, mid, cid);
    }

//   ===================
//     MovieDesigner
//   ===================
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayMovieDesigner")
    @RolesAllowed("Admin")
    public Collection<MovieDesigner> displayMovieDesigner() {
        return admin.getMovieDesigner();
    }

    @POST
    @Path("addMovieDesigner/{mid}/{did}")
    @RolesAllowed("Admin")
    public void addMovieDesigner(@PathParam("mid") Integer mid, @PathParam("did") Integer did) {
        admin.addMovieDesigner(mid, did);
    }

    @DELETE
    @Path("deleteMovieDesigner/{id}")
    @RolesAllowed("Admin")
    public void deleteMovieDesigner(@PathParam("id") Integer id) {
        admin.deleteMovieDesigner(id);
    }

    @POST
    @Path("updateMovieDesigner/{id}/{mid}/{did}")
    @RolesAllowed("Admin")
    public void updateMovieDesigner(@PathParam("id") Integer id, @PathParam("mid") Integer mid, @PathParam("did") Integer did) {
        admin.editMovieDesigner(id, mid, did);
    }

    
    //---------------------------------------------------------------------------------------------------------------- 
//                                          User Module Rest Logic 
//----------------------------------------------------------------------------------------------------------------  
    
//   ===================
//     User
//   ===================
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("displayUsers")
    public Collection<UserTb> displayUsers()
    {
        return user.displayUsers();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getUserById/{id}")
    public UserTb getUserById(@PathParam("id") Integer id)
    {
        return user.getUserById(id);
    }
    
    @POST
    @Path("registerUser/{name}/{password}/{email}/{mobileno}/{gender}/{address}/{image}/{dob}")
    public void registerUser(@PathParam("name") String name,@PathParam("password") String password,@PathParam("email") String email,@PathParam("mobileno") String mobileno,@PathParam("gender") String gender,@PathParam("address") String address,@PathParam("image") String image,@PathParam("dob") String date)
    {
        try{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dobdata = sdf.parse(date);
        user.RegisterUser(name, password, email, mobileno,gender, address, image, dobdata);
        }
        catch(Exception e)
        {
             e.printStackTrace();
        }
    }
    
    @DELETE
    @Path("deleteUser/{id}")
    public void deleteUser(@PathParam("id") Integer id)
    {
        user.deleteUser(id);
    }
    
    @POST
    @Path("editUser/{id}/{name}/{password}/{email}/{mobileno}/{gender}/{address}/{image}/{dob}")
    public void editUser(@PathParam("id") Integer id,@PathParam("name") String name,@PathParam("password") String password,@PathParam("email") String email,@PathParam("mobileno") String mobileno,@PathParam("gender") String gender,@PathParam("address") String address,@PathParam("image") String image,@PathParam("dob") Date date)
    {
        user.editUser(id, name, password, email, mobileno,gender, address, image, date);
    }

    @POST
    @Path("afterRegister/{email}")
    public void afterRegister(@PathParam("email") String email)
    {
        user.afterRegister(email);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllProducts")
    public Collection<ProductTb> getAllProduct()
    {
        return user.getAllProduct();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getProductDetails/{pid}")
    public ProductTb getProductDetails(@PathParam("pid") Integer pid)
    {
        return user.getProductDetails(pid);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getCartProducts/{uid}")
    public Collection<UserCartTb> getCartProducts(@PathParam("uid") Integer uid)
    {
        return user.getCartProducts(uid);
    }
    
    @POST
    @Path("addToCart/{uid}/{pid}/{size}/{qty}/{price}")
    public void addToCart(@PathParam("uid") Integer uid,@PathParam("pid") Integer pid,@PathParam("size") String size,@PathParam("qty") Integer qty,@PathParam("price") Integer price)
    {
        user.addToCart(uid, pid, size, qty, price);
    }
    
    @DELETE
    @Path("removefromCart/{cid}")
    public void removefromCart(@PathParam("cid") Integer cid)
    {
        user.removefromCart(cid);
    }
    
    @POST
    @Path("increasecartProductQuantity/{cid}")
    public void increasecartProductQuantity(@PathParam("cid") Integer cid)
    {
        user.increasecartProductQuantity(cid);
    }
    
    @POST
    @Path("decreasecartProductQuantity/{cid}")
    public void decreasecartProductQuantity(@PathParam("cid") Integer cid)
    {
        user.decreasecartProductQuantity(cid);
    }
    
    @POST
    @Path("addOrder/{uid}/{pid}/{size}/{qty}/{price}")
    public void addOrder(@PathParam("uid") Integer uid,@PathParam("pid") Integer pid,@PathParam("size") String size,@PathParam("qty") Integer qty,@PathParam("price") Integer price)
    {
        user.addOrder(uid, pid, size, qty, price);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getOrderHistory/{uid}")
    public Collection<UserOrderTb> getOrderHistory(@PathParam("uid") Integer uid)
    {
        return user.getOrderHistory(uid);
    }
    
    @GET
    @Path("p1")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public String path(){
        return "admin allowed";
    }
    @GET
    @Path("p2")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("User")
    public String path2(){
        return "User allowed";
    }
}
