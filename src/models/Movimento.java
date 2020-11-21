
package models;

/**
 *
 * @author jonathan.silva
 */
public class Movimento {
    private int codigo;
    private String Data;
    private String Hora;
    private int Conta;
    private float Valor;
    private TipoMovimento Tipo;
    
    /**
     * @return the Data
     */
    public String getData() {
        return Data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData(String Data) {
        this.Data = Data;
    }

    /**
     * @return the Hora
     */
    public String getHora() {
        return Hora;
    }

    /**
     * @param Hora the Hora to set
     */
    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    /**
     * @return the Conta
     */
    public int getConta() {
        return Conta;
    }

    /**
     * @param Conta the Conta to set
     */
    public void setConta(int Conta) {
        this.Conta = Conta;
    }

    /**
     * @return the Valor
     */
    public float getValor() {
        return Valor;
    }

    /**
     * @param Valor the Valor to set
     */
    public void setValor(float Valor) {
        this.Valor = Valor;
    }

    /**
     * @return the Tipo
     */
    public TipoMovimento getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(TipoMovimento Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
}
