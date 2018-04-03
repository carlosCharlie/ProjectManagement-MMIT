/*
 * Copyright (C) 2018 Your Organisation
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mmit.integracion.entrenadores;

import com.mmit.integracion.conexion.Conexion;
import com.mmit.negocio.entrenadores.EntrenadorTrans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Fernando
 */
public class EntrenadoresDAOTestNG {
    
    public EntrenadoresDAOTestNG() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void testReadAll(){
        try{
            System.out.println("Listar entrenadores");
            EntrenadorDAO instance = new EntrenadorDAOImp();
            ArrayList<EntrenadorTrans> result = instance.readAll();
            assertNotNull(result);
        }catch(Exception e){
            Assert.fail();
        }
    }
    @Test
    public void testReadByName(){
        try{
            System.out.println("Buscar Entrenador");
            EntrenadorDAO instance = new EntrenadorDAOImp();
           
            Conexion.getInstancia().abrir();
            Connection c = Conexion.getInstancia().getResource();

            PreparedStatement ps = c.prepareStatement("INSERT INTO ENTRENADOR VALUES"
                    + "('Kobe','Bryant', 67, 34, 2)");  

            ResultSet rs = ps.executeQuery();
            ArrayList<EntrenadorTrans> result = instance.readByName("Kobe","Bryant");
            assertNotNull(result);
        }catch(Exception e){
            Assert.fail();
        }
    }
    
    
}