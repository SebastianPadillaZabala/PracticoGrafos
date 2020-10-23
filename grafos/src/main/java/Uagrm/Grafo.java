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
public class Grafo<T extends Comparable<T>> {

    protected List<T> listaDeVertices;
    protected List<List<Integer>> listDeAdyacencia;
    static final int POSICION_INVALIDA = -1;

    public Grafo() {
        this.listaDeVertices = new ArrayList<>();
        this.listDeAdyacencia = new ArrayList<>();
    }

    public void insertarVertice(T vertice) throws ExcepcionVerticeYaExiste {
        if (existeVertice(vertice)) {
            throw new ExcepcionVerticeYaExiste("Vertice ya esta en el grafo");
        }
        this.listaDeVertices.add(vertice);
        List<Integer> listaDeAdyacentesDelVertice = new ArrayList<>();
        this.listDeAdyacencia.add(listaDeAdyacentesDelVertice);

    }

    public int cantidadDeVertices() {
        return this.listaDeVertices.size();
    }

    public boolean existeVertice(T vertice) {
        return this.posicionDeVerticie(vertice) != POSICION_INVALIDA;
    }

    protected int posicionDeVerticie(T vertice) {
        for (int i = 0; i < this.listaDeVertices.size(); i++) {
            T verticeActual = this.listaDeVertices.get(i);
            if (verticeActual.compareTo(vertice) == 0) {
                return i;
            }
        }
        return POSICION_INVALIDA;
    }

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

