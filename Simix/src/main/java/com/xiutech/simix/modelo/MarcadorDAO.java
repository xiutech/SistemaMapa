/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.modelo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author fercho117
 */
public class MarcadorDAO extends AbstractDAO<Marcador>{
    
    public MarcadorDAO(){
        super();
    }
    
    /**
     * Agrega un Marcador en la base.
     * @param mcr El marcador a añadir
     */
    @Override
    public void save(Marcador mcr){
        super.save(mcr);
    }
    
    /**
     * Actualiza un Marcador en la base.
     * @param mcr El marcador a actualizar
     */
    @Override
    public void update(Marcador mcr){
        super.update(mcr);
    }
     
    /**
     * Elimina un Marcador en la base.
     * @param mcr El marcador a eliminar.
     */
    @Override
    public void delete(Marcador mcr){
        super.delete(mcr);
    }
    
    /**
     * Consulta un marcador en la base mediante su identicador.
     * @param id El identificador del marcador.
     * @return El Marcador construido con datos de la base. Null si no existe.
     */
    public Marcador find(int id){
        return super.find(Marcador.class, id);
    }
    
    /**
     * Consulta de todos los marcadores en la base.
     * @return Una lista de los marcadores en la base.
     */
    public List<Marcador> findAll(){
        return super.findAll(Marcador.class);
    }
    
        /**
     * Consulta un marcador en la base mediante su ubicacion.
     * @param latitud La latitud de la posicion del marcador.
     * @param longitud La latitud de la posicion del marcador.
     * @return El Marcador construido con datos de la base. Null si no existe.
     */
    public Marcador buscaPorLatLng(double lat, double lng) {
        Marcador m = null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from Marcador where longitud = :lng and latitud = :lat";
            Query query = session.createQuery(hql);
            query.setParameter("lng", lng);
            query.setParameter("lat", lat);
            m = (Marcador)query.uniqueResult();
            tx.commit();
            
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();

        }finally{
            session.close();
        }
        return m;
    }
}