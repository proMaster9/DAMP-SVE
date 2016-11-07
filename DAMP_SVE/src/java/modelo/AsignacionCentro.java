/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author carlos
 */
public class AsignacionCentro {
    private String departamento;
    private String municipio;
    private int totalCentros;
    private int totalVotantes;
    private int votantesPorCentro;

    public AsignacionCentro() {
    }

    public AsignacionCentro(String departamento, String municipio, int totalCentros, int totalVotantes, int votantesPorMunicipio) {
        this.departamento = departamento;
        this.municipio = municipio;
        this.totalCentros = totalCentros;
        this.totalVotantes = totalVotantes;
        this.votantesPorCentro = votantesPorMunicipio;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getTotalCentros() {
        return totalCentros;
    }

    public void setTotalCentros(int totalCentros) {
        this.totalCentros = totalCentros;
    }

    public int getTotalVotantes() {
        return totalVotantes;
    }

    public void setTotalVotantes(int totalVotantes) {
        this.totalVotantes = totalVotantes;
    }

    public int getVotantesPorCentro() {
        return votantesPorCentro;
    }

    public void setVotantesPorCentro(int votantesPorMunicipio) {
        this.votantesPorCentro = votantesPorMunicipio;
    }
    
}
