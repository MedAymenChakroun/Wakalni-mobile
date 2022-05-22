/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Pyrodramatic
 */
public class Panier {
    private int panierid;
    private int produitid;
    private int clientid;
    private int quantite;
    private float prixprod;
    private float prix;
    private String nom;

    public Panier() {
    }

    public Panier(int produitid, int clientid, int quantite) {
        this.produitid = produitid;
        this.clientid = clientid;
        this.quantite = quantite;
    }

    public Panier(int produitid, int clientid, int quantite, float prixprod, float prix, String nom) {
        this.produitid = produitid;
        this.clientid = clientid;
        this.quantite = quantite;
        this.prixprod = prixprod;
        this.prix = prix;
        this.nom = nom;
    }

    public int getPanierid() {
        return panierid;
    }

    public void setPanierid(int panierid) {
        this.panierid = panierid;
    }

    public int getProduitid() {
        return produitid;
    }

    public void setProduitid(int produitid) {
        this.produitid = produitid;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrixprod() {
        return prixprod;
    }

    public void setPrixprod(float prixprod) {
        this.prixprod = prixprod;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "panier{" + "panierid=" + panierid + ", produitid=" + produitid + ", clientid=" + clientid + ", quantite=" + quantite + ", prixprod=" + prixprod + ", nom=" + nom + '}';
    }

    
}
