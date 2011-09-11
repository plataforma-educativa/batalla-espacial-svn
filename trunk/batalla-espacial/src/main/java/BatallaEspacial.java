
import ar.com.comunidadesfera.batallaespacial.galaxias.andromeda.BatallaEspacialAndromeda;

/**
 * Batalla Espacial en la Galaxia Andr�meda.
 * Construida en el paquete default para facilitar su utilizaci�n en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BatallaEspacial 
    implements ar.com.comunidadesfera.batallaespacial.BatallaEspacial {

    private static final String DESCRIPCION = "Batalla Espacial iniciada";
    
    private ar.com.comunidadesfera.batallaespacial.BatallaEspacial implementacion;
    
    public BatallaEspacial() {
     
        this.implementacion = new BatallaEspacialAndromeda();
    }

    @Override
    public void iniciar() {

        this.implementacion.iniciar();
    }
    
    @Override
    public String toString() {

        /* Devuelve una descripci�n para que al ser usado desde un int�rprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return DESCRIPCION;
    }
}
