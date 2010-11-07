package ar.com.comunidadesfera.batallaespacial.piezas;

import ar.com.comunidadesfera.batallaespacial.comandos.Comando;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;

public abstract class ComandoAbstracto implements Comando {

    
    /**
     * Comando que representa no realizar ninguna operaci�n. 
     */
    public static final Comando ESPERAR = new ComandoAbstracto() {

        public void ejecutar(Partida partida) {
            
            /* no se realiza ninguna operaci�n */
        }

    };
    
    
    /**
     * @see Comando#invertir();
     */
    public Comando invertir() {

        /* por defecto un comando no se puede invertir */
        return null;
    }

}
