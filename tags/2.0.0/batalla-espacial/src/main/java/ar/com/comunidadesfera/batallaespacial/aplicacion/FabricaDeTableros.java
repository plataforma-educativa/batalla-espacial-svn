package ar.com.comunidadesfera.batallaespacial.aplicacion;

import java.io.InputStream;

import ar.com.comunidadesfera.batallaespacial.juego.Configuracion;
import ar.com.comunidadesfera.batallaespacial.juego.FabricaDePiezas;
import ar.com.comunidadesfera.batallaespacial.juego.Tablero;

/**
 * La FabricaDeTableros define la interfaz para la creaci�n
 * de Tableros a partir de una FabricaDePiezas especificada. 
 * @author mariano
 *
 */
public interface FabricaDeTableros {

    /**
     * @pre ha sido especificada la Fabrica de Piezas a utilizar. 
     * @post construye un Tablero.
     * 
     * @return Tablero  
     */
    @Deprecated
    Tablero crearTablero();

    /**
     * @pre ha sido especificada la Fabrica de Piezas a utilizar. 
     * @post construye un Tablero.
     *
     * @param configuracion 
     * @return nuevo tablero creado a partir de la configuraci�n dada.  
     */
    Tablero crearTablero(Configuracion configuracion, InputStream origen);
    
    /**
     * Asigna la Fabrica de Piezas especificada, que ser� utilizada
     * en la construcci�n del Tablero.
     * 
     * @param fabricaDePiezas
     */
    void setFabricaDePiezas(FabricaDePiezas fabricaDePiezas);
}
