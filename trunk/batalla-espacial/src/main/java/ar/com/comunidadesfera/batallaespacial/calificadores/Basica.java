package ar.com.comunidadesfera.batallaespacial.calificadores;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Calificador para una implementaci�n b�sica, por defecto, de una interfaz.
 * 
 * Aplicado a un clase indica que corresponde con una implementaci�n
 * b�sica de una interfaz.
 * 
 * Aplicado a un m�todo, un par�mero o un atributo indica que debe inyectarse
 * la implementaci�n as� anotada.
 * 
 * 
 * @author Mariano Tugnarelli
 *
 */
@Qualifier
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Basica {

}
