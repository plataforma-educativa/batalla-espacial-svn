package ar.com.comunidadesfera.batallaespacial;

import ar.com.comunidadesfera.batallaespacial.aplicacion.ControlAplicacion;
import ar.com.comunidadesfera.batallaespacial.ui.FrameAplicacion;
import ar.com.comunidadesfera.batallaespacial.ui.VistaAplicacion;
import ar.com.comunidadesfera.batallaespacial.ui.VistaAplicacionThreadSafe;

public class BatallaEspacial {

    private ControlAplicacion control;
    private VistaAplicacion vista;
    
    public BatallaEspacial() {
        
        super();
        this.vista = this.crearVistaAplicacion();
        
        this.control = this.crearControlAplicacion();
        
        /* hace visible el frame */
        this.getVista().setVisible(true);
        
    }

    /**
     * @pre se encuentra inicializada la Vista de la Aplicación.
     * @post instancia y devuelve el ControlAplicación.
     */
    protected ControlAplicacion crearControlAplicacion() {
        
        return new ControlAplicacion(this.getVista());
    }
    
    /**
     * @post instancia y devuelve la VistaAplicación.
     */
    protected VistaAplicacion crearVistaAplicacion() {
        
        return new VistaAplicacionThreadSafe(new FrameAplicacion());
    }

    public ControlAplicacion getControl() {
        
        return this.control;
    }
    
    public VistaAplicacion getVista() {
        
        return this.vista;
    }
}
