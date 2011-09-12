package ar.com.comunidadesfera.batallaespacial.calificadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Calificador para una implementaci�n seleccionada din�micamente de una interfaz.
 * 
 * Dise�ada para calificar m�todos anotados como @Produces que seleccionan la implementaci�n
 * concreta din�micamente. 
 * 
 * Aplicado a un m�todo, un par�mero o un atributo indica que debe inyectarse
 * una implementaci�n seleccionada din�micamente.
 * 
 * 
 * @author Mariano Tugnarelli
 *
 */
@Qualifier
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dinamica {

}
