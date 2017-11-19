package model;

import java.util.Comparator;

/**
 *
 * @author Mateus Hideki Simote Borges
 */
public class Cliente implements Comparable <Cliente> {
    private int id_cliente;
    private String cpf_cnpj, nome;
     private double vl_total;
    private boolean is_active;

    public Cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getVl_total() {
        return vl_total;
    }

    public void setVl_total(double vl_total) {
        this.vl_total = vl_total;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    
   

   
     @Override
    public int compareTo(Cliente Cliente2) {
        if (this.getVl_total() > Cliente2.getVl_total()) {
            return -1;
        }else if(this.getVl_total() < Cliente2.getVl_total()) {
            return 1;
        }else{
            return 0;
        }
    }
}
