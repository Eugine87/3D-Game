/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

import java.awt.event.KeyEvent;

/**
 *
 * @author Star
 */
public class Game {
   public int time;
   public Controller controls;
   public Level level;
   
   public  Game() {
    
       controls = new Controller();
       level = new Level(20,20);
}
   
   public void tick(boolean[]key){
       time++;
       boolean forward = key[KeyEvent.VK_W];
       boolean back = key[KeyEvent.VK_S];
       boolean left = key[KeyEvent.VK_A];
       boolean right = key[KeyEvent.VK_D];
        boolean rleft = key[KeyEvent.VK_LEFT];
       boolean rright = key[KeyEvent.VK_RIGHT];
       boolean jump = key [KeyEvent.VK_SPACE];
       boolean crouch = key [KeyEvent.VK_CONTROL];
       boolean run = key [KeyEvent.VK_SHIFT];
       
       //boolean turnLeft = key[KeyEvent.VK_LEFT];
       //boolean turnRight = key[KeyEvent.VK_RIGHT];
       
       //controls.tick(forward, back, left, right , turnLeft, turnRight);
        controls.tick(forward, back, left, right,rleft,rright,jump,crouch,run);
   }
}
