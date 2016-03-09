/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simorion;
import javax.swing.*;
import javax.sound.midi.Synthesizer;
import java.util.Arrays;

/**
 * Sets up the GUI and creates sixteen logical layers
 * @author Mark Fowden
 */
public class Controller {
    static Layer currentLayer;
    static Mode currentMode;

    /*
    * @param args arguments
    */
    public static void main(String[] args){
        //Create GUI
        GUI mainGUI = new GUI();
        mainGUI.setUp();
        
        //Create synthesizer
        MusicPlayer musicplayer = new MusicPlayer();
        Synthesizer synthesizer = musicplayer.getSynthesizer();
        
        
        //Must set up the logical layer objects in order to manipulate them when on click.
        Layer[] layers = new Layer[16];
        
        for (int i = 0; i<16; i++)
        {
            layers[i]= new Layer();
        }
        setCurrentLayer(layers[0]);
        
        //Loop through each layer
        
        Thread thread = new Thread(){
            public void run(){
                int columnCounter = 0;
                while(true)
                {
                    //Display visually the current line
                    for(int j=0;j<4;j++){
                        //Replace the icons' pictures with the timer picture
                        mainGUI.ibutton.buttons[j*5][columnCounter].setIcon(mainGUI.buttonOnUp);
                    }

                    //Play sounds
                    for(int layerCounter=0;layerCounter<16;layerCounter++){
                        //Get layer values
                        Layer curLayer = layers[layerCounter];
                        boolean[] notesToPlay = curLayer.getColumn(columnCounter);

                        for(int rowCounter=0;rowCounter<16;rowCounter++){
                            if(notesToPlay[rowCounter]){
                                int adjustedPitch = 100-(int)(100*((double)(rowCounter)/15));
                                musicplayer.playInstrument(synthesizer, 1, curLayer.getInstrument(), adjustedPitch, curLayer.getVelocity());
                            }
                        }

                    }

                    //Sleep the thread to pause between notes
                    try{
                        this.sleep(300);
                    }catch(InterruptedException e){
                        System.out.println("Thread died");
                        System.exit(1);
                    }

                    //Revert the display changes
                    for(int j=0;j<4;j++){
                        if(getCurrentLayer().getDotState(j*5, columnCounter)){
                            mainGUI.ibutton.buttons[j*5][columnCounter].setIcon(mainGUI.buttonOnUp);
                        }else{
                            mainGUI.ibutton.buttons[j*5][columnCounter].setIcon(mainGUI.buttonOffUp);
                        }
                    }

                    //Increment the current column with a cap
                    columnCounter++;
                    if(columnCounter==16){
                        columnCounter = 0;
                    }
                }
            }
        };
        thread.start();
    }
    
    /*
    * @return the current logical later
    */
    public static Layer getCurrentLayer(){
        return currentLayer;
    }
    
    /*
    *Set the current layer
    *@param layer the 16x16 grid of booleans
    */
    public static void setCurrentLayer(Layer layer){
        currentLayer = layer;
    }
    
    /*
    * @return the current Mode
    * @Ilden Dengtash
    */
    public static Mode getMode(){
        return currentMode;
    }
    
    /*
    *Set the current mode
    *@param mode ...
    *@author Ilden Dengtash
    */
    public static void setMode(Mode mode){
        currentMode = mode;
    }
}
