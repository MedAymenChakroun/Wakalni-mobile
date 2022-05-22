/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author malek guemri
 */
public class Reclamation {
    
    private int id;
    private String sujet;
    private String contenu;
    private String etat;
    private String commande;
    private int commandeid;
    private String client;
    private int clientid;

    public Reclamation(String sujet, String contenu, String etat, int commandeid, int clientid) {
        this.sujet = sujet;
        this.contenu = contenu;
        this.etat = etat;
        this.commandeid = commandeid;
        this.clientid = clientid;
    }

    
    
    public Reclamation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCommande() {
        return commande;
    }

    public void setCommande(String commande) {
        this.commande = commande;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getCommandeid() {
        return commandeid;
    }

    public void setCommandeid(int commandeid) {
        this.commandeid = commandeid;
    }
    
}
