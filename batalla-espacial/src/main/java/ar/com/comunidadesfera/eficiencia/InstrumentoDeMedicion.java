package ar.com.comunidadesfera.eficiencia;

import ar.com.comunidadesfera.eficiencia.registros.Medicion;

/**
 * Interfaz com�n para todos los Instrumentos de Medici�n de M�dulos, que 
 * crean Mediciones.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface InstrumentoDeMedicion {

    /**
     * @return devuelve una nueva Medici�n que tiene los datos recolectados
     *         por el Instrumento de Medici�n.
     */
    Medicion getMedicion();
}
