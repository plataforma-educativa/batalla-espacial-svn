package ar.com.comunidadesfera.batallaespacial.juego.escenarios;

import java.util.Set;

import ar.com.comunidadesfera.batallaespacial.Notificacion;

/**
 * Un Escenario representa la interacci�n entre Piezas.
 * Mantiene la informaci�n del contexto del iteracci�n y
 * determina los efectos entre los participantes.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Escenario {

    /**
     * @pre ninguna.
     * @post realiza la interacci�n entre los particiapantes;
     *
     */
    void ejecutar();
    
    /**
     * @return Notificaciones vinculadas a la interacci�n.
     */
    Set<Notificacion> getNotificaciones();
}
