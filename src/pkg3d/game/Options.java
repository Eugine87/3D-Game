/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Star
 */
public class Options extends JFrame {

     public static final long serialVersionUID = 1L;   
     //private JPanel w  = new JPanel();
     private int width = 540;
     private int height = 450;
     private JButton OK;
     private Rectangle rOK,rresolution;
     private Choice resolution = new Choice();
     Configuration config = new Configuration();
     public JTextField twidth,theight;
     public JLabel lwidth,lheight;
     private int button_width = 80;
     private int button_height = 40;
     
    int w=0;
    int h = 0;
              
    
     JPanel window  = new JPanel();
    
    public Options() {
      // super(1); 
       setTitle("Options - Doom Launcher");
        setSize(new Dimension(width, height));
        add(window);
        setResizable(true);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        window.setLayout(new BorderLayout());
        
        drawButtons();
        repaint();
       //   setVisible(true);
       
    }

    private void drawButtons() {
      OK = new JButton("OK"); 
      rOK = new Rectangle((width-100),(height-70),button_width,button_height-10);
      OK.setBounds(rOK);
      window.add(OK);
      
      rresolution = new Rectangle(50,80,80,25);
      //resolution.add("600,400");
      resolution.add("600,400");
      resolution.setBounds(rresolution);
      resolution.add("800,600");
      resolution.add("1024,768");
      resolution.select(1);
      window.add(resolution);
      
      lwidth = new JLabel("Width :"); 
      lwidth.setBounds(30, 150, 120, 20);
      //lwidth.setFont(new Font("Verdana",2,50));
      window.add(lwidth);
      lheight = new JLabel("Height :"); 
      lheight.setBounds(30, 180, 120, 20);
      window.add(lheight);
      
      twidth = new JTextField(); 
      twidth.setBounds(80, 150, 60, 20);
      window.add(twidth);
      theight = new JTextField(); 
      theight.setBounds(80, 180, 60, 20);
      window.add(theight);
      
      OK.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              
              //System.out.println(Display.selection);
              dispose();
              new Launcher(0);
              config.saveConfigurataion("width", parseWidtgh());
              config.saveConfigurataion("height", parseHeight());
          }

          
      });
    }
   private void drop(){
       int selection = resolution.getSelectedIndex();
              
              if(selection==0){
                  w = 600;
                  h = 400;
              }
              if(selection==1||selection==-1){
                  w = 800;
                  h = 600;
              }
              if(selection==2){
                  w = 1024;
                  h = 768;
              }
              
   }
   private int parseWidtgh(){
       try {
        int w = Integer.parseInt(twidth.getText());
    return w;    
       } catch (NumberFormatException e) {
          drop();
        return w;   
       }
       
   }
  private int parseHeight() {
      try {
      int h = Integer.parseInt(theight.getText());  
          return h;    
      } catch (NumberFormatException e) {
          drop();
        return h;
      }
          
          }
}
