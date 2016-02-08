package simorion;
import java.io.Serializable;

//Can serializable only be applied to the array? probably a question for sprint 3 etc.
public class Layer implements Serializable{
    boolean[][] dots = new boolean[16][16];
    
    boolean toggleDot(int posx, int posy){
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
    
    void ClearDots(){
            dots = new boolean[16][16];
    }
}
