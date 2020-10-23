/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uagrm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Sebastian Padilla
 */
public class Consolita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExcepcionVerticeYaExiste, ExcepcionAristaYaExiste, ExcepcionVerticeNoExiste {
        // TODO code application logic here
        Grafo<String> grafo = new Grafo<>();
        DiGrafo<String> diGrafo = new DiGrafo<>();
        grafo.insertarVertice("A");
        grafo.insertarVertice("B");
        grafo.insertarVertice("C");
        grafo.insertarVertice("D");
   
        grafo.insertarArista("A", "B");
        grafo.insertarArista("B", "C");
        grafo.insertarArista("C", "D");
        grafo.insertarArista("D", "A");

        diGrafo.insertarVertice("A");
        diGrafo.insertarVertice("B");
        diGrafo.insertarVertice("C");
        diGrafo.insertarVertice("D");
        diGrafo.insertarVertice("E");

        diGrafo.insertarArista("A", "B");
        diGrafo.insertarArista("A", "E");
        diGrafo.insertarArista("A", "D");
        diGrafo.insertarArista("A", "C");
        diGrafo.insertarArista("B", "E");
        diGrafo.insertarArista("C", "B");
        diGrafo.insertarArista("C", "D");
        diGrafo.insertarArista("E", "D");

        System.out.println("GRAFO NO DIRIGIDO");
        grafo.mostrar();
        System.out.println("GRAFO DIRIGIDO (DIGRAFO)");
        diGrafo.mostrar();
        System.out.println("1 )grafo dirigido implementar un algoritmo para encontrar si el grafo tiene ciclos");
        System.out.println("2) grafo dirigido implementar un algoritmo para encontrar si es débilmente conexo ");
        System.out.println("3) grafo no dirigido implementar un algoritmo para encontrar si el grafo tiene ciclo");
        System.out.println("4) grafo no dirigido implementar un algoritmo para encontrar el número de islas que hay en el grafo");
        System.out.println("5) grafo dirigido implementar un algoritmo para encontrar el número de islas que hay en el grafo");
        System.out.println("12)  En un grafo dirigido implementar al algoritmo de ordenamiento topológico. "
                + "Debe mostrar cual es el orden de los vértices según este algoritmo.");

        int num = -1;
        Scanner entrada = new Scanner(System.in);
        while (num != 0) {
            num = entrada.nextInt();
            switch (num) {
                case 1:
                    boolean b = diGrafo.tieneCicloDirigido();
                    System.out.println("Tiene ciclo el grafo dirigido: "+ b);
                    break;
                case 2:
                    boolean z = diGrafo.esDebilmenteConexo();
                    System.out.println("Es debilmente conexo: "+z);
                    break;
                case 3:
                    boolean x = grafo.tieneCicloNoDirigidos();
                    System.out.println("Tiene ciclo el grafo no dirigido: "+x);
                    break;
                case 4:
                    int c;
                    c = grafo.contarIslasNoDirigido();
                    System.out.println("Numero de Islas del Grafo No Dirigido: "+c);
                    break;
                case 5:
                    int q,s;
                    s = diGrafo.contrarIslasDirigidos();
                    q = diGrafo.contarIslasDirigidos2();
                    System.out.println("Numero De Islas Del Grafo Dirigido (1era forma): "+ s);
                    System.out.println("Numero De Islas Del Grafo Dirigido (2da forma): "+ q);
                    break;
                case 12:
                    List<String> orden = new ArrayList<>();
                    orden = diGrafo.ordenamientoTopologico();
                    System.out.println("Ordenamiento Topologico: "+ orden);
                    break;
            }
        }

    }

}
