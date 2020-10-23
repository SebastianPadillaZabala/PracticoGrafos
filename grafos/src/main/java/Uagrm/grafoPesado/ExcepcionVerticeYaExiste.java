/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uagrm.GrafoPesado;

/**
 *
 * @author Sebastian Padilla
 */
class ExcepcionVerticeYaExiste extends Exception {
    
    public ExcepcionVerticeYaExiste(){
        super();
    }
    
    public ExcepcionVerticeYaExiste(String message){
        super(message);
    }
}
