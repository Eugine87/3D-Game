 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

 
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
 import static java.util.Locale.ITALIAN;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author Star
 */
public class Display extends Canvas implements Runnable {

 public static final long serialVersionUID = 1L;    
    
 //public static final int width = 600;
 //public static final int height = 400; 
//public static int scale = 3;  
 public static  int width = 600;
 public static int height = 400; 
 public static final String TITLE = "Doom";
 
 private Game game;
 private Thread thread;
 private Screen screen;
 private BufferedImage img;
 //private Render render;
 
 private boolean running = false;
 private int[]pixels;
 private InputHandler input;
 private int newX = 0;
 private int oldX = 0;
 private int fps;
 public static int selection = 0;
 static Launcher launcher;
 public static int MouseSpeed;
 
 
 public Display(){
     Dimension size = new Dimension(width ,height );
     setPreferredSize(size); 
     setMinimumSize(size); 
     setMaximumSize(size); 
     game = new Game();
     screen = new Screen(getGameWidth(),getGameHeight());
     img = new BufferedImage (getGameWidth(),getGameHeight(),BufferedImage.TYPE_INT_RGB);
     pixels =((DataBufferInt)img.getRaster().getDataBuffer()).getData();
     
     
     input = new InputHandler();
     addKeyListener(input);
     addFocusListener(input);
     addMouseListener(input);
     addMouseMotionListener(input);
    // setFocusable(true);
 }
 public static Launcher getLauncherInstance(){
     if(launcher==null){
       launcher = new Launcher(0);  
     }
    return launcher;
 }
 public static int getGameWidth(){
    
     return width;
 }
 public static int getGameHeight(){
     
     return height;
 }
 public synchronized void start(){
   if(running)return ; 
   running = true;
   thread = new Thread(this,"game");
   thread.start();
    
   //setFocusable(true);
   System.out.println("Working");
 }
 
  public synchronized void stop(){
      if(!running)return;
      running = false;
     try {
         thread.join();
     } catch (Exception e) {
         e.printStackTrace();
         System.exit(1);
     }
  }
  
    public static void main(String[] args) {
      //  Display display=new Display();
     //new Launcher(0);
    // new RunGame();
    getLauncherInstance();
    }

    @Override
    public void run() {
      int frames = 0;
      double unprocessedSeconds = 0;
      long previousTime = System.nanoTime();
      double secondsPerTick = 1/60.0;
      int tickCount =0;
      boolean ticked = false;
      
      
      while(running){
       long currentTime = System.nanoTime();
       long passedTime = currentTime - previousTime;
       previousTime = currentTime;
       unprocessedSeconds += passedTime/1000000000.0;
      launcher.updateFrame();
        requestFocus();
       
       while(unprocessedSeconds>secondsPerTick){
           tick();
           unprocessedSeconds -=secondsPerTick;
           ticked = true;
           tickCount++;
           if(tickCount % 60.0==0){
               System.out.println(frames + "fps");
               fps = frames;
               previousTime +=1000;
               frames = 0;
           }
       
       if(ticked =true){
           render();
           frames++;
          // renderMenu();
       }
     //render();
      } 
       
     
      }  
    }
    
   private void tick(){
     game.tick(input.key);
     
       newX = InputHandler.MouseX;  
       if(newX > oldX){
       //System.out.println("RIGHT!!");    
       Controller.turnRight =true;
       }
       if(newX < oldX ){
          // System.out.println("LEFT!!");
           Controller.turnLeft = true;
       }
       if(newX == oldX ){
           //System.out.println("NONE!!");
           Controller.turnLeft = false;
           Controller.turnRight = false;
       }
       MouseSpeed = Math.abs(newX-oldX);
       
       oldX = newX; 
   }
    
   private void render(){
    BufferStrategy bs = this.getBufferStrategy(); 
    if(bs == null){
     createBufferStrategy(3); 
     return;
    }
    //screen.clear();
    screen.render(game);
    for(int i=0;i < getGameWidth() * getGameHeight();i++){
        pixels[i]=screen.pixels[i];
    }
    Graphics g = bs.getDrawGraphics();
    g.drawImage(img, 0,0, getGameWidth() +10 ,getGameHeight() ,null);
    g.setFont(new Font ("Verdana",3,50));
    g.setColor(Color.YELLOW);
    g.drawString(fps + "FPS", 20, 50);
    g.dispose();
    bs.show();
   }
}
