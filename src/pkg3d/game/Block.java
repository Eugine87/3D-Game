 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Star
 */
class Block {
    
    public boolean solid = false;
    
    public static Block solidWall = new solidBlock();
    
    public List<Sprite> sprites = new ArrayList<Sprite>();
    
    public void addSprite(Sprite sprite){
      sprites.add(sprite);
    }
}
