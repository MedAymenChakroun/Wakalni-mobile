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
public class Produit {
    private int produitid;
    private String nom;
    private String type;
    private int crid;
    private float prix;
    private String image;
    private String image_name;
    private String firstname;

    public Produit() {
    }

    public Produit(String nom, String type, int crid, float prix, String image, String image_name, String firstname) {
        this.nom = nom;
        this.type = type;
        this.crid = crid;
        this.prix = prix;
        this.image = image;
        this.image_name = image_name;
        this.firstname = firstname;
    }

    public int getProduitid() {
        return produitid;
    }

    public void setProduitid(int produitid) {
        this.produitid = produitid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCrid() {
        return crid;
    }

    public void setCrid(int crid) {
        this.crid = crid;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "Produit{" + "produitid=" + produitid + ", nom=" + nom + ", type=" + type + ", crid=" + crid + ", prix=" + prix + ", image=" + image + ", image_name=" + image_name + ", firstname=" + firstname + '}';
    }
    

   
    
    
    
}
