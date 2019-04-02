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
public class MarcadorDAO extends AbstractDAO<Marcador>{
    
    public MarcadorDAO(){
        super();
    }
    
    @Override
    public void save(Marcador mcr){
        super.save(mcr);
    }
    
    
    @Override
    protected void update(Marcador mcr){
        super.update(mcr);
    }
        
    @Override
    protected void delete(Marcador mcr){
        super.delete(mcr);
    }
    
    protected Marcador find(int id){
        return super.find(Marcador.class, id);
    }
    
    protected List<Marcador> findAll(){
        return super.findAll(Marcador.class);
    }
}