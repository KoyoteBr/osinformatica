/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.seinfo.dal;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author daniel
 */
public class Module_Connection {
    public static Connection autentic() {
        
        java.sql.Connection connection = null;
        String driver = "com.mysql.jdbc.Driver";
        
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";
        
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            return connection;
            
        } catch (Exception e) {
            System.out.println("Não conectou "+e);
            return null;
        }
    }
}
