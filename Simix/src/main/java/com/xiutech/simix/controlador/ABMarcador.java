/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xiutech.simix.controlador;

import com.xiutech.simix.modelo.Marcador;
import com.xiutech.simix.modelo.MarcadorDAO;
import com.xiutech.simix.modelo.Tema;
import com.xiutech.simix.modelo.TemaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author fercho117
 */
@ManagedBean
public class ABMarcador {

    private double longitud;
    private double latitud;
    private String descripcion;
    private String datosUtiles;
    private String tema;
    
    /**
     * @return the tema
     */
    public String getTema() {
        return tema;
    }

    /**
     * @param tema the tema to set
     */
    public void setTema(String tema) {
        this.tema = tema;
    }
    
    /**
     * @return the longitud
     */
    public double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the latitud
     */
    public double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String agregaMarcador(){
        MarcadorDAO mdb =new MarcadorDAO();
        Marcador marcador = mdb.buscaPorLatLng(latitud, longitud);
        if(marcador!= null){
            this.descripcion ="";
            Mensajes.error("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        marcador = new Marcador();
        marcador.setDescripcion(descripcion);
        marcador.setDatosUtiles(datosUtiles);
        marcador.setLatitud(latitud);
        marcador.setLongitud(longitud);
        TemaDAO udbT = new TemaDAO();
        Tema temaO = udbT.find(this.tema);
        if(temaO == null){
            this.descripcion ="";
            Mensajes.error("El tema no existe");
            return "";
        }
        marcador.setTema(temaO);
        mdb.save(marcador);
        Mensajes.info("Marcador añadido");
        return "/informador/agregaMarcador?faces-redirect=true";
    }

        public String agregaMarcadorNuevoTema(){
        MarcadorDAO mdb =new MarcadorDAO();
        Marcador marcador = mdb.buscaPorLatLng(latitud, longitud);
        if(marcador!= null){
            this.descripcion ="";
            Mensajes.error("Ya existe un marcador con estas cordenadas \n" +"Lat: "+this.latitud +" Lng: "+this.longitud);
            return "";
        }
        marcador = new Marcador();
        marcador.setDescripcion(descripcion);
        marcador.setDatosUtiles(datosUtiles);
        marcador.setLatitud(latitud);
        marcador.setLongitud(longitud);
        TemaDAO udbT = new TemaDAO();
        Tema temaO = udbT.find(this.tema);
        if(temaO == null){
            this.descripcion ="";
            Mensajes.error("El tema no existe");
            return "";
        }
        marcador.setTema(temaO);
        mdb.save(marcador);
        return "/informador/agregaMarcador?faces-redirect=true";
    }
    
    /**
     * @return the datosUtiles
     */
    public String getDatosUtiles() {
        return datosUtiles;
    }

    /**
     * @param datosUtiles the datosUtiles to set
     */
    public void setDatosUtiles(String datosUtiles) {
        this.datosUtiles = datosUtiles;
    }
    
    
}
