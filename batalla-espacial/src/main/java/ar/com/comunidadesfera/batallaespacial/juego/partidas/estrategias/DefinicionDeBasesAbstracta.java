package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;

/**
 * Implementaci�n b�sica para las Estrategias de Definici�n de Bases.
 * Provee los m�todos b�sicos de construcci�n y disposici�n de Bases. 
 * 
 * @author Mariano Tugnarelli
 *
 */
public abstract class DefinicionDeBasesAbstracta extends DefinicionDePiezasAbstracta 
                                                 implements DefinicionDeBases {

    /**
     * @post construye una Base Espacial para el Participante.
     * @param participante
     * @return Pieza construida.
     */
    protected BaseEspacial construirBaseEspacial(Participante participante) {

        /* crea una base para el participante */
        BaseEspacial base = participante.getFabricaDePiezas().crearBaseEspacial();
        
        base.setCivilizacion(participante.getCivilizacion());
        
        base.agregarObservador(this.getPartida().getReglamentacion()
                                                .getReglamento(base));
        return base;
    }

}
