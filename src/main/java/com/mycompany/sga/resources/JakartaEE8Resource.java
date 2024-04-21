package com.mycompany.sga.resources;

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
import Entitys.SongTb;
import java.util.Collection;
import java.util.Date;
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
public class JakartaEE8Resource {
    
   @EJB designerBeanLocal designer;
   @EJB adminBeanLocal admin;

//----------------------------------------------------------------------------------------------------------------   
//                                            Designer Module Rest Logic
//----------------------------------------------------------------------------------------------------------------

   
//   ================
//      Designer
//   ================
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("displayDesigners")
   public Collection<DesignerTb> displayDesigners()
   {
       return designer.getDesigners();
   }
   
   @POST
   @Path("addDesigner/{name}/{password}/{email}/{mobile_no}/{gender}/{image}")
   public void addDesigner(@PathParam("name") String name,@PathParam("password") String password,@PathParam("email") String email,@PathParam("mobileno")String mobileno,@PathParam("gender") String gender,@PathParam("image") String image)
   {
       designer.addDesigner(name, password, email, mobileno, gender, image);
   }
   
   @DELETE
   @Path("deleteDesigner/{id}")
   public void deleteDesigner(@PathParam("id") Integer id)
   {
       designer.deleteDesigner(id);
   }
  
   @POST
   @Path("updateDesigner/{id}/{name}/{password}/{email}/{mobile_no}/{gender}/{image}")
   public void updateDesigner(@PathParam("id") Integer id,@PathParam("name") String name,@PathParam("password") String password,@PathParam("email") String email,@PathParam("mobileno")String mobileno,@PathParam("gender") String gender,@PathParam("image") String image)
   {
       designer.editDesigner(id, name, password, email, mobileno, gender, image, id);
   }
   
//   ================
//      Product
//   ================
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("displayDesignersProducts")
   public Collection<ProductTb> displayDesignersProducts()
   {
       return designer.getProducts();
   }
   
   @POST
   @Path("addDesignerProducts/{name}/{price}/{stock}/{size}/{image}/{cid}/{mid}/{cbid}/{sid}/{did}")
   public void addDesignerProducts(@PathParam("name") String name,@PathParam("price") Integer price,@PathParam("stock") Integer stock,@PathParam("size")String size,@PathParam("image") String image,@PathParam("cid")Integer cid,@PathParam("mid")Integer mid,@PathParam("cbid")Integer cbid,@PathParam("sid")Integer sid,@PathParam("did")Integer did)
   {
       designer.addProduct(name, price, stock, name, image, cid, mid, cbid, sid, did);
   }
   
   @DELETE
   @Path("deleteDesignerProducts/{id}")
   public void deleteDesignerProduct(@PathParam("id") Integer id)
   {
       designer.deleteProduct(id);
   }
  
