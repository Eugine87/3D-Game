/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;


/**
 *
 * @author Star
 */
public class RunGame {
  public RunGame(){
         BufferedImage cursor = new BufferedImage(16,16,BufferedImage.TYPE_INT_ARGB);
        Cursor blank = Toolkit.getDefaultToolkit().createCustomCursor(cursor, new Point(0,0), "blank");
        Display game = new Display();
        JFrame f = new JFrame();
        f.add(game);
        f.setSize(Display.getGameWidth(),Display.getGameHeight());
       // f.pack();
        //f.getContentPane().setCursor(blank);
        f.setTitle(Display.TITLE);
       // f.setSize(width,height);
        f.setLocationRelativeTo(null);
        f.setResizable(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        
        game.start();
        stopMenuThread();
  }  
  private void stopMenuThread(){
      Display.getLauncherInstance().stopMenu();
  }
}
