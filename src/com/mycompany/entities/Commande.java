/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;
import java.util.Date;  
/**
 *
 * @author Pyrodramatic
 */
public class Commande {
    private int id;
    private String datecreation;
    private String dateexpedition;
    private String datearivee;
    private int quantite;
    private int panierid;
    private int clientid;
    private float prixproduit;
    private String nomclient;


    public Commande() {
    }

    public Commande(int panierid, int clientid) {
        this.panierid = panierid;
        this.clientid = clientid;
    }
    

    public Commande(String datecreation, String dateexpedition, String datearivee, int quantite, int panierid, int clientid) {
        this.datecreation = datecreation;
        this.dateexpedition = dateexpedition;
        this.datearivee = datearivee;
        this.quantite = quantite;
        this.panierid = panierid;
        this.clientid = clientid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public String getDateexpedition() {
        return dateexpedition;
    }

    public void setDateexpedition(String dateexpedition) {
        this.dateexpedition = dateexpedition;
    }

    public String getDatearivee() {
        return datearivee;
    }

    public void setDatearivee(String datearivee) {
        this.datearivee = datearivee;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPanierid() {
        return panierid;
    }

    public void setPanierid(int panierid) {
        this.panierid = panierid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public float getPrixproduit() {
        return prixproduit;
    }

    public void setPrixproduit(float prixproduit) {
        this.prixproduit = prixproduit;
    }

    public String getNomclient() {
        return nomclient;
    }

    public void setNomclient(String nomclient) {
        this.nomclient = nomclient;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", datecreation=" + datecreation + ", dateexpedition=" + dateexpedition + ", datearivee=" + datearivee + ", quantite=" + quantite + ", panierid=" + panierid + ", clientid=" + clientid + ", prixproduit=" + prixproduit + ", nomclient=" + nomclient + '}';
    }

   
    
    
    
}
