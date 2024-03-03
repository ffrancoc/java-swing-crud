/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.ffrancoc.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author FRANCISCO
 */
public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost:3306/crud";
    private static final String USER = "root";
    private static final String PASS = "";

    private static Connection conn = null;
    private static Statement stmt = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;

    public static boolean postPersona(Persona p) {
        initConnection();

        try {
            String sql = "INSERT INTO persona(nombre, apellido, fnacimiento, genero) VALUES (?, ?, STR_TO_DATE(?, '%d/%m/%Y'), ?);";
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getFnacimiento());
            ps.setString(4, p.getGenero());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al guardar registro: " + ex.getMessage());
        } finally {
            freeResources();
        }
        return false;
    }

    public static boolean putPersona(Persona p) {
        initConnection();

        try {
            String sql = "UPDATE persona SET nombre = ?, apellido = ?, fnacimiento = STR_TO_DATE(?, '%d/%m/%Y'), genero = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());
            ps.setString(3, p.getFnacimiento());
            ps.setString(4, p.getGenero());
            ps.setInt(5, p.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al actualizar el registro: " + ex.getMessage());
        } finally {
            freeResources();
        }
        return false;
    }

    public static boolean deletePersona(int id) {
        initConnection();
        try {
            String sql = "DELETE FROM persona WHERE id = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            System.err.println("Error al eliminar el registro: " + ex.getMessage());
        } finally {
            freeResources();
        }

        return false;
    }

    public static ArrayList<Persona> getPersonas() {
        initConnection();

        ArrayList<Persona> personas = new ArrayList<>();

        try {
            String sql = "SELECT id, nombre, apellido, DATE_FORMAT(fnacimiento, '%d/%m/%Y'), genero FROM persona;";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                personas.add(
                        new Persona(
                                rs.getInt(1), // Id
                                rs.getString(2), // Nombre
                                rs.getString(3), // Apellido
                                rs.getString(4), // Fecha de Nacimiento
                                rs.getString(5) // Genero
                        )
                );
            }

        } catch (SQLException ex) {
            // Manejo de exepciones
        } finally {
            freeResources();
        }

        return personas;
    }

    private static void initConnection() {
        try {
            if (conn == null) {
                Class.forName("org.mariadb.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USER, PASS);
            }
            System.out.println("Se ha establecido la conexion con la base de datos");
        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("Error al conectar con la base de datos: " + ex.getMessage());
        }
    }

    public static void freeResources() {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
            if (stmt != null) {
                stmt.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
        }
    }

}
