package ar.com.comunidadesfera.eficiencia.registros;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import ar.com.comunidadesfera.persistencia.Entidad;

@Entity
public class Medicion extends Entidad {

    private Problema problema;
    
    private Modulo modulo;

    private Discriminante discriminante;
    
    private Medida resultado;

    public Medicion() {
    }

    /**
     * Inicializa la Medici�n del M�dulo para el Problema indicado con
     * el resultado dado. El Discriminante de la Medici�n es todo M�dulo.
     * 
     * @param problema
     * @param modulo
     * @param resultado
     */
    public Medicion(Problema problema, Modulo modulo, Medida resultado) {
        
        this(problema, modulo, modulo, resultado);
    }
    
    public Medicion(Problema problema, Modulo modulo, Discriminante discriminente, 
                    Medida resultado) {
        
        this.setProblema(problema);
        this.setModulo(modulo);
        this.setDiscriminante(discriminente);
        this.setResultado(resultado);
    }
    
    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {})
    public Problema getProblema() {
        return problema;
    }

    public void setProblema(Problema problema) {
        this.problema = problema;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {})
    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }
    
    @OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Medida getResultado() {
        
        return this.resultado;
    }

    public void setResultado(Medida resultado) {

        this.resultado = resultado;
    }

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = {})
    public Discriminante getDiscriminante() {

        return this.discriminante;
    }
    
    public void setDiscriminante(Discriminante discriminante) {

        this.discriminante = discriminante;
    }
    
    @Override
    protected void describir(StringBuilder builder) {
        
        super.describir(builder);
        this.describirPropiedad(builder, "problema", this.getProblema());
        this.describirPropiedad(builder, "modulo", this.getModulo());
        this.describirPropiedad(builder, "discriminante", this.getDiscriminante());
        this.describirPropiedad(builder, "resultado", this.getResultado());
    }


}