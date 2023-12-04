package Taller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

    public void guardarMoto (Motocicleta moto) throws IOException{
            try {
                FileOutputStream fichero;
                fichero = new FileOutputStream("moto.txt");
                ObjectOutputStream crear = new ObjectOutputStream(fichero);
                crear.writeObject(moto);
            } catch (FileNotFoundException ex) {
                System.out.println("\n033[32mA ocurrido un problema con el fichero");;
            }
    }
}
