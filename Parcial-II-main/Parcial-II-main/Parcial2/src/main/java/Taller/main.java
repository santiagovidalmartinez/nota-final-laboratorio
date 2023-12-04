package Taller;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class main {

    public static void main(String[] args) {
        
        Gson gson = new Gson();
        
        LinkedList <Vehiculo> automoviles = new LinkedList<>();
        LinkedList <Vehiculo> motos = new LinkedList<>();
        
        
        int valorHoraAuto = 2000;
        int valorHoramoto = 1000;
        
        Automovil auto = new Automovil(4, "Mazda", "3", "ZYX987",11, 3);
        automoviles.add(auto);
        
        Motocicleta moto = new Motocicleta( "Honda", "CBR600", "XYZ789",600, 11, 16);
        motos.add(moto);
        
        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motos);
        });
        
        get("/automoviles", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> temporalAutomoviles = new LinkedList<>();
            
            for (Vehiculo vehiculo : automoviles) {
                if(vehiculo.getHoraEntrada() != 0){
                    temporalAutomoviles.add(vehiculo);
                }
            }
            
            return gson.toJson(temporalAutomoviles);
        });
        
        get("/motos", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> temporalMotos = new LinkedList<>();
            
            for (Vehiculo vehiculo : motos) {
                if(vehiculo.getHoraSalida() != 0){
                    temporalMotos.add(vehiculo);
                }
            }
            
            return gson.toJson(temporalMotos);
        });
        

        get("/agregarMoto/:marca/:modelo/:placa/:cilindrada", (req, res) -> {
            
            res.type("application/json");

            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
                       
            for (Vehiculo vehiculo : motos) {
                if(vehiculo.getPlaca().equalsIgnoreCase(placa)){
                    return gson.toJson(null);
                }
            }
            
            int cilindrada = Integer.parseInt(req.params(":cilindrada"));

            Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);
            
            Motocicleta nuevaMoto = new Motocicleta(marca, modelo,placa,cilindrada, hour, 0);
            motos.add(nuevaMoto);
            nuevaMoto.guardarMoto(nuevaMoto);
            
            return gson.toJson(nuevaMoto);
        });
        
        get("/salida/:tipo/:indice", (req, res) -> {
            res.type("application/json");
            
            String tipo = req.params(":tipo");
            int indice = Integer.parseInt(req.params(":indice"));
            
            Calendar rightNow2 = Calendar.getInstance();
            int hour2 = rightNow2.get(Calendar.HOUR_OF_DAY);
            
            
            if(tipo.equals("automovil") && indice < automoviles.size()){
                automoviles.get(indice).setHoraSalida(hour2);
            return gson.toJson(automoviles);
            }         
            if (tipo.equals("motocicleta") && indice < motos.size()){
               motos.get(indice).setHoraSalida(hour2);
               return gson.toJson(motos);
            }else {
            return "Tipo de vehículo o índice incorrecto.";
            }
          });
        
        
        get("/agregarAutomovil/:marca/:modelo/:placa/:numeroPuertas", (req, res) -> {
            
            res.type("application/json");

            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            
            
            for (Vehiculo vehiculo : automoviles) {
                if(vehiculo.getPlaca().equalsIgnoreCase(placa)){
                    return gson.toJson(null);
                }
            }
            
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));

            Calendar rightNow = Calendar.getInstance();
            int hour = rightNow.get(Calendar.HOUR_OF_DAY);
            
            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo,placa,hour, 0);
            automoviles.add(nuevoAuto);   
            
            nuevoAuto.guardarAuto(nuevoAuto);
            
            return gson.toJson(nuevoAuto);
        });
        
        

        get("/AutomovilesReporte", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> temporalAutomoviles = new LinkedList<>();
            String reporte = "";
            
            for (Vehiculo vehiculo : automoviles) {
                if(vehiculo.getHoraSalida() != 0){
                    reporte +=
                            " Placa: "+ vehiculo.getPlaca() +
                            " Ingreso: " + vehiculo.getHoraEntrada()+
                            " Salida: " + vehiculo.getHoraSalida() +
                            " Ganancia: " + ( vehiculo.getHoraSalida() - vehiculo.getHoraEntrada()) * valorHoraAuto;
                } else {
                    reporte +=
                            " Placa: "+ vehiculo.getPlaca() +
                            " Ingreso: " + vehiculo.getHoraEntrada() +
                            " Salida: " + vehiculo.getHoraSalida() +
                            " Ganancia: Aun aquí";
                }
            }
            return gson.toJson(reporte);
        });
        
            get("/motosReporte", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> temporalmotos = new LinkedList<>();
            String reporte = "";
            
            for (Vehiculo vehiculo : motos) {
                if(vehiculo.getHoraSalida() != 0){
                    reporte +=
                            " Placa: "+ vehiculo.getPlaca() +
                            " Ingreso: " + vehiculo.getHoraEntrada()+
                            " Salida: " + vehiculo.getHoraSalida() +
                            " Ganancia: " + ( vehiculo.getHoraSalida() - vehiculo.getHoraEntrada()) * valorHoramoto;
                } else {
                    reporte +=
                            " Placa: "+ vehiculo.getPlaca() +
                            " Ingreso: " + vehiculo.getHoraEntrada() +
                            " Salida: " + vehiculo.getHoraSalida() +
                            " Ganancia: Aun aquí";
                }
            }
            return gson.toJson(reporte);
        });
              get("/motosActuales", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> temporalmotos = new LinkedList<>();
            
            for (Vehiculo vehiculo : motos) {
                if(vehiculo.getHoraSalida()== 0){
                    temporalmotos.add(vehiculo);
                }
            }
            
            return gson.toJson(temporalmotos);
        });
                
              get("/AutomovilesActuales", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> temporalAutomoviles = new LinkedList<>();
            
            for (Vehiculo vehiculo : automoviles) {
                if(vehiculo.getHoraSalida()== 0){
                    temporalAutomoviles.add(vehiculo);
                }
            }
            
            return gson.toJson(temporalAutomoviles);
        });
    }
}
