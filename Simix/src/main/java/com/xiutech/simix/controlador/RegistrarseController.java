/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.ComentaristaDAO;
import java.util.Date;
import javax.faces.bean.ManagedBean;

import org.apache.commons.codec.binary.Base64;


/**
 *
 * @author jhovangallardo
 */

@ManagedBean
public class RegistrarseController {
    private String nombre;
    private String correo;
    private String contrasenia;

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

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    /**
    * Guarda al comentarista en la base de datos 
    * y envia un correo electronico de confirmacion 
    * con el link para activar la cuenta y las credenciales
    *
    * @return      el path de la vista "confirmacion_visitante" con un mensaje de confirmacion
    */
    public String enviarCorreo() {
        
        //agrega al comentarista a la base de datos
        String mensaje = "El registro se llevó a cabo con exito. Para activar su cuenta haga clic en el link que le fue enviado a su correo.";
        String mensaje_error = "Ha ocurrido un error";
        
        try { 
            //correo encriptado en base64
            String codigo = new String(Base64.encodeBase64(getCorreo().getBytes()));
            // link de activacion que enviara al comentarista
            String link = "http://localhost:8084/Simix/activar_cuenta.xhtml?faces-redirect=true&codigo=" + codigo;
            Comentarista c = new Comentarista();
            c.setNombre(nombre);
            c.setCorreo(correo);
            c.setContrasenia(contrasenia);
            c.setEstado(false);

            ComentaristaDAO udb = new ComentaristaDAO();
            udb.save(c);

            //envia el correo 
            SendingMailThroughGmailSMTPServer em = new SendingMailThroughGmailSMTPServer();
            String SMTP_SERVER_HOST = "smtp.gmail.com";
            String SMTP_SERVER_PORT = "587";
            String SUBJECT = "Tu cuenta de SIMIX se ha registrado correctamente";
            String BODY = "Hola, esas son tus credenciales:<br><br>Nombre: " + this.getNombre() + "<br>Contraseña: " + this.getContrasenia() + "<br>Haz clic <a href='" + link + "'>aqui</a> para activar tu cuenta";
            String FROM_USER_EMAIL = "jhovan.simix@gmail.com";
            String FROM_USER_FULLNAME = "SIMIX";
            String CLIENT_ID = "";
            String CLIENT_SECRET = "";
            String REFRESH_TOKEN = "";
            //String FROM_USER_ACCESSTOKEN = em.getAccessTokenFromRefreshToken(CLIENT_ID,CLIENT_SECRET, REFRESH_TOKEN);
            String FROM_USER_ACCESSTOKEN = "ya29.GlzlBqmLvKrLbRR93KonUaJ63ZA3ZYrxO0tcOlSirYcmFHA6qxp4TYzysZPTdh5K6nWJCWOfWvcVVgmXrjDUsLfJkY4ZmGlUd-Yokj34ObdfopY3_3yMkdSlodXhUw";
            String TO_USER_EMAIL = this.correo;
            em.sendMail(SMTP_SERVER_HOST, SMTP_SERVER_PORT, FROM_USER_EMAIL, FROM_USER_ACCESSTOKEN, FROM_USER_EMAIL, FROM_USER_FULLNAME, TO_USER_EMAIL, SUBJECT, BODY);

            return "confirmacion_visitante?faces-redirect=true&mensaje=" + mensaje;
        }
        catch(Exception e){
            return "confirmacion_visitante?faces-redirect=true&mensaje=" + mensaje_error;
        }
    }
    
    /**
    * Activa una cuenta a partir de su codigo unico 
    * (su correo en base64)
    *
    * @param codigo     correo de la cuenta a activar en base64
    * @return           el path de la vista "confirmacion_visitante" con un mensaje de confirmacion
    */
    public String activarCuenta(String codigo){
        String mensaje = "Su cuenta fue activada correctamente";
        String mensaje_error = "Ha ocurrido un error";
        
        try { 
            Comentarista c =  new Comentarista();
            ComentaristaDAO udb = new ComentaristaDAO();
            c = udb.find(new String(Base64.decodeBase64(codigo)));
            c.setEstado(true);
            udb.update(c);
            return "confirmacion_visitante?faces-redirect=true&mensaje=" + mensaje;
        }
        catch(Exception e){
            return "confirmacion_visitante?faces-redirect=true&mensaje=" + mensaje_error;
        }
    }
}
