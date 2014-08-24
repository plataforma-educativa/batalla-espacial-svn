package ar.com.comunidadesfera.batallaespacial.comandos;

import ar.com.comunidadesfera.batallaespacial.Direccion;

/**
 * Comando que representa la acci�n de Avance.
 * 
 * @author Mariano Tugnarelli
 *
 */
public interface Avanzar extends Comando {

	/**
	 * @pre ninguna.
	 * @post devuelve la Direcci�n de avance.
	 * 
	 * @return direcci�n en la cual se ejecuta la acci�n de avance.
	 */
	Direccion getDireccion();
	
	/**
	 * @pre ninguna.
	 * @post devuelve el valor del impulso utilizado para avanzar.
	 * 
	 * @return valor de impulso.
	 */
	int getImpulso();
}