   @POST
   @Path("updateDesignerProducts/{name}/{price}/{stock}/{size}/{image}/{cid}/{mid}/{cbid}/{sid}/{did}")
   public void updateDesignerProducts(@PathParam("name") String name,@PathParam("price") Integer price,@PathParam("stock") Integer stock,@PathParam("size")Integer size,@PathParam("image") String image,@PathParam("cid")Integer cid,@PathParam("mid")Integer mid,@PathParam("cbid")Integer cbid,@PathParam("sid")Integer sid,@PathParam("did")Integer did)
   {
       designer.editProduct(did, name, price, stock, name, image, cid, mid, cbid, sid, did);
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
   public Collection<CategoryTb> displayCategorys()
   {
       return admin.getCategorys();
   }
   
   @POST
   @Path("addCategory/{name}")
   public void addCategory(@PathParam("name") String name)
   {
       admin.addCategory(name);
   }
   
   @DELETE
   @Path("deleteCategory/{id}")
   public void deleteCategory(@PathParam("id") Integer id)
   {
       admin.deleteCategory(id);
   }
  
   @POST
   @Path("updateCategory/{id}/{name}")
   public void updateCategory(@PathParam("id")Integer id,@PathParam("name") String name)
   {
       admin.editCategory(id, name);
   }
      
//   ===================
//      Movie_category
//   ===================
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("displayMovieCategorys")
   public Collection<MovieCategoryTb> displayMovieCategorys()
   {
       return admin.getMovieCategorys();
   }
   
   @POST
   @Path("addMovieCategory/{name}")
   public void addMovieCategory(@PathParam("name") String name)
   {
       admin.addMovieCategory(name);
   }
   
   @DELETE
   @Path("deleteMovieCategory/{id}")
   public void deleteMovieCategory(@PathParam("id") Integer id)
   {
       admin.deleteMovieCategory(id);
   }
  
   @POST
   @Path("updateMovieCategory/{id}/{name}")
   public void updateMovieCategory(@PathParam("id")Integer id,@PathParam("name") String name)
   {
       admin.editMovieCategory(id, name);
   }
   
//   ===================
//      Celebrity
//   ===================
   
   
  @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("displayCelebrity")
   public Collection<CelebrityTb> displayCelebrity()
   {
       return admin.getCelebritys();
   }
   
   @POST
   @Path("addCelebrity/{name}/{dob}/{gender}/{image}")
   public void addCelebrity(@PathParam("name") String name,@PathParam("dob") Date dob,@PathParam("gender")String gender,@PathParam("image") String image)
   {
       admin.addCelebrity(name, dob, gender, image);
   }
   
   @DELETE
   @Path("deleteCelebrity/{id}")
   public void deleteCelebrity(@PathParam("id") Integer id)
   {
       admin.deleteCelebrity(id);
   }
  
   @POST
   @Path("updateCelebrity/{id}/{name}/{dob}/{gender}/{image}")
   public void updateCelebrity(@PathParam("id")Integer id,@PathParam("name") String name,@PathParam("dob") Date dob,@PathParam("gender")String gender,@PathParam("image") String image)
   {
       admin.editCelebrity(id, name, dob, gender, image);
   }
   
   
//   ===================
//        Movie
//   ===================
    
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("displayMovie")
   public Collection<MovieTb> displayMovie()
   {
       return admin.getMovies();
   }
   
   @POST
   @Path("addMovie/{name}/{date}/{mcid}")
   public void addMovie(@PathParam("name") String name,@PathParam("date") Date date,@PathParam("mcid") Integer mcid)
   {
       admin.addMovie(name, date, mcid);
   }
   
   @DELETE
   @Path("deleteMovie/{id}")
   public void deleteMovie(@PathParam("id") Integer id)
   {
       admin.deleteMovie(id);
   }
  
   @POST
   @Path("updateMovie/{id}/{name}/{date}/{mcid}")
   public void updateMovie(@PathParam("id")Integer id,@PathParam("name") String name,@PathParam("date") Date date,@PathParam("mcid") Integer mcid)
   {
       admin.editMovie(id, name, date, mcid);
   }
   
//   ===================
//        Song
//   ===================
   
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("displaySongs")
   public Collection<SongTb> displaySongs()
   {
       return admin.getSongs();
   }
   
   @POST
   @Path("addSong/{name}/{mid}")
   public void addSong(@PathParam("name") String name,@PathParam("mid") Integer mid)
   {
       admin.addSong(name, mid);
   }
   
   @DELETE
   @Path("deleteSong/{id}")
   public void deleteSong(@PathParam("id") Integer id)
   {
       admin.deleteSong(id);
   }
  
   @POST
   @Path("UpdateSong/{id}/{name}/{mid}")
   public void updateSong(@PathParam("id")Integer id,@PathParam("name") String name,@PathParam("mid") Integer mid)
   {
       admin.editSong(id, name, mid);
   }
   
//   ===================
//     MovieCelebrity
//   ===================

   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("displayMovieCelebrity")
   public Collection<MovieCelebrity> displayMovieCelebrity()
   {
       return admin.getMovieCelebrity();
   }
   
   @POST
   @Path("addMovieCelebrity/{mid}/{cid}")
   public void addMovieCelebrity(@PathParam("mid") Integer mid,@PathParam("cid") Integer cid)
   {
       admin.addMovieCelebrity(mid, cid);
   }
   
   @DELETE
   @Path("deleteMovieCelebrity/{id}")
   public void deleteMovieCelebrity(@PathParam("id") Integer id)
   {
       admin.deleteMovieCelebrity(id);
   }
  
   @POST
   @Path("updateMovieCelebrity/{id}/{mid}/{cid}")
   public void updateMovieCelebrity(@PathParam("id") Integer id,@PathParam("mid") Integer mid,@PathParam("cid") Integer cid)
   {
       admin.editMovieCelebrity(id, mid, cid);
   }
   
//   ===================
//     MovieDesigner
//   ===================
   
    @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("displayMovieDesigner")
   public Collection<MovieDesigner> displayMovieDesigner()
   {
       return admin.getMovieDesigner();
   }
   
   @POST
   @Path("addMovieDesigner/{mid}/{cid}")
   public void addMovieDesigner(@PathParam("mid") Integer mid,@PathParam("did") Integer did)
   {
       admin.addMovieDesigner(mid, did);
   }
   
   @DELETE
   @Path("deleteMovieDesigner/{id}")
   public void deleteMovieDesigner(@PathParam("id") Integer id)
   {
       admin.deleteMovieDesigner(id);
   }
  
   @POST
   @Path("updateMovieDesigner/{id}/{mid}/{did}")
   public void updateMovieDesigner(@PathParam("id") Integer id,@PathParam("mid") Integer mid,@PathParam("did") Integer cid)
   {
       admin.editMovieDesigner(id, mid, cid);
   }
   
}
