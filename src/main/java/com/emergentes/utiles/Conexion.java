
package com.emergentes.utiles;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection con;

    public Connection getConnection() {
        try {
            String myBD = "jdbc:mysql://localhost:3333/inventario-beta?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD, "root", "admin");
            return con;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

}