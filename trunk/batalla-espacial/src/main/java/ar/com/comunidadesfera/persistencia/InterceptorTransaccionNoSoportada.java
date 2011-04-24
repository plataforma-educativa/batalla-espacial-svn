package ar.com.comunidadesfera.persistencia;

import javax.interceptor.Interceptor;
import javax.persistence.EntityManager;

/**
 * Implementaci�n del Interceptor Transaccional para m�todos que 
 * no soportan ser ejecutados en una transacci�n.
 * 
 * @see InterceptorTransaccional
 * @author Mariano Tugnarelli
 *
 */
@Interceptor
@Transaccional(EstrategiaTransaccional.NO_SOPORTADA)
public class InterceptorTransaccionNoSoportada extends InterceptorTransaccional {

    @Override
    protected EntityManager init(Escenario escenario) {
        
        EntityManager entityManager = this.contexto.buscarEntityManager();
        
        /* s�lo comienza una nueva sesi�n si no existe una activa o si
         * la existente ha iniciado una transacci�n, en cuyo caso debe
         * crear una nueva para aislarse de la transacci�n */
        if ((entityManager == null) || (entityManager.getTransaction().isActive())) {
            
            entityManager = this.contexto.agregarEntityManager();
            escenario.setIniciaSesion();
        
        } 

        return entityManager;
    }

}
