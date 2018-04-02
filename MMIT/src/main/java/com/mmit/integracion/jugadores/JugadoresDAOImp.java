package com.mmit.integracion.jugadores;

import com.mmit.integracion.conexion.Conexion;
import com.mmit.integracion.equipos.EquiposDAOImp;
import com.mmit.negocio.equipos.EquipoTrans;
import com.mmit.negocio.jugadores.JugadorTrans;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JugadoresDAOImp implements JugadoresDAO {

    @Override
    public ArrayList<JugadorTrans> readAll() {
       try {
            Conexion.getInstancia().abrir();
            Connection c = Conexion.getInstancia().getResource();
            
            PreparedStatement ps = c.prepareStatement("SELECT * FROM jugadores");
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList<JugadorTrans> jugadores = new ArrayList<JugadorTrans>();
            
            while(rs.next())
                jugadores.add(new JugadorTrans(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getInt("id_equipo"), rs.getString("posicion"), rs.getInt("partidos"), rs.getInt("minutos"), rs.getInt("campoAnotados"), rs.getInt("campoIntentados"),rs.getInt("dosAnotados"), rs.getInt("dosIntentados"), rs.getInt("tresAnotados"), rs.getInt("tresIntentados"), rs.getInt("libresAnotados"), rs.getInt("libresIntentados"), rs.getInt("rebotesOfensivos"), rs.getInt("rebotesDefensivos"), rs.getInt("asistencias"), rs.getInt("robos"), rs.getInt("faltas"), rs.getInt("puntos"), rs.getInt("tapones"), rs.getInt("perdidas")));
            
            Conexion.getInstancia().cerrar();
            return jugadores;
        } catch (SQLException ex) {
            Logger.getLogger(EquiposDAOImp.class.getName()).log(Level.SEVERE, null, ex);
            Conexion.getInstancia().cerrar();
        }
        
        return null;
    }
    /**
     *Metodo donde se recoge la información de un jugador por un id ya dado
     * @param id_jugador : ID del jugador a buscar
     * @return Un transfer con toda la información del jugador o null si no existe
     * @exception SQLException si hay algun error con la base de datos
     */
    @Override
    public JugadorTrans getByID(int id_jugador){
        try {
            Conexion.getInstancia().abrir();
            Connection c = Conexion.getInstancia().getResource();
            
            PreparedStatement ps = c.prepareStatement("SELECT * FROM jugadores WHERE id = " + id_jugador);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
              return new JugadorTrans(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getInt("id_equipo"), rs.getString("posicion"), rs.getInt("partidos"), rs.getInt("minutos"), rs.getInt("campoAnotados"), rs.getInt("campoIntentados"),rs.getInt("dosAnotados"), rs.getInt("dosIntentados"), rs.getInt("tresAnotados"), rs.getInt("tresIntentados"), rs.getInt("libresAnotados"), rs.getInt("libresIntentados"), rs.getInt("rebotesOfensivos"), rs.getInt("rebotesDefensivos"), rs.getInt("asistencias"), rs.getInt("robos"), rs.getInt("faltas"), rs.getInt("puntos"), rs.getInt("tapones"), rs.getInt("perdidas")); 
            }
            
            Conexion.getInstancia().cerrar();

        } catch (SQLException ex) {
            Conexion.getInstancia().cerrar();
        }
        
        return null;
    
    }
    
    /**
     * Esta función nos permite conocer la plantilla de un equipo completo
     * @param id_equipo :Id del equipo del cual queremos saber su plantilla
     * @return un ArrayList con la plantilla, se devolvera vacio si no hay ningun jugador
     */
    @Override
    public ArrayList<JugadorTrans> getRoster(Integer id_equipo){
        ArrayList<JugadorTrans> jugadores = new ArrayList();
        try {
            Conexion.getInstancia().abrir();
            Connection c = Conexion.getInstancia().getResource();
            
            PreparedStatement ps = c.prepareStatement("SELECT * FROM jugadores WHERE id_equipo = " + id_equipo);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
              jugadores.add(new JugadorTrans(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getInt("id_equipo"), rs.getString("posicion"), rs.getInt("partidos"), rs.getInt("minutos"), rs.getInt("campoAnotados"), rs.getInt("campoIntentados"),rs.getInt("dosAnotados"), rs.getInt("dosIntentados"), rs.getInt("tresAnotados"), rs.getInt("tresIntentados"), rs.getInt("libresAnotados"), rs.getInt("libresIntentados"), rs.getInt("rebotesOfensivos"), rs.getInt("rebotesDefensivos"), rs.getInt("asistencias"), rs.getInt("robos"), rs.getInt("faltas"), rs.getInt("puntos"), rs.getInt("tapones"), rs.getInt("perdidas"))); 
            }
            
            Conexion.getInstancia().cerrar();

        } catch (SQLException ex) {
            Conexion.getInstancia().cerrar();
        }
        
        return jugadores;
    }
}
