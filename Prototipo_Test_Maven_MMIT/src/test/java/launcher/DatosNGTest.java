/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author crash
 */
public class DatosNGTest {
    
    @Test
    public void testRead() {
        System.out.println("Lectura BBDD usuario Existente");
        String nombre = "Prueba A";
        Datos instance = new Datos();
        int expResult = 0;
        int result = instance.read(nombre);
        assertEquals(result, expResult);
    }
    
    @Test
    public void testReadError() {
        System.out.println("Lectura BBDD usuario Existente");
        String nombre = "Prueba B";
        Datos instance = new Datos();
        int expResult = 0;
        int result = instance.read(nombre);
        assertNotEquals(result, expResult);
    }
/*
    @Test
    public void testWrite() {
        System.out.println("write");
        String nombre = "";
        Datos instance = new Datos();
        int expResult = 0;
        int result = instance.write(nombre);
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }
    */
}