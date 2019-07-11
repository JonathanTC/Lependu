
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class SeekLetter implements ActionListener{
    private String letter;
    private String word;
    
    public SeekLetter(String letter, String word){
        this.letter = letter;
        this.word = word;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {          
        letter = letter.toLowerCase();
        System.out.println(word.indexOf(letter));
        ((JButton)e.getSource()).setEnabled(false);
    } 
}
