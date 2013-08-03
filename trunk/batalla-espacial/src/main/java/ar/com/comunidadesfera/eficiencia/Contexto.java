package ar.com.comunidadesfera.eficiencia;

public interface Contexto {

    /**
     * @post activa o desactiva la persistencia en los servicio de Eficiencia.
     *       
     * @param esPersistente
     */
    void setPersistente(boolean esPersistente);

    /**
     * @post indica si la persistencia en los servicios de Eficiencia est�
     *       activada o desactivada.
     * 
     * @return
     */
    boolean isPersistente();

    /**
     * @pre  <code>modulo</code> identifica un�vocamente el m�dulo a medir.
     *       <code>problema</code> identifica un�vocamente el problema que el m�dulo resuelve.
     * @post inicia una nueva Ejecuci�n para el m�dulo indicado sobre
     *       el problema del tama�o indicado.
     * 
     * @param modulo identificador del m�dulo a medir.
     * @param problema identificador del problema que se resuelve.
     * @param tama�o tama�o del problema.
     * @return
     */
    Ejecucion iniciarEjecucion(String modulo, String problema, long tama�o);
}
