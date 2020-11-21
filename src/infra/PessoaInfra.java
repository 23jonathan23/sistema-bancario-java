
package infra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import models.Pessoa;

/**
 *
 * @author jonathan.silva
 */
public class PessoaInfra {
    
    public void gravarCliente(Pessoa cliente) {
      File arquivo = new File("/TMP/clientesDoBanco.txt"); 
 
      try {
        if (!arquivo.exists()) {
            arquivo.createNewFile(); 
      } } catch (IOException e) {
      }
      
      try {
          FileWriter fw = new FileWriter(arquivo, true);
          BufferedWriter bw = new BufferedWriter(fw);
          
          String nome = cliente.getNome();
          String cpf = cliente.getCpf();
          String endereco = cliente.getEndereco();
          String telefone = cliente.getTelefone();
          String email = cliente.getEmail();
 
          bw.write(nome + ";" + cpf + ";" + endereco + ";" + telefone  + ";" + email); 
          bw.newLine();
 
          bw.close();
          fw.close();
      } catch (IOException e) {}
    } 
    
    public ArrayList<Pessoa> buscarClientes() {
         
      File arquivo = new File("/TMP/clientesDoBanco.txt");
      
      ArrayList<Pessoa> clientesEncontrados = new ArrayList<>();
       
      try {
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
 
        while (br.ready()) {
            String[] linha = br.readLine().split(";"); 
            Pessoa cliente = new Pessoa();
            cliente.setNome(linha[0]);
            cliente.setCpf(linha[1]);
            cliente.setEndereco(linha[2]);
            cliente.setTelefone(linha[3]);
            cliente.setEmail(linha[4]);
            clientesEncontrados.add(cliente);
        }
    
        br.close();
        fr.close();
                
       } catch (IOException e) {
       }
      
       return clientesEncontrados;
    }
    
       
}
