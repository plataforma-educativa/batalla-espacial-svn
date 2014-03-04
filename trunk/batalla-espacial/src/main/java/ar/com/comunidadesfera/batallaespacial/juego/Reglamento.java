package ar.com.comunidadesfera.batallaespacial.juego;


/**
 * El Reglamento define que hacer cuando se produce un cambio en una 
 * Pieza.
 *
 */
public class Reglamento implements Pieza.Observador {

    private Partida partida;
    
    public Reglamento(Partida partida) {
        
        this.partida = partida;
    }
    
    protected Partida getPartida() {
        
        return this.partida;
    }
    
    /**
     * @see Pieza.Observador#cambio(Pieza)
     */
    public void cambio(Pieza pieza) {

        this.cambioNotificado(pieza);
    }
    
    /**
     * Template method que verifica si una Pieza debe o no
     * continuar en la Partida. 
     * 
     * @param pieza
     */
    protected void cambioNotificado(Pieza pieza) {
        
        /* si la pieza no tiene m�s puntos se retira de la partida */
        if ((pieza.getPuntos() < 1) && 
            (! this.partida.getPiezasDestruidas().contains(pieza))) {
 
            this.partida.getPiezasDestruidas().add(pieza);
            
            this.antesDeDestruir(pieza);
            
            if ( pieza.getCasillero() != null ) {
                
                this.partida.getTablero().retirarPieza(pieza);
            }
            
            this.despuesDeDestruir(pieza);
            
        }
    }
 
    /**
     * @pre la Pieza <code>pieza</code> ser� destruida y
     *       retirada de la Partida.
     * @param pieza
     */
    protected void antesDeDestruir(Pieza pieza) {

        /* punto de extensi�n  */
    }
    
    /**
     * pre:  la Pieza <code>pieza</code> fue destruida y retirada
     *       de la Partida.
     * @param pieza
     */
    protected void despuesDeDestruir(Pieza pieza) {
        
        /* punto de extensi�n */
    }
}
