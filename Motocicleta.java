    package Models;

/**
 *
 * @author 104622020680 - Juan Esteban Alarcon Lasso 
 */
    public class Motocicleta extends Vehiculo {
        private int cilindrada;

    // Constructores, getters y setters
    public Motocicleta(String marca, String modelo, String placa, int cilindrada, String horaIngreso) {
        super(marca, modelo, placa, horaIngreso);
        this.cilindrada = cilindrada;
    }
    
    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
    // Otros métodos específicos para motocicletas

    

}