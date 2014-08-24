package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import ar.com.comunidadesfera.batallaespacial.juego.Partida;

/**
 * Implementaci�n b�sica de Estrategia.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public abstract class EstrategiaAbstracta implements Estrategia {

    private Partida partida;

    public void setPartida(Partida partida) {

        this.partida = partida;

        this.configurar();
    }

    protected Partida getPartida() {

        return this.partida;
    }
    
    /**
     * @pre la Partida ha sido asignada.
     * @post realiza la configuraci�n de la Estragia una vez que la 
     *       Partida ha sido asignada.
     */
    protected void configurar() {

        /* punto de extensi�n para las subclases */ 
    }
}
