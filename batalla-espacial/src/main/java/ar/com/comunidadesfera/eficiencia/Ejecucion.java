package ar.com.comunidadesfera.eficiencia;

import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;

public interface Ejecucion {

    /**
     * @return Contador de instrucciones asociado a la Ejecuci�n.
     */
    Contador getContadorDeInstrucciones();

    /**
     * @return M�dulo al que corresponde la Ejecuci�n.
     */
    Modulo getModulo();

    /**
     * @return Problema sobre el que se realiza la Ejecuci�n.
     */
    Problema getProblema();

}
