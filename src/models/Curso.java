package models;

import java.util.Date;

public class Curso {
    // private static se utiliza para generar identificadores unicos para instancias de la clase
    // en este caso se crea una instancia de curso, el atributo ID se incrementa utilizando este contador ID
    // asegurando que cada instancia tenga un identificador unico va , en 1 para que no haya ID cero
    private static int contadorId = 1;  // Contador para el ID
    private int id;
    private String nombre;
    private Date fechaInicio;
    private String profesor;

    // construye las variables y le asigna un valor
    public Curso(String nombre, Date fechaInicio, String profesor) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.profesor = profesor;
    }

    // Getters devuelve valores y setters los modifica


    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Curso.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    // llama a las variables
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre Del Curso: " + nombre + ", Fecha de Inicio: " + fechaInicio + ", Profesor: " + profesor;
    }
}