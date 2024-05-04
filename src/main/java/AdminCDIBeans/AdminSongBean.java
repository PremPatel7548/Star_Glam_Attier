/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package AdminCDIBeans;

import Entitys.SongTb;
import RestFullClient.RestClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Admin
 */
@Named(value = "adminSongBean")
@RequestScoped
public class AdminSongBean {

    /**
     * Creates a new instance of AdminSongBean
     */
    Collection<SongTb> songList;
    GenericType<Collection<SongTb>> gc;
    Response res;
    RestClient rc;
    SongTb song = new SongTb();
    Integer movie_id;
    String successmessage;
    
    public AdminSongBean() {
        rc = new RestClient();
        gc = new GenericType<Collection<SongTb>>() {
        };
        songList = new ArrayList<>();
    }

    public String getSuccessmessage() {
        return successmessage;
    }

    public void setSuccessmessage(String successmessage) {
        this.successmessage = successmessage;
    }

    public Collection<SongTb> getSongList() {
        res=rc.displaySongs(Response.class);
        songList=res.readEntity(gc);
        return songList;
    }

    public void setSongList(Collection<SongTb> songList) {
        this.songList = songList;
    }

    public SongTb getSong() {
        return song;
    }

    public void setSong(SongTb song) {
        this.song = song;
    }

    public Integer getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(Integer movie_id) {
        this.movie_id = movie_id;
    }

    public String addSong(){
        rc.addSong(song.getName(), String.valueOf(movie_id));
        successmessage = "Song Added Successfully";
        return "SongList";
    }
    
      public String deleteSong(Integer id) {
        rc.deleteSong(String.valueOf(id));
        successmessage = "Song Deleted Successfully";
        return "SongList";
    }

    public String getdataForSong(SongTb song) {
        this.song = song;
        this.movie_id = song.getMovieId().getId();
        return "Editsong";
    }
    
    public String editSong(){
        rc.updateSong(String.valueOf(song.getId()),song.getName(), String.valueOf(movie_id));
        successmessage = "Song Edited Successfully";
        return "SongList";
    }
    
    public void clearSuccessMessage() {
        successmessage = null;
    }
    
}
