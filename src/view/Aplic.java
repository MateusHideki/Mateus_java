package view;

import model.Cliente;
import control.Conexao;
import control.DaoCliente;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 *
 * @author Hideki
 */
public class Aplic {

    public static void main(String[] args) {
        Conexao conexao;
        DaoCliente daocliente;
        DecimalFormat df = new DecimalFormat("#,##0.00");
        Scanner entrada = new Scanner(System.in);
        
        /* conexao com o banco de dados.*/
        conexao = new Conexao("system","master1995");
        conexao.setDriver("oracle.jdbc.driver.OracleDriver");
        conexao.setConnectionString("jdbc:oracle:thin:@localhost:1521:xe");
        daocliente = new DaoCliente(conexao.conectar());
        
        /*instanciar array para agrupar clientes.*/
        ArrayList<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;
        
        int idCliente = 0, j = 1, opcao;
        Double Media = 0.0;
        String continua;
        
        
        
        do{
        /* for para pegar a id do cliente e verificar se ja esta sendo utilizado.*/
        for (int i = 0; i < j  ; i--){                       
            System.out.println("insira um ID de cliente:");
            idCliente = entrada.nextInt();
            
            if(!daocliente.idConsulta(idCliente)){
                    System.out.println("Este Id Já esta sendo utilizado, insira outro");
                }else if(daocliente.idConsulta(idCliente)){
                    i = 2;   
                }
            
        }
            /*instanciar cliente*/
            cliente =  new Cliente(idCliente);
            
            System.out.println("Insira o CPF ou CNPJ:");
            cliente.setCpf_cnpj(entrada.next());
            System.out.println("Insira o nome:");
            cliente.setNome(entrada.next());
            System.out.println("Insira 1 para conta ativa ou 0 para conta desativa");
            idCliente = entrada.nextInt();
            cliente.setIs_active(idCliente == 1);
            System.out.println("Insira o valor total:");
            cliente.setVl_total(entrada.nextDouble());
            
            daocliente.inserir(cliente);
            
            System.out.println("Quer cadastrar mais um cliente, sim ou nao?");
  
        
        System.out.println("----------Menu---------");
        System.out.println("Quer cadastrar mais um cliente? ");
        System.out.println("1 - cadastrar mais um cliente ");
        System.out.println("2 - Sair");
        System.out.println("Digite a opção: ");
        
        opcao = entrada.nextInt();
        
        /* switch para cadastrar mais clientes ou sair */
        switch (opcao) {
            case 1:
                 cliente =  new Cliente(idCliente);
            
            System.out.println("Insira o CPF ou CNPJ:");
            cliente.setCpf_cnpj(entrada.next());
            System.out.println("Insira o nome:");
            cliente.setNome(entrada.next());
            System.out.println("Insira 1 para conta ativa ou 0 para conta desativa");
            idCliente = entrada.nextInt();
            cliente.setIs_active(idCliente == 1);
            System.out.println("Insira o valor total:");
            cliente.setVl_total(entrada.nextDouble());
            
            daocliente.inserir(cliente);
            
                break;
            case 2:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
                
        }  
            
        }while (opcao != 2);
        
        clientes = daocliente.getALL();
        
        for (int i = 0; i < clientes.size(); i++) {    
            if (clientes.get(i).getVl_total() > 560 && clientes.get(i).getId_cliente() <  2700 && clientes.get(i).getId_cliente() > 1500){                
                    System.out.println("OS clintes ativos são:");
                    for (int k = 0; k < clientes.size(); k++) {
                        Media += clientes.get(k).getVl_total();
                    }
                    System.out.println("A média dos clientes: " + df.format(Media / clientes.size()));
                    Collections.sort(clientes);
                    System.out.println(clientes);
            }
            else
                break;
        }        
        
    }
}