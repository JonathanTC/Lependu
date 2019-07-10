
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import javax.swing.JButton;
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
public class Game extends JPanel{
    
    private JPanel keyboard;
    private Image[] img = new Image[7];
    private int life = 6;
    private int wordCount = 0;
    private int score = 0;
    private String word;
    
    public Game(){
        // test
       GridBagConstraints gbc = new GridBagConstraints();
       int py, px, nb;
       
       this.setBackground(Color.WHITE);
       this.setLayout( null);

       keyboard = new JPanel();
       keyboard.setLayout(new GridBagLayout());
       
        try {
            this.word = loadWord("dictionnaire.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

       nb = px = py = 0;
       for(int i=65; i<91; i++){
           gbc.gridx = px;
           gbc.gridy = py;
           gbc.fill = GridBagConstraints.BOTH;
           
           JPanel p = new JPanel();
           p.setPreferredSize(new Dimension(60,40));
           p.setLayout(null);
           
           String letter = "" + (char)i;
           Bouton bouton = new Bouton(letter);
           bouton.setBounds(2, 2, 56, 36);
           bouton.addActionListener(new SeekLetter(letter, word));
           
           p.add(bouton);
           keyboard.add(p, gbc);
           
           if(px >= 6){
               py++; 
               if(py < 3) px = 0;
               else px = 1;
           }
           else
               px++;
       }    
       
       keyboard.setBounds(new Rectangle(30, 170, 420, 160));
       keyboard.setBackground(Color.RED);

       this.add(keyboard);    
       
       for(int i=0; i<7; i++){
           String str = "images/" + Integer.toString(i) + ".jpg";
            try{
                img[i] = ImageIO.read(new File(str));
            } catch (IOException ex) {
               ex.printStackTrace();
           }
       }
       

    } 
        
    private String loadWord(String path) throws IOException{        
        String word = null;
        
        try {
            BufferedReader b = new BufferedReader(new FileReader(path));            
            Stream<String> s = b.lines(); 
            Object o[] = s.toArray();

            double rand = Math.random() * o.length;   
            word = o[(int)rand].toString();
           
            b.close();  
        } catch (IOException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        return word;
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        
        g2d.setFont(new Font("Arial", Font.BOLD, 15));
        g2d.setColor(Color.BLACK);
        String str = "Nombre de mot trouvé: " + wordCount;
        g2d.drawString(str, 30, 70);
        
        str = "Votre score actuel est de : " + score;
        g2d.drawString(str, 30, 90);
        
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLUE);
        str = this.word;
        g2d.drawString(str, keyboard.getLocation().x + (keyboard.getWidth()/2) - (g2d.getFontMetrics().stringWidth(str)/2), 150);
        
        g2d.drawImage(img[this.life], 480, 30, 300, 300, this);
    }   
}
