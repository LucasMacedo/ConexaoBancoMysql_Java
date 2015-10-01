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
public class ProdutoDao{
        
    public void incluir(double valor,int quantidade,String descricao) throws SQLException{
        try{
            String sql;
            sql = "INSERT INTO produto(valor,quantidade,descricao) VALUES (?,?,?);";
            
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
            st.setDouble(1, valor);
            st.setInt(2, quantidade);
            st.setString(3, descricao);            
            st.execute();
            
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }finally{
            ConnectionManager.closeConnection();
        }
    }
    
    public void alterar(int id,double valor,String descricao) throws SQLException{
        try{
            String sql;
            sql = "UPDATE produto SET valor=?, descricao=?  WHERE idproduto=? ;";
            
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
            st.setDouble(1, valor);
            st.setString(2, descricao);
            st.setInt(3, id);
            st.executeUpdate();
            
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }finally{
            ConnectionManager.closeConnection();
        }
    }
    
    public ArrayList<Generica> selecionar() throws SQLException{
        ArrayList<Generica> listaProdutos = new ArrayList<>();
        try{
            String sql;
            sql = "SELECT * FROM produto";
            
            PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Generica gen = new Generica();
                gen.setId(rs.getInt("idproduto"));
                gen.setDescricao(rs.getString("descricao"));
                listaProdutos.add(gen);
            }
            
            return listaProdutos;
        }catch(SQLException ex){
            Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }finally{
            ConnectionManager.closeConnection();
        }
    }

    public void deletar(int id) throws SQLException{
        try{
           String sql;
           sql = "DELETE FROM produto WHERE idproduto=?";
            
           PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);
           st.setInt(1, id);
           st.execute();
           
        }catch(SQLException ex){
           Logger.getLogger(ProdutoDao.class.getName()).log(Level.SEVERE, null, ex);
           throw ex;
        }finally{
            ConnectionManager.closeConnection();
        }
    };
    
}
