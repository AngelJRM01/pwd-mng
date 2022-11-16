package com.example.pwd_mng;

public class ListItem {
    private int id;
    private String nombre;
    private String user;
    private String pass;
    private Boolean favourite;
    private String link;
    private String notes;

    public ListItem(){

    }

    public ListItem(int id, String nombre, String user) {
        this.id = id;
        this.nombre = nombre;
        this.user = user;
    }

    public ListItem(int id, String nombre, String user, String pass, Boolean favourite, String link, String notes) {
        this.id = id;
        this.nombre = nombre;
        this.user = user;
        this.pass = pass;
        this.favourite = favourite;
        this.link = link;
        this.notes = notes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
