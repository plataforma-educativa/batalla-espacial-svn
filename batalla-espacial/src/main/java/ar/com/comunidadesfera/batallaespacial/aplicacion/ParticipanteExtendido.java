package ar.com.comunidadesfera.batallaespacial.aplicacion;

import java.awt.Color;

import ar.com.comunidadesfera.batallaespacial.Civilizacion;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Participante;

/**
 * Configuraci�n de los Participantes que incorporan los par�metros de la
 * interfaz gr�fica.
 * 
 * 
 */
public class ParticipanteExtendido extends Participante {

    private Color color;
    
    public ParticipanteExtendido(Civilizacion civilizacion, int naves, 
                        FabricaDePiezas fabrica, String color) {
        super(civilizacion, naves, fabrica);
        this.setColor(Color.decode(color)); 
    }

    public Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

}
