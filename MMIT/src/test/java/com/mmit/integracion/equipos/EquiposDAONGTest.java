/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mmit.integracion.equipos;

import com.mmit.negocio.equipos.EquipoTrans;
import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author carlos
 */
public class EquiposDAONGTest {
    
    public EquiposDAONGTest() {
    }

    /**
     * Prueba que el primer equipo de prueba se lea bien
     */
    @Test
    public void testListarEquipos() {
        System.out.println("listarEquipos");
        EquiposDAO instance = new EquiposDAOImp();
        ArrayList<EquipoTrans> result = instance.listarEquipos();
        EquipoTrans primero = result.get(0);
        
        assertEquals(primero.getId(),0);
        assertTrue(primero.getNombre().equals("prueba"));
        assertEquals(primero.getVictorias(),1);
        assertEquals(primero.getDerrotas(),2);
        assertEquals(primero.getEntrenador(),0);
    }
    
}