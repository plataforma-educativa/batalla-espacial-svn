package ar.com.comunidadesfera.batallaespacial.comandos;

import ar.com.comunidadesfera.batallaespacial.Direccion;
import ar.com.comunidadesfera.batallaespacial.Sustancia;


/**
 * Comando que representa la transferencia de carga. 
 *  
 * @author Mariano Tugnarelli
 *
 */
public interface TransferirCarga extends Comando {


    /**
     * @pre ninguna.
     * @post devuelve la Sustancia transferida.
     * 
     * @return Sustancia transferida.
     */
    Sustancia getSustancia();
    
    /**
     * @pre ninguna.
     * @post devuelve la cantidad de carga transferida.
     * 
     * @return cantidad de carga transferida.
     */
    long getCarga();
    
    /**
     * @pre ninguna.
     * @post devuelve la Direcci�n desde la cual se transfiere la carga.
     *  
     * @return Direcci�n de origen de la transferencia.
     */
    Direccion getDireccionOrigen();
    
    /**
     * @pre ninguna.
     * @post devuelve la Direcci�n hacia la cual se transfiere la carga.
     *  
     * @return Direcci�n de destino de la transferencia.
     */
    Direccion getDireccionDestino();
    

}
