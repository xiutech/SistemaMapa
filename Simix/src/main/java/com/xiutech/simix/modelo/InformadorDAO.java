/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.modelo;

import java.util.List;

/**
 *
 * @author jhovangallardo
 */
public class InformadorDAO extends AbstractDAO<Informador>{
    /**
     * 
     */
    public InformadorDAO(){
        super();
    }
    
    
    /**
     * 
     * @param informador 
     */
    @Override
    public void save(Informador informador){
        super.save(informador);
    }
    
    /**
     * 
     * @param informador 
     */
    @Override
    public void update(Informador informador){
        super.save(informador);
    }
    
    /**
     * 
     * @param informador 
     */
    @Override
    public void delete(Informador informador){
        super.delete(informador);
    }
       
    /**
     * 
     * @param id
     * @return 
     */
    public Informador find(int id){
        return super.find(Informador.class, id);
    }
    
    /**
     * 
     * @return 
     */
    public List<Informador> findAll(){
        return super.findAll(Informador.class);
    } 
}
