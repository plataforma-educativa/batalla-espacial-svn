
import ar.com.comunidadesfera.plataformaeducativa.AplicacionBatallaEspacialInteractiva;
import ar.com.comunidadesfera.plataformaeducativa.JuegoInteractivo;

/**
 * Batalla Espacial en la Galaxia Andr�meda.
 * Construida en el paquete default para facilitar su utilizaci�n en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class BatallaEspacial extends JuegoInteractivo{

    public BatallaEspacial() {

        super(AplicacionBatallaEspacialInteractiva.class);
    }

    @Override
    public String toString() {

        /* Devuelve una descripci�n para que al ser usado desde un int�rprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return "Batalla Espacial";
    }
}
