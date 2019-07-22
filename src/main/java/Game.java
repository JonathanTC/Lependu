
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Game extends JPanel implements Observer{
    
    private JPanel keyboard;
    private Image[] img = new Image[7];
    private int life, wordCount , score;
    
    private char letter;
    private String word;
    private char[] tabWord;
    
    private Controler controler;
    
    public Game(Controler controler){
       this.controler = controler;
       loadGame();
    } 
        
    public void loadGame(){
        
       life = 7;
       wordCount = score = 0;
        
       GridBagConstraints gbc = new GridBagConstraints();
       int py, px, nb;
       
       this.setBackground(Color.WHITE);
       this.setLayout( null);

       keyboard = new JPanel();
       keyboard.setLayout(new GridBagLayout());
       
       /*
        * Chargement du mot à partir du dictionnaire
        * Creation d'une table qui contient les '*'
       */
        try {
            this.word = loadWord("dictionnaire.txt");
            removeAccents(this.word);
            tabWord = new char[this.word.length()];
            for(int i=0; i<tabWord.length; i++) tabWord[i] = '*';
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        /*
         * Creation du clavier virtuel (Controller)
        */
        nb = px = py = 0;
        for(int i=65; i<91; i++){
           gbc.gridx = px;
           gbc.gridy = py;
           gbc.fill = GridBagConstraints.BOTH;
           
           JPanel p = new JPanel();
           p.setPreferredSize(new Dimension(60,40));
           p.setLayout(null);
           
           letter = (char)i;
           Bouton bouton = new Bouton("" + letter);
           bouton.setBounds(2, 2, 56, 36);
           
           bouton.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   controler.check(letter);
               }
           });
           
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
       
       for(int i=0; i < life; i++){
           String str = "images/" + Integer.toString(i) + ".jpg";
            try{
                img[i] = ImageIO.read(new File(str));
            } catch (IOException ex) {
               ex.printStackTrace();
           }
       }
       
       System.out.println(word);
       this.revalidate();
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
        
        /*
         * affichage du mot à trouver
        */
        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLUE);
        g2d.drawChars(tabWord, 0, tabWord.length, keyboard.getX() + keyboard.getWidth()/2 - (g2d.getFontMetrics().charsWidth(tabWord, 0, tabWord.length)/2), keyboard.getY() - 15);
        
        /*
        * dessin de l'image
        */
        g2d.drawImage(img[this.life-1], 480, 30, 300, 300, this);        
    } 
    
    private void removeAccents(String word){   
        String avec = "âäàçéêëèîïôöûüù";
        String sans = "aaaceeeeiioouuu";
        
        for(int i=0; i < avec.length(); i++)
            this.word = this.word.replace(avec.charAt(i), sans.charAt(i));
    }
    
    public char getLetter(){
        return this.letter;
    }
    
    public String getWord(){
        return this.word;
    }
    
    public char[] getTabWord(){
        return this.tabWord;
    }
    
    public int getLife(){
        return this.life;
    }
    
    public void setLife(int life){
        this.life = life;
    }

    public int getScore(){
        return this.score;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
