package control;

import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Hideki
 */
public class DaoCliente {
     private Connection connection;
    
    public DaoCliente(Connection connection) {
         this.connection = connection;
    }
    
    public void inserir(Cliente cliente) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("INSERT INTO tb_customer_account(id_customer, cpf_cnpj, nm_customer, active, vl_total) " +
                                             "VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, cliente.getId_cliente());
            ps.setString(2, cliente.getCpf_cnpj());
            ps.setString(3, cliente.getNome());
            if(cliente.isIs_active()){
                ps.setInt(4, 1);
            }else{
                ps.setInt(4, 0);
            }
            ps.setDouble(5, cliente.getVl_total());
            
        } catch (SQLException ex) {
             System.out.println(ex.toString() + " em daoCliente");   
        }
    }
    
    public ArrayList<Cliente> getALL() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente;
        boolean active;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("SELECT * from tb_customer_account");
            ResultSet rs = ps.executeQuery();
           
            while(rs.next()){
                if(rs.getInt("active") == 1){
                    active = true;
                }else{
                    active = false;
                }
                
                cliente = new Cliente(rs.getInt("id_customer"));
                cliente.setCpf_cnpj(rs.getString("cpf_cnpj"));
                cliente.setIs_active(active);
                cliente.setVl_total(rs.getDouble("vl_total"));
                cliente.setNome(rs.getString("nm_customer"));
                clientes.add(cliente);
            }
        }
        catch (SQLException ex) { 
            System.out.println(ex.toString() + " em daoCliente");    
        }
        
        return clientes;
    }
    
    public boolean idConsulta(int idc){
        PreparedStatement ps = null;
        
        try{
            ps = connection.prepareStatement("SELECT * from tb_customer_account where id_customer = ?");
            ps.setInt(1, idc);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                return true;
            }else{
                return false;
            }            
        }
        catch (SQLException ex) { 
            System.out.println(ex.toString() + " em daoCliente");    
        }
        
        return false;
    }
    
}
