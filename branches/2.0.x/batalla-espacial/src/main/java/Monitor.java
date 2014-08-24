import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.PilotoSubalterno;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.MonitorearCantidadDeTorpedos;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.MonitorearCarga;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.MonitorearNivelDeCarga;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.MonitorearNivelDeEscudos;
import ar.com.comunidadesfera.batallaespacial.civilizaciones.humana.ordenes.OrdenSincronizada;

/**
 * El Monitor se encuentra asociado a una Nave de la cual 
 * reporta su condici�n.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class Monitor {

    private static final String DESCRIPCION = "Monitor de la Nave";
    
    private PilotoSubalterno piloto;
    
    protected Monitor(PilotoSubalterno piloto) {
     
        this.piloto = piloto;
    }
    
    private <T> T monitorear(OrdenSincronizada<T> orden) {
        
        this.piloto.getOrdenes().add(orden);
        
        return orden.getResultado();
    }
    
    /**
     * @post devuelve la cantidad de Torpedos de Fotones que posee disponibles.
     */
    public int consultarCantidadDeTorpedos() {
        
        return this.monitorear(new MonitorearCantidadDeTorpedos());
    }

    /**
     * @post devuelve la cantidad de Sustancia sustancia que 
     *       existe en la bodega. 
     */
    public long consultarCarga(Sustancia sustancia) {
        
        return this.monitorear(new MonitorearCarga(Traductor.instancia().traducir(sustancia)));
    }

    /**
     * @post devuelve un valor entre 0 y 100 que representa
     *       el porcentaje de la bodega que es ocupado por
     *       la carga.
     *       El valor 0 indica que no existe carga, bodega vacia.
     *       El valor 100 indica que la bodega est� completa.
     */
    public byte consultarNivelDeCarga() {
        
        return this.monitorear(new MonitorearNivelDeCarga());
    }

    /**
     * @post devuelve un valor entre 0 y 100 que representa el
     *       nivel de energ�a que tienen los Escudos [%].
     *       El valor 100 indica m�ximo poder en los Escudos.
     *       El valor 0 indica Escudos fuera de funcionamiento.
     */  
    public byte consultarNivelDeEscudos() {
        
        return this.monitorear(new MonitorearNivelDeEscudos());
    }
    
    @Override
    public String toString() {

        /* Devuelve una descripci�n para que al ser usado desde un int�rprete al intentar mostrar
         * el contenido de una variable que la referencie.
         */
        return DESCRIPCION;
    }
}
