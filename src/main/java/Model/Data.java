package Model;


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
    public transient int score, life;
    public transient String word;
    public transient char[] tabWord;
    public transient int wordCount = 0;
    public ArrayList<Score> listScore = new ArrayList<Score>();
}
