package ar.com.comunidadesfera.batallaespacial.juego;

import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;


public interface FabricaDePiezas {

    /**
     * @pre <code>origen</code> posee la representaci�n / identificaci�n 
     *       de una Pieza.
     * @post devuelve una nueva Pieza construida a partir de <code>origen</code>.
     * 
     * @param origen
     * @return
     */
    Pieza crearPieza(String origen);
    
    /**
     * 
     * @return 
     */
    NaveDeCombate crearNaveDeCombate();
    
    /**
     * 
     * @return 
     */
    BaseEspacial crearBaseEspacial();
}
