package ar.com.comunidadesfera.eficiencia;

import java.util.List;

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
     *         por el Instrumento de Medici�n. La Medici�n devuelta
     *         corresponde con el resultado total del proceso.
     */
    Medicion getMedicion();
    
    /**
     * @return devuelve una lista con todas las Mediciones intermedias,
     *         parciales y totales recolectadas por el Instrumento de
     *         Medici�n.
     */
    List<Medicion> getMediciones();
}
