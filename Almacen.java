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
}