        if (posicionVerticeOrigen != posicionVerticeDestino) {
            List<Integer> adyacentesDelDestino = this.listDeAdyacencia.get(posicionVerticeDestino);
            adyacentesDelDestino.add(posicionVerticeOrigen);
            Collections.sort(adyacentesDelDestino);
        }
    }

    public boolean existeAdyacencia(T verticeOrigen, T verticeDestino) {
        int posicionVerticeOrigen = this.posicionDeVerticie(verticeOrigen);
        int posicionVerticeDestino = this.posicionDeVerticie(verticeDestino);
        List<Integer> adyacentesDelOrigen = this.listDeAdyacencia.get(posicionVerticeOrigen);
        return adyacentesDelOrigen.contains(posicionVerticeDestino);
    }
    //hacer

    public int cantidadDeAristas() {
        int nroAristas = 0;
        int nroLazos = 0;
        for (int i = 0; i < this.listDeAdyacencia.size(); i++) {
            List<Integer> lista = this.listDeAdyacencia.get(i);
            nroAristas = nroAristas + lista.size();
            if (lista.contains(i)) {
                nroLazos = nroLazos + 1;
                nroAristas = nroAristas - 1;
            }
        }
        return ((nroAristas / 2) + nroLazos);
    }

    public void eliminarVertice(T verticeaEliminar) throws ExcepcionVerticeNoExiste {
        if (!this.existeVertice(verticeaEliminar)) {
            throw new ExcepcionVerticeNoExiste("El vertice a Eliminar No existe");
        }
        int posicionDelVerticeAEliminar = this.posicionDeVerticie(verticeaEliminar);
        this.listaDeVertices.remove(posicionDelVerticeAEliminar);
        this.listDeAdyacencia.remove(posicionDelVerticeAEliminar);
        for (List<Integer> adyacentesDeUnVertice : this.listDeAdyacencia) {
            if (adyacentesDeUnVertice.contains(posicionDelVerticeAEliminar)) {
                int posicionDelVerticeComoAdyacente = adyacentesDeUnVertice.indexOf(posicionDelVerticeAEliminar);
                adyacentesDeUnVertice.remove(posicionDelVerticeComoAdyacente);
            }
            for (int i = 0; i < adyacentesDeUnVertice.size(); i++) {
                int posicionDeAdyacente = adyacentesDeUnVertice.get(i);
                if (posicionDeAdyacente > posicionDelVerticeAEliminar) {
                    posicionDeAdyacente--;
                    adyacentesDeUnVertice.set(i, posicionDeAdyacente);
                }
            }
        }
    }

    public void eliminarArista(T verticeOrigen, T verticeDestino) throws ExcepcionAristaNoExiste,
            ExcepcionVerticeNoExiste {
        if (!this.existeAdyacencia(verticeOrigen, verticeDestino)) {
            throw new ExcepcionAristaNoExiste("No Existe Arista");
        }
        if (!this.existeVertice(verticeOrigen)) {
            throw new ExcepcionVerticeNoExiste("No Existe Vertice Origen");
        }
        if (!this.existeVertice(verticeDestino)) {
            throw new ExcepcionVerticeNoExiste("No Existe Vertice Destino");
        }
        int posicionDelVerticeOrigen = this.posicionDeVerticie(verticeOrigen);
        int posicionDelVerticeDestino = this.posicionDeVerticie(verticeDestino);
        List<Integer> adyacentesDelOrigen = this.listDeAdyacencia.get(posicionDelVerticeOrigen);
        adyacentesDelOrigen.remove(posicionDelVerticeDestino);
        List<Integer> adyacentesDelDestino = this.listDeAdyacencia.get(posicionDelVerticeDestino);
        adyacentesDelDestino.remove(posicionDelVerticeOrigen);
    }

    public int gradoDe(T vertice) throws ExcepcionVerticeNoExiste {
        if (!this.existeVertice(vertice)) {
            throw new ExcepcionVerticeNoExiste("El Vertice No Existe");
        }
        int posicionDelVertice = this.posicionDeVerticie(vertice);
        List<Integer> adyacentesDelVertice = this.listDeAdyacencia.get(posicionDelVertice);
        return adyacentesDelVertice.size();
    }

    public List<T> bfs(T verticeInicial) {
        List<T> recorrido = new ArrayList<>();
        if (!this.existeVertice(verticeInicial)) {
            return recorrido;
        }
        List<Boolean> marcados = inicializarMarcados();
        Queue<T> colaVertices = new LinkedList<>();
        colaVertices.offer(verticeInicial);
        int posicionDelVerticeInicial = this.posicionDeVerticie(verticeInicial);
        marcarVertice(marcados, posicionDelVerticeInicial);
        do {
            T verticeEnTurno = colaVertices.poll();
            recorrido.add(verticeEnTurno);
            int posicionVerticeEnTurno = this.posicionDeVerticie(verticeEnTurno);
            List<Integer> adyacenciasDelVerticeEnTurno = this.listDeAdyacencia.get(posicionVerticeEnTurno);
            for (Integer posicionDeAdyacente : adyacenciasDelVerticeEnTurno) {
                if (!estaMarcadoElVertice(marcados, posicionDeAdyacente)) {
                    colaVertices.offer(this.listaDeVertices.get(posicionDeAdyacente));
                    marcarVertice(marcados, posicionDeAdyacente);
                }
            }
        } while (!colaVertices.isEmpty());
        return recorrido;
    }

    protected void marcarVertice(List<Boolean> marcados, int posicionDelVerticeInicial) {
        marcados.set(posicionDelVerticeInicial, Boolean.TRUE);
    }

    protected List<Boolean> inicializarMarcados() {
        List<Boolean> marcados = new ArrayList<>();
        for (int i = 0; i < this.cantidadDeVertices(); i++) {
            marcados.add(i, Boolean.FALSE);
        }
        return marcados;
    }

    private boolean estaMarcadoElVertice(List<Boolean> marcados, int posicion) {
        return marcados.get(posicion);
    }

    public List<T> dfs(T verticeInicial) {
        List<T> recorrido = new ArrayList<>();
        if (!this.existeVertice(verticeInicial)) {
            return recorrido;
        }
        List<Boolean> marcados = inicializarMarcados();
        int posicionDelVerticeInicial = this.posicionDeVerticie(verticeInicial);
        dfs(recorrido, marcados, posicionDelVerticeInicial);
        return recorrido;
    }

    protected void dfs(List<T> recorrido, List<Boolean> marcados, int posicionEnTurno) {
        marcarVertice(marcados, posicionEnTurno);
        recorrido.add(this.listaDeVertices.get(posicionEnTurno));
        List<Integer> adyacenciasDelVerticeEnTurno = this.listDeAdyacencia.get(posicionEnTurno);
        for (Integer posicionDeAdyacente : adyacenciasDelVerticeEnTurno) {
            if (!estaMarcadoElVertice(marcados, posicionDeAdyacente)) {
                dfs(recorrido, marcados, posicionDeAdyacente);
            }
        }
    }

    public boolean esConexo() {
        List<T> elRecorrido = bfs(this.listaDeVertices.get(0));
        return elRecorrido.size() == this.cantidadDeVertices();
    }

    protected boolean estanTodosMarcados(List<Boolean> marcados) {
        for (int i = 0; i < marcados.size(); i++) {
            if (marcados.get(i) == false) {
                return false;
            }
        }
        return true;
    }

    public int contarIslasNoDirigido() {
        List<T> recorrido = new ArrayList<>();
        List<Boolean> marcados = inicializarMarcados();
        int contador = 1;
        int posicionDelVerticeInicial = 0;
        do {
            dfs(recorrido, marcados, posicionDelVerticeInicial);
            if (estanTodosMarcados(marcados)) {
                return contador;
            }
            posicionDelVerticeInicial = marcados.indexOf(false);
            contador++;
        } while (!estanTodosMarcados(marcados));
        return contador;
    }

    public boolean dfsAyuda(Grafo<T> grafo, int posicionEnTurno, List<Boolean> marcados) throws ExcepcionAristaYaExiste, ExcepcionVerticeNoExiste {
        marcarVertice(marcados, posicionEnTurno);
        List<Integer> adyacenciasDelVerticeEnTurno = this.listDeAdyacencia.get(posicionEnTurno);
        for (Integer posicionDeAdyacente : adyacenciasDelVerticeEnTurno) {
            T verticeInicio = this.listaDeVertices.get(posicionEnTurno);
            T verticeAdyacente = this.listaDeVertices.get(posicionDeAdyacente);
            if (!estaMarcadoElVertice(marcados, posicionDeAdyacente)) {
                grafo.insertarArista(verticeInicio, verticeAdyacente);
                dfsAyuda(grafo, posicionDeAdyacente, marcados);
            } else {
                if (!grafo.existeAdyacencia(verticeInicio, verticeAdyacente)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean tieneCicloNoDirigidos() throws ExcepcionVerticeYaExiste, ExcepcionAristaYaExiste, ExcepcionVerticeNoExiste {
        Grafo<T> grafoAuxiliar = new Grafo<>();
        for (int i = 0; i < this.listaDeVertices.size(); i++) {
            grafoAuxiliar.insertarVertice(this.listaDeVertices.get(i));
        }
        List<Boolean> marcados = this.inicializarMarcados();
        int posicionDelVerticeInicial = 0;
        while (this.dfsAyuda(grafoAuxiliar, posicionDelVerticeInicial, marcados) == false) {
            if (this.estanTodosMarcados(marcados)) {
                return false;
            } else {
                posicionDelVerticeInicial = marcados.indexOf(false);
            }
        }
        return true;
    }
    public void mostrar(){
        System.out.println("VERTICES" + this.listaDeVertices);
        for(int i = 0; i < this.listaDeVertices.size(); i++){
           T vertice = this.listaDeVertices.get(i);
           System.out.print(vertice);
           System.out.print("["+ i + "]");
           System.out.println(this.listDeAdyacencia.get(i));
        }
    }

}
