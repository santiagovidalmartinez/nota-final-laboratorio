package Taller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Automovil extends Vehiculo{
    private int numeroPuertas;

    public Automovil(int numeroPuertas, String marca, String modelo, String placa, int horaEntrada, int horaSalida) {
        super(marca, modelo, placa,horaEntrada);
        this.numeroPuertas = numeroPuertas;
    }

    public int getNumeroPuertas() {
        return numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }
    
    public void guardarAuto (Automovil auto) throws IOException{
        try {
            FileOutputStream fichero;
            fichero = new FileOutputStream("auto.txt");
            ObjectOutputStream crear = new ObjectOutputStream(fichero);
            crear.writeObject(auto);
        } catch (FileNotFoundException ex) {
            System.out.println("\n033[32mA ocurrido un problema con el fichero");;
        }
    }
}
