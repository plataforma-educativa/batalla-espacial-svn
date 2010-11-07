package ar.com.comunidadesfera.batallaespacial;

public interface Civilizacion {

    /**
     * Devuelve el nombre que identifica la Cilizaci�n.
     * 
     * @return nombre de la Civilizaci�n.
     */
	String getNombre();

    /**
     * @pre ninguna.
     * @post construye un Comandante para la Civilizaci�n.   
     * 
     * @return Comandante de la Civilizaci�n.
     */
    Comandante construirComandante();
    
}
