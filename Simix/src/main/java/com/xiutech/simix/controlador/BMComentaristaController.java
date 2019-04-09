package com.xiutech.simix.controlador;

//import com.mashape.unirest.http.exceptions.UnirestException;
import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.ComentaristaDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author jhovangallardo
 */

@ManagedBean
@SessionScoped
public class BMComentaristaController {
    private String nombre;
    private String correo;
    private String contrasenia;
    
    private List<Comentarista> resultado;

    public List<Comentarista> getResultado() {
        return resultado;
}

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    public String buscaPorNombre(){
        IniciarSesionController.UserLogged ul = (IniciarSesionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        return ul.getNombre();
    }
    
    public String actualizaNombre() {
        IniciarSesionController.UserLogged ul = (IniciarSesionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        ComentaristaDAO cdb = new ComentaristaDAO();
        Comentarista comen = cdb.find(ul.getCorreo());
        comen.setNombre(this.getNombre());
        cdb.update(comen);
        return "/comentarista/perfilComentarista?faces-redirect=true";
    }
    
    public String actualizaContrasenia() {
        IniciarSesionController.UserLogged ul = (IniciarSesionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        ComentaristaDAO cdb = new ComentaristaDAO();
        Comentarista comen = cdb.find(ul.getCorreo());
        comen.setContrasenia(this.getContrasenia());
        cdb.update(comen);
        return "/comentarista/perfilComentarista?faces-redirect=true";
    }
    
    
    public String borrarCuenta() {
        IniciarSesionController.UserLogged ul = (IniciarSesionController.UserLogged) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        ComentaristaDAO cdb = new ComentaristaDAO();
        Comentarista comen = cdb.find(ul.getCorreo());
        cdb.delete(comen);
        return "/comentarista/perfilComentarista?faces-redirect=true";
    }

}