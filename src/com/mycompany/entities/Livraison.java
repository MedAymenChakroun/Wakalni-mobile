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
public class Livraison {
    
    private int id;
    private int idcommande;
    private int idlivreur;
    private int idclient;
    private String livreur;
    private String livreurtel;

    public Livraison() {
    }

    public Livraison(int idcommande, int idlivreur, int idclient, String livreur, String livreurtel) {
        this.idcommande = idcommande;
        this.idlivreur = idlivreur;
        this.idclient = idclient;
        this.livreur = livreur;
        this.livreurtel = livreurtel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    public int getIdlivreur() {
        return idlivreur;
    }

    public void setIdlivreur(int idlivreur) {
        this.idlivreur = idlivreur;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public String getLivreur() {
        return livreur;
    }

    public void setLivreur(String livreur) {
        this.livreur = livreur;
    }

    public String getLivreurtel() {
        return livreurtel;
    }

    public void setLivreurtel(String livreurtel) {
        this.livreurtel = livreurtel;
    }
    
    
    
}
