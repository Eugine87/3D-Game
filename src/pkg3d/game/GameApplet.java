/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

import java.applet.Applet;
import java.awt.BorderLayout;

/**
 *
 * @author Star
 */
public class GameApplet extends Applet {
   public static final long serialVersionUID = 1L;    
   
   private Display display = new Display();
   public void init(){
       setLayout(new BorderLayout());
       add(display);
   }
   public void start(){
       display.start();
   }
   public void stop(){
       display.stop();
   }
}
