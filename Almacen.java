import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Arrays;
import java.util.HashSet;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Clase que representa un almacen de objetos de la clase Extintor.
 *
 * @author Didac Fernandez Fernandez
 * @version 2018-01-24
 */
public class Almacen
{
    // Array que almacena los objetos de tipo Extintor
    private ArrayList<Extintor> almacen;
    // Contador de identificadores de los objetos Extintor
    private int id;

    /**
     * Constructor para los objetos de la clase Almacen
     */
    public Almacen()
    {
        almacen = new ArrayList<Extintor>();
        id = 1;
    }

    /**
     * Constructor con parametro para los objetos de la clase Almacen
     * @param txt Nombre del archivo externo con los datos de los objetos del
     * almacen. Escriba el nombre del archivo con su extension ("archivo.txt").
     */
    public Almacen(String txt)
    {
        almacen = new ArrayList<Extintor>();
        id = 1;
        fileParser(txt);
    }

    /**
     * AÃ±ade un nuevo objeto Extintor al almacen.
     * @param tipo El tipo a fijar: agua/espuma/gas/polvo.
     * @param tipo El peso a fijar (Kg).
     * @param fechaRevision La fecha de revision en formato "yyyy-mm-dd".
     */
    public void addExtintor(String tipo, int peso, String fechaRevision)
    {
        almacen.add(new Extintor(tipo,peso,fechaRevision,id));
        id++;
    }

    /**
     * Muestra todos los elementos de la coleccion y sus datos por pantalla.
     */
    public void mostrarExtintores()
    {
        // Crea un iterador de la coleccion de extintores.
        Iterator<Extintor> it = almacen.iterator();
        while (it.hasNext()){
            // Inicializa una variable local de tipo Extintor al puntero del iterador.
            Extintor extintorActual = it.next();
            System.out.println(extintorActual.getDatosExtintor());
        }
    }

    /**
     * Muestra un listado ordenado de los extintores del almacen en función
     * de su peso (de mayor a menor).
     */
    public void ordenarDeMayorAMenorPorPeso(){
        ArrayList<Extintor> coleccion = new ArrayList<>();
        coleccion.addAll(almacen);
        if (coleccion.size() > 0){
            while(coleccion.size() > 0){    // Da tantas vueltas como objetos en la coleccion.
                Extintor extMasPesado = coleccion.get(0);
                int x = 0;  // Contador del bucle
                int posicionMasPesado = 0;
                while(x < coleccion.size()){
                    if(coleccion.get(x).getPeso() < 1){
                        coleccion.remove(x);
                        x--;
                    }
                    else if(coleccion.get(x).getPeso() >= extMasPesado.getPeso()){
                        extMasPesado = coleccion.get(x);
                        posicionMasPesado = x;
                    }
                    x++;
                }
                // Imprime y borra solo el de mayor peso en el ciclo actual.
                System.out.println(coleccion.get(posicionMasPesado).getDatosExtintor());
                coleccion.remove(posicionMasPesado);
            }
        }
        else{
            System.out.println("Error: el almacén está vacío.");
        }
    }

    /**
     * Muestra un listado ordenado de los extintores del almacen en función
     * de su ultima fecha de revision (de mas cercana a mas lejana).
     */
    public void ordenarPorFechaDeRevisionMasCercana(){
        ArrayList<Extintor> coleccion = new ArrayList<>();
        coleccion.addAll(almacen);
        LocalDate fechaTope = LocalDate.of(1900,01,01); // Fecha exagerada aun sabiendo que los extintores caducan a los 25 años.
        if (coleccion.size() > 0){
            while(coleccion.size() > 0){
                Extintor extMasCercano = coleccion.get(0);
                int x = 0;  // Contador del bucle
                int posicionMasCercano = 0;
                while(x < coleccion.size()){                    
                    if(coleccion.get(x).getUltimaRevision().isBefore(fechaTope)){   // Elimina fechas inferiores a la tope.
                        coleccion.remove(x);
                    }
                    else if(coleccion.get(x).getUltimaRevision().isBefore(LocalDate.now().plusDays(1))){    // Considera el dia actual
                        if(coleccion.get(x).getUltimaRevision().isAfter(extMasCercano.getUltimaRevision())){
                            extMasCercano = coleccion.get(x);
                            posicionMasCercano = x;
                        }
                        x++;
                    }
                    else{   // Elimina fechas posteriores al dia actual
                        coleccion.remove(x);
                    }
                }
                if(!coleccion.isEmpty()){
                    System.out.println(coleccion.get(posicionMasCercano).getDatosExtintor());
                    coleccion.remove(posicionMasCercano);
                }
                else{
                    System.out.println("Error: las fechas a ordenar no son válidas.");                
                }
            }
        }
        else{
            System.out.println("Error: el almacén está vacío.");
        }
    }

