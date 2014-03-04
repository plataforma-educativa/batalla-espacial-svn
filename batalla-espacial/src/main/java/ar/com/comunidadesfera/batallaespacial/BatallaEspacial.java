package ar.com.comunidadesfera.batallaespacial;

import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;

/**
 * Interfaz que define las instancias de Batalla Espacial.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface BatallaEspacial {

    /**
     * @post inicia la ejecuci�n de una BatallaEspacial.
     */
    void iniciar();
    
    /**
     * @post incluye a <code>observador</code> entre los objetos a ser
     *       notificados ante un cambio en el estado de la instancia.
     *       
     * @param observador
     */
    void agregarObservador(Observador observador);
    
    /**
     * @post inicia una nueva Partida utilizando la Configuraci�n indicada.
     * 
     * @param configuracion
     * @return Partida creada
     */
    Partida jugar(Configuracion configuracion);

    /**
     * BatallaEspacial.Observador define la interfaz para los objetos que deben
     * ser notificados ante un cambio de la BatallaEspacial. 
     *
     */
    public interface Observador {

        /**
         * @pre <code>batallaEspacial</code> ha sido iniciada.
         * 
         * @param batallaEspacial
         */
        void iniciada(BatallaEspacial batallaEspacial);

        /**
         * @pre <code>batallaEspacial</code> comenz� a jugar la Partida <code>partida</code>.
         * @param batallaEspacial
         * @param partida
         */
        void jugando(BatallaEspacial batallaEspacial, Partida partida);
    }

}
