package ar.com.comunidadesfera.persistencia.test.transacciones;

import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import ar.com.comunidadesfera.persistencia.ContextoDePersistencia;
import ar.com.comunidadesfera.persistencia.EntityManagerDelegado;

@RunWith(JMock.class)
public class EntityManagerDelegadoTest {

    private Mockery simulador = new JUnit4Mockery();

    private Sequence secuencia;

    private ContextoDePersistencia contextoDePersistencia;
    
    private EntityManager implementacion;
    
    private EntityManagerDelegado delegado;
    
    @Before
    public void inicializar() {
        
        this.contextoDePersistencia = this.simulador.mock(ContextoDePersistencia.class);
        this.implementacion = this.simulador.mock(EntityManager.class);
        this.secuencia = this.simulador.sequence("secuencia");
        this.simulador.checking(this.expectativasComunes());
        
        this.delegado = new EntityManagerDelegado();
        this.delegado.setContexto(this.contextoDePersistencia);
    }
    
    private Expectations expectativasComunes() {
        
        return new Expectations() {{

            allowing(contextoDePersistencia).getEntityManager();
            will(returnValue(implementacion));
        
        }};
    }
    
    /**
     * @post invoca sobre el delegado todos los m�todos ofrecidos por la
     *       interfaz EntityManager.
     *       
     * @throws Exception
     */
    @Test
    public void delegar() throws Exception {

        /* Utiliza reflection para invocar todos los m�todos expuestos 
         * por la interfaz EntityManager y definir las expectativas
         * de delegaci�n */
        
        /* itera por todos los metodos de la interfaz comprobando que se
         * delegan las invocaciones */
        for (Method metodo : EntityManager.class.getDeclaredMethods()) {
            
            /* par�metros del m�todo */
            Object[] parametros = new Object[metodo.getParameterTypes().length];

            /* agrega la expectativa de invocaci�n sobre la implementacion */
            this.simulador.checking(this.expectativasDeInvocar(metodo, parametros));
            
            /* realiza la invocaci�n sobre el delegado */
            metodo.invoke(this.delegado, parametros);
        }
    }
    
    private Expectations expectativasDeInvocar(final Method metodo,
                                               final Object[] parametros) 
        throws Exception {
        
        return new Expectations() {{ 
            
            metodo.invoke(oneOf(implementacion), parametros);
            inSequence(secuencia);
        }};
    }
}
