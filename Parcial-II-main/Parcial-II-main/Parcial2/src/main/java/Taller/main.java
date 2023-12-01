package Taller;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.time.Duration;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class main {

    public static void main(String[] args) {
        
        
        Gson gson = new Gson();
        
        LinkedList <Vehiculo> automoviles = new LinkedList<>(); 
        
        Automovil auto = new Automovil(4, "Mazda", "3", "ZYX987",11, 3);
        automoviles.add(auto);
        
        LinkedList <Vehiculo> motocicletas = new LinkedList<>();
        
        Motocicleta moto = new Motocicleta("Honda", "CBR600", "XYZ789",600,3, 6);
        motocicletas.add(moto);
        
        

        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motocicletas);
        });

       
    
        get("/agregarMoto/:marca/:modelo/:placa/:cilindrada/:horaEntrada/:horaSalida", (req, res) -> {
           
            res.type("application/json");
            
    
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            
            int cilindrada = Integer.parseInt(req.params(":cilindrada"));
            int horaEntrada = Integer.parseInt(req.params(":horaEntrada"));
            int horaSalida = Integer.parseInt(req.params(":horaSalida"));
            
            Motocicleta nuevaMoto = new Motocicleta( marca, modelo, placa, cilindrada,horaEntrada, horaSalida);
            motocicletas.add(nuevaMoto);

            return gson.toJson(nuevaMoto);
        });
        
        
       
        get("/automoviles", (req, res) -> {
            res.type("application/json");
            return gson.toJson(automoviles);
        });

        
        get("/agregarAutomovil/:marca/:modelo/:placa/:numeroPuertas/:horaEntrada/:horaSalida", (req, res) -> {
               
            res.type("application/json");

            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");
            

            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));
            int horaEntrada = Integer.parseInt(req.params(":horaEntrada"));
            int horaSalida = Integer.parseInt(req.params(":horaSalida"));
            
            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo,placa,horaEntrada, horaSalida);
            automoviles.add(nuevoAuto);

            return gson.toJson(nuevoAuto);
        });
        
        get("/sacarVehiculo/:placa/:hora", (req, res) -> {
            
            res.type("application/json");
            
            String placa = req.params(":placa");
            int horaSalida = Integer.parseInt(req.params(":hora"));
            
            boolean flag = false;
            for(Vehiculo m : motocicletas){
                if (m.getPlaca() == null ? placa == null : m.getPlaca().equals(placa)) {
                    m.setHoraSalida(horaSalida);
                    flag = true;
                    return gson.toJson("Moto retirada");
                }
            }
            if (flag==false) {
                for(Vehiculo a : automoviles){
                if (a.getPlaca() == null ? placa == null : a.getPlaca().equals(placa)) {
                    a.setHoraSalida(horaSalida);
                    motocicletas.remove(a);
                    flag = true;
                    return gson.toJson("Auto retirado");
                }
            }
            }
            return null;
        });
        
        get("/automovilesAcuales", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> actuales = new LinkedList<>();
            res.type("application/json");
            for(Vehiculo e : automoviles ){
                if(e.getHoraSalida() == 0){
                actuales.add(e);
                }
            }
        return gson.toJson(actuales);
        });
        
        get("/motosActuales", (req, res) -> {
            res.type("application/json");
            LinkedList <Vehiculo> actuales = new LinkedList<>();
            for(Vehiculo e : motocicletas ){
                if(e.getHoraSalida() == 0){
                    actuales.add(e);
                }
            }
        return gson.toJson(actuales);
        });
        
        get("/motosReporte", (req, res) -> {
            res.type("application/json");
            int i = 0;
            int Ganancias = 0;
            for(Vehiculo e : motocicletas){
                if (e.getHoraSalida() != 0) {
                    Ganancias += e.CalcularCosto(2000);
                    i++;
                }
            }
            return gson.toJson("Total de motos Retiradas:"+ i 
                    + "|||  Ganancias del Dia:"+ Ganancias); 
        });
        
        get("/AutomovilesReporte", (req, res) -> {
            res.type("application/json");
            int i = 0;
            int Ganancias = 0;
            for(Vehiculo e : automoviles){
                if (e.getHoraSalida() != 0) {
                    Ganancias += e.CalcularCosto(4000);
                    i++;
                }
            }
            return gson.toJson("Total de Autos Retirados:"+ i 
                    + "|||   Ganancias del Dia:"+ Ganancias); 
        });
        
        

    get("/totalVentas", (req, res) -> {
        res.type("application/json");

        int gananciasTotales = 0;


        for(Vehiculo e : automoviles){
            if (e.getHoraSalida() != 0) {
                gananciasTotales += e.CalcularCosto(4000); 
            }
        }

     
        for(Vehiculo e : motocicletas){
            if (e.getHoraSalida() != 0) {
                gananciasTotales += e.CalcularCosto(2000); 
            }
        }

        return gson.toJson("Ganancias totales: " + gananciasTotales); 
    });

        
        
        
    }
}
