/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

import java.util.Random;

/**
 *
 * @author Star
 */
public class Screen extends Render {
    
    private Render test;
    private Render3D render;
    
    public Screen(int width, int height) {
        
        super(width, height);
        Random random = new Random();
        render = new Render3D(width, height);
        test = new Render(256,256);
        for(int i=0;i < 256 * 256;i++){
            test.pixels[i] = random.nextInt() * (random.nextInt(5)/4);
        }
    }
    public void render(Game game){
     for(int i=0;i < width * height;i++){
         pixels[i]=0;
     }
    //  for(int i=0;i <50;i++){
    // int anim0 =(int)(Math.sin(System.currentTimeMillis()+1%1000.0/1000 * Math.PI *2)*100);
     //int anim =(int)(Math.sin((game.time +i *2 )%1000.0/100)*100);
     //int anim2 =(int)(Math.cos((game.time +i *2)%1000.0/100)*100);
     
    //draw(test,(width - 256)/2 +anim,(height - 256)/2 - + anim2  ) ;
     //int anim =(int)(Math.sin((System.currentTimeMillis()+i * 8 )%2000.0/2000*Math.PI*2)*200); 
     //int anim2 =(int)(Math.cos((System.currentTimeMillis()+i * 8 )%2000.0/2000*Math.PI*2)*200);
     // draw(test,0,0) ;
   // }
      render.floor(game);
      render.renderDistanceLimiter();
      //render.walls();
     /** render.renderWall(0, 0.5, 1.5,1.5, 0);
      render.renderWall(0, 0.5, 1,1.5, 0);
      render.renderWall(0, 0.5, 1,1, 0);
      render.renderWall(0.5, 0.5, 1,1.5, 0);*/
     
      
      draw(render,0,0) ;
}
}