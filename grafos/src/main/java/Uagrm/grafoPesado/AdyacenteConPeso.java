/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uagrm.grafoPesado;

/**
 *
 * @author Sebastian Padilla
 */
public class AdyacenteConPeso implements Comparable<AdyacenteConPeso> {

    private int indiceVertice;
    private double costo;

    public AdyacenteConPeso(int indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public AdyacenteConPeso(int indiceVertice, double costo) {
        this.indiceVertice = indiceVertice;
        this.costo = costo;
    }

    public int getIndiceVertice() {
        return indiceVertice;
    }

    public void setIndiceVertice(int indiceVertice) {
        this.indiceVertice = indiceVertice;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public int compareTo(AdyacenteConPeso vert) {
        Integer esteVertice = this.indiceVertice;
        Integer elOtroVertice = vert.indiceVertice;
        return esteVertice.compareTo(elOtroVertice);
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.indiceVertice;
        return hash;
    }
    
    @Override
    public boolean equals(Object otro){
        if(otro == null){
            return false;
        }
        if(getClass() != otro.getClass()){
            return false;
        }
        AdyacenteConPeso other = (AdyacenteConPeso) otro;
        return this.indiceVertice == other.indiceVertice;
    }

}
