package com.mmit.integracion.equipos;

import com.mmit.integracion.conexion.Conexion;
import com.mmit.integracion.usuarios.UsuariosDAOImp;
import com.mmit.negocio.equipos.EquipoTrans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquiposDAOImp implements EquiposDAO{

       public ArrayList<EquipoTrans> readAll() throws Exception {
       
        try {
            Conexion.getInstancia().abrir();
            Connection c = Conexion.getInstancia().getResource();
            
            PreparedStatement ps = c.prepareStatement("SELECT * FROM equipos order by victorias DESC");
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<EquipoTrans> equipos = new ArrayList<EquipoTrans>();
            
            while(rs.next()){
                equipos.add(new EquipoTrans(rs.getInt("id"),rs.getString("nombre"),rs.getInt("victorias"),rs.getInt("derrotas"), rs.getInt("anotados"), rs.getInt("recibidos"), rs.getInt("campoAnotados"), rs.getInt("campoIntentados"), rs.getInt("tresAnotados"), rs.getInt("tresIntentados"), rs.getInt("libresAnotados"), rs.getInt("libresIntentados"), rs.getInt("rebotesOfensivos"), rs.getInt("rebotesTotales"), rs.getInt("asistencias"), rs.getInt("robos"), rs.getInt("faltas"), rs.getInt("tapones"), rs.getInt("perdidas")));
            }
            Conexion.getInstancia().cerrar();
            return equipos;

        } catch (SQLException ex) {
            Conexion.getInstancia().cerrar();
            throw new Exception("Error al conectarse a la BBDD");
        }
    }

    @Override
    public EquipoTrans readById(Integer id) throws Exception{
        try {
            Conexion.getInstancia().abrir();
            Connection c = Conexion.getInstancia().getResource();
            
            PreparedStatement ps = c.prepareStatement("SELECT * FROM equipos WHERE id = " + id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                return new EquipoTrans(rs.getInt("id"),rs.getString("nombre"),rs.getInt("victorias"),rs.getInt("derrotas"), rs.getInt("anotados"), rs.getInt("recibidos"), rs.getInt("campoAnotados"), rs.getInt("campoIntentados"), rs.getInt("tresAnotados"), rs.getInt("tresIntentados"), rs.getInt("libresAnotados"), rs.getInt("libresIntentados"), rs.getInt("rebotesOfensivos"), rs.getInt("rebotesTotales"), rs.getInt("asistencias"), rs.getInt("robos"), rs.getInt("faltas"), rs.getInt("tapones"), rs.getInt("perdidas"));
            }
            
            Conexion.getInstancia().cerrar();

        } catch (SQLException ex) {
            Conexion.getInstancia().cerrar();
            throw new Exception("Error al conectarse a la BBDD");
        }
        
        return null;
    }
    
    public boolean insert(EquipoTrans team) throws Exception{
        boolean devolver = false;
         try {
            Conexion.getInstancia().abrir();
            Connection c = Conexion.getInstancia().getResource();
          
            PreparedStatement ps =  c.prepareStatement("Insert into equipos (nombre, victorias, derrotas) values (?,?,?)");
        
            ps.setString(1, team.getNombre());
            ps.setInt(2, team.getVictorias());
            ps.setInt(3, team.getDerrotas());
            devolver = ps.execute();
            
            Conexion.getInstancia().cerrar();
            
        } catch (SQLException ex) {
            Conexion.getInstancia().cerrar();
            throw new Exception("Error al conectarse a la BBDD");
        }
         return devolver;
    }
    
    /*public int Modify(EquipoTrans team) throws Exception{
        
        int devolver = 0;
        
        try {
            Conexion.getInstancia().abrir();
            Connection c = Conexion.getInstancia().getResource();
            
            PreparedStatement ps = c.prepareStatement("UPDATE equipos SET nombre=?, victorias=?, derrotas=? WHERE id = " + team.getId());
            
            ps.setString(1, team.getNombre());
            ps.setInt(2, team.getVictorias());
            ps.setInt(3, team.getDerrotas());
            
            devolver = ps.executeUpdate();
            
            Conexion.getInstancia().cerrar();

        } catch (SQLException ex) {
            Conexion.getInstancia().cerrar();
            throw new Exception("Error al conectarse a la BBDD");
        }
        return devolver;
    }*/
    
}
