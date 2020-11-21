
package infra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import models.Movimento;
import models.TipoMovimento;

/**
 *
 * @author jonathan.silva
 */
public class MovimentoInfra {
    
    public void gravarMovimento(Movimento movimento) {
      File arquivo = new File("/TMP/movimentosContasDoBanco.txt"); 
 
      try {
        if (!arquivo.exists()) {
            arquivo.createNewFile(); 
      } } catch (IOException e) {
      }
      
      try {
          FileWriter fw = new FileWriter(arquivo, true);
          BufferedWriter bw = new BufferedWriter(fw);
          
          int codigo = movimento.getCodigo();
          String data = movimento.getData();
          String hora = movimento.getHora();
          String conta = Integer.toString(movimento.getConta());
          String valor = Float.toString(movimento.getValor());
          String tipo = movimento.getTipo().name();

          bw.write(codigo + ";" + data + ";" + hora + ";" + conta + ";" + valor  + ";" + tipo); 
          bw.newLine();
 
          bw.close();
          fw.close();
      } catch (IOException e) {}
    } 
    
    public ArrayList<Movimento> buscarMovimentacoes() {
         
      File arquivo = new File("/TMP/movimentosContasDoBanco.txt"); 
      
      ArrayList<Movimento> movimentosDeContasEncontrados = new ArrayList<>();
       
      try {
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
 
        while (br.ready()) {
            String[] linha = br.readLine().split(";"); 
            Movimento movimento = new Movimento();
            
            movimento.setCodigo(Integer.parseInt(linha[0]));
            movimento.setData(linha[1]);
            movimento.setHora(linha[2]);
            movimento.setConta(Integer.parseInt(linha[3]));
            movimento.setValor(Float.parseFloat(linha[4]));
            movimento.setTipo(
                    movimento.getCodigo() == TipoMovimento.DEPOSITO.ordinal()
                        ?   TipoMovimento.DEPOSITO
                            :   TipoMovimento.SAQUE
            );
        
            movimentosDeContasEncontrados.add(movimento);
        }
    
        br.close();
        fr.close();
                
       } catch (IOException e) {
       }
      
       return movimentosDeContasEncontrados;
    }
    
}
