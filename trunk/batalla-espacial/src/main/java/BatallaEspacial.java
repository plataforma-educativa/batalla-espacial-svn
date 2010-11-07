
import ar.com.comunidadesfera.batallaespacial.galaxias.andromeda.BatallaEspacialAndromeda;

/**
 * Batalla Espacial en la Galaxia Andr�meda.
 * Construida en el paquete default para facilitar su utilizaci�n en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BatallaEspacial {

    @SuppressWarnings("unused")
    private ar.com.comunidadesfera.batallaespacial.BatallaEspacial implementacion;
    
    /**
     * @post Inicia la ejecuci�n de la batalla espacial.
     */
    public BatallaEspacial() {
     
        this.implementacion = new BatallaEspacialAndromeda();
    }
}
