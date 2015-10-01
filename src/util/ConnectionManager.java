/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lucas
 */
public class ConnectionManager {
    
    private static Connection connection;
    public static String conexao= "";
    
    public static Connection getConnection(){
        
        try{
            if(connection == null || connection.isClosed()){
                Class.forName("com.mysql.jdbc.Driver");
                String url;
                if(!conexao.equals("")){
                    url = "jdbc:mysql://"+conexao+":3306/mydb";
                }else{
                    url = "jdbc:mysql://localhost:3306/mydb";
                }                
                String user = "root";
                String password = "Figueira17";
                
                connection = DriverManager.getConnection(url, user, password);
                connection.setAutoCommit(false);
                
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.INFO, "Conexão estabelecida.");
            }
        }catch(ClassNotFoundException ex){
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Driver não encontrado.", ex);
        }catch(SQLException ex){
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Conexão NÃO estabelecida.", ex);
        }
        return connection;
    }
    
    public static void closeConnection(){
        try{
            connection.commit();
            if(!connection.isClosed()){
                connection.close();
            }            
        }catch(SQLException ex){
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Erro ao salvar dados.", ex);
            try{
                connection.rollback();
            }catch(SQLException e){
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, "Erro a realizar o RollBack", e);
            }
        }
    }
    
}
