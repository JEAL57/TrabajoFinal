package Models;

/**
 *
 * @author 104622020680 - Juan Esteban Alarcon Lasso 
 */
public class Automovil extends Vehiculo {
    private int numeroPuertas;

    // Constructores, getters y setters    
    public Automovil(int numeroPuertas, String marca, String modelo, String placa, String horaIngreso) {
        super(marca, modelo, placa, horaIngreso);
        this.numeroPuertas = numeroPuertas; 
    }
    
    public int calcularCostoParqueo() {
        int costobase = 20;
        return costobase + (numeroPuertas * 5);
    }
    
    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }
    // Otros métodos específicos para automóviles

    
}