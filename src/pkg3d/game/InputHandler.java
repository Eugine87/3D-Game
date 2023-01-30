/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Star
 */
class InputHandler implements KeyListener,FocusListener,MouseListener,MouseMotionListener{

  
    public boolean[]key = new boolean[68836];
    public  static int MouseX ;
    public static int MouseY ;
    public  static int MouseDX ;
    public static int MouseDY ;
    public  static int MousePX ;
    public static int MousePY ;
   public static int MouseButton;
   public static boolean dragged = false;
    public void keyTyped(KeyEvent e) {
       
    }

   
    public void focusGained(FocusEvent e) {
        
    }

    @Override
    public void focusLost(FocusEvent e) {
    for(int i=0;i<key.length;i++){    
        key[i]=false;
    }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
     dragged=false;   
    }

    @Override
    public void mousePressed(MouseEvent e) {
      MouseButton = e.getButton();  
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    MouseButton = 0;   
    dragged=false;    
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
     
    }

    @Override
    public void mouseDragged(MouseEvent e) {
     MouseDX=e.getX();
     MouseDY=e.getY();  
    }

    @Override
    public void mouseMoved(MouseEvent e) {
     dragged=true;
     MouseX=e.getX();
     MouseY=e.getY();
    }
    @Override
     public void keyPressed(KeyEvent e) {
       int keyCode = e.getKeyCode();
       if(keyCode > 0 && keyCode<key.length){
           key[keyCode] = true;
       }
    }

    
    @Override
    public void keyReleased(KeyEvent e) {
       int keyCode = e.getKeyCode();
       if(keyCode > 0 && keyCode<key.length){
       key[keyCode]=false; 
       }
    }
    
}
