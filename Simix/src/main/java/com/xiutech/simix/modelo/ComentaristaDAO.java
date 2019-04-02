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
public class ComentaristaDAO extends AbstractDAO<Comentarista>{
      
    public ComentaristaDAO(){
        super();
    }
    
    @Override
    protected void save(Comentarista comentarista){
        super.save(comentarista);
    }
    
    
    @Override
    protected void update(Comentarista comentarista){
        super.update(comentarista);
    }
        
    @Override
    protected void delete(Comentarista comentarista){
        super.delete(comentarista);
    }
    
    protected Comentarista find(String correo){
        return super.find(Comentarista.class, correo);
    }
    
    protected List<Comentarista> findAll(){
        return super.findAll(Comentarista.class);
    }
      
}
