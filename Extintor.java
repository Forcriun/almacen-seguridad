import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase que crea objetos de tipo Extintor con tres caracteristicas: tipo,
 * peso y ultima revision.
 *
 * @author Didac Fernandez Fernandez
 * @version 2018-01-24
 */
public class Extintor
{
    // El tipo de agente extintor: agua, espuma, gas o polvo.
    private String tipo;
    // El peso del agente extintor que contiene el objeto.
    private int peso;
    // La fecha de la ultima revision.
    private LocalDate ultimaRevision;

    /**
     * Constructor for objects of class Extintor
     * @param tipo El tipo a fijar.
     * @param tipo El peso a fijar.
     * @param fechaRevision La fecha de revision en formato "yyyy-mm-dd".
     */
    public Extintor(String tipo, int peso, String fechaRevision)
    {
        this.tipo = tipo;
        this.peso = peso;
        ultimaRevision = LocalDate.parse(fechaRevision);        
    }

    /**
     * @return Devuelve el tipo del extintor: agua, espuma, gas o polvo.
     */
    public String getTipo(){
        return tipo;
    }

    /**
     * Fija el tipo del extintor.
     * @param tipo El tipo a fijar.
     */
    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    /**
     * @return Devuelve el peso del extintor, en kilogramos.
     */
    public int getPeso(){
        return peso;
    }

    /**
     * Fija el peso del extintor.
     * @param tipo El peso a fijar.
     */
    public void setPeso(int peso){
        this.peso = peso;
    }

    /**
     * @return Devuelve la fecha de la ultima revision del extintor.
     */
    public LocalDate getUltimaRevision(){
        return ultimaRevision;
    }

    /**
     * Fija la fecha en que ha sido revisado el extintor
     * por ultima vez.
     * @param fechaRevision La fecha de revision en formato "yyyy-mm-dd"
     */
    public void setUltimaRevision(String fechaRevision){
        ultimaRevision = LocalDate.parse(fechaRevision);
    }

    /**
     * @return Devuelve la tarea. Si la tarea esta completada, entonces muestra
     * un "[X]" delante de la tarea; si no esta completada muestra un "[ ]"
     */
    public String getDatosExtintor(){
        String datosExtintor =  "Extintor de " + tipo + ". Peso del agente: " + peso + "kg. -- Fecha de la última revisión: " + fechaEsp(ultimaRevision) + "." ;
        return datosExtintor;
    }

    /**
     * Metodo para dar formato español a las fechas de revision.
     * @param fechaAFormatear La fecha a formatear.
     * @return La cadena de la fecha ya formateada.
     */
    public String fechaEsp(LocalDate fechaAFormatear){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd' de 'MMMM' de 'yyyy");        
        String fechaEspanol = fechaAFormatear.format(formateador);
        return fechaEspanol;
    }
}