    /**
     * Permite modificar los datos de un extintor especifico del almacen
     * por medio de su id.
     * @param id El identificador del extintor.
     * @param tipo El tipo a fijar: agua/espuma/gas/polvo.
     * @param tipo El peso a fijar (Kg).
     * @param fechaRevision La fecha de revision en formato "yyyy-mm-dd".
     */
    public void modificarExtintor(int id, String tipo, int peso, String fechaRevision){
        if(idValido(id)){
            almacen.get(id-1).setTipo(tipo);
            almacen.get(id-1).setPeso(peso);
            almacen.get(id-1).setUltimaRevision(fechaRevision);
        }
    }

    /**
     * Comprueba que el id dado sea valido para el inventario con el que
     * se trabaja.
     * @param id El id a comprobar.
     * @return true si el id es valido, false si no lo es.
     */
    private boolean idValido(int id)
    {
        boolean valido;
        if(id < 1) {
            System.out.println("El id no puede ser menor de 1.");
            valido = false;
        }
        else if(id > almacen.size()) {
            System.out.println("El id introducido es mayor al tamaño del inventario (" + almacen.size() + ").");
            valido = false;
        }
        else {
            valido = true;
        }
        return valido;
    }

    /**
     * Elimina los extintores del tipo introducido que haya en el inventario.
     * @param tipoAEliminar El tipo que se quiere eliminar (agua/espuma/gas/polvo).
     */
    public void eliminarPorTipo(String tipoAEliminar){
        Iterator<Extintor> it = almacen.iterator();
        while(it.hasNext()){
            Extintor extintorActual = it.next();
            if(extintorActual.getTipo().toLowerCase().contains(tipoAEliminar.toLowerCase())){
                it.remove();
            }
        }
    }

    /**
     * Añade un listado de extintores al almacen. Los datos se obtienen 
     * escaneando un archivo de texto plano externo en el directorio del proyecto.
     * @param txt Nombre del archivo externo. Escriba el nombre del archivo
     * con su extension ("archivo.txt").
     */
    public void fileParser(String txt)
    {
        try {
            File archivo = new File(txt);
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine()) {
                String[] arrayDatosExtintor = sc.nextLine().split(" # ");
                addExtintor(arrayDatosExtintor[0], Integer.parseInt(arrayDatosExtintor[1]), arrayDatosExtintor[2]);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo que muestra por pantalla los extintores del almacen agrupados
     * por su peso, previa ordenacion alfabetica.
     */   
    public void agrupaPorPeso(){
        HashSet<Integer> conjuntosPeso = new HashSet<>();
        almacen = ordenaAlfabeticamentePorTipo(almacen);
        
        for(Extintor extintor : almacen){
            conjuntosPeso.add(extintor.getPeso());
        }

        for(Integer categoriaPeso : conjuntosPeso){
            System.out.println("Extintores de " + categoriaPeso + "kg:");
            int x = 0;  // contador del bucle
            while(x < almacen.size()){
                if(almacen.get(x).getPeso() == categoriaPeso){
                    System.out.println(almacen.get(x).getDatosExtintor());
                }
                x++;
            }
            System.out.println();
        }
    }

    /**
     * Metodo que ordena los extintores del almacen que recibe por parametro
     * alfabeticamente segun el tipo.
     * @param coleccion El almacen a ordenar.
     * @return El almacen ya ordenado.
     */
    public ArrayList<Extintor> ordenaAlfabeticamentePorTipo(ArrayList<Extintor> coleccion){
        ArrayList<Extintor> copiaAlmacen = new ArrayList<>(coleccion);
        ArrayList<Extintor> aDevolver = new ArrayList<>();

        while(copiaAlmacen.size() > 0){
            Extintor extMasAlf = null;
            String tipoReferencia = copiaAlmacen.get(0).getTipo();                

            for (Extintor ext : copiaAlmacen){
                if (ext.getTipo().compareTo(tipoReferencia) <= 0){
                    tipoReferencia = ext.getTipo();
                    extMasAlf = ext;
                }
            }
            aDevolver.add(extMasAlf);
            copiaAlmacen.remove(extMasAlf);
        }
        
        return aDevolver;
    }
}

