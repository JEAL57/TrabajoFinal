package Main;

import Models.Automovil;
import Models.Motocicleta;
import Models.PersistenciaParqueadero;
import Models.Vehiculo;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.Date;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        Date fecha = new Date();
        
        Gson gson = new Gson();

        LinkedList<Vehiculo> automoviles = new LinkedList<>();
        LinkedList<Motocicleta> motos = new LinkedList<>();

        Automovil auto = new Automovil(4, "Mazda", "3", "ZYX987", (fecha.getHours() + ":" + fecha.getMinutes()));
        automoviles.add(auto);

        Motocicleta moto = new Motocicleta("Honda", "CBR600", "XYZ789", 600, (fecha.getHours() + ":" + fecha.getMinutes()));
        motos.add(moto);

        get("/automoviles", (req, res) -> {
            res.type("application/json");
            return gson.toJson(automoviles);
        });

        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motos);
        });

        get("/agregarAutomovil/:numeroPuertas/:marca/:modelo/:placa", (req, res) -> {

            res.type("application/json");

            Date nuevaFecha = new Date();

            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));

            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo, placa, (nuevaFecha.getHours() + ":" + nuevaFecha.getMinutes()));
            automoviles.add(nuevoAuto);

            return gson.toJson(nuevoAuto);
        });

        get("/agregarMoto/:marca/:modelo/:placa/:cilindrada", (req, res) -> {

            res.type("application/json");

            Date nuevaFecha = new Date();
 
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            int cilindrada = Integer.parseInt(req.params(":cilindrada"));

            Motocicleta nuevaMoto = new Motocicleta(marca, modelo, placa, cilindrada, (nuevaFecha.getHours() + ":" + nuevaFecha.getMinutes()));
            motos.add(nuevaMoto);

            return gson.toJson(nuevaMoto);
        });

        get("/registrarSalidaAutomovil/:placa", (req, res) -> {

            res.type("application/json");

            Date nuevaFecha = new Date();

            String placa = req.params(":placa");

            for (Vehiculo automovil : automoviles) {
                if (automovil.getPlaca().equals(placa)) {
                    automovil.setHoraSalida((nuevaFecha.getHours() + ":" + nuevaFecha.getMinutes()));
                    return gson.toJson(automovil);
                }

            }
            return gson.toJson("Automovil no encontrado en el parqueadero");
        });

        get("/registrarSalidaMoto/:placa", (req, res) -> {

            res.type("application/json");

            Date nuevaFecha = new Date();

            String placa = req.params(":placa");

            for (Motocicleta motocicleta : motos) {
                if (motocicleta.getPlaca().equals(placa)) {
                    motocicleta.setHoraSalida((nuevaFecha.getHours() + ":" + nuevaFecha.getMinutes()));
                    return gson.toJson(motocicleta);
                }
            }
            return gson.toJson("Motocicleta no encontrada en el parqueadero");
        });

        get("/automovilesActuales", (req, res) -> {
            res.type("application/json");

            LinkedList<Vehiculo> automovilesActuales = new LinkedList<>();

            for (Vehiculo automovil : automoviles) {
                if (automovil.getHoraSalida() == null) {
                    automovilesActuales.add(automovil);
                }
            }

            return gson.toJson(automovilesActuales);
        });

        get("/motosActuales", (req, res) -> {
            res.type("application/json");

            LinkedList<Motocicleta> motosActuales = new LinkedList<>();

            for (Motocicleta motocicleta : motos) {
                if (motocicleta.getHoraSalida() == null) {
                    motosActuales.add(motocicleta);
                }
            }

            return gson.toJson(motosActuales);
        });

        get("/motosReporte", (req, res) -> {
            res.type("application/json");

            LinkedList<String> informeMotos = new LinkedList<>();
            for (Motocicleta mo : motos) {
                informeMotos.add("Placa: " + mo.getPlaca()
                        + ", Ganancia: " + mo.calcularGanancia()
                        + ", Hora de Entrada: " + mo.getHoraIngreso()
                        + ", Hora de Salida: " + mo.getHoraSalida());
            }

            return new Gson().toJson(informeMotos);
        });

        get("/AutomovilesReporte", (req, res) -> {
            res.type("application/json");

            LinkedList<String> informeAutomoviles = new LinkedList<>();
            for (Vehiculo au : automoviles) {
                informeAutomoviles.add("Placa: " + au.getPlaca()
                        + ", Ganancia: " + au.calcularGanancia()
                        + ", Hora de Entrada: " + au.getHoraIngreso()
                        + ", Hora de Salida: " + au.getHoraSalida());
            }
            return new Gson().toJson(informeAutomoviles);
        });
        
        get("/totalGananciasMotos", (req, res) -> {
            res.type("application/json");

            // Calcular el total de ganancias de motocicletas del día
            int totalGananciasMotos = 0;
            for (Motocicleta mo : motos) {
                totalGananciasMotos += mo.calcularGanancia();
            }

            return new Gson().toJson(totalGananciasMotos);
        });
        
       
        get("/totalGananciasAutomoviles", (req, res) -> {
            res.type("application/json");

            
            int totalGananciasAutomoviles = 0;
            for (Vehiculo au : automoviles) {
                totalGananciasAutomoviles += au.calcularGanancia();
            }

            return new Gson().toJson(totalGananciasAutomoviles);
        });

        get("/totalGananciasDia", (req, res) -> {
            res.type("application/json");

            int totalGanancias = 0;

           
            for (Motocicleta mo : motos) {
                totalGanancias+= mo.calcularGanancia();
            }

           
            for (Vehiculo au : automoviles) {
                totalGanancias += au.calcularGanancia();
            }

            return new Gson().toJson(totalGanancias);
        });
        
        get("/cerrarPrograma", (req, res) -> {
            PersistenciaParqueadero.guardarAutomoviles(automoviles);
            PersistenciaParqueadero.guardarMotos(motos);
            stop(); // Detiene el servidor Spark
            return "Programa cerrado. Datos guardados.";
        });
    }
}
