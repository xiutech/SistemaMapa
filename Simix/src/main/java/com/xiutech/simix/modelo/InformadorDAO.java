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
     * @param correo
     * @return 
     */
    public Informador find(String correo){
        return super.find(Informador.class, correo);
    }
    
    /**
     * 
     * @return 
     */
    public List<Informador> findAll(){
        return super.findAll(Informador.class);
    } 
    
        public List<Informador> buscaPorNombre(String nombre){
//        if(nombre.equals(""))
//            return null;
        List<Informador> usuarios =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Informador  u where u.nombre like concat('%',:nombre,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("nombre", nombre);
            usuarios = (List<Informador>)query.list();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return usuarios;
    }
    
    public Informador buscaPorCorreoContrasenia(String correo,String contrasenia){
        Informador u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Informador where correo = :correo and contrasenia = :contrasenia";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            query.setParameter("contrasenia",contrasenia);
            u = (Informador)query.uniqueResult();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return u;
    }
    
    public Informador buscaPorCorreo(String correo){
        Informador u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Informador where correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            u = (Informador)query.uniqueResult();
            tx.commit();
        }catch(HibernateException e){
            if(tx!=null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return u;
}
}
