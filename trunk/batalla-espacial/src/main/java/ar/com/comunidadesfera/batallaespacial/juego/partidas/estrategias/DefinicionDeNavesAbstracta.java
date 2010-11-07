package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import ar.com.comunidadesfera.batallaespacial.Piloto;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.piezas.nave.NaveDeCombate;

/**
 * Implementaci�n b�sica para las Estrategias de Definici�n de Naves.
 * Provee los m�todos b�sicos de construcci�n y disposici�n de Naves. 
 * 
 * @author Mariano Tugnarelli
 *
 */
public abstract class DefinicionDeNavesAbstracta extends DefinicionDePiezasAbstracta 
                                                 implements DefinicionDeNaves {

    /**
     * @post construye una Nave de Combate para el Piloto del Participante.
     * @return Pieza construida.
     */
    protected NaveDeCombate construirNaveDeCombate(Participante participante, 
                                                   Piloto piloto) {

        /* construye la Nave y le asigna el Radar */
        NaveDeCombate nave = participante.getFabricaDePiezas().crearNaveDeCombate();

        /* reglamento para la Nave de Combate */
        nave.agregarObservador(this.getPartida().getReglamentacion().getReglamento(nave));
        
        nave.setRadar(this.getPartida().getControlDeRadar().crearRadar(nave));
        
        /* vincula el piloto con la Nave */
        nave.setPiloto(piloto);
        
        return nave;
    }
    
}
