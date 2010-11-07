package ar.com.comunidadesfera.batallaespacial;


/**
 * Un Soldado es un miembro de una Civilizaci�n que cumplir� un rol
 * espec�fico en la misma.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Soldado {

    /**
     * @pre ninguna.
     * @post devuelve la Civilizaci�n a la que pertenece el Soldado. 
     *  
     * @return Civilizaci�n del Soldado.
     */
    Civilizacion getCivilizacion();

    /**
     * @pre ninguna.
     * @post devuelve el nombre del Soldado.
     * 
     *  @return Nombre del Soldado.
     */
    String getNombre();

}