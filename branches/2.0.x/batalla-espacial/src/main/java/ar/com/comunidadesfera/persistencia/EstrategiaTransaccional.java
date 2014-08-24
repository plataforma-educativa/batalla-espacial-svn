package ar.com.comunidadesfera.persistencia;


/**
 * Define las estrategias de manipulaci�n del contexto transaccional en el que 
 * se ejecuta un m�todo.
 *
 * @see http://download.oracle.com/javaee/5/api/javax/ejb/TransactionAttribute.html
 * @author Mariano Tugnarelli
 */
public enum EstrategiaTransaccional {

    /**
     * Transacci�n requerida. Si existe una transacci�n activa se une, sino
     * la inicia.
     */
    REQUERIDA,
    
    /**
     * Nueva transacci�n requerida. Inicia una nueva transacci�n.
     */
    NUEVA_REQUERIDA,
    
    /**
     * Transacci�n soportada. Si existe una transacci�n activa se une, 
     * sino continua sin transacci�n.
     */
    SOPORTADA,
    
    /**
     * Transacci�n no soportada. Si existe una transacci�n la suspende
     * y la reactiva al terminar el m�todo.
     */
    NO_SOPORTADA
}
