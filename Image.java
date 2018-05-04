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
    
    public Image() {}
    
    public void setImage(BufferedImage image) {
    	this.height = image.getHeight();
    	this.width = image.getWidth();
    	this.image = image;
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



    public void bandaRed(){

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

    public void bandaBlue(){

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

    public void bandaGreen(){

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

    public void bandaRedAndShow(BufferedImage image){
        BufferedImage newImg = new BufferedImage(this.width, this.height,1);
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++ ){

                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int red = c.getRed();
                Color newColor = new Color(red, 0, 0);

                newImg.setRGB(i, j, newColor.getRGB());

            }
        }
        this.showImage(newImg);
    }
    
    public BufferedImage getBandaRed(BufferedImage image){
        BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(),1);
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++ ){

                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int red = c.getRed();
                Color newColor = new Color(red, 0, 0);

                newImg.setRGB(i, j, newColor.getRGB());

            }
        }
        return newImg;
    }

    public void bandaBlueAndShow(BufferedImage image){
        BufferedImage newImg = new BufferedImage(this.width, this.height,1);
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++ ){
                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int blue = c.getBlue();
                Color newColor = new Color(0, 0, blue);
                newImg.setRGB(i, j, newColor.getRGB());
            }
        }
        this.showImage(newImg);
    }
    
    public BufferedImage getBandaBlue(BufferedImage image){
        BufferedImage newImg = new BufferedImage(image.getWidth(), image.getHeight(),1);
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++ ){
                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int blue = c.getBlue();
                Color newColor = new Color(0, 0, blue);
                newImg.setRGB(i, j, newColor.getRGB());
            }
        }
        return newImg;
    }

    public void bandaGreenAndShow(BufferedImage image){
        BufferedImage newImg = new BufferedImage(this.width, this.height,1);
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++ ){

                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int green = c.getGreen();
                Color newColor = new Color(0, green, 0);

                newImg.setRGB(i, j, newColor.getRGB());
            }
        }
        this.showImage(newImg);
    }
    
    public BufferedImage getBandaGreen(BufferedImage image){
        BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++ ){

                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int green = c.getGreen();
                Color newColor = new Color(0, green, 0);

                newImg.setRGB(i, j, newColor.getRGB());
            }
        }
        return newImg;
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
    
    public BufferedImage toGrayScale(BufferedImage image) {
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++ ){

                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int green = c.getGreen();
                Color newColor = new Color(green, green, green);

                newImg.setRGB(i, j, newColor.getRGB());
            }
        }
        return newImg;
    }
    
    public BufferedImage negativeRgb(BufferedImage image) {
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++ ){

                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int g = c.getGreen();
                int r = c.getRed();
                int b = c.getBlue();
                
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                
                r = (r > 255 ? 255 : r);
                g = (g > 255 ? 255 : g);
                b = (b > 255 ? 255 : b);
                
                r = (r < 0 ? 0 : r);
                g = (g < 0 ? 0 : g);
                b = (b < 0 ? 0 : b);
                
                newImg.setRGB(i, j, new Color(r,g,b).getRGB());
            }
        }
        return newImg;
    }
    
    public BufferedImage negativeY(BufferedImage image) {
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++ ){

                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int g = c.getGreen();
                int r = c.getRed();
                int b = c.getBlue();
                
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                
                r = (r > 255 ? 255 : r);
                g = (g > 255 ? 255 : g);
                b = (b > 255 ? 255 : b);
                
                r = (r < 0 ? 0 : r);
                g = (g < 0 ? 0 : g);
                b = (b < 0 ? 0 : b);
                
                newImg.setRGB(i, j, new Color(r,g,b).getRGB());
            }
        }
        return newImg;
    }
    
    public BufferedImage aditiveControl(BufferedImage image, int value){
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++ ){

                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int g = c.getGreen();
                int r = c.getRed();
                int b = c.getBlue();
                
                r = r + value;
                g = g + value;
                b = b + value;
                
                r = (r > 255 ? 255 : r);
                g = (g > 255 ? 255 : g);
                b = (b > 255 ? 255 : b);
                
                r = (r < 0 ? 0 : r);
                g = (g < 0 ? 0 : g);
                b = (b < 0 ? 0 : b);
                
                newImg.setRGB(i, j, new Color(r,g,b).getRGB());
            }
        }
        return newImg;	
    }
    
    public BufferedImage multiplicativeControl(BufferedImage image, int value){
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
        for(int i = 0; i < image.getWidth(); i++){
            for(int j = 0; j < image.getHeight(); j++ ){

                int rgb = image.getRGB(i,j);
                Color c = new Color(rgb);
                int g = c.getGreen();
                int r = c.getRed();
                int b = c.getBlue();
                
                r = r * value;
                g = g * value;
                b = b * value;
                
                r = (r > 255 ? 255 : r);
                g = (g > 255 ? 255 : g);
                b = (b > 255 ? 255 : b);
                
                r = (r < 0 ? 0 : r);
                g = (g < 0 ? 0 : g);
                b = (b < 0 ? 0 : b);
                
                newImg.setRGB(i, j, new Color(r,g,b).getRGB());
            }
        }
        return newImg;	
    }

	public float[][][] rgbToYuv2(){
		float yuv[][][] = new float[this.width][this.height][3];	
	
		for(int i = 0; i < this.width; i++){
		    for(int j = 0; j < this.height; j++ ){
		
			/*Get the respective values of r g and b*/ 
			int rgb = this.image.getRGB(i,j);
			Color c = new Color(rgb);
			int red = (int) c.getRed();
			int green = (int) c.getGreen();
			int blue = (int) c.getBlue();

            /*Convert to yuv using the formula*/
            float y = (float)((0.299 * red) + (0.587 * green) + (0.114 * blue));
            float u = (float)((0.596 * red) - (0.274 * green) - (0.322 * blue));
            float v = (float)((0.211 * red) - (0.523 * green) + (0.312 * blue));
					       
			//int yuvValue = (y<<16) | (u<<8) | v;


			yuv[i][j][0] = y;
			yuv[i][j][1] = u;
			yuv[i][j][2] = v;
			//System.out.println(yuv[i][j][0] +" "+yuv[i][j][1] + " " + yuv[i][j][2] );


			/*Pass the values to the image */
			//this.image.setRGB(i,j,yuvValue);
		    }
		}
		return yuv;
    	}
	
	public float[][][] rgbToYuvByImage(BufferedImage image){
		float yuv[][][] = new float[image.getWidth()][image.getHeight()][3];	
	
		for(int i = 0; i < image.getWidth(); i++){
		    for(int j = 0; j < image.getHeight(); j++ ){
		
			/*Get the respective values of r g and b*/ 
			int rgb = image.getRGB(i,j);
			Color c = new Color(rgb);
			int red = (int) c.getRed();
			int green = (int) c.getGreen();
			int blue = (int) c.getBlue();

            /*Convert to yuv using the formula*/
            float y = (float)((0.299 * red) + (0.587 * green) + (0.114 * blue));
            float u = (float)((0.596 * red) - (0.274 * green) - (0.322 * blue));
            float v = (float)((0.211 * red) - (0.523 * green) + (0.312 * blue));
					       
			//int yuvValue = (y<<16) | (u<<8) | v;


			yuv[i][j][0] = y;
			yuv[i][j][1] = u;
			yuv[i][j][2] = v;
			//System.out.println(yuv[i][j][0] +" "+yuv[i][j][1] + " " + yuv[i][j][2] );


			/*Pass the values to the image */
			//this.image.setRGB(i,j,yuvValue);
		    }
		}
		return yuv;
	}
	
	public BufferedImage negativeYuv(BufferedImage image) {
		float yuvArr[][][] = rgbToYuvByImage(image);
		
		for(int i = 0; i < image.getWidth(); i++){
		    for(int j = 0; j < image.getHeight(); j++ ){
		    	yuvArr[i][j][0] = 255 - yuvArr[i][j][0];
		    }
	    }
		
		return yuvToRgb2(yuvArr);
	}

	public BufferedImage yuvToRgb2(float yuv[][][]){
		BufferedImage newImage = new BufferedImage(this.width, this.height,1);		
		
		for(int i = 0; i < this.width; i++){
		    for(int j = 0; j < this.height; j++ ){
		       

                    /*Convert to yuv using the formula*/
                float r =(float) ((1.000 * yuv[i][j][0]) + (0.956 * yuv[i][j][1]) + (0.621 * yuv[i][j][2]));
                float g =(float) ((1.000 * yuv[i][j][0]) - (0.272 * yuv[i][j][1]) - (0.647 * yuv[i][j][2]));
                float b =(float) ((1.000 * yuv[i][j][0]) - (1.106 * yuv[i][j][1]) + (1.703 * yuv[i][j][2]));
		
			//System.out.println("r: "+ r+ "g: "+ g);		

                r = (r > 255 ? 255 : r);
                g = (g > 255 ? 255 : g);
                b = (b > 255 ? 255 : b);
                
                r = (r < 0 ? 0 : r);
                g = (g < 0 ? 0 : g);
                b = (b < 0 ? 0 : b);

		        int rgbValue = ((int)r<<16) | ((int)g<<8) | (int)b;
		        /*Pass the values to the image */
		        newImage.setRGB(i,j,rgbValue);
		    }
		}
        return newImage;
    }
	
	
	public BufferedImage overlapImages(BufferedImage img1, BufferedImage img2) {
		
		BufferedImage newImg = new BufferedImage(img1.getWidth(),img1.getHeight(),1);
        for(int i = 0; i < img1.getWidth(); i++){
            for(int j = 0; j < img1.getHeight(); j++ ){

                int rgbImg1 = img1.getRGB(i,j);
                int rgbImg2 = img2.getRGB(i,j);
                
                Color cImg1 = new Color(rgbImg1);
                Color cImg2 = new Color(rgbImg2);
                int g = (cImg1.getGreen() + cImg2.getGreen()) / 2;
                int r = (cImg1.getRed() + cImg2.getRed()) / 2;
                int b = (cImg1.getBlue() + cImg2.getBlue()) / 2;
                
                r = (r > 255 ? 255 : r);
                g = (g > 255 ? 255 : g);
                b = (b > 255 ? 255 : b);
                
                r = (r < 0 ? 0 : r);
                g = (g < 0 ? 0 : g);
                b = (b < 0 ? 0 : b);
                
                newImg.setRGB(i, j, new Color(r,g,b).getRGB());
            }
        }
        return newImg;	
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
		
		//System.out.println("r: "+ r+ "g: "+ g);		


		r = (r > 255 ? 255 : r);
		g = (g > 255 ? 255 : g);
		b = (b > 255 ? 255 : b); 

                int rgbValue = (r<<16) | (g<<8) | b;
                /*Pass the values to the image */
                image.setRGB(i,j,rgbValue);
            }
        }
        return image;
    }

    public void saveImageToDisk(BufferedImage image, String newImageTitle, String path) throws java.io.IOException{
        
    	path = path+"\\"+newImageTitle;
    	
    	String[] acceptedFormats = {"jpeg","png","jpg","gif","JPG","PNG"};
        
        if(path.contains(".") && hasFormat(acceptedFormats, path.substring(path.indexOf(".")+1, path.length())) ) {
        	File outputFile = new File(path);
        	ImageIO.write(image, path.substring(path.indexOf(".")+1, path.length()), outputFile);        	
        }else {
        	path = path+".jpg";
        	File outputFile = new File(path);
        	ImageIO.write(image, "jpg", outputFile);
        }
        
    }
    
    private boolean hasFormat(String[] formats, String format) {
    	for(int i = 0; i < formats.length; i++) {
    		if(formats[i].equals(format)) {
    			return true;
    		}
    	}
    	return false;
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
