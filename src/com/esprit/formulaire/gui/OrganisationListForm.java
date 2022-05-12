/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.formulaire.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.esprit.formulaire.entities.Organisation;
import com.esprit.formulaire.services.OrganisationService;
import java.util.ArrayList;

/**
 *
 * @author dell
 */

public class OrganisationListForm extends Form 
{

    public OrganisationListForm() {
                getToolbar().addMaterialCommandToSideMenu("LeftOvers", FontImage.MATERIAL_AV_TIMER, e ->  new LeftoversListForm().show() );
                                 getToolbar().addMaterialCommandToSideMenu("Organisations", FontImage.MATERIAL_EVENT, e ->  new OrganisationListForm().show() );
                
                                 
                                 setTitle("Organisations");

                
                                 getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
              new AddUpdateOrganisationForm(this,true,new Organisation()).show();
        });
         
                                 
                         Container organisationsContainer = new Container(BoxLayout.y());
                                 
                         Container rechercheContainer = new Container(BoxLayout.y());
                         TextField rechercheTF = new TextField("","Recherche");
                         
                         Button rechercheBT = new Button("Recherche");
                                                  rechercheContainer.addAll(rechercheTF,rechercheBT);

                         
                                                  ArrayList<Organisation> organisations = OrganisationService.getInstance().getAllOrganisations();


                         rechercheBT.addActionListener(l->{
                             
                             
                         organisationsContainer.removeAll();
                         
                           for (Organisation o : organisations) {
                               if (o.getNom().toLowerCase().contains(rechercheTF.getText().toLowerCase()) || o.getEmail().toLowerCase().contains(rechercheTF.getText().toLowerCase())||  rechercheTF.getText().equals("")) 
                                    organisationsContainer.add(this.addOrganisationsHolder(o));
                               }
                           
                           this.refreshTheme();
                         
                         
                         });
                         
                                 
                                 
                                 
                                 
                                 
                                 
        for (Organisation o : organisations) {
         organisationsContainer.add(this.addOrganisationsHolder(o));
        }
        
        this.addAll(rechercheContainer,organisationsContainer);
                
      
    }

    private Container addOrganisationsHolder(Organisation o) {
        try {
           
            Container holderContainer = new Container(BoxLayout.x());
            Container detailsContainer = new Container(BoxLayout.y());
            Container buttonContainer = new Container(BoxLayout.x());

                       
            Label lbNom = new Label(o.getNom());
            Label lbEmail = new Label(o.getEmail());
            Button btUpdate = new Button("Modifier");
            Button btDelete = new Button("Supprimer");
            
                btUpdate.getAllStyles().setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0x228B22).
                    strokeColor(0).
                    strokeOpacity(120).
                    stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1)));
            btUpdate.getAllStyles().setFgColor(0xffffff);
            
            btDelete.getAllStyles().setBorder(RoundBorder.create().
                    rectangle(true).
                    color(0xFF0000).
                    strokeColor(0).
                    strokeOpacity(120).
                    stroke(new Stroke(2, Stroke.CAP_SQUARE, Stroke.JOIN_MITER, 1)));
            btDelete.getAllStyles().setFgColor(0xffffff);
            
            
            buttonContainer.addAll(btUpdate, btDelete);
            detailsContainer.addAll(lbNom, lbEmail,buttonContainer);
            holderContainer.addAll(detailsContainer);

            btUpdate.addActionListener(e -> {
               new AddUpdateOrganisationForm(this,false,o).show();
            });
 
            btDelete.addActionListener(e -> {
               OrganisationService.getInstance().deleteOrganisation(o.getOrganisationid());
               new OrganisationListForm().show();
            });
            
            return holderContainer;
            
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        } 
        
        return new Container(BoxLayout.x());
    }

  

}
