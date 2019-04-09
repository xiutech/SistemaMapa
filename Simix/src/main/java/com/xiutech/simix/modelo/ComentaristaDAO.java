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
public class ComentaristaDAO extends AbstractDAO<Comentarista>{
      
    public ComentaristaDAO(){
        super();
    }
    
    @Override
    public void save(Comentarista comentarista){
        super.save(comentarista);
    }
    
    
    @Override
    public void update(Comentarista comentarista){
        super.update(comentarista);
    }
        
    @Override
    public void delete(Comentarista comentarista){
        super.delete(comentarista);
    }
    
    public Comentarista find(String correo){
        return super.find(Comentarista.class, correo);
    }
    
    public List<Comentarista> findAll(){
        return super.findAll(Comentarista.class);
    }
    
    public List<Comentarista> buscaPorNombre(String nombre){
//        if(nombre.equals(""))
//            return null;
        List<Comentarista> usuarios =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Comentarista  u where u.nombre like concat('%',:nombre,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("nombre", nombre);
            usuarios = (List<Comentarista>)query.list();
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
    
    public Comentarista buscaPorCorreoContrasenia(String correo,String contrasenia){
        Comentarista u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentarista where correo = :correo and contrasenia = :contrasenia";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            query.setParameter("contrasenia",contrasenia);
            u = (Comentarista)query.uniqueResult();
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
    
    public Comentarista buscaPorCorreo(String correo){
        Comentarista u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Comentarista where correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            u = (Comentarista)query.uniqueResult();
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
