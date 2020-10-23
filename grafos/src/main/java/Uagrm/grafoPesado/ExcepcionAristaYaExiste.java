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
class ExcepcionAristaYaExiste extends Exception {
       public ExcepcionAristaYaExiste(){
        super();
    }
    
    public ExcepcionAristaYaExiste(String message){
        super(message);
    }
}
