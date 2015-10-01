/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ConnectionManager;
import util.Generica;

/**
 *
 * @author lucas
 */
public class EquipeDao{
        
    public void incluir(String descricao) throws SQLException{
        try{
            String sql;
            sql = "INSERT INTO equipe(descricao) VALUES (?);";
            
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
            st.setString(1, descricao);            
            st.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(EquipeDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }finally{
            ConnectionManager.closeConnection();
        }
    }
    
    public void alterar(int id,String descricao) throws SQLException{
        try{
            String sql;
            sql = "UPDATE equipe SET descricao=? WHERE idequipe=? ;";
            
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
            st.setString(1, descricao);
            st.setInt(2, id);
            st.executeUpdate();
            
        }catch(SQLException ex){
            Logger.getLogger(EquipeDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }finally{
            ConnectionManager.closeConnection();
        }
    }
    
    public ArrayList selecionar() throws SQLException{
        ArrayList<Generica> listaEquipe = new ArrayList<>();
        try{
            String sql;
            sql = "SELECT * FROM equipe";
            
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Generica gen = new Generica();
                gen.setId(rs.getInt("idequipe"));
                gen.setDescricao(rs.getString("descricao"));
                listaEquipe.add(gen);
            }
            
            return listaEquipe;
        }catch(SQLException ex){
            Logger.getLogger(EquipeDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }finally{
            ConnectionManager.closeConnection();
        }
    }

    public void deletar(int id) throws SQLException{
        try{
           String sql;
           sql = "DELETE FROM equipe WHERE idequipe=?";
            
           PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
           st.setInt(1, id);
           st.execute();
           
        }catch(SQLException ex){
           Logger.getLogger(EquipeDao.class.getName()).log(Level.SEVERE, null, ex);
           throw ex;
        }finally{
            ConnectionManager.closeConnection();
        }
    };
    
}
