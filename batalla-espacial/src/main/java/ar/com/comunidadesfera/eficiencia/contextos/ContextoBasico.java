package ar.com.comunidadesfera.eficiencia.contextos;

import javax.persistence.EntityManagerFactory;

import ar.com.comunidadesfera.eficiencia.Contexto;
import ar.com.comunidadesfera.eficiencia.Ejecucion;
import ar.com.comunidadesfera.eficiencia.ejecuciones.EjecucionBasica;

/**
 * Implementaci�n b�sica de un Contexto de Eficiencia.
 * 
 * @author Mariano Tugnarelli
 *
 */
public class ContextoBasico implements Contexto {

    private EntityManagerFactory emFactory;
    
    private boolean persistente = false;
    
    @Override
    public void setPersistente(boolean esPersistente) {

        this.persistente = esPersistente;
    }

    @Override
    public boolean isPersistente() {

        return this.persistente;
    }

    @Override
    public Ejecucion iniciarEjecucion(String nombreAlgoritmo, long[] tama�o) {
        
        return new EjecucionBasica(nombreAlgoritmo, tama�o);
    }

    public void setEntityManagerFactory(EntityManagerFactory emFactory) {

        this.emFactory = emFactory;
    }

}
