package ar.com.comunidadesfera.persistencia;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interceptor para los m�todos marcados como Transaccionales (anotados con
 * Transaccional). Proporciona un contexto transaccional a la ejecuci�n del
 * m�todo, propagando las transacciones, de acuerdo a los calificadores de la
 * anotaci�n Transaccional.
 * 
 * @see http://www.laliluna.de/articles/2011/01/12/jboss-weld-jpa-hibernate.html
 * @see http://seamframework.org/Documentation/WeldAndJPARunningInTomcat
 * @author Mariano Tugnarelli
 */
@Interceptor
@Transaccional
public class InterceptorTransaccional {

    private static final Logger logger = LoggerFactory.getLogger(InterceptorTransaccional.class);

    private ContextoDePersistencia contexto;

    @AroundInvoke
    public Object runInTransaction(InvocationContext invocationContext) throws Exception {
        
        logger.debug("Creando Entity Manager");
        final EntityManager em = this.contexto.agregarEntityManager();

        Object result = null;

        try {
            
            logger.debug("Iniciando transacci�n");
            em.getTransaction().begin();

            result = invocationContext.proceed();

            logger.debug("Haciendo commit de la transacci�n");
            em.getTransaction().commit();

        } catch (Exception e) {
            
            this.rollback(em);
            
            throw e;
            
        } finally {
            
            this.close(em);
        }

        return result;
    }

    /**
     * @pre la ejecuci�n del m�todo transaccional termin�.
     * @post cierra el Entity Manager.
     * 
     * @param em
     */
    private void close(final EntityManager em) {
        
        if (em != null) {
            
            contexto.removerEntityManager(em);

            try {
                
                em.close();
                
            } catch (Exception ne) {
                
                logger.warn("No fue posible cerrar el Entity Manager: " +
                            ne.getMessage());
                logger.debug("Exception", ne);
            }
        }
    }

    /**
     * @pre  una excepci�n fue capturada al ejecutar el m�todo transaccional.
     * @post si existe una transacci�n activa, realiza el rollback.

     * @param em EntityManager asociado al m�todo transaccional que lanz� 
     *           la excepci�n.
     */
    private void rollback(EntityManager em) {

        try {
            
            if (em.getTransaction().isActive()) {
                
                logger.debug("Haciendo rollback de la transacci�n.");
                
                em.getTransaction().rollback();
            }
            
        } catch (Exception ne) {
            
            logger.warn("No se ha podido hacer rollback de la transacci�n: " + 
                        ne.getMessage());
            logger.debug("Exception", ne);
        }
    }
    
    @Inject
    public void setContextoDePersistencia(ContextoDePersistencia contexto) {
        
        this.contexto = contexto;
    }
}
