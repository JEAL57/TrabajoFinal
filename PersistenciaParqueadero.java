package Models;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class PersistenciaParqueadero {
    private static final String AUTOMOVILES_FILE = "automoviles.json";
    private static final String MOTOS_FILE = "motos.json";
    private static final Gson gson = new Gson();

    public static LinkedList<Vehiculo> cargarAutomoviles() {
        try (FileReader reader = new FileReader(AUTOMOVILES_FILE)) {
            Vehiculo[] automovilesArray = gson.fromJson(reader, Vehiculo[].class);
            return new LinkedList<>(Arrays.asList(automovilesArray));
        } catch (IOException e) {
            // Manejar la excepción si el archivo no existe o hay un problema de lectura
            return new LinkedList<>();
        }
    }

    public static LinkedList<Motocicleta> cargarMotos() {
        try (FileReader reader = new FileReader(MOTOS_FILE)) {
            Motocicleta[] motosArray = gson.fromJson(reader, Motocicleta[].class);
            return new LinkedList<>(Arrays.asList(motosArray));
        } catch (IOException e) {
            // Manejar la excepción si el archivo no existe o hay un problema de lectura
            return new LinkedList<>();
        }           
    }

    public static void guardarAutomoviles(LinkedList<Vehiculo> automoviles) {
        try (FileWriter writer = new FileWriter(AUTOMOVILES_FILE)) {
            gson.toJson(automoviles.toArray(), writer);
        } catch (IOException e) {
            // Manejar la excepción si hay un problema al escribir en el archivo
            System.err.println("Error al escribir en el archivo de automóviles: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void guardarMotos(LinkedList<Motocicleta> motos) {
        try (FileWriter writer = new FileWriter(MOTOS_FILE)) {
            gson.toJson(motos.toArray(), writer);
        } catch (IOException e) {
            // Manejar la excepción si hay un problema al escribir en el archivo
            System.err.println("Error al escribir en el archivo de motos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
