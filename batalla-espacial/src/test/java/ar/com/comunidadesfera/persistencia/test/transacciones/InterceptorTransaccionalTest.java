package ar.com.comunidadesfera.persistencia.test.transacciones;

import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.States;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.persistencia.ContextoDePersistencia;
import ar.com.comunidadesfera.persistencia.InterceptorTransaccional;

@RunWith(JMock.class)
public abstract class InterceptorTransaccionalTest<T extends InterceptorTransaccional> {
    
    protected static final String ACTIVA = "transaccion activa";
    protected static final String INACTIVA = "transaccion inactiva";
    
    
    private Mockery simulador = new JUnit4Mockery();
    
    private T interceptor;

    protected InvocationContext contextoDeInvocacion;

    protected ContextoDePersistencia contextoDePersistencia;
    
    /**
     * Sesi�n (EntityManager) existente en el contexto, previo a la invocaci�n
     * del m�todo interceptado.
     */
    protected EntityManager sesionPrevia;
    
    /**
     * Sesi�n (EntityManager) abierto/propagado en la invocaci�n
     * del m�todo interceptado.
     */
    protected EntityManager sesion;
    
    /**
     * Transacci�n (EntityTransaction) activada en la invocaci�n del m�todo
     * interceptado. 
     */
    protected EntityTransaction transaccion;
    
    /**
     * Secuencia principal de invocaciones.
     */
    protected Sequence secuencia;

    /**
     * Estados por los que pasa la transacci�n (ACTIVA / INACTIVA)
     */
    protected States estadoTransaccion;
    
    @Before
    public void inicializarInterceptor() throws Exception {
        
        this.interceptor = this.crearInterceptor();
        
        this.contextoDeInvocacion = this.simulador.mock(InvocationContext.class);
        this.contextoDePersistencia = this.simulador.mock(ContextoDePersistencia.class);
        this.sesion = this.simulador.mock(EntityManager.class, "sesion");
        this.transaccion = this.simulador.mock(EntityTransaction.class, "transaccion");
        this.sesionPrevia = this.simulador.mock(EntityManager.class, "sesionPrevia");
        
        this.secuencia = this.simulador.sequence("invocacion");
        this.estadoTransaccion = this.simulador.states("transaccion").startsAs(INACTIVA);
        
        this.interceptor.setContextoDePersistencia(this.contextoDePersistencia);

        this.simulador.checking(this.expectativasComunes());
    }
    
    /**
     * @return Expectativas comunes a todos los tests.
     */
    private Expectations expectativasComunes() {
        
        return new Expectations() {{

            allowing(sesion).getTransaction();
            will(returnValue(transaccion));
            
            allowing(sesionPrevia).getTransaction();
            will(returnValue(transaccion));
            
            allowing(transaccion).isActive();
            when(estadoTransaccion.is(ACTIVA));
            will(returnValue(true));
            
            allowing(transaccion).isActive();
            when(estadoTransaccion.is(INACTIVA));
            will(returnValue(false));
            
        }};
    }
    
    /**
     * @return Expectativas comunes a todos los tests sin sesi�n.
     */
    private Expectations expectativasComunesSinSesion() { 
        
        return new Expectations() {{
           
            allowing(contextoDePersistencia).buscarEntityManager();
            will(returnValue(null));
            
        }};
    }
    
    /**
     * @return Expectativas comunes a todos los tests con sesi�n. 
     */
    private Expectations expectativasComunesConSesion() {
        
        return new Expectations() {{
    
            allowing(contextoDePersistencia).buscarEntityManager();
            will(returnValue(sesionPrevia));
            
        }};
    }
    
    /**
     * @return Expectativs comunes a todos los test con transacci�n activa.
     */
    private Expectations expectativasComunesConTransaccionActiva() {
        
        return new Expectations() {{

            estadoTransaccion.become(ACTIVA);
        }};
    }
    
    /**
     * @return Expectativs comunes a todos los test sin transacci�n activa.
     */
    private Expectations expectativasComunesSinTransaccionActiva() {
        
        return new Expectations() {{

            estadoTransaccion.become(INACTIVA);
        }};
    }
    
    protected abstract T crearInterceptor();  
    
    /**
     * @post delimita la transacci�n para el contexto de invocaci�n dado,
     *       verificando las expectativas proporcionadas.
     *       
     * @param expectativas
     * @throws Exception
     */
    private void delimitarTransaccion(Expectations... expectativas) 
        throws Exception {
        
        for (Expectations e : expectativas) {
            
            this.simulador.checking(e);
        }
        
        this.interceptor.deliminarTransaccion(this.contextoDeInvocacion);
    }
    
