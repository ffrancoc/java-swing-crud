/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.ffrancoc.crud;

/**
 *
 * @author FRANCISCO
 */
public class Persona {

    private String nombre;
    private String apellido;
    private String fnacimiento;
    private String genero;

    public Persona() {
    }

    public Persona(String nombre, String apellido, String fnacimiento, String genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fnacimiento = fnacimiento;
        this.genero = genero;
    }

    public Object[] getPersona() {
        return new Object[]{nombre, apellido, fnacimiento, genero};
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
