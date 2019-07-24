
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
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
public class SceneScore extends JPanel implements Observer{
    
    private Controler controler;
    private Data data;
    
    public SceneScore(Controler controler){
        this.controler = controler;
    }
    
    public void load(){
        controler.loadScore();
        controler.getBestScore();
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        int i = 0;
        for(Score s : data.listScore){
            System.out.println(s.getName());
            g2d.drawString(s.getName() + " -> " + s.getScore() + " pts", 20, 20 + i);
            i += 20;
        }
        
    }

    @Override
    public void update(GameState notifycation, Data data) {
        if(notifycation.equals(GameState.Score))
            this.data = data;
    }
}
