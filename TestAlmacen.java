import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TestAlmacen.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TestAlmacen
{
    /**
     * Default constructor for test class TestAlmacen
     */
    public TestAlmacen()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testFuncionalidades()
    {
        Almacen almacen1 = new Almacen();
        System.out.println("Creamos un nuevo almacen con 6 extintores y mostramos el listado por pantalla:");
        System.out.println();
        almacen1.addExtintor("",0,"0000-01-01");
        almacen1.addExtintor("agua",1,"2022-01-25");
        almacen1.addExtintor("agua",6,"2015-09-05");
        almacen1.addExtintor("espuma",2,"2018-01-25");
        almacen1.addExtintor("agua",12,"2017-12-10");
        almacen1.addExtintor("polvo",12,"2015-08-04");
        almacen1.mostrarExtintores();
        System.out.println();
        System.out.println("Ordenamos el inventario según el peso de los extintores, de mayor a menor:");
        System.out.println();
        almacen1.ordenarDeMayorAMenorPorPeso();
        System.out.println();
        System.out.println("¡Se esperan 5 extintores listados!");
        System.out.println();
        System.out.println("Ahora ordenamos según la última fecha de revisión, empezando por la más cercana:");
        System.out.println();
        almacen1.ordenarPorFechaDeRevisionMasCercana();
        System.out.println();
        System.out.println("¡Se esperan 4 extintores listados!");
        System.out.println();
        System.out.println("Modificamos los datos de 2 extintores y eliminamos todos los de agua. Mostramos el listado por pantalla:");
        almacen1.modificarExtintor(1, "espuma", 6, "2017-08-23");
        almacen1.modificarExtintor(5, "gas", 2, "2013-04-05");
        almacen1.eliminarPorTipo("agua");
        System.out.println();
        almacen1.mostrarExtintores();
        System.out.println();
        System.out.println("¡Se esperan 4 extintores listados!");
        System.out.println();
    }
}

