import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

public class Image{
    private int height;
    private int width;
    //private int r,g,b;
    private BufferedImage image; 



    public Image(BufferedImage image){
        this.image = image;
        this.height = image.getHeight();
        this.width = image.getWidth();
    }

    public BufferedImage getImage(){
        return this.image;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public void show(){
        JLabel picLabel = new JLabel(new ImageIcon(this.image));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(this.image.getWidth(), this.image.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
    }

    public void showImage(BufferedImage image){
        
        JLabel picLabel = new JLabel(new ImageIcon(image));
        JPanel jPanel = new JPanel();
        jPanel.add(picLabel);
        JFrame f = new JFrame();
        f.setSize(new Dimension(image.getWidth(), image.getHeight()));
        f.add(jPanel);
        f.setVisible(true);
    }



    public void getRed(){

        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++ ){

                int rgb = this.image.getRGB(i,j);
                Color c = new Color(rgb);
                int red = c.getRed();
                Color newColor = new Color(red, 0, 0);

                this.image.setRGB(i, j, newColor.getRGB());

            }
        }
    }

    public void getGreen(){

        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++ ){

                int rgb = this.image.getRGB(i,j);
                Color c = new Color(rgb);
                int green = c.getGreen();
                Color newColor = new Color(0, green, 0);

                this.image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void rgbToYuv(){
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++ ){
                
                /*Get the respective values of r g and b*/ 
                int rgb = this.image.getRGB(i,j);
                Color c = new Color(rgb);
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();

               /*Convert to yuv using the formula*/
               int y = (int)((0.257*red)+(0.504*green)+(0.098*blue)+16);
               int u = (int)(-(0.148 * red) - (0.291 * green) + (0.439 * blue) + 128);
               int v = (int)((0.439 * red) - (0.368 * green) - (0.071 * blue) + 128);
               int yuvValue = (y<<16) | (u<<8) | v;
                /*Pass the values to the image */
                this.image.setRGB(i,j,yuvValue);
            }
        }
    }

    public BufferedImage yuvToRgb(BufferedImage image){
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++ ){
                
                /*Get the respective values of r g and b*/ 
                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int y = c.getRed();
                int u = c.getGreen();
                int v = c.getBlue();

                /*Convert to yuv using the formula*/
                int r = (int)(1.164*((double) y - 16.0) + 1.596*((double) v - 128.0));
                int g = (int) (1.164*((double) y - 16.0) - 0.813*((double) v - 128.0) - 0.391*((double) u - 128.0));
                int b = (int)(1.164*((double) y - 16.0)+ 2.018*((double) u - 128.0));

                int rgbValue = (r<<16) | (g<<8) | b;
                /*Pass the values to the image */
                image.setRGB(i,j,rgbValue);
            }
        }
        return image;
    }

    public void getBlue(){

        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++ ){
                int rgb = this.image.getRGB(i,j);
                Color c = new Color(rgb);
                int blue = c.getBlue();
                Color newColor = new Color(0, 0, blue);
                this.image.setRGB(i, j, newColor.getRGB());
            }
        }
    }

    public void saveImageToDisk(String newImageTitle) throws java.io.IOException{
        File outputFile = new File(newImageTitle+".jpeg");
        ImageIO.write(this.image, "jpeg", outputFile);
    }

    public void getRGBHex(){
        int rgb = this.image.getRGB(100,150);

        System.out.println("RGB: "+rgb);
        Color c = new Color(rgb);
        int red = c.getRed();
        int blue = c.getBlue();
        int green = c.getGreen();

        System.out.println(" RED: " + red + " Blue: "+ blue + " Green: "+ green);

        System.out.println("Hash: ");

        System.out.println(c.hashCode());
    }




}