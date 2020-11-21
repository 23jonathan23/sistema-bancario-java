package handlers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import models.Conta;
import models.Pessoa;
import infra.ContaInfra;

/**
 *
 * @author jonathan.silva
 */
public class ContaHandler {
    
    public Conta acessarConta() {
        
        Scanner entrada = new Scanner (System.in);
        
        int numeroContaCliente = 0, senhaContaCliente = 0;
        
        boolean prosseguir = false;
        
        do{
            
            try {
                 System.out.println("Informe o numero da conta: ");
                 numeroContaCliente = Integer.parseInt(entrada.next());
                 
                 System.out.println("Informe a senha da conta: ");
                 senhaContaCliente = Integer.parseInt(entrada.next());
            }catch(NumberFormatException err) {
                System.out.println("Conta ou senha informada é inválido!");
                continue;
            }
            
            prosseguir = true;
        
        }while(prosseguir == false);
 
        ContaInfra contaInfra = new ContaInfra();
        
        ArrayList<Conta> contasCadastradas = contaInfra.buscarContas();
        
        Conta contaSelecionada = null;
        
        for (Conta conta : contasCadastradas) {
          if((conta.getNumero() == numeroContaCliente) && (conta.getSenha() == senhaContaCliente)
          && (conta.getDataEncerramento().equals(""))) {
              contaSelecionada = conta;
          }
        }
        
        return contaSelecionada;
        
    }
    
    public void cadastrarNovaConta() {
        
        PessoaHandler pessoaHandler = new PessoaHandler();
        
        Pessoa clienteCadastrado = pessoaHandler.cadastrarNovoCliente();
        
        ContaInfra contaInfra = new ContaInfra();
        
        ArrayList<Conta> contasCadastradas = contaInfra.buscarContas();
        
        Scanner entrada = new Scanner (System.in);
        
        int numeroContaCliente = 0, senhaContaCliente = 0;
        
        boolean prosseguir = false;
        
        do{ 
            try {
                System.out.println("Informe o numero da conta, deve ser formado por 5 digitos ex:10236: ");
                String numeroInformado = entrada.next();
                
                if(numeroInformado.length() != 5) {
                    System.out.println("Numero da conta informado é muito curto ou longo demais!");
                    continue;
                }
                
                numeroContaCliente = Integer.parseInt(numeroInformado);
                
                Conta contaEncontrada = null;
        
                for (Conta conta : contasCadastradas) {
                    if(conta.getNumero() == numeroContaCliente) {
                        contaEncontrada = conta;
                    }
                }
                    
                if(contaEncontrada != null) {
                    System.out.println("Numero da conta informado é inválido!");
                    continue;
                 }
                
            }catch(NumberFormatException e) {
                System.out.println("Numero da conta informado é inválido!");
                continue;
            }

            try {
                System.out.println("Informe a senha da conta, OBS: somente numeros: ");
                senhaContaCliente = Integer.parseInt(entrada.next());
            }catch(NumberFormatException err) {
                System.out.println("Senha informada é inválido!");
                continue;
            }
            
            prosseguir = true;
            
        }while(prosseguir == false);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar dataAtual = new GregorianCalendar();
        
        Conta contaParaCadastrar = new Conta();
        
        contaParaCadastrar.setNumero(numeroContaCliente);
        contaParaCadastrar.setSenha(senhaContaCliente);
        contaParaCadastrar.setCpf(clienteCadastrado.getCpf());
        contaParaCadastrar.setDataAbertura(sdf.format(dataAtual.getTime()));
        contaParaCadastrar.setDataEncerramento("");
        contaParaCadastrar.setSaldo(0.00f);
        
        contaInfra.gravarConta(contaParaCadastrar);
        
        System.out.println("Abertura de conta realizada com sucesso!");
    
    }
    
    public void encerrarConta(Conta contaParaEncerrar) {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        GregorianCalendar dataAtual = new GregorianCalendar();
        
        contaParaEncerrar.setDataEncerramento(sdf.format(dataAtual.getTime()));
        
        ContaInfra contaInfra = new ContaInfra();
        
        ArrayList<Conta> contasCadastradas = contaInfra.buscarContas();
        
        contaInfra.excluirContas();
        
        for(Conta conta : contasCadastradas) {
            
            if(conta.getNumero() == contaParaEncerrar.getNumero()) {
                conta.setDataEncerramento(contaParaEncerrar.getDataEncerramento());
            }
            
            contaInfra.gravarConta(conta);       
        }
        
        System.out.println("Conta encerrada com sucesso!");
    }
}
