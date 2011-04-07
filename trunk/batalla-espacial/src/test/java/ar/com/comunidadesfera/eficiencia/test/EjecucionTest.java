package ar.com.comunidadesfera.eficiencia.test;

import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.SIMPLE_10;
import static ar.com.comunidadesfera.eficiencia.test.Datos.Ejecucion.SIMPLE_20;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.InstrumentoDeMedicion;
import ar.com.comunidadesfera.eficiencia.excepciones.EjecucionTerminadaException;
import ar.com.comunidadesfera.eficiencia.instrumentos.Contador;
import ar.com.comunidadesfera.eficiencia.registros.Dimension;
import ar.com.comunidadesfera.eficiencia.registros.Medicion;
import ar.com.comunidadesfera.eficiencia.registros.Medida;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;
import ar.com.comunidadesfera.eficiencia.registros.Unidad;

public class EjecucionTest extends TestBasico {

    private Ejecucion ejecucion;
    
    @Test
    public void getModulo() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre, 
                                                        SIMPLE_10.tama�o);
        
        Modulo modulo = this.ejecucion.getModulo();
        
        Assert.assertThat("nombre del m�dulo",
                          modulo.getNombre(), 
                          Matchers.is(SIMPLE_10.modulo.nombre));
        
        this.ejecucion.terminar();
    }
    
    @Test
    public void getProblema() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_10.modulo.nombre,
                                                        SIMPLE_10.tama�o);
        
        Problema problema = this.ejecucion.getProblema();
        Assert.assertThat(problema, Matchers.notNullValue());
        
        List<Dimension> dimensiones = problema.getDimensiones();
        Assert.assertThat("dimensiones",
                          dimensiones, 
                          Matchers.notNullValue());
        Assert.assertThat("cantidad de dimensiones", 
                          dimensiones.size(), 
                          Matchers.is(SIMPLE_10.tama�o.length));
        
        for (int i = 0; i < SIMPLE_10.tama�o.length; i++) {
            
            Dimension dimension = dimensiones.get(i);
            
            Assert.assertThat("nombre de la dimensi�n " + i,
                    dimension.getNombre(), 
                              Matchers.nullValue());
            Assert.assertThat("valor de la dimensi�n " + i,
                              dimension.getValor(), 
                              Matchers.is(SIMPLE_10.tama�o[i]));
        }
        
        Assert.assertThat("cantidad de dimensiones", 
                          problema.dimensiones(), 
                          Matchers.is(SIMPLE_10.tama�o.length));
        
        for (int i = 0; i < SIMPLE_10.tama�o.length; i++) {

            Dimension dimension = problema.getDimension(i);
            
            Assert.assertThat("nombre de la dimensi�n " + i,
                    dimension.getNombre(), 
                              Matchers.nullValue());
            Assert.assertThat("valor de la dimensi�n " + i,
                              dimension.getValor(), 
                              Matchers.is(SIMPLE_10.tama�o[i]));
            
            dimension.setNombre("N" + i);
            
            dimension = problema.getDimension(i);
            
            Assert.assertThat("nombre de la dimensi�n " + i,
                              dimension.getNombre(), 
                              Matchers.is("N" + i));
        }
       
        this.ejecucion.terminar();
    }
    
    @Test
    public void contarInstrucciones() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_20.modulo.nombre,
                                                        SIMPLE_20.tama�o);

        Contador contador = this.ejecucion.contarInstrucciones();
        long cuenta = 0;
        
        for (int dimension = 0; dimension < SIMPLE_20.tama�o.length; dimension++) {
            
            contador.incrementar(2);
            cuenta+=2;
            
            for (int i = 0; i < SIMPLE_20.tama�o[dimension]; i++) {

                contador.incrementar();
                cuenta+=1;
            }
        }
        
        Assert.assertThat(contador, Matchers.is(InstrumentoDeMedicion.class));
        
        InstrumentoDeMedicion instrumento = contador;
        
        Medicion medicion = instrumento.getMedicion();
        
        Assert.assertThat("medici�n", medicion, Matchers.notNullValue());

        Medida medida = medicion.getResultado();
        
        Assert.assertThat("resultado de la medici�n", medida, 
                          Matchers.notNullValue());
        Assert.assertThat("magnitud del resultado", medida.getMagnitud(),
                          Matchers.is(cuenta));
        Assert.assertThat("unidad del resultado", medida.getUnidad(),
                          Matchers.is(Unidad.INSTRUCCIONES));
        
        this.ejecucion.terminar();
    }
    
    
    @Test(expected = EjecucionTerminadaException.class)
    public void throwEjecucionTerminadaException() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(SIMPLE_20.modulo.nombre,
                                                        SIMPLE_20.tama�o);
        
        this.ejecucion.terminar();
        
        this.ejecucion.contarInstrucciones();
    }
}
