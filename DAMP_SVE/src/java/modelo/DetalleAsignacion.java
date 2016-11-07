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
public class DetalleAsignacion {
    private String departamento;
    private String municipio;
    private String centro;
    private String correlativo;
    private String ciudadano;
    private int idCentro;

    public DetalleAsignacion() {
    }

    public DetalleAsignacion(String departamento, String municipio, String centro, String correlativo, String ciudadano, int idCentro) {
        this.departamento = departamento;
        this.municipio = municipio;
        this.centro = centro;
        this.correlativo = correlativo;
        this.ciudadano = ciudadano;
        this.idCentro = idCentro;
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

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(String ciudadano) {
        this.ciudadano = ciudadano;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

  
    
}
