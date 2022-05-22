/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


import java.util.Date;




/**
 *
 * @author dell
 */
public class Leftovers {

  
   
    
    private int leftoverid;
    private String sujet;
    private String type;
    private int quantite;
    private Date dateexpiration;
    private int chefrestoid;
  
    public Leftovers  () {
    }

    public Leftovers(int leftoverid, String sujet, String type, int quantite, Date dateexpiration, int chefrestoid) {
        this.leftoverid = leftoverid;
        this.sujet = sujet;
        this.type = type;
        this.quantite = quantite;
        this.dateexpiration = dateexpiration;
        this.chefrestoid = chefrestoid;
    }

    public Leftovers(String sujet, String type, int quantite, Date dateexpiration, int chefrestoid) {
        this.sujet = sujet;
        this.type = type;
        this.quantite = quantite;
        this.dateexpiration = dateexpiration;
        this.chefrestoid = chefrestoid;
    }


    @Override
    public String toString() {
        return "leftovers{" + "leftoverid=" + leftoverid + ", sujet=" + sujet + ", type=" + type + ", quantite=" + quantite + ", dateexpiration=" + dateexpiration + ", chedfrestoid=" + chefrestoid + '}';
    }

    public int getLeftoverid() {
        return leftoverid;
    }

    public String getSujet() {
        return sujet;
    }

    public String getType() {
        return type;
    }

    public int getQuantite() {
        return quantite;
    }

    

    public int getchefrestoid() {
        return chefrestoid;
    }

    public void setLeftoverid(int leftoverid) {
        this.leftoverid = leftoverid;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    

    public void setChefrestoid(int chefrestoid) {
        this.chefrestoid = chefrestoid;
    }

    public Date getDateexpiration() {
        return dateexpiration;
    }

    public void setDateexpiration(Date dateexpiration) {
        this.dateexpiration = dateexpiration;
    }

  

   
    

  
    
}

