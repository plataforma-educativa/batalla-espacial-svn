package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import java.util.List;

import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.PilotoControlado;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.Nave;

/**
 * Estrategia utilizada para la construcci�n de Naves para un Participante.
 * 
 * @author Mariano Tugnarelli
 * 
 */
public interface DefinicionDeNaves extends Estrategia {

   /**
    * @pre la Estrategia se encuentra asociada a una Partida.
    * @post construye las Naves para el escuadr�n de Pilotos del Participante
    *       posicion�ndolas en el Tablero.
    *
    * @param participante
    * @param escuadron
    * @return
    */
   List<Nave> asignar(Participante participante, List<BaseEspacial> bases, 
                      List<PilotoControlado> pilotos);

}
