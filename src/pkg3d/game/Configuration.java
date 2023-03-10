/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3d.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Star
 */
public class Configuration {
   
    Properties properties = new Properties();
    
    public void saveConfigurataion(String key,int value){
      try{
          String path = "res/settings/config.xml";
          File file = new File(path);
          boolean exist = file.exists();
          if(!exist){
           file.createNewFile();
          }
          OutputStream write = new FileOutputStream(path);
          properties.setProperty(key, Integer.toString(value));
          properties.storeToXML(write, "Resolution");
      }catch(Exception e){
         e.printStackTrace();
      }  
    }
    public void loadConfiguration(String path){
        try{
        InputStream read = new FileInputStream(path);
        properties.loadFromXML(read);
        String width = properties.getProperty("width");
        String height = properties.getProperty("height");
            //System.out.println("Width =" + width + ", Height =" + height);
            setResolution(Integer.parseInt(width),Integer.parseInt(height));
            read.close();
        }catch(FileNotFoundException e){
            saveConfigurataion("width", 800);
            saveConfigurataion("height", 600);
            loadConfiguration(path);
        } catch(IOException e1){  
             e1.printStackTrace();
                }
        }
    public void setResolution(int width,int height){
    Display.width = width;
    Display.height=height;
}
}