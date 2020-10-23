/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uagrm;

/**
 *
 * @author Sebastian Padilla
 */
class ExcepcionVerticeNoExiste extends Exception {
     public ExcepcionVerticeNoExiste(){
        super();
    }
    
    public ExcepcionVerticeNoExiste(String message){
        super(message);
    }
}
