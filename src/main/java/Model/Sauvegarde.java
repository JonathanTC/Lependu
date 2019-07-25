package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.Score;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author thiro
 */
public class Sauvegarde implements Serializable{  
    public ArrayList<Score> scores; 
    
    public Sauvegarde(){
        scores = new ArrayList<Score>();
    }
}
