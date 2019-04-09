package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Comentario;
import com.xiutech.simix.modelo.ComentarioDAO;
import com.xiutech.simix.modelo.ComentarioId;
import com.xiutech.simix.modelo.Comentarista;
import com.xiutech.simix.modelo.ComentaristaDAO;
import com.xiutech.simix.modelo.Marcador;
import com.xiutech.simix.modelo.MarcadorDAO;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author ummyers
 */


/** Clase del controlador para comentario
 *  Se Agregara, Borrará y modificará un comentario.
 * @author Ummyers
 */
//@RequestScoped

@ManagedBean 
public class ABMComentarioPropioController{
    
    //private int id_marcador;
    //private String correo_comentarista;
    private String texto; 
    //private ComentarioId id_comentario;
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public String agregaComentario(){
        String error = "Ocurrio un error";
        String mensaje = "Se agrego exitosamente";
        
      //  try{
            ComentaristaDAO udb = new ComentaristaDAO ();
            Comentarista cmn = udb.find("mu.andrea@ciencias.unam.mx");
            
            MarcadorDAO udb_prima = new MarcadorDAO();
            Marcador ma = udb_prima.find(2);

            ComentarioId id = new ComentarioId();
            id.setIdMarcador(ma.getIdMarcador());
            id.setCorreoComentarista(cmn.getCorreo());

            //Se crea el objeto comentario para agregarlo a la base de datos
            Comentario u = new Comentario();
            u.setTexto(texto);
            u.setComentarista(cmn);
            u.setMarcador(ma);
            u.setId(id);
            ComentarioDAO uu = new ComentarioDAO ();
             //Para agregar a la base de datos.
        uu.save(u);
        return "MensajeExitoIH?faces-redirect=true&mensaje=" + mensaje;
        
        //}catch(Exception e){
            
        // return "MensajeExitoIH?faces-redirect=true&mensaje="  + mensaje ;           
        //}
       
       
    }
    
    public void eliminaComentario(){}
    
    public void editaComentario(){

    }
    
}