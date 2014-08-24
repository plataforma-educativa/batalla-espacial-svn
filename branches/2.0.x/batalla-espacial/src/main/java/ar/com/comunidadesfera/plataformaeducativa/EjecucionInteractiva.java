package ar.com.comunidadesfera.plataformaeducativa;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import ar.com.comunidadesfera.fx.Aplicacion;

/**
 * Runnable que lanza la Aplicaci�n Batalla Espacial Interactiva.
 * 
 */
public class EjecucionInteractiva implements Runnable {

    /**
     * �nica instancia de Ejecuci�n de la Aplicaci�n Batalla Espacial Interactiva 
     */
    private final static EjecucionInteractiva instancia = new EjecucionInteractiva();
    
    private Class<? extends Aplicacion> clase = AplicacionBatallaEspacialInteractiva.class;
    
    private final CountDownLatch cargada;
    
    private EjecucionInteractiva() {

        this.cargada = new CountDownLatch(1);
    }
    
    void cargaTerminada() {
        
        this.cargada.countDown();
    }
    
    public void setClase(Class<? extends Aplicacion> clase) {
        
        this.clase = clase;
    }
    
    /**
     * Indica si la Batalla Espacial Interactiva termin� de cargar y est� lista
     * para recibir interacciones, esper�ndo a lo sumo el tiempo especificado por 'espera' y 'unidad'.
     * 
     * @param espera
     * @param unidad
     * @return
     */
    public boolean estaCargando(long espera, TimeUnit unidad) {
        
        boolean cargando = true;
        
        try {
            
            cargando = ! this.cargada.await(espera, unidad);
            
        } catch (InterruptedException ignorada) {
            
            /* si se interrumpe la ejecuci�n, asume que la carga termin� */
        }
        
        return cargando;
    }
    
    @Override
    public void run() {

        Application.launch(this.clase);
    }
    
    public static EjecucionInteractiva instancia() {
        
        return instancia;
    }
}