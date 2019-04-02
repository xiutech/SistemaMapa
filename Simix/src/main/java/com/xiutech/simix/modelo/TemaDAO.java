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
public class TemaDAO extends AbstractDAO<Tema>{
    public TemaDAO(){
        super();
    }
    
    @Override
    public void save(Tema tema){
        super.save(tema);
    }
    
    
    @Override
    protected void update(Tema tema){
        super.update(tema);
    }
        
    @Override
    protected void delete(Tema tema){
        super.delete(tema);
    }
    
    protected Tema find(int id){
        return super.find(Tema.class, id);
    }
    
    protected List<Tema> findAll(){
        return super.findAll(Tema.class);
    }
}
