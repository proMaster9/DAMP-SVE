
package modelo;

/**
 * EQUIPO DAMP-2016
 */

import java.util.ArrayList;

public class VotoMarca extends Voto{
    
    private int idVotoMarca;
    private ArrayList<Candidato> candidato;
    private ArrayList<Double> valorPorcentual;

    public VotoMarca() {
    }

    public VotoMarca(int idVoto, int idJrv) {
        super(idVoto, idJrv);
    }

    public VotoMarca(int idVotoMarca, ArrayList<Candidato> candidato, ArrayList<Double> valorPorcentual) {
        this.idVotoMarca = idVotoMarca;
        this.candidato = candidato;
        this.valorPorcentual = valorPorcentual;
    }

    public VotoMarca(int idVotoMarca, ArrayList<Candidato> candidato, ArrayList<Double> valorPorcentual, int idVoto, int idJrv) {
        super(idVoto, idJrv);
        this.idVotoMarca = idVotoMarca;
        this.candidato = candidato;
        this.valorPorcentual = valorPorcentual;
    }

    public int getIdVotoMarca() {
        return idVotoMarca;
    }

    public void setIdVotoMarca(int idVotoMarca) {
        this.idVotoMarca = idVotoMarca;
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
