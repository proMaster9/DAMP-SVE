
package modelo;

import java.util.ArrayList;

/**
 * EQUIPO DAMP-2016
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
