 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author Star
 */
public class Launcher extends Canvas implements Runnable{
    
    public static final long serialVersionUID = 1L;  
    protected JPanel window  = new JPanel();
    private JButton play,options,help,quit;
    
    
    private Rectangle rplay,roptions,rhelp,rquit;
    Configuration config = new Configuration();
    
    private int width = 800;
    private int height = 400;
    protected int button_width = 80;
    protected int button_height = 40;
    boolean running = false;
    Thread thread;
    JFrame frame = new JFrame();
    
 public  Launcher(int id){
  
     try{
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
     }catch(Exception e){
      e.printStackTrace();
     }
     frame.setUndecorated(true);
     frame.setTitle("Doom Launcher");
     frame.setSize(new Dimension(width,height));
     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
     frame.add(this);
    // getContentPane().add(window);
     //add(display); 
     frame.setLocationRelativeTo(null);
     frame.setResizable(false);
     frame.setVisible(true);
     window.setLayout(null);
     if(id==0){
      drawButtons();   
     }
     InputHandler input = new InputHandler();
     addKeyListener(input);
     addFocusListener(input);
     addMouseListener(input);
     addMouseMotionListener(input);
     startMenu();
     //display.start();
     frame.repaint();
 }  
 public void updateFrame(){
     if(InputHandler.dragged){
        Point p = frame.getLocation();
       //int x = getX();
       //int y = getY();
       frame.setLocation(p.x + InputHandler.MouseDX-InputHandler.MousePX,p.y + InputHandler.MouseDY-InputHandler.MousePY);
     }
    // setLocation(500,300);
     //System.out.println("X:"+ InputHandler.MouseX +",Y:"+ InputHandler.MouseY);
 }
  public void startMenu(){
      running = true;
      thread = new  Thread(this,"menu");
    thread.start();
  }
  public void stopMenu() {
     // running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
  }
    public void run() {
        requestFocus();
        while(running){
            try {
             renderMenu();   
            } catch (IllegalStateException e) {
            }
            
          updateFrame();
        }
    }
     private void renderMenu()throws IllegalStateException{
    BufferStrategy bs = this.getBufferStrategy(); 
    if(bs == null){
     createBufferStrategy(3); 
     return;
    }
   
    Graphics g = bs.getDrawGraphics();
  //  g.drawImage(img, 0,0, getGameWidth() +10 ,getGameHeight() ,null);
    g.setColor(Color.black);
    g.fillRect(0, 0,800, 400);
     try {
         g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/menu_img.jpg")),0,0,800,400,null);
         if(InputHandler.MouseX>670&&InputHandler.MouseX<670+80&&InputHandler.MouseY>130&&InputHandler.MouseY<130+30){
             g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/play.png")),670,130,80,30,null); 
             g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/add.png")),670+80,134,22,20,null); 
             if(InputHandler.MouseButton ==1 ){
                 config.loadConfiguration("res/settings/config.xml");
                 frame.dispose();
                 new RunGame();
              }
         }else{
            g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/play_off.png")),670,130,80,30,null);  
         }
         if(InputHandler.MouseX>670&&InputHandler.MouseX<670+130&&InputHandler.MouseY>170&&InputHandler.MouseY<170+30){
             g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/options.png")),670,170,130,30,null); 
              g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/add.png")),670+120,174,22,20,null); 
              if(InputHandler.MouseButton ==1 ){
                 // new RunGame();  
                 // dispose();
            new Options();
              }
         }else{
            g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/options_off.png")),670,170,130,30,null);  
         }
         if(InputHandler.MouseX>670&&InputHandler.MouseX<670+80&&InputHandler.MouseY>210&&InputHandler.MouseY<210+30){
             g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/Help.png")),670,210,80,30,null); 
              g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/add.png")),670+80,214,22,20,null); 
              if(InputHandler.MouseButton ==1 ){
                 // new RunGame();  
                  System.out.println("Help");  
              }
         }else{
            g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/Help_off.png")),670,210,80,30,null);  
         }
         if(InputHandler.MouseX>670&&InputHandler.MouseX<670+80&&InputHandler.MouseY>250&&InputHandler.MouseY<250+30){
             g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/Quit.png")),670,250,80,30,null); 
              g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/add.png")),670+80,254,22,20,null); 
              if(InputHandler.MouseButton ==1 ){
                  System.exit(0);
              }
         }else{
            g.drawImage(ImageIO.read(Launcher.class.getResource("/textures/Quit_off.png")),670,250,80,30,null);  
         }
        
     } catch (IOException ex) {
         ex.printStackTrace();
     }
    g.setColor(Color.red);
    g.setFont(new Font ("Verdana",0,40));
    //g.drawString("play", 650, 90);
    //g.drawString("options", 650, 130);
    //g.drawString("help", 650, 170);
    //g.drawString("quit", 650, 210);
    g.dispose();
    bs.show();
   }   
private void drawButtons(){
    play = new JButton("Play!");
    play.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            config.loadConfiguration("res/settings/config.xml");
            //dispose();
            new RunGame();
        }
    });
    rplay = new Rectangle((width/2)-(button_width/2),50,button_width,button_height);
    play.setBounds(rplay);
    window.add(play);
    
    options = new JButton("Options");
    options.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
           // dispose();
            new Options();
        }
    });
    roptions = new Rectangle((width/2)-(button_width/2),90,button_width,button_height);
    options.setBounds(roptions);
    window.add(options);
    
    help= new JButton("Help");
    help.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           // new RunGame();
        }
    });
    rhelp = new Rectangle((width/2)-(button_width/2),130,button_width,button_height);
    help.setBounds(rhelp);
    window.add(help);
    
    quit = new JButton("Quit");
    quit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             JOptionPane.showMessageDialog(null, "????????????.??????????????,?????????????????? ?? ??????!");
             System.exit(0);
        }
    });
    rquit = new Rectangle((width/2)-(button_width/2),170,button_width,button_height);
    quit.setBounds(rquit);
    window.add( quit);
    
} 

   
}
