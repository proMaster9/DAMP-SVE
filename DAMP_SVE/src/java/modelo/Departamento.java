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
public class Departamento {
    private int idDepartamento;
    private String departamento;

    public Departamento() {
    }

    public Departamento(int idDepartamento, String departamento) {
        this.idDepartamento = idDepartamento;
        this.departamento = departamento;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
}