    /**
     * @post delimita la transacci�n en una ejecuci�n en cuyo contexto
     *       NO existe un Entity Manager (Sesi�n de persistencia) creado.
     */
    @Test
    public final void delimitarTransaccionEnEjecucionSinSesion() 
        throws Exception{
        
        this.delimitarTransaccion(this.expectativasComunesSinSesion(),
                                  this.expectativasSinSesion());
    }
    
    /**
     * @post delimita la transacci�n en una ejecuci�n en cuyo contexto
     *       NO existe un Entity Manager (Sesi�n de persistencia) creado
     *       y la invocaci�n arroja una excepci�n.
     */
    @Test
    public final void delimitarTransaccionEnEjecucionSinSesionArrojandoExcepcion() 
        throws Exception {

        try {
            
            this.delimitarTransaccion(this.expectativasComunesSinSesion(),
                                      this.expectativasSinSesionArrojandoException());
            
            /* se espera una excepci�n */
            Assert.fail("Se deber�a haber lanzado una excepci�n en la ejecuci�n");
            
            // No se configura en la anotaci�n @Test(exception = Exception.class) 
            // porque ante un fail se distociona el error
            
        } catch (Exception e) {
            
            /* excepcion esperada */
        }
    }
    
    /**
     * @post delimita la transacci�n en una ejecuci�n en cuyo contexto
     *       existe un Entity Manager (Sesi�n de persistencia) creado
     *       que NO tiene una transacci�n activa. 
     *       
     * @throws Exception
     */
    @Test
    public final void delimitarTransaccionEnEjecucionConSesionSinTransaccionActiva() 
        throws Exception {
        
        this.delimitarTransaccion(this.expectativasComunesConSesion(),
                                  this.expectativasComunesSinTransaccionActiva(),
                                  this.expectativasConSesionSinTransaccionActiva());
    }
    
    /**
     * @post delimita la transacci�n en una ejecuci�n en cuyo contexto
     *       existe un Entity Manager (Sesi�n de persistencia) creado
     *       que NO tiene una transacci�n activa y la invocaci�n
     *       arroja una excepci�n. 
     *       
     * @throws Exception
     */
    @Test
    public final void delimitarTransaccionEnEjecucionConSesionSinTransaccionActivaArrojandoException() 
        throws Exception {

        try {
            
            this.delimitarTransaccion(this.expectativasComunesConSesion(),
                                      this.expectativasComunesSinTransaccionActiva(),
                                      this.expectativasConSesionSinTransaccionActivaArrojandoException());
            
            /* se espera una excepci�n */
            Assert.fail("Se deber�a haber lanzado una excepci�n en la ejecuci�n");
            
            // No se configura en la anotaci�n @Test(exception = Exception.class) 
            // porque ante un fail se distociona el error
            
        } catch (Exception e) {
            
            /* excepcion esperada */
        }

    }
    
    /**
     * @post delimita la transacci�n en una ejecuci�n en cuyo contexto
     *       existe un Entity Manager (Sesi�n de persistencia) creado
     *       que tiene una transacci�n activa. 
     *       
     * @throws Exception
     */
    @Test
    public final void delimitarTransaccionEnEjecucionConTransaccionActiva() 
        throws Exception {
        
        this.delimitarTransaccion(this.expectativasComunesConSesion(),
                                  this.expectativasComunesConTransaccionActiva(),
                                  this.expectativasConTransaccionActiva());
    }
    
    /**
     * @post delimita la transacci�n en una ejecuci�n en cuyo contexto
     *       existe un Entity Manager (Sesi�n de persistencia) creado
     *       que tiene una transacci�n activa y la invocaci�n
     *       arroja una excepci�n. 
     *       
     * @throws Exception
     */
    @Test
    public final void delimitarTransaccionEnEjecucionConTransaccionActivaArrojandoException() 
        throws Exception {
        
        try {
            
            this.delimitarTransaccion(this.expectativasComunesConSesion(),
                                      this.expectativasComunesConTransaccionActiva(),
                                      this.expectativasConTransaccionActivaArrojandoException());
            
            /* se espera una excepci�n */
            Assert.fail("Se deber�a haber lanzado una excepci�n en la ejecuci�n");
            
            // No se configura en la anotaci�n @Test(exception = Exception.class) 
            // porque ante un fail se distociona el error
            
        } catch (Exception e) {
            
            /* excepcion esperada */
        }
    }
    
    protected abstract Expectations expectativasSinSesion()
        throws Exception;

    protected abstract Expectations expectativasSinSesionArrojandoException()
        throws Exception;
    
    protected abstract Expectations expectativasConSesionSinTransaccionActiva()
        throws Exception;

    protected abstract Expectations expectativasConSesionSinTransaccionActivaArrojandoException()
        throws Exception;

    protected abstract Expectations expectativasConTransaccionActiva()
        throws Exception;

    protected abstract Expectations expectativasConTransaccionActivaArrojandoException()
        throws Exception;

}


