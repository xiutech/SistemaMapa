/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Informador;
import com.xiutech.simix.modelo.Marcador;
import com.xiutech.simix.modelo.MarcadorDAO;
import com.xiutech.simix.modelo.Tema;
import com.xiutech.simix.modelo.TemaDAO;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author fercho117
 */
public class ABTemaPropioController {
    private Tema tema;
    private List<Marcador> marcadores;
    
    public ABTemaPropioController(Tema tema){
    this.tema = tema;
    this.marcadores = new LinkedList<>();
    }
    
    public ABTemaPropioController(String nombre, Informador informador){
    this.tema = new Tema();
    tema.setNombre(nombre);
    tema.setInformador(informador);
    tema.setColor(null); //por agregar color
    this.marcadores = new LinkedList<>();
    }
    
    /**
     * @return the tema
     */
    public Tema getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(Tema tema) {
        this.tema = tema;
    }

    /**
     * @return the marcadores
     */
    public List<Marcador> getMarcadores() {
        return marcadores;
    }

    /**
     * @param marcadores the marcadores to set
     */
    public void setMarcadores(List<Marcador> marcadores) {
        this.marcadores = marcadores;
    }
    
    //Tal vez sea necesario modificar flujo de eventos.
    public void agregaTema(){
        //if(marcadores.isEmpty())
            //error
        TemaDAO udbT = new TemaDAO();
        MarcadorDAO udbM = new MarcadorDAO();
        udbT.save(getTema());
        for(Marcador mcr:getMarcadores())
            udbM.save(mcr);
    }

    /**
    private void verificaDatos(){
    }
    
    public void agregaMarcador(Marcador marcador){
        
    }
    **/
}
