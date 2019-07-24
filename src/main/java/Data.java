
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiro
 */
public class Data implements Serializable{
    public int score, life;
    public String word;
    public char[] tabWord;
    public int wordCount = 0;
    public ArrayList<Score> listScore = new ArrayList<Score>();
}
