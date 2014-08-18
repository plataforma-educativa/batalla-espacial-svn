package ar.com.comunidadesfera.plataformaeducativa;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.enterprise.inject.Instance;
import javax.inject.Provider;

import ar.com.comunidadesfera.fx.Aplicacion;
import ar.com.comunidadesfera.fx.Iniciar;

/**
 * Aplicaci�n JavaFX que inicia una versi�n de la Batalla Espacial en modo Interactivo.
 *
 */
public class AplicacionBatallaEspacialInteractiva extends Aplicacion {

    /**
     * �nica instancia de Ejecuci�n de la Aplicaci�n Batalla Espacial Interactiva 
     */
    public final static Ejecucion EJECUCION = new Ejecucion();
    
    @Override
    protected Provider<? extends Iniciar> seleccionarIniciar(Instance<Object> instancias) {

        return instancias.select(IniciarBatallaEspacialInteractiva.class);
    }
    
    @Override
    protected void cargaTerminada() {

        super.cargaTerminada();

        /* notifica a la Ejecuci�n que la Batalla Espacial est� lista para
         * interactuar con ella */
        EJECUCION.cargaTerminada();
    }
    
    @Override
    protected void cargando(Stage stage) {
        
        Pane panel = new Pane();
        panel.setStyle("-fx-background-image: url('/ar/com/comunidadesfera/batallaespacial/interfaz/inicio-interactivo.png')");
        Scene scene = new Scene(panel, 600, 231);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        
        Application.launch(args);
    }

    /**
     * Runnable que lanza la Aplicaci�n Batalla Espacial Interactiva.
     * 
     */
    public static class Ejecucion implements Runnable {

        public AtomicBoolean cargaTerminada = new AtomicBoolean(false);

        private final CountDownLatch cargada;
        
        private Ejecucion() {

            this.cargada = new CountDownLatch(1);
        }
        
        private void cargaTerminada() {
            
            this.cargada.countDown();
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

            Application.launch(AplicacionBatallaEspacialInteractiva.class);
        }
    }
}
