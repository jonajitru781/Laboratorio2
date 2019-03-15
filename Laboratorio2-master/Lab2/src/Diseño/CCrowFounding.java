/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diseño;

import LogicaNegocio.GestorPlataforma;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/**
 *
 * @author ADMIN
 */
public class CCrowFounding {
    CrowFunding cf;
    GestorPlataforma gestor;

    public CCrowFounding(GestorPlataforma gestor) {
        this.gestor = gestor;
        cf=new CrowFunding();
        cf.getPromotor().setOnAction(new Ep());
        cf.getAportante().setOnAction(new Ea());
        cf.getIniciativa().setOnAction(new Ei());
        cf.getSubasta().setOnAction(new ESubasta());
    }
    int ac=0;//revisar asignacion del promotor a la iniciativa que se agregue
    
    class Ep implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            CPromotor cp=new CPromotor(gestor);
            cp.mostrarVista();
        }
        
    }
    
    class Ea implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            CAportante ca=new CAportante(gestor);
            ca.mostrarVista();
        }
        
    }
    
    
    class Ei implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            
            try{
                if(gestor.getPromotor()==null){
                    throw new NullPointerException ("Debes inscribir al promotor de la iniciativa");
                }
                
                CIniciativa ci=new CIniciativa(gestor,gestor.getPromotor().get(ac));
                ci.mostrarVista();
                ac++;
                
            }catch(NullPointerException e){
                Alert alert= new Alert(Alert.AlertType.ERROR,e.getMessage());
                alert.setTitle("Error");
                alert.showAndWait();
            }catch(IndexOutOfBoundsException f){
                Alert alert= new Alert(Alert.AlertType.ERROR,"No existen promotres en el sistema, debes agregar almenos un Promotor");
                alert.setTitle("Error");
                alert.showAndWait();
            }
        }//COLOCAR BIEN ESTE ALERT PARA QUE INDIQUE EL ERROR    
        
    }
    
    class ESubasta implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
            CVSubasta subasta= new CVSubasta(gestor);
            subasta.mostrarVista();
        }
        
    }
    
    
    public void mostrar(){
        Singleton singleton=Singleton.getSingleton();
        this.cf.show(singleton.getStage());

    }
    
    
}
