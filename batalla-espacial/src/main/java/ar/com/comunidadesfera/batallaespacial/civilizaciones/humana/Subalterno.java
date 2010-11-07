package ar.com.comunidadesfera.batallaespacial.civilizaciones.humana;

import java.util.Queue;

import ar.com.comunidadesfera.batallaespacial.Soldado;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.Orden;

/**
 * Perspectiva de un Soldado que act�a bajo las �rdenes de un superior.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Subalterno<S extends Soldado> extends Soldado {

    /**
     * @post devuelve el Soldado al que reporta.
     */
    S getSuperior();

    /**
     * @post devuelve la cola de ordenes que el soldado realizar�. 
     */
    Queue<Orden> getOrdenes();
}
