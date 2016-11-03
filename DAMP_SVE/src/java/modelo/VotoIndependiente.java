/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class VotoIndependiente extends Voto{
    
    private int idVotoIndependiente;
    private ArrayList<Candidato> candidato;
    private ArrayList<Double> valorPorcentual;

    public VotoIndependiente() {
    }

    public VotoIndependiente(int idVoto, int idJrv) {
        super(idVoto, idJrv);
    }

    public VotoIndependiente(int idVotoIndependiente, ArrayList<Candidato> candidato, ArrayList<Double> valorPorcentual) {
        this.idVotoIndependiente = idVotoIndependiente;
        this.candidato = candidato;
        this.valorPorcentual = valorPorcentual;
    }

    public VotoIndependiente(int idVotoIndependiente, ArrayList<Candidato> candidato, ArrayList<Double> valorPorcentual, int idVoto, int idJrv) {
        super(idVoto, idJrv);
        this.idVotoIndependiente = idVotoIndependiente;
        this.candidato = candidato;
        this.valorPorcentual = valorPorcentual;
    }

    public int getIdVotoIndependiente() {
        return idVotoIndependiente;
    }

    public void setIdVotoIndependiente(int idVotoIndependiente) {
        this.idVotoIndependiente = idVotoIndependiente;
    }

    public ArrayList<Candidato> getCandidato() {
        return candidato;
    }

    public void setCandidato(ArrayList<Candidato> candidato) {
        this.candidato = candidato;
    }

    public ArrayList<Double> getValorPorcentual() {
        return valorPorcentual;
    }

    public void setValorPorcentual(ArrayList<Double> valorPorcentual) {
        this.valorPorcentual = valorPorcentual;
    }
    
    
    
}
