package ar.com.comunidadesfera.batallaespacial;

import ar.com.comunidadesfera.batallaespacial.comandos.Atacar;
import ar.com.comunidadesfera.batallaespacial.comandos.Avanzar;
import ar.com.comunidadesfera.batallaespacial.comandos.Comando;
import ar.com.comunidadesfera.batallaespacial.comandos.TransferirCarga;

/**
 * El ControlAplicacion act�a como una f�brica de Comandos que se utilizan
 * para manipular una Nave. 
 * Todo ControlAplicacion se encuentra vinculado a una Nave sobre la cual
 * operan los Comandos creados.
 * 
 * @author Mariano Tugnarelli
 */
public interface Control {

    /**
     * @pre <code>direcci�n</code> es distinto de Direccion.ORIGEN.
     * @post construye un Comando para Avanzar en la Direcci�n indicada. 
     * 
     * @param direccion : Direcci�n de avance.
     * @return Comando creado.
     */
    Avanzar avanzar(Direccion direccion);
    
    /**
     * @pre <code>direcci�n</code> es distinto de Direccion.ORIGEN.
     * @post construye un Comando para Atacar en la Direci�n indicada.
     * 
     * @param direccion : Direcci�n de ataque.
     * @return Comando creado.
     */
    Atacar atacar(Direccion direccion);
    
    /**
     * @pre <code>origen</code> y <code>destino</code> son diferentes.
     * @post construye un Comando para TransferirCargaNaveDeCombate <code>carga</code>
     *       desde la Direcci�n <code>origen</code> a la Direcci�n
     *       <code>destino</code>. 
     * 
     * @param origen : Direcci�n de origen.
     * @param destino : Direcci�n de destino.
     * @param sustancia : Sustancia a transferir.
     * @param carga : cantidad de carga transferida.
     * @return Comando creado.
     */
    TransferirCarga transferirCarga(Direccion origen, Direccion destino,
                                    Sustancia sustancia, long carga);

    
    /**
     * @pre ninguna.
     * @post construye un Comando para no realizar ninguna acci�n.
     * 
     * @return Comando creado.
     */
    Comando esperar(); 
    
}
