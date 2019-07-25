package Scenes;

import Controler.Controler;
import Model.Model;
import Model.Score;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
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
public class SceneScore extends JPanel{
    
    private Controler controler;
    private Image img;
    private ArrayList<Score> scores;
    
    public SceneScore(Model model){       
        try{
            img = ImageIO.read( new File("images/131868.jpg"));
        }
        catch(IOException ex){
            ex.getMessage();
        }
        
        scores = model.getScores();
    }
    
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());  
        g2d.setColor(Color.BLACK);
        
        int size = 40;
        int i = 0;
        
        for(Score s : scores){
            g2d.setFont(new Font("Arial", Font.PLAIN, size));
            g2d.drawString(s.getName() + " -> " + s.getScore() + " pts", 20, size + i);
            i = i + size + 10;
            size -= 3;
        }
        
        g2d.drawImage(img, 450, 35, 300, 300, this);
    }
}
