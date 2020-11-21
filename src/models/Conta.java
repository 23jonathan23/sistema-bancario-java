/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author jonathan.silva
 */
public class Conta {
    private int Numero;
    private String Cpf;
    private int Senha;
    private String DataAbertura;
    private String DataEncerramento;
    private float Saldo;

    /**
     * @return the Numero
     */
    public int getNumero() {
        return Numero;
    }

    /**
     * @param Numero the Numero to set
     */
    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    /**
     * @return the Cpf
     */
    public String getCpf() {
        return Cpf;
    }

    /**
     * @param Cpf the Cpf to set
     */
    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    /**
     * @return the Senha
     */
    public int getSenha() {
        return Senha;
    }

    /**
     * @param Senha the Senha to set
     */
    public void setSenha(int Senha) {
        this.Senha = Senha;
    }

    /**
     * @return the DataAbertura
     */
    public String getDataAbertura() {
        return DataAbertura;
    }

    /**
     * @param DataAbertura the DataAbertura to set
     */
    public void setDataAbertura(String DataAbertura) {
        this.DataAbertura = DataAbertura;
    }

    /**
     * @return the DataEncerramento
     */
    public String getDataEncerramento() {
        return DataEncerramento;
    }

    /**
     * @param DataEncerramento the DataEncerramento to set
     */
    public void setDataEncerramento(String DataEncerramento) {
        this.DataEncerramento = DataEncerramento;
    }

    /**
     * @return the Saldo
     */
    public float getSaldo() {
        return Saldo;
    }

    /**
     * @param Saldo the Saldo to set
     */
    public void setSaldo(float Saldo) {
        this.Saldo = Saldo;
    }
    
}
