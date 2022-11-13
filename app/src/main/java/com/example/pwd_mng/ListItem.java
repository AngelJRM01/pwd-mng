package com.example.pwd_mng;

public class ListItem {
    public String nombre;
    public String user;

    public ListItem(String nombre, String user) {
        this.nombre = nombre;
        this.user = user;
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
}
