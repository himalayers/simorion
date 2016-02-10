package simorion;
import java.io.Serializable;

/*
*This handles a 16x16 boolean array which represent a layer of music.
*@author Tom Fullalove
*
*/
public class Layer implements Serializable{
    boolean[][] dots;
    
    public Layer(){
        dots = new boolean[16][16];
    }
    
    /*
    *Given the coordinates of a button it will toggle its stored value.
    *@author Tom Fullalove
    *@param posx the x coordinate of the button to be toggled
    *@param posy the y coordinate of the button to be toggled
    *@return returns the state of the button after being toggled
    */
    public boolean toggleDot(int posx, int posy){
        if (dots[posx][posy])
        {
            dots[posx][posy] = false;
            return false;
        }
        else
        {
            dots[posx][posy] = true;
            return true;
        }
    }

    /*
    *Resets the 16x16 array of dots to false.
    *@author Ilden Dengtash
    */
    public void ClearDots(){
            dots = new boolean[16][16];
    }
}
