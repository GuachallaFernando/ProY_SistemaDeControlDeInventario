package com.emergentes.modelo;


public class Cliente {
    private int id;
    private String dni;
    private String nombre;
    private String telefono;
    private String direccion;


    public Cliente(int id, String dni, String nombre, String telefono, String direccion) {
        this.id = 0;
        this.dni = "";
        this.nombre = "";
        this.telefono = "";
        this.direccion = "";
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + '}';
    }
    
}
