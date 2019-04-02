/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.modelo;

import java.util.List;

/**
 *
 * @author fercho117
 */
public class CalificacionDAO extends AbstractDAO<Calificacion>{
      
    public CalificacionDAO(){
        super();
    }
    
    @Override
    protected void save(Calificacion calificacion){
        super.save(calificacion);
    }
    
    
    @Override
    protected void update(Calificacion calificacion){
        super.update(calificacion);
    }
        
    @Override
    protected void delete(Calificacion calificacion){
        super.delete(calificacion);
    }
    
    protected Calificacion find(CalificacionId id){
        return super.find(Calificacion.class, id);
    }
    
    protected List<Calificacion> findAll(){
        return super.findAll(Calificacion.class);
    }
      
}

