/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;
import java.util.ArrayList;
import java.util.Scanner;
import models.Conta;
import infra.PessoaInfra;
import models.Pessoa;

/**
 *
 * @author jonathan.silva
 */
public class PessoaHandler {
    
    public Pessoa cadastrarNovoCliente() {
    
        Scanner entrada = new Scanner (System.in);
        Pessoa clienteParaCadastrar = new Pessoa();

        System.out.println("Informe seu nome: ");
        clienteParaCadastrar.setNome(entrada.nextLine());

        System.out.println("Informe seu cpf: ");
        clienteParaCadastrar.setCpf(entrada.nextLine());

        System.out.println("Informe seu endereço: ");
        clienteParaCadastrar.setEndereco(entrada.nextLine());

        System.out.println("Informe seu telefone: ");
        clienteParaCadastrar.setTelefone(entrada.nextLine());

        System.out.println("Informe seu e-mail: ");
        clienteParaCadastrar.setEmail(entrada.nextLine());
        
        Pessoa clienteEncontrado = this.buscarCliente(clienteParaCadastrar.getCpf());
        
        if(clienteEncontrado != null) {
            return clienteEncontrado;
        }

        PessoaInfra pessoaHandler = new PessoaInfra();

        pessoaHandler.gravarCliente(clienteParaCadastrar);
        
        return clienteParaCadastrar;

    } 
    
    public Pessoa buscarCliente(String cpfCliente) {
        
        PessoaInfra pessoaHandler = new PessoaInfra();
    
        ArrayList<Pessoa> clientesCadastradas = pessoaHandler.buscarClientes();
        
        Pessoa clienteSelecionado = null;
        
        for (Pessoa cliente : clientesCadastradas) {
          if(cliente.getCpf().equals(cpfCliente)) {
              clienteSelecionado = cliente;
          }
        }
        
        return clienteSelecionado;
    }
    
       
}
