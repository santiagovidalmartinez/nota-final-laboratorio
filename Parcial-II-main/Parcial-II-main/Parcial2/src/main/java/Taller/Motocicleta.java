package Taller;
public class Motocicleta extends Vehiculo{
 private int cilindrada;

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
    

    public Motocicleta(String marca, String modelo, String placa,int cilindrada,int horaEntrada, int horaSalida) {
        super(marca, modelo, placa, horaEntrada);
        this.cilindrada = cilindrada;
    }
    
    
}
