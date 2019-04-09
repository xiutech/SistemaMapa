/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Administrador;
import com.xiutech.simix.modelo.Informador;
import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.AdministradorDAO;
import com.xiutech.simix.modelo.InformadorDAO;
import com.xiutech.simix.modelo.ComentaristaDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author kevin
 */
@ManagedBean
@SessionScoped
public class IniciarSesionController implements Serializable{
    private String correo;
    private String contrasenia;

    public String getCorreo() {
        return correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

   
    
    public String login(){
        //UsuarioDAO udb = new UsuarioDAO();
        AdministradorDAO adb = new AdministradorDAO();
        ComentaristaDAO cdb = new ComentaristaDAO();
        InformadorDAO idb = new InformadorDAO();
        
        Administrador admin = adb.buscaPorCorreoContrasenia(correo, contrasenia);
        Comentarista comen = cdb.buscaPorCorreoContrasenia(correo, contrasenia);
        Informador info = idb.buscaPorCorreoContrasenia(correo, contrasenia);
        
        FacesContext context = FacesContext.getCurrentInstance();
        if(admin != null){
            UserLogged u = new UserLogged(admin.getNombre(),admin.getCorreo());   
            context.getExternalContext().getSessionMap().put("user", u);
            return "/administrador/perfilAdministrador?faces-redirect=true";
        } else if (comen != null){
            UserLogged u = new UserLogged(comen.getNombre(),comen.getCorreo());
            context.getExternalContext().getSessionMap().put("user", u);
            return "/comentarista/perfilComentarista?faces-redirect=true";
        } else if (info != null) {
            UserLogged u = new UserLogged(info.getNombre(),info.getCorreo());
            context.getExternalContext().getSessionMap().put("user", u);
            return "/informador/perfilInformador?faces-redirect=true";
        }
        
        Mensajes.error("NO hay usuarios con este correo"+this.correo);
        return "";
        
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
    
    
    public class UserLogged implements Serializable{
        private String nombre;
        private String correo;
        
        public UserLogged(String nombre, String correo) {
            this.nombre = nombre;
            this.correo = correo;
        }

        
        
        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }
        
        
    }
}