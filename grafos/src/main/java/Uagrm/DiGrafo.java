/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uagrm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Sebastian Padilla
 */
public class DiGrafo<T extends Comparable<T>> extends Grafo<T> {

    public DiGrafo() {
        super();
    }

    @Override
    public void insertarArista(T verticeOrigen, T verticeDestino) throws ExcepcionAristaYaExiste,
            ExcepcionVerticeNoExiste {
        if (!this.existeVertice(verticeOrigen)) {
            throw new ExcepcionVerticeNoExiste("Vertice origen no existe");
        }
        if (!this.existeVertice(verticeDestino)) {
            throw new ExcepcionVerticeNoExiste("Vertice destino no existe");
        }
        if (this.existeAdyacencia(verticeOrigen, verticeDestino)) {
            throw new ExcepcionAristaYaExiste("Ya existe arista");
        }
        int posicionVerticeOrigen = this.posicionDeVerticie(verticeOrigen);
        int posicionVerticeDestino = this.posicionDeVerticie(verticeDestino);

        List<Integer> adyacentesDelOrigen = this.listDeAdyacencia.get(posicionVerticeOrigen);
        adyacentesDelOrigen.add(posicionVerticeDestino);
        Collections.sort(adyacentesDelOrigen);

    }

    @Override
    public int cantidadDeAristas() {
        int contador = 0;
        for (int i = 0; i < this.listDeAdyacencia.size(); i++) {
            contador = contador + this.listDeAdyacencia.get(i).size();
        }
        return contador;
    }

    @Override
    public void eliminarArista(T verticeOrigen, T verticeDestino) throws ExcepcionAristaNoExiste, ExcepcionVerticeNoExiste {
        super.eliminarArista(verticeOrigen, verticeDestino);
    }

    @Override
    public int gradoDe(T vertice) throws ExcepcionVerticeNoExiste {
        throw new UnsupportedOperationException("No soportado en grafos dirigidos");
    }

    //hacer
    public int gradoDeEntrada(T unVertice) throws ExcepcionVerticeNoExiste {
        int contador = 0;
        int posicionDelVertice = this.posicionDeVerticie(unVertice);
        for (int i = 0; i < this.listDeAdyacencia.size(); i++) {
            List<Integer> adyacentes = this.listDeAdyacencia.get(i);
            for (int x = 0; x < adyacentes.size(); x++) {
                if (posicionDelVertice == adyacentes.get(x)) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public int gradoDeSalida(T unVertice) throws ExcepcionVerticeNoExiste {
        return super.gradoDe(unVertice);
    }

    @Override
    public boolean esConexo() {
        throw new UnsupportedOperationException("No soportado en grafos dirigidos");
    }

    public boolean esFuertementeConexo() {
        List<T> elRecorrido;
        for (T unVertice : this.listaDeVertices) {
            elRecorrido = dfs(unVertice);
            if (elRecorrido.size() != this.cantidadDeVertices()) {
                return false;
            }
        }
        return true;
    }

    public boolean esDebilmenteConexo() {
        boolean b = true;
        List<T> recorrido = new ArrayList<>();
        List<Boolean> marcados = inicializarMarcados();
        int posicionDelVerticeInicial = 0;
        do {
            dfs(recorrido, marcados, posicionDelVerticeInicial);
            if (estanTodosMarcados(marcados)) {
                return true;
            }
            boolean z = false;
            for (int i = 0; i < marcados.size() && z == false; i++) {
                if (marcados.get(i) == false) {
                    List<Integer> adyacentes = this.listDeAdyacencia.get(i);
                    for (int x = 0; x < adyacentes.size() && z == false; x++) {
                        if (marcados.get(adyacentes.get(x)) == true) {
                            z = true;
                            posicionDelVerticeInicial = i;
                        }
                    }
                }
            }
            if (z == false) {
                b = false;
            }
        } while (b == true);
        return false;
    }

    public int contrarIslasDirigidos() {
        boolean b = true;
        int contador = 1;
        List<T> recorrido = new ArrayList<>();
        List<Boolean> marcados = inicializarMarcados();
        int posicionDelVerticeInicial = 0;
        do {
            dfs(recorrido, marcados, posicionDelVerticeInicial);
            if (estanTodosMarcados(marcados)) {
                return contador;
            }
            boolean z = false;
            for (int i = 0; i < marcados.size() && z == false; i++) {
                if (marcados.get(i) == false) {
                    List<Integer> adyacentes = this.listDeAdyacencia.get(i);
                    if(adyacentes.size() != 0){
                    for (int x = 0; x < adyacentes.size() && z == false; x++) {
                        if (marcados.get(adyacentes.get(x)) == false) {
                            z = true;
                            posicionDelVerticeInicial = i;
                            contador++;
                        }
                    }
                    }else{
                        contador++;
                    }
                }
            }
            if (z == false) {
                b = false;
            }
        } while (b == true);
        return contador;
    }

    public int contarIslasDirigidos2() throws ExcepcionVerticeYaExiste {
        Grafo<T> nuevoGrafo = new Grafo<>();
        for (int i = 0; i < this.listaDeVertices.size(); i++) {
            nuevoGrafo.insertarVertice(this.listaDeVertices.get(i));
        }
        for (int i = 0; i < this.listDeAdyacencia.size(); i++) {
            nuevoGrafo.listDeAdyacencia.set(i, this.listDeAdyacencia.get(i));
        }
        int contador = nuevoGrafo.contarIslasNoDirigido();
        return contador;
    }

    public boolean tieneCicloDirigido() {
        List<T> recorrido = new ArrayList<>();
        List<Boolean> marcados = inicializarMarcados();
        for (int i = 0; i < this.listaDeVertices.size(); i++) {
            dfs(recorrido, marcados, i);
            if (estanTodosMarcados(marcados)) {
                return true;
            }
            marcados = this.inicializarMarcados();
        }
        return false;
    }

    public List<T> ordenamientoTopologico() throws ExcepcionVerticeNoExiste, ExcepcionVerticeYaExiste {
        DiGrafo<T> nuevoGrafo = new DiGrafo<>();
        for (int i = 0; i < this.listaDeVertices.size(); i++) {
            nuevoGrafo.insertarVertice(this.listaDeVertices.get(i));
        }
        for (int i = 0; i < this.listDeAdyacencia.size(); i++) {
            nuevoGrafo.listDeAdyacencia.set(i, this.listDeAdyacencia.get(i));
        }

        List<T> resultado = new ArrayList<>();
        Queue<T> colaVertices = new LinkedList<>();
        for (T unVertice : this.listaDeVertices) {
            if (this.gradoDeEntrada(unVertice) == 0) {
                colaVertices.add(unVertice);

            }
        }
        while (!colaVertices.isEmpty()) {
            T verticeEnTurno = colaVertices.poll();
            resultado.add(verticeEnTurno);
            nuevoGrafo.eliminarVertice(verticeEnTurno);
            if (!nuevoGrafo.listaDeVertices.isEmpty()) {
                for (int i = 0; i < nuevoGrafo.cantidadDeVertices(); i++) {
                    T verticeP = nuevoGrafo.listaDeVertices.get(i);
                    if (nuevoGrafo.gradoDeEntrada(verticeP) == 0) {
                        colaVertices.add(verticeP);

                    }
                }
            }
        }

        return resultado;
    }
   
}
