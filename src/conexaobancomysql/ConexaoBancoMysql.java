/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaobancomysql;

import dao.EquipeDao;
import dao.ProdutoDao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.ConnectionManager;
import util.Generica;

/**
 *
 * @author lucas
 */
public class ConexaoBancoMysql {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ip = JOptionPane.showInputDialog("Informe o endereço do Banco");
        ConnectionManager.conexao = ip;
        
        while(true){
            int escolha = Integer.parseInt(JOptionPane.showInputDialog("Informe a tabela a ser alterada! \n1-Equipe \n2-Produto"));
        
            if(escolha == 1){
                escolhaEquipe();
            }else if(escolha == 2){
                escolhaProduto();
            }
        }
    }
    
    private static void escolhaProduto(){
        ProdutoDao produto = new ProdutoDao();
        int escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha a opção:"
                    + "\n1-Listar \n2-Incluir \n3-Editar \n4-Deletar \n5-Sair"));
        
        try{
            ArrayList<Generica> listaProdutos = produto.selecionar();
            
            if(escolha == 1){
                ArrayList<Generica> lista = produto.selecionar();
                String mensagem = apresentaItem(lista);                
                JOptionPane.showMessageDialog(null, mensagem);
            }
            else if(escolha == 2){
                String descricao = JOptionPane.showInputDialog("Qual o nome do produto:");
                int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade do produto:"));
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Qual o valor do produto:"));
                
                produto.incluir(valor, quantidade, descricao);
                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
            }
            else if(escolha == 3){
                int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id a ser alterado:"));
                while(verificaItem(id, listaProdutos) == false){
                    JOptionPane.showMessageDialog(null, "Identificador invalido ! Insira um Id valido");
                    String mensagem = apresentaItem(listaProdutos);
                    id = Integer.parseInt(JOptionPane.showInputDialog("Insira um id valido!\n"+mensagem));
                }
                String descricao = JOptionPane.showInputDialog("Informe a nova descrição:");
                double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o novo valor:"));
                produto.alterar(id, valor, descricao);
                JOptionPane.showMessageDialog(null, "Item alterado com sucesso");
                
            }
            else if(escolha == 4){
                int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o item a ser excluido:"));
                while(verificaItem(id, listaProdutos) == false){
                    JOptionPane.showMessageDialog(null, "Identificador invalido ! Insira um Id valido");
                    String mensagem = apresentaItem(listaProdutos);
                    id = Integer.parseInt(JOptionPane.showInputDialog("Insira um id valido! \n"+mensagem));
                }
                produto.deletar(id);
                JOptionPane.showMessageDialog(null, "Item excluido com sucesso");
                
            }else{
                System.exit(0);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    private static void escolhaEquipe(){
        EquipeDao equipe = new EquipeDao();
        int escolha = Integer.parseInt(JOptionPane.showInputDialog("Escolha a opção:"
                    + "\n1-Listar \n2-Incluir \n3-Editar \n4-Deletar \n5-Sair"));   
        
        try{
            ArrayList<Generica> listaEquipe = equipe.selecionar();
            
            if(escolha == 1){
                ArrayList<Generica> lista = equipe.selecionar();
                String mensagem = apresentaItem(lista);                
                JOptionPane.showMessageDialog(null, mensagem);
            }
            else if(escolha == 2){
                String descricao = JOptionPane.showInputDialog("Qual o nome da equipe:");
                equipe.incluir(descricao);
                JOptionPane.showMessageDialog(null, "Equipe cadastrado com sucesso");
            }
            else if(escolha == 3){
                int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o id a ser alterado:"));
                while(verificaItem(id, listaEquipe) == false){
                    JOptionPane.showMessageDialog(null, "Identificador invalido ! Insira um Id valido");
                    String mensagem = apresentaItem(listaEquipe);
                    id = Integer.parseInt(JOptionPane.showInputDialog("Insira um id valido!\n"+mensagem));
                }
                String descricao = JOptionPane.showInputDialog("Informe um nova nome de equipe:");
                equipe.alterar(id,descricao);
                JOptionPane.showMessageDialog(null, "Item alterado com sucesso");
            }
            else if(escolha == 4){
                int id = Integer.parseInt(JOptionPane.showInputDialog("Informe o item a ser excluido:"));
                while(verificaItem(id, listaEquipe) == false){
                    JOptionPane.showMessageDialog(null, "Identificador invalido ! Insira um Id valido");
                    String mensagem = apresentaItem(listaEquipe);
                    id = Integer.parseInt(JOptionPane.showInputDialog("Insira um id valido! \n"+mensagem));
                }
                equipe.deletar(id);
                JOptionPane.showMessageDialog(null, "Item excluido com sucesso");
            }else{
                System.exit(0);
            }            
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    private static boolean verificaItem(int id,ArrayList<Generica> listaItem){
        for(int i=0 ; i < listaItem.size();i++){
            if(listaItem.get(i).getId() == id){
                return true;
            }
        }
        return false;
    }
    
    private static String apresentaItem(ArrayList<Generica> lista){
        String mensagem = "";
        for(int i=0; i < lista.size(); i++){
                    mensagem += lista.get(i).getId()+" - "+lista.get(i).getDescricao()+"\n";
        }
        return mensagem;        
    }
    
    
    
    
}
