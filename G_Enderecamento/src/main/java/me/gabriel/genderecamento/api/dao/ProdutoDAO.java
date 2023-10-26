/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.gabriel.genderecamento.api.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import me.gabriel.genderecamento.api.Produto;
import me.gabriel.genderecamento.connection.ConnectionFactory;
import static me.gabriel.genderecamento.menu.Menu.jCodigoTextArea;
import static me.gabriel.genderecamento.menu.Menu.jLocalTextArea;
import static me.gabriel.genderecamento.menu.Menu.jLoteTextArea;
import static me.gabriel.genderecamento.menu.Menu.jProdutoTextArea;
import static me.gabriel.genderecamento.menu.Menu.jQuantidadeTextArea;

/**
 *
 * @author gabri
 */
public class ProdutoDAO {
    
    public void create(Produto produto) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produto (codigo,nome,lote,quantidade,local)VALUES(?,?,?,?,?)");
            stmt.setString(1, produto.getCodigo());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getLote());
            stmt.setString(4, produto.getQuantidade());
            stmt.setString(5, produto.getLocal());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar! " + ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Produto> read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto p = new Produto();
                p.setCodigo(rs.getString("codigo"));
                p.setNome(rs.getString("nome"));
                p.setLote(rs.getString("lote"));
                p.setQuantidade(rs.getString("quantidade"));
                p.setLocal(rs.getString("local"));
                produtos.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        } 
        return produtos;
    }
    
}
