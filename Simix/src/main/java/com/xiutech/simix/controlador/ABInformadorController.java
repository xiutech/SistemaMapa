/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Informador;
import com.xiutech.simix.modelo.InformadorDAO;
import java.util.Date;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author jhovangallardo
 */

@ManagedBean
public class ABInformadorController {
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
    * Genera una cadena alfanumerica de longitud n
    *
    * @param n     la longitud de la cadena a generar
    * @return      una cadena alfanumerica aleatoria
    */
    private static String getAlphaNumericString(int n) 
    { 
  
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"
                                    + "!?&#%"; 
  
        // crea un stringBuilder del tamaño de la contraseña que queremos 
        StringBuilder sb = new StringBuilder(n); 
  
        // agrega n caracteres al azar
        for (int i = 0; i < n; i++) { 
  
            int index = (int)(AlphaNumericString.length() * Math.random()); 
  
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
    
    /**
    * Actualiza la contraseña del objeto con una contraseña aleatoria
    */
    private void generarContrasenia(){
        this.setContrasenia(getAlphaNumericString(10));
    }
    
    
    /**
    * Envia correo electronico con las credenciales del objeto
    * Se debe actualizar el token de acceso cada hora
    */    
    private void enviarCorreo() {
        SendingMailThroughGmailSMTPServer em = new SendingMailThroughGmailSMTPServer();
        String SMTP_SERVER_HOST = "smtp.gmail.com";
        String SMTP_SERVER_PORT = "587";
        String SUBJECT = "Tu cuenta de SIMIX se ha registrado correctamente";
        String BODY = "Hola, esas son tus credenciales:<br><br>Nombre: " + this.getNombre() + "<br>Contraseña: " + this.getContrasenia();
        String FROM_USER_EMAIL = "jhovan.simix@gmail.com";
        String FROM_USER_FULLNAME = "SIMIX";
        String CLIENT_ID = "";
        String CLIENT_SECRET = "";
        String REFRESH_TOKEN = "";
        //String FROM_USER_ACCESSTOKEN = em.getAccessTokenFromRefreshToken(CLIENT_ID,CLIENT_SECRET, REFRESH_TOKEN);
        String FROM_USER_ACCESSTOKEN = "ya29.GlzlBqmLvKrLbRR93KonUaJ63ZA3ZYrxO0tcOlSirYcmFHA6qxp4TYzysZPTdh5K6nWJCWOfWvcVVgmXrjDUsLfJkY4ZmGlUd-Yokj34ObdfopY3_3yMkdSlodXhUw";
        String TO_USER_EMAIL = this.correo;
        em.sendMail(SMTP_SERVER_HOST, SMTP_SERVER_PORT, FROM_USER_EMAIL, FROM_USER_ACCESSTOKEN, FROM_USER_EMAIL, FROM_USER_FULLNAME, TO_USER_EMAIL, SUBJECT, BODY);
    }
    
    /**
    * Da de alta a un informador en la base de datos
    *
    * @return      el path de la vista "confirmacion_administrador" con un mensaje de confirmacion
    */
    public String darAltaInformador() {
        String mensaje = "El registro se llevó a cabo con exito y se ha enviado la contraseña al informador";
        String mensaje_error = "Ha ocurrido un error";
        
        try { 
            this.generarContrasenia();
            Informador i = new Informador();
            i.setNombre(nombre);
            i.setCorreo(correo);
            i.setContrasenia(contrasenia);
            InformadorDAO udb = new InformadorDAO();
            udb.save(i);
            enviarCorreo();
            return "confirmacion_administrador?faces-redirect=true&mensaje=" + mensaje;
        }
        catch(Exception e){
            return "confirmacion_visitante?faces-redirect=true&mensaje=" + mensaje_error;
        }
    }
}
