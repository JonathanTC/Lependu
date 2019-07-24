
import java.io.Serializable;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiro
 */
public class Score extends JPanel implements Serializable{
    private String name;
    private int score;
    
    public Score(String name, int score){
        this.name = name;
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }
    
    public String getName(){
        return this.name;
    }
}
