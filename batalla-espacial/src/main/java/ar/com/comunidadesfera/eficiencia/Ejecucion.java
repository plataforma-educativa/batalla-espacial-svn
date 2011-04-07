package ar.com.comunidadesfera.eficiencia;

import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;

public interface Ejecucion {

    /**
     * @return M�dulo al que corresponde la Ejecuci�n.
     */
    Modulo getModulo();

    /**
     * @return Problema sobre el que se realiza la Ejecuci�n.
     */
    Problema getProblema();

    /**
     * @return Contador de instrucciones asociado a la Ejecuci�n.
     */
    Contador contarInstrucciones();
    
    /**
     * @post registra un Observador de la Ejecuci�n.
     * @param observador
     */
    void agregarObservador(Observador observador);

    /**
     * @post da por terminada la Ejecuci�n. Invocaciones posteriores a m�todos
     *       que devuelvan Contadores generar�n exceptiones. 
     */
    void terminar();

    
    /**
     * Interfaz a implementar por aquellos componentes que deseen ser
     * notificados de los eventos generados por una ejecuci�n.
     *
     */
    public interface Observador {
        
        /**
         * @pre <code>instrumento</code> ha sido creado para medir
         *      la Ejecuci�n.
         *      
         * @param ejecucion
         * @param instrumento Instrumento de Medici�n creado.
         */
        void instrumentoDeMedicionCreado(Ejecucion ejecucion, 
                                         InstrumentoDeMedicion instrumento);
        
        /**
         * @pre la Ejecuci�n ha terminado.
         * @param ejecucion 
         */
        void ejecucionTerminada(Ejecucion ejecucion);
        
    }
}
