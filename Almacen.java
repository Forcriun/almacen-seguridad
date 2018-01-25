import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Iterator;

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
     * Añade un nuevo objeto Extintor al almacen.
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
     * Muestra un listado ordenado de los extintores del almacen en funci�n
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
                    if(coleccion.get(x).getPeso() >= extMasPesado.getPeso()){
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
            System.out.println("Error: el almac�n est� vac�o.");
        }
    }

    /**
     * Muestra un listado ordenado de los extintores del almacen en funci�n
     * de su ultima fecha de revision (de mas cercana a mas lejana).
     */
    public void ordenarPorFechaDeRevisionMasCercana(){
        ArrayList<Extintor> coleccion = new ArrayList<>();
        coleccion.addAll(almacen);
        LocalDate fechaTope = LocalDate.of(1900,01,01); // Fecha exagerada aun sabiendo que los extintores caducan a los 25 a�os.
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
                    System.out.println("Error: las fechas a ordenar no son v�lidas.");                
                }
            }
        }
        else{
            System.out.println("Error: el almac�n est� vac�o.");
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
            System.out.println("El id introducido es mayor al tama�o del inventario (" + almacen.size() + ").");
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
}

