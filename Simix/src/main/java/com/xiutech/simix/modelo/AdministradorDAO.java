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
public class AdministradorDAO extends AbstractDAO<Administrador>{
    
    public AdministradorDAO(){
        super();
    }
    
    @Override
    public void save(Administrador admin){
        super.save(admin);
    }
    
    
    @Override
    public void update(Administrador admin){
        super.update(admin);
    }
        
    @Override
    public void delete(Administrador admin){
        super.delete(admin);
    }
    
    public Administrador find(String correo){
        return super.find(Administrador.class, correo);
    }
    
    public List<Administrador> findAll(){
        return super.findAll(Administrador.class);
    }
 
    public List<Administrador> buscaPorNombre(String nombre){
//        if(nombre.equals(""))
//            return null;
        List<Administrador> usuarios =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "From Administrador  u where u.nombre like concat('%',:nombre,'%')";
            Query query = session.createQuery(hql);
            query.setParameter("nombre", nombre);
            usuarios = (List<Administrador>)query.list();
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
    
    public Administrador buscaPorCorreoContrasenia(String correo,String contrasenia){
        Administrador u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Administrador where correo = :correo and contrasenia = :contrasenia";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            query.setParameter("contrasenia",contrasenia);
            u = (Administrador)query.uniqueResult();
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
    
    public Administrador buscaPorCorreo(String correo){
        Administrador u =null;
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try{
            tx = session.beginTransaction();
            String hql = "from Administrador where correo = :correo";
            Query query = session.createQuery(hql);
            query.setParameter("correo", correo);
            u = (Administrador)query.uniqueResult();
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
