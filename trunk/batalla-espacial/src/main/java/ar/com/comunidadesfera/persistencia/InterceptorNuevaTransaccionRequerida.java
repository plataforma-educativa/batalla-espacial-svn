package ar.com.comunidadesfera.persistencia;

import javax.interceptor.Interceptor;
import javax.persistence.EntityManager;

/**
 * Implementaci�n del Interceptor Transaccional para m�todos que 
 * requieren ser ejecutados en una nueva transacci�n.
 * 
 * @see InterceptorTransaccional
 * @author Mariano Tugnarelli
 *
 */
@Interceptor
@Transaccional(estrategia = EstrategiaTransaccional.NUEVA_REQUERIDA)
public class InterceptorNuevaTransaccionRequerida extends InterceptorTransaccional {

    @Override
    protected EntityManager init(Escenario escenario) {

        escenario.setSesionPropagada(false);

        return this.contexto.agregarEntityManager();
    }

    @Override
    protected void beginTry(EntityManager em, Escenario escenario) {
        
        if (! escenario.isSesionPropagada()) {
            
            this.begin(em);
        }
    }

    @Override
    protected void endTry(EntityManager em, Escenario escenario) {
        
        if (! escenario.isSesionPropagada()) {
            
            this.commit(em);
        }
    }

    @Override
    protected void onCatch(EntityManager em, Escenario escenario) {

        if (escenario.isSesionPropagada()) {
            
            this.setRollbackOnly(em);
            
        } else {
            
            this.rollback(em);
        }
    }

}
