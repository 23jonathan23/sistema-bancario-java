
package handlers;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import models.Conta;
import models.Movimento;
import models.TipoMovimento;
import infra.ContaInfra;
import infra.MovimentoInfra;

/**
 *
 * @author jonathan.silva
 */
public class MovimentoHandler {
    
    private void registarMovimento(Conta contaAtual, TipoMovimento tipoMovimento, float valorMovimento) {
        
        Movimento movimentoParaCadastrar = new Movimento();
        
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm:ss");
        
        GregorianCalendar dataAtual = new GregorianCalendar();
        
        movimentoParaCadastrar.setCodigo(tipoMovimento.ordinal());
        movimentoParaCadastrar.setData(sdfData.format(dataAtual.getTime()));
        movimentoParaCadastrar.setHora(sdfHora.format(dataAtual.getTime()));
        movimentoParaCadastrar.setConta(contaAtual.getNumero());
        movimentoParaCadastrar.setValor(valorMovimento);
        movimentoParaCadastrar.setTipo(tipoMovimento);
        
        MovimentoInfra movimentoInfra = new MovimentoInfra();
        
        movimentoInfra.gravarMovimento(movimentoParaCadastrar);
        
        
        ContaInfra contaInfra = new ContaInfra();
        
        ArrayList<Conta> contasCadastradas = contaInfra.buscarContas();
        
        contaInfra.excluirContas();
        
        for(Conta conta : contasCadastradas) {
            
            if(conta.getNumero() == contaAtual.getNumero()) {
                conta.setSaldo(contaAtual.getSaldo());
            }
            
            contaInfra.gravarConta(conta);       
        }

    }
    
    public Conta depositar(Conta contaAtual) {
        
        Scanner entrada = new Scanner (System.in);
        
        float valorDeposito = 0.0f;
        
        boolean prosseguir = false;
        
        do{
            
            try {
                 System.out.println("Informe o valor do depósito, seguindo o ex: 20.00: ");
                 valorDeposito = Float.parseFloat(entrada.next());
                 
            }catch(NumberFormatException err) {
                System.out.println("Valor informado é inválido!");
                continue;
            }
            
            prosseguir = true;
        
        }while(prosseguir == false);
        
        float novoSaldo = contaAtual.getSaldo() + valorDeposito;
        
        contaAtual.setSaldo(novoSaldo);
        
        this.registarMovimento(contaAtual, TipoMovimento.DEPOSITO, valorDeposito);
        
        return contaAtual;
    }
    
    public Conta saque(Conta contaAtual) {
        
        Scanner entrada = new Scanner (System.in);
        
        float valorSaque = 0.0f;
        
        boolean prosseguir = false;
        
        do{
            
            try {
                 System.out.println("Informe o valor do saque, seguindo o ex: 20.00: ");
                 valorSaque = Float.parseFloat(entrada.next());
                 
            }catch(NumberFormatException err) {
                System.out.println("Valor informado é inválido!");
                continue;
            }
            
            prosseguir = true;
        
        }while(prosseguir == false);
        
        float novoSaldo = contaAtual.getSaldo() - valorSaque;
        
        if(!(novoSaldo >= 0)) {
            return null;
        }
        
        contaAtual.setSaldo(novoSaldo);
        
        this.registarMovimento(contaAtual, TipoMovimento.SAQUE, valorSaque);
        
        return contaAtual;
        
    }
    
    public void  emitirExtrato(Conta contaAtual) {
        MovimentoInfra movimentoInfra = new MovimentoInfra();
        
        ArrayList<Movimento> movimentacoesDeContas = movimentoInfra.buscarMovimentacoes();
        
        ArrayList<Movimento> movimentosDaContaAtual = new ArrayList();
        
        for( Movimento movimento : movimentacoesDeContas ) {
            if(movimento.getConta() == contaAtual.getNumero()) {
                movimentosDaContaAtual.add(movimento);
            }
        }
        
        if(movimentosDaContaAtual.isEmpty()) {
            System.out.println("Ainda não foi realizado movimentações nesta conta!");
        } else {
            
            NumberFormat formataDecimal = new DecimalFormat("0.00");                    
        
            System.out.println("Inicio do extrato da conta:");

            for(Movimento movimento : movimentosDaContaAtual) {
                System.out.println(movimento.getTipo().name() + " de " +  formataDecimal.format(movimento.getValor()) + " realizado em: " + movimento.getData() + " as " + movimento.getHora());
            }

            System.out.println("Final do extrato da conta:");
        
        }
    }
    
}
