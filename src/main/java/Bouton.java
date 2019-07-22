
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiro
 */
public class Bouton extends JButton{
    
    public Bouton(){
        this.setMinimumSize(new Dimension(80, 30));
        this.setBounds(2, 2, 56, 36);
    }
    
    public Bouton(String name){
        super(name);
        this.setMinimumSize(new Dimension(80, 30));
        this.setBounds(2, 2, 56, 36);
    }
    
    public void setText(String text){
        super.setText(text);
    }
 
}
