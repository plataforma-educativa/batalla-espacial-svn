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
     * @pre <code>nombreModulo</code> identifica univocamente el m�dulo
     *       a medir.
     * @post inicia una nueva Ejecuci�n para el m�dulo indicado sobre
     *       un problema del tama�o indicado.
     * 
     * @param nombreModulo identificador del m�dulo a medir.
     * @param tama�o tama�o del problema.
     * @return
     */
    Ejecucion iniciarEjecucion(String nombreModulo, long[] tama�o);

}
