package ar.com.comunidadesfera.persistencia;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interceptor para los m�todos marcados como Transaccionales (anotados con
 * Transaccional). Proporciona un contexto transaccional a la ejecuci�n del
 * m�todo, propagando las transacciones, de acuerdo a los calificadores de la
 * anotaci�n Transaccional.
 * Esta clase abstracta permite extender f�cilmente el comportamiento
 * transaccional espec�fico de cada estrategia.
 * 
 * @see InterceptorNuevaTransaccionRequerida
 * 
 * @see http://www.laliluna.de/articles/2011/01/12/jboss-weld-jpa-hibernate.html
 * @see http://seamframework.org/Documentation/WeldAndJPARunningInTomcat
 * @author Mariano Tugnarelli
 */
public abstract class InterceptorTransaccional {

    protected static final Logger logger = LoggerFactory.getLogger(InterceptorTransaccional.class);

    protected ContextoDePersistencia contexto;

    /**
     * Comportamiento (advice) que encierra la ejecuci�n (AroundInvoke)
     * de un m�todo transaccional. 
     * 
     * Implementado como un Template Method para que los descendientes
     * definan el comportamiento espec�fico dependiendo de la estrategia
     * transaccional configurada por el m�todo transaccional que intercepta.
     * 
     * @param invocationContext
     * @return
     * @throws Exception
     */
    @AroundInvoke
    public final Object deliminarTransaccion(InvocationContext invocationContext) throws Exception {
        
        Escenario escenario = new Escenario();
        
        final EntityManager em = this.init(escenario);

        Object result = null;

        try {
            this.beginTry(em, escenario);

            result = invocationContext.proceed();

            this.endTry(em, escenario);

        } catch (Exception e) {
            
            this.rollback(em);
            
            throw e;
            
        } finally {

            this.onFinally(em, escenario);
        }

        return result;
    }

    /**
     * @pre la ejecuci�n del m�todo transaccional est� por iniciarse.
     * @param escenario
     * @return Entity Manager a utilizar por todo los m�todos subsiguientes.
     */
    protected abstract EntityManager init(Escenario escenario);
    
    /**
     * @pre  la ejecuci�n del m�todo transaccional est� por iniciarse y se
     *       ha obtenido un Entity Manager apropiado.
     * @param em
     * @param escenario
     */
    protected void beginTry(EntityManager em, Escenario escenario) {
        
        /* implementaci�n vacia por defecto */
    }
    
    /**
     * @pre la ejecuci�n del m�todo transaccional finaliz� normalmente.
     * @param em
     * @param escenario
     */
    protected void endTry(EntityManager em, Escenario escenario) {
        
        /* implementaci�n vacia por defecto */
    }

    /**
     * @pre  una excepci�n fue capturada al ejecutar el m�todo transaccional.
     * @param em
     * @param escenario
     */
    protected void onCatch(EntityManager em, Escenario escenario) {
        
        /* implementaci�n vacia por defecto */
    }
    
    /**
     * @pre la ejecuci�n del m�todo transaccional termin�.
     * @post cierra el Entity Manager y lo remueve del contexto si es que no fue
     *       propagado.
     * @param em
     * @param escenario
     */
    protected void onFinally(EntityManager em, Escenario escenario) {
        
        if (! escenario.isSesionPropagada()) {
            
            this.contexto.removerEntityManager(em);

            this.close(em);
        }
    }
    
    /**
     * @post inicia la transacci�n.
     * @param em
     */
    protected void begin(EntityManager em) {
        
        logger.debug("Iniciando transacci�n");
        em.getTransaction().begin();
    }
    
    /**
     * @post hace commit de la transacci�n.
     * @param em
     */
    protected void commit(EntityManager em) {
        
        logger.debug("Haciendo commit de la transacci�n");
        em.getTransaction().commit();
    }

    
    protected void setRollbackOnly(EntityManager em) {
        
        logger.debug("Marcando la transacci�n para rollback");
        em.getTransaction().setRollbackOnly();
    }
    
    /**
     * @post cierra el Entity Manager.
     * @param em
     */
    protected void close(final EntityManager em) {
        
        try {
            
            em.close();
            
        } catch (Exception ne) {
            
            logger.warn("No fue posible cerrar el Entity Manager: " +
                        ne.getMessage());
            logger.debug("Exception", ne);
        }
    }

    /**
     * @post si existe una transacci�n activa, realiza el rollback.

     * @param em EntityManager asociado al m�todo transaccional que lanz� 
     *           la excepci�n.
     */
    protected void rollback(EntityManager em) {

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
    
    /**
     * Conjunto de condiciones en las que se lleva adelante la delimitaci�n
     * de un contexto transaccional. 
     * 
     * @author Mariano Tugnarelli
     *
     */
    protected static class Escenario {
        
        private boolean sesionPropagada = false;

        public boolean isSesionPropagada() {
            return sesionPropagada;
        }

        public void setSesionPropagada(boolean sesionPropagada) {
            this.sesionPropagada = sesionPropagada;
        }
    }
}
