
package main;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;
import models.Conta;
import handlers.ContaHandler;
import handlers.MovimentoHandler;

/**
 *
 * @author jonathan.silva
 */
public class SistemaDeControleBancario {

    public static void main(String[] args) {
        Scanner entrada = new Scanner (System.in);
        String opcaoMenu;
        
        int fecharPrograma = 0;
        
        while(fecharPrograma == 0) {
            
            System.out.println("Sistema bancário, escolha uma das operações listadas: \n"
                + "[1] Abrir uma conta \n"
                + "[2] Já tem uma conta? Entrar como um cliente \n"
                + "[0] Sair \n"
            );
            opcaoMenu = entrada.next();
            
            switch(opcaoMenu) {
                case "1": {
                    ContaHandler contaHandler = new ContaHandler();
                    
                    contaHandler.cadastrarNovaConta();
                    
                    break;
                }
                
                case "2": {
                    
                    ContaHandler contaHandler = new ContaHandler();
                    
                    Conta contaAcessada = contaHandler.acessarConta();
                    
                    if(contaAcessada == null) {
                        System.out.println("Conta ou senha informadas estão incorretas ou a conta foi encerrada.");
                        break;
                    }
                    
                    System.out.println("Seja bem vindo(a)!");
                    
                    int voltarAoInicio = 0;
                    
                    do{
                        
                        System.out.println("O que deseja fazer hoje?: \n"
                            + "[1] Sacar \n"
                            + "[2] Depositar \n"
                            + "[3] Verificar saldo \n"
                            + "[4] Emitir extrato \n"
                            + "[5] Encerrar conta \n"
                            + "[0] Sair \n"
                        );
                        
                        String operacaoSelecionada = entrada.next();
                        
                        switch(operacaoSelecionada) {
                        
                            case "1": {
                                MovimentoHandler movimentoHandler = new MovimentoHandler();
                                
                                Conta contaAtualizada = movimentoHandler.saque(contaAcessada);
                                
                                if(contaAtualizada == null) {
                                    System.out.println("Saldo insuficiente!");
                                    break;
                                }
                                
                                contaAcessada.setSaldo(contaAtualizada.getSaldo());
                                
                                NumberFormat formataDecimal = new DecimalFormat("0.00");
                                
                                String saldoAtual = formataDecimal.format(contaAcessada.getSaldo());
                                
                                System.out.println("Saque realizado com sucesso!, novo saldo: R$ " + saldoAtual);
                                
                                break;
                                
                            }
                            case "2": {
                                MovimentoHandler movimentoHandler = new MovimentoHandler();
                                
                                Conta contaAtualizada = movimentoHandler.depositar(contaAcessada);
                                
                                contaAcessada.setSaldo(contaAtualizada.getSaldo());
                                
                                NumberFormat formataDecimal = new DecimalFormat("0.00");
                                
                                String saldoAtual = formataDecimal.format(contaAcessada.getSaldo());
                                
                                System.out.println("Deposito realizado com sucesso!, novo saldo: R$ " + saldoAtual);
                                
                                break;
                            }
                            case "3": {
                                NumberFormat formataDecimal = new DecimalFormat("0.00");
                                
                                String saldoAtual = formataDecimal.format(contaAcessada.getSaldo());
                                
                                System.out.println("Saldo em conta é: " + "R$" + saldoAtual);
                                
                                break;
                            }
                            case "4": {
                                MovimentoHandler movimentoHandler = new MovimentoHandler();
                                
                                movimentoHandler.emitirExtrato(contaAcessada);
                                
                                break;
                            }
                            case "5": {
                                contaHandler.encerrarConta(contaAcessada);
                                voltarAoInicio++;
                                break;
                            }
                            case "0": {
                                voltarAoInicio++;
                                break;
                            }
                            default: {
                                System.out.println("Opção inválida!");
                            }
                        }
                        
                    }while(voltarAoInicio == 0);
                    
                    break;
                   
                }
                
                case "0": {
                    fecharPrograma++;
                    break;
                }
                
                default: {
                    System.out.println("Opção inválida!");
                }
            }
        }
    
    }
    
}
