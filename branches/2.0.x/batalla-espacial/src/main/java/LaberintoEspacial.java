import ar.com.comunidadesfera.plataformaeducativa.AplicacionBatallaEspacialLaberinto;
import ar.com.comunidadesfera.plataformaeducativa.JuegoInteractivo;



/**
 * Batalla Espacial en la Galaxia Magallanes, que se presenta como
 * un laberinto de Asteroides en el espacio desde la Base hasta el Contenedor
 * buscado.
 * 
 * Construida en el paquete default para facilitar su utilizaci�n en los
 * cursos iniciales.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class LaberintoEspacial extends JuegoInteractivo {
    
    public LaberintoEspacial() {
        
        super(AplicacionBatallaEspacialLaberinto.class);
    }
    
    @Override
    public String toString() {

        /* Devuelve una descripci�n para que al ser usado desde un int�rprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return "Laberinto Espacial";
    }
}
