package ar.com.comunidadesfera.eficiencia.test;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.registros.Dimension;
import ar.com.comunidadesfera.eficiencia.registros.Modulo;
import ar.com.comunidadesfera.eficiencia.registros.Problema;

public class EjecucionTest extends TestBasico {

    private Ejecucion ejecucion;
    
    @Test
    public void getModulo() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(Datos.Ejecucion.SIMPLE_10.modulo.nombre, 
                                                        Datos.Ejecucion.SIMPLE_10.tama�o);
        
        Modulo modulo = this.ejecucion.getModulo();
        
        Assert.assertThat("nombre del m�dulo",
                          modulo.getNombre(), 
                          Matchers.is(Datos.Ejecucion.SIMPLE_10.modulo.nombre));
    }
    
    @Test
    public void getProblema() {
        
        this.ejecucion = this.contexto.iniciarEjecucion(Datos.Ejecucion.SIMPLE_10.modulo.nombre,
                                                        Datos.Ejecucion.SIMPLE_10.tama�o);
        
        Problema problema = this.ejecucion.getProblema();
        Assert.assertThat(problema, Matchers.notNullValue());
        
        List<Dimension> dimensiones = problema.getDimensiones();
        Assert.assertThat("dimensiones",
                          dimensiones, 
                          Matchers.notNullValue());
        Assert.assertThat("cantidad de dimensiones", 
                          dimensiones.size(), 
                          Matchers.is(Datos.Ejecucion.SIMPLE_10.tama�o.length));
        
        for (int i = 0; i < Datos.Ejecucion.SIMPLE_10.tama�o.length; i++) {
            
            Dimension dimension = dimensiones.get(i);
            
            Assert.assertThat("nombre de la dimensi�n " + i,
                    dimension.getNombre(), 
                              Matchers.nullValue());
            Assert.assertThat("valor de la dimensi�n " + i,
                              dimension.getValor(), 
                              Matchers.is(Datos.Ejecucion.SIMPLE_10.tama�o[i]));
        }
        
        Assert.assertThat("cantidad de dimensiones", 
                          problema.dimensiones(), 
                          Matchers.is(Datos.Ejecucion.SIMPLE_10.tama�o.length));
        
        for (int i = 0; i < Datos.Ejecucion.SIMPLE_10.tama�o.length; i++) {

            Dimension dimension = problema.getDimension(i);
            
            Assert.assertThat("nombre de la dimensi�n " + i,
                    dimension.getNombre(), 
                              Matchers.nullValue());
            Assert.assertThat("valor de la dimensi�n " + i,
                              dimension.getValor(), 
                              Matchers.is(Datos.Ejecucion.SIMPLE_10.tama�o[i]));
            
            dimension.setNombre("N" + i);
            
            dimension = problema.getDimension(i);
            
            Assert.assertThat("nombre de la dimensi�n " + i,
                              dimension.getNombre(), 
                              Matchers.is("N" + i));
        }
       
    }
    
}
