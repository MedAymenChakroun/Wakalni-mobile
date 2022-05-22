/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author dell
 */
public class Organisation {

   
    private  int organisationid ;
    private  String Nom;
    private  String adresse;
    private  String email;
    private  int    numero;
    private  int    leftoverid;
   

    public Organisation() {
    }

    public Organisation(int organisationid, String Nom, String adresse, String email, int numero, int leftoverid) {
        this.organisationid = organisationid;
        this.Nom = Nom;
        this.adresse = adresse;
        this.email = email;
        this.numero = numero;
        this.leftoverid = leftoverid;
    }

    public Organisation(String Nom, String adresse, String email, int numero, int leftoverid) {
        this.Nom = Nom;
        this.adresse = adresse;
        this.email = email;
        this.numero = numero;
        this.leftoverid = leftoverid;
    }
    

    @Override
    public String toString() {
        return "Organisation{ Nom=" + Nom + ", adresse=" + adresse + ", email=" + email + ", numero=" + numero + '}';
    }

    public int getOrganisationid() {
        return organisationid;
    }

    public String getNom() {
        return Nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public int getNumero() {
        return numero;
    }

    public int getLeftoverid() {
        return leftoverid;
    }

    public void setOrganisationid(int organisationid) {
        this.organisationid = organisationid;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLeftoverid(int leftoverid) {
        this.leftoverid = leftoverid;
    }

   
    
    
    
    
}
