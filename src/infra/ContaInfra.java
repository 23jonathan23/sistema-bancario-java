
package infra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import models.Conta;

/**
 *
 * @author jonathan.silva
 */
public class ContaInfra {
    
    public void gravarConta(Conta conta) {
      File arquivo = new File("/TMP/contasDoBanco.txt"); 
 
      try {
        if (!arquivo.exists()) {
            arquivo.createNewFile(); 
      } } catch (IOException e) {
      }
      
      try {
          FileWriter fw = new FileWriter(arquivo, true);
          BufferedWriter bw = new BufferedWriter(fw);
          
          String numero = Integer.toString(conta.getNumero());
          String cpf = conta.getCpf();
          String senha = Integer.toString(conta.getSenha());
          String dataAbertura = conta.getDataAbertura();
          String dataEncerramento = conta.getDataEncerramento();
          String saldo = Float.toString(conta.getSaldo());
 
          bw.write(numero + ";" + cpf + ";" + senha + ";" + dataAbertura  + ";" + dataEncerramento + ";" + saldo); 
          bw.newLine();
 
          bw.close();
          fw.close();
      } catch (IOException e) {}
    } 
    
    public ArrayList<Conta> buscarContas() {
         
      File arquivo = new File("/TMP/contasDoBanco.txt");
      
      ArrayList<Conta> contasEncontradas = new ArrayList<>();
       
      try {
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
 
        while (br.ready()) {
            String[] linha = br.readLine().split(";"); 
            Conta conta = new Conta();
            conta.setNumero(Integer.parseInt(linha[0]));
            conta.setCpf(linha[1]);
            conta.setSenha(Integer.parseInt(linha[2]));
            conta.setDataAbertura(linha[3]);
            conta.setDataEncerramento(linha[4]);
            conta.setSaldo(Float.parseFloat(linha[5]));
            contasEncontradas.add(conta);
        }
    
        br.close();
        fr.close();
                
       } catch (IOException e) {
       }
      
       return contasEncontradas;
    }
    
    public void excluirContas() {
      File arquivo = new File("/TMP/contasDoBanco.txt"); 
 
      try {
        if (!arquivo.exists()) {
            arquivo.createNewFile(); 
      } } catch (IOException e) {
      }
      
      try {
          PrintWriter pw = new PrintWriter(new FileWriter(arquivo));
          
          pw.write("");
          pw.flush();
          pw.close();
 
      } catch (IOException e) {}
    } 
}
