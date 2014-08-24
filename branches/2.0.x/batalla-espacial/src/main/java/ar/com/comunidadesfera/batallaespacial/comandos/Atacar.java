package ar.com.comunidadesfera.batallaespacial.comandos;

import ar.com.comunidadesfera.batallaespacial.Direccion;

/**
 * Comando que representa la acci�n de AtaqueIndividual.
 * 
 * @author mariano
 *
 */
public interface Atacar extends Comando {

	
	/**
	 * @pre ninguna
	 * @post devuelve la Direcci�n hacia la cual se realiza el ataque.
	 * 
	 * @return Direcci�n de ataque.
	 */
	Direccion getDireccion();
    
}
