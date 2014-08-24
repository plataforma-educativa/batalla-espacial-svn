package ar.com.comunidadesfera.fx;

import java.io.IOException;
import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;

public abstract class Iniciar {
    
    @Inject 
    private FXMLLoader fxmlLoader;
    
    public void ejecutar(Stage stage) {

        this.configurar();
        
        try (InputStream is = getClass().getResourceAsStream(this.getFxmlLocation())) {
            
            Parent root = (Parent) fxmlLoader.load(is);

            Scene scene = new Scene(root);
            this.configurar(scene);
            stage.setScene(scene);
            
            this.configurar(stage);
            stage.show();
            
        } catch (IOException e) {
            
            throw new IllegalStateException("No se pudo cargar el FXML: " + this.getFxmlLocation(), e);
        }
    }

    protected abstract String getFxmlLocation();
    
    /**
     * pre : a�n no se ha inicializado la Aplicaci�n.
     * post: configura el ambiente.
     * 
     * Punto de extensi�n para que cada implementaci�n pueda llevar adelante tareas de configuraci�n
     * previas a iniciar la Aplicaci�n.
     */
    protected void configurar() {
        
    }
    
    /**
     * pre : a�n no se ha inicializado la Aplicaci�n.
     * post: configura el Scene de la Aplicaci�n, antes de agregarlo al Stage principal.
     *
     * Punto de extensi�n para que cada implementaci�n pueda llevar adelante tareas de configuraci�n
     * sobre la Scene, por ejemplo para agregar stylesheets, etc.
     * 
     * @param scene
     */
    protected void configurar(Scene scene) {
        
    }
 
    /**
     * pre : a�n no se ha inicializado la Aplicaci�n.
     * post: configura el Stage de la Aplicaci�n, antes de mostrarlo.
     *
     * Punto de extensi�n para que cada implementaci�n pueda llevar adelante tareas de configuraci�n
     * sobre el Stage, por ejemplo para definir el �cono.
     *
     * @param stage
     */
    protected void configurar(Stage stage) {
        
        stage.setTitle(this.fxmlLoader.getResources().getString("titulo"));
    }
}
