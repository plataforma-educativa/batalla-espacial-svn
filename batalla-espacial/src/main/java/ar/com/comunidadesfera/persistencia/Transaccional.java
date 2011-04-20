package ar.com.comunidadesfera.persistencia;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.InterceptorBinding;

/**
 * Define un m�todo/clase (todos los m�todos de una clase) como transaccionales.
 * Un m�todo transaccional se ejecuta en el contexto de una trasacci�n JPA. Si
 * el m�todo finaliza normalmente se hace commit de la transacci�n. Si el 
 * m�todo lanza una excepci�n se hace rollback de la transacci�n.
 * 
 * @see http://download.oracle.com/javaee/5/api/javax/ejb/TransactionAttribute.html
 * @author Mariano Tugnarelli
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Transaccional {

    /**
     * Define la estrategia para manipular el contexto transaccional.
     */
    EstrategiaTransaccional estrategia() default EstrategiaTransaccional.REQUERIDA;
    
}
