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
class Level {
  
    public Block[] blocks;
    public final int width;
    public final int height;
    
    
    public Level(int width,int height){
      this.width = width;  
      this.height = height;  
      blocks = new Block[width*height];
      Random random = new Random();
      for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        
            
             Block  block = null;   
             if(random.nextInt(15)==0){
                 block = new solidBlock();
             }else{
                 block = new Block();
                 if(random.nextInt(18)==0){

                 block.addSprite(new Sprite(0,0,0));
                 }
             }
             blocks[x+y*width] = block;
            }
            
        }
    }
    public Block create(int x,int y){
        if(x<0||y<0||x>=width||y>=height){
            return Block.solidWall;
        }
    return blocks[x+y*width];    
    }
}
