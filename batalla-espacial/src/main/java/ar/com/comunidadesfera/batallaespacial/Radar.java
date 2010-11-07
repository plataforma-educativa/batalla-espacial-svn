package ar.com.comunidadesfera.batallaespacial;

/**
 * El Radar da informaci�n del contexto en el que se 
 * encuentra una Nave.
 * 
 * @author Mariano Tugnarelli
 */
public interface Radar {

    /**
	 * @pre <code>direccion</code> no es nulo.
	 * @post devuelve un Reporte con los datos acerca de la 
     *       posici�n contigua en la Direccion dada.
	 *  
	 * @param direccion : Direcci�n de la cual se quiere obtener el Reporte. 
	 * @return Reporte de datos.
	 */
	Reporte getReporte(Direccion direccion);
}
