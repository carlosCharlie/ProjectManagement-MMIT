package com.mmit.negocio.equipos;

import com.mmit.integracion.entrenadores.EntrenadorDAO;
import com.mmit.integracion.factoriaIntegracion.FactoriaIntegracion;
import com.mmit.integracion.equipos.EquiposDAO;
import com.mmit.integracion.jugadores.JugadoresDAO;
import com.mmit.negocio.entrenadores.EntrenadorTrans;
import com.mmit.negocio.entrenadores.TOAEntrenadorEquipo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquiposSAImp implements EquiposSA {
    
    @Override
    public ArrayList<EquipoTrans> listarEquipos() throws Exception{
        FactoriaIntegracion instancia =  FactoriaIntegracion.getInstancia();
        EquiposDAO daoe= instancia.crearEquiposDAO();
       
    return daoe.readAll();
}

    @Override
    public TOAEntrenadorEquipoJugadores obtenerEquipoCompleto(int id) throws Exception{
       
        try {
            EquiposDAO equiposDao = FactoriaIntegracion.getInstancia().crearEquiposDAO();
            EntrenadorDAO entrenadoresDao = FactoriaIntegracion.getInstancia().crearEntrenadoresDAO();
            JugadoresDAO jugadoresDao = FactoriaIntegracion.getInstancia().crearJugadoresDAO();
            
            EquipoTrans equipo = equiposDao.readById(id);
            
            if(equipo==null){
                return null;
            }
            else{
                return new TOAEntrenadorEquipoJugadores(entrenadoresDao.readByEquipo(id),equipo,jugadoresDao.readRoster(id));
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    @Override
    public EquipoTrans obtenerEquipo(int id){
       
        try {
            EquiposDAO equiposDao = FactoriaIntegracion.getInstancia().crearEquiposDAO();
            
            EquipoTrans equipo = equiposDao.readById(id);
            
            return equipo;
        } catch (Exception ex) {
            Logger.getLogger(EquiposSAImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    @Override
    public int insert(TOAEntrenadorEquipo equipoEntr) {
         int respuesta = 0;
            try{
                EquiposDAO equiposDao = FactoriaIntegracion.getInstancia().crearEquiposDAO();
                EntrenadorDAO entrenadorDao = FactoriaIntegracion.getInstancia().crearEntrenadoresDAO();
                
                EquipoTrans t1 = equipoEntr.getEquipo();
                EntrenadorTrans t2 = equipoEntr.getEntrenador();
                if (equiposDao.readById(t1.getId()) == null && t2.getIdEquipo() == null) {
                    
                    //comprobaciones de estadisticas de juego
                    //si el numero es < que 0 se guarda un 0, no se puede dejar vacio
                    t1.setDerrotas(java.lang.Math.max(0,t1.getDerrotas()));
                    t1.setVictorias(java.lang.Math.max(0,t1.getVictorias()));
                    
                    
                    
                    equiposDao.insert(t1);
                    t2.setIdEquipo(equiposDao.readByNombre(t1.getNombre()).getId());
                    entrenadorDao.update(t2);
                }
                else{
                    respuesta = -1;                   
                }
                
            } catch (Exception ex) {
                respuesta = -5;
            }
            return respuesta;
    }

}
