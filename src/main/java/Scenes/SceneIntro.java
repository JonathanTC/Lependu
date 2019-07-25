package Scenes;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
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
public class SceneIntro extends JPanel{
    
    private Image img;
    
    public SceneIntro(){
        try {
            img = ImageIO.read(new File("images/131868.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void paintComponent(Graphics g){
        
        int posX, posY = 30;
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        
        String data = "Bienvenue dans le jeu du Pendu";  
        g2d.drawString(data, this.getWidth()/2 - g2d.getFontMetrics().stringWidth(data)/2, posY);
        
        posX = this.getWidth()/2 - 250/2;
        posY += 20;
        g2d.drawImage(img, posX, posY, 250, 250, this);
        
        g2d.setFont(new Font("Arial", Font.PLAIN, 15));
        posX -= 200;
        posY += 250 + 30;
        data = "Vous avez 7 coups pour trouver le mot caché et si vous réussissez... et bien on recommence !";
        g2d.drawString(data, posX, posY);
        
        posY += 15;
        data = "Plus vous avez trouvé de mots, plus votre score grandira !!! Alors à vous de jouer !";
        g2d.drawString(data, posX, posY);
    }
}
