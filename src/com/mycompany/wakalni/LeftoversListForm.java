/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.wakalni;

/**
 *
 * @author dell
 */

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Stroke;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.mycompany.entities.Leftovers;
import com.mycompany.services.LeftoversService;
import java.io.IOException;
import java.util.ArrayList;



public class LeftoversListForm extends Form 
{

    public LeftoversListForm() {
        
                getToolbar().addMaterialCommandToSideMenu("LeftOvers", FontImage.MATERIAL_AV_TIMER, e ->  new LeftoversListForm().show() );
                                 getToolbar().addMaterialCommandToSideMenu("Organisations", FontImage.MATERIAL_EVENT, e ->  new OrganisationListForm().show() );
                
               
                setTitle("Leftovers");
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD, e -> {
                new AddUpdateLeftoverForm(this,true,new Leftovers()).show();
            
        });
                 
        ArrayList<Leftovers> leftovers = LeftoversService.getInstance().getAllLeftovers();

        
        for (Leftovers l : leftovers) {
         this.add(this.addleftoverHolder(l));
        }
                
    }

    private Container addleftoverHolder(Leftovers l) {
        try {
           
            Container holderContainer = new Container(BoxLayout.x());
            Container detailsContainer = new Container(BoxLayout.y());
            Container buttonContainer = new Container(BoxLayout.x());

           

            Label lbSujet = new Label(l.getSujet());
            Label lbAdresse = new Label(l.getType());
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
            detailsContainer.addAll(lbSujet, lbAdresse,buttonContainer);
            holderContainer.addAll(detailsContainer);

            btUpdate.addActionListener(e -> {
               new AddUpdateLeftoverForm(this,false,l).show();
            });
 
            btDelete.addActionListener(e -> {
               LeftoversService.getInstance().deleteLeftover(l.getLeftoverid());
                new LeftoversListForm().show();
            });
            
            return holderContainer;
            
        } catch (NullPointerException e) {
            System.err.println(e.getMessage());
        } 
        
        return new Container(BoxLayout.x());
    }

  

}

