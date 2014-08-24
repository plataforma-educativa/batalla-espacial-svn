package ar.com.comunidadesfera.batallaespacial.juego.partidas.estrategias;

import java.util.Arrays;
import java.util.List;

import ar.com.comunidadesfera.batallaespacial.juego.CasilleroInvalidoException;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero.Iterador;
import ar.com.comunidadesfera.batallaespacial.piezas.base.BaseEspacial;

/**
 * Implementaci�n de la estrategia de Definici�n de Bases que asigna una �nica
 * BaseEspacial para cada Participante, localizada en la fila y columna con 
 * que se cre� la instancia.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BaseUnica extends DefinicionDeBasesAbstracta {
    
    private String nombre;
    
    private int fila;
    
    private int columna;
    
    public BaseUnica(String nombre, int fila, int columna) {
        
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;
    }
    
    public List<BaseEspacial> asignar(Participante participante) {

        BaseEspacial base = this.construirBaseEspacial(participante);
        base.setNombre(this.nombre);

        Iterador iterador = this.getPartida().getTablero().iterator();
        
        try {
            
            iterador.move(this.fila, this.columna);
           
            this.getPartida().getTablero().colocarPieza(base, iterador);
            
        } catch (CasilleroInvalidoException cie) {
            
            throw new RuntimeException("No se ha posido colocar la pieza en la posici�n indicada del Tablero", cie);
        }

        return Arrays.asList(base);
    }

}
