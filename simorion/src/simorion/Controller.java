/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simorion;

/**
 *
 * @author Student
 */
public class Controller {
    static Layer currentLayer;

    public static void main(String[] args){
        //Create GUI
        GUI mainGUI = new GUI();
        mainGUI.setUp();
        
        
        //Must set up the logical layer objects in order to manipulate them when on click.
        Layer[] layers = new Layer[16];
        
        for (int i = 0; i<16; i++)
        {
            layers[i]= new Layer();
        }
        setCurrentLayer(layers[0]);
    }
    
    public static Layer getCurrentLayer(){
        return currentLayer;
    }
    
    public static void setCurrentLayer(Layer layer){
        currentLayer = layer;
    }
}
