package ar.com.comunidadesfera.batallaespacial.juego.partidas;

import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Partida;

/**
 * Fabrica utilizada para construir una Partida a partir de la Configuraci�n
 * dada.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public interface FabricaDePartidas {

    /**
     * @post construye una Partida con la Configuraci�n dada.
     * 
     */
    <T extends Participante> Partida<T> construir(Configuracion<T> configuracion);
}
