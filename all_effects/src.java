package javaapplication1;

// File class to get the image file from the user's pc
import java.io.File;
import java.io.IOException;

// ImageIO class to read and write the image data from and into the user's pc
import javax.imageio.ImageIO;

// BufferedImage class to store and manipulate the image data 
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class JavaApplication1 {

    // create an object from BufferedImage class
    static BufferedImage img = null;
    
    // create an object from File class
    static File f = null;
    
    // initialize the width and height int variables
    static int width = 0, height = 0;

    public static void main(String[] args) {

        // get the path from the user
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the path of the image : ");
        String path = in.nextLine();
        
        // read the image data
        try {
            f = new File(path);
            img = ImageIO.read(f);
            width = img.getWidth();
            height = img.getHeight();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

        // get the name of the edited image that the user want to save
        System.out.print("Save the image as : ");
        String name = in.nextLine();
        
        // end the name with .jpg
        if (!name.endsWith(".jpg") && !name.endsWith(".png")) {
            name += ".jpg";
        }

        // print all effects that user can choose from it
        System.out.println("\nChoose one of the effects bellow :");
        System.out.println("1- contrast effect");
        System.out.println("2- brightness effect");
        System.out.println("3- sunset effect");
        System.out.println("4- winter effect");
        System.out.println("5- tellscop effect");
        System.out.println("6- Black and White effect\n");
        System.out.println("7- negative effect");
        System.out.println("8- rgb effect");
        System.out.print("Choose : ");
        
        int chsnEfct = in.nextInt();
        
        //-------------------------------------------------------------------------------------------------------------------------------------------------------------//

        if (chsnEfct == 1) {
            
            // get the amount of the effect from the user
            System.out.print("\nEnter the effect's strong from 1 to 100 : ");
            double amount = in.nextDouble()/100.0;
            
            // apply the contrast effect 
            contrast(amount);  
            
        } else if (chsnEfct == 2) {
            
            // get the amount of the effect from the user
            System.out.print("\nEnter the effect's strong from 1 to 100 : ");
            double amount = in.nextDouble()/100.0;   
            
            // apply the brightness effect
            brightness(amount);
            
        } else if (chsnEfct == 3) {
            
            // get the amount of the effect from the user
            System.out.print("\nEnter the effect's strong from 1 to 100 : ");
            double amount = in.nextDouble()/100.0;
            
            // apply the sunset effect
            sunsetEffect(amount);
            
        } else if (chsnEfct == 4) {
            
            // get the amount of the effect from the user
            System.out.print("\nEnter the effect's strong from 1 to 100 : ");
            double amount = in.nextDouble()/100.0; 
            
            // apply the winter effect
            winterEffect(amount);
            
        } else if (chsnEfct == 5) {
            
            // get the amount of the effect from the user
            System.out.print("\nEnter the percentage of radius to width : ");
            int r = (int)(in.nextDouble()/100.0*width);
            System.out.println("=> "+r+"px");
            
            // apply the tellscop effect
            tellscopEffect(r);
            
        }else if (chsnEfct == 6){
            
            // apply the Black and White effect
            BWeffect();
            
        }else if (chsnEfct == 7){
            
            // apply the negative effect
            negativeEffect();
            
        }else if (chsnEfct == 8){
            
            // apply the rgb effect
            rgbEffect();
            
        }

        //-------------------------------------------------------------------------------------------------------------------------------------------------------------//        
        
        // save the image in the same path of the original img
        path = path.substring(0, path.lastIndexOf('\\') + 1) + name;

        // create the file and write the image data into it
        try {
            f = new File(path);
            ImageIO.write(img, "jpg", f);
            System.out.println("\nImage saved in " + path + "!!");
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

    }
    
    //-------------------------------------------------------------------------------------------------------------------------------------------------------------//
    
    // contrast effect
    private static void contrast(double amount){
        
        // nested for loop to loop in each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                // get the rgb hexadecimal value of the pixel in (x,y)
                int p = img.getRGB(x, y);

                // get the rgb decimal values
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int hv = Math.max(r, Math.max(g, b));
                if(r == hv){
                    if(r == g) {
                        r += r*amount;
                        g += g*amount;
                        if(r > 255)r = 255;
                        if(b > 255)b = 255;
                    }
                    else if(r == b){
                        r += r*amount;
                        b += b*amount;
                        if(r > 255)r = 255;
                        if(b > 255)b = 255;
                    }
                    else r += r*amount;
                    if(r > 255)r = 255;
                }
                else if(g == hv){
                    if(g == r) {
                        g += g*amount;
                        r += r*amount;
                        if(r > 255)r = 255;
                        if(g > 255)g = 255;
                    }
                    else if(g == b){
                        g += g*amount;
                        b += b*amount;
                        if(g > 255)g = 255;
                        if(b > 255)b = 255;
                    }
                    else g += g*amount;
                    if(g > 255)g = 255;
                }
                else if(b == hv){
                    if(b == g){
                        b += b*amount;
                        g += g*amount;
                        if(g > 255)g = 255;
                        if(b > 255)b = 255;
                    }
                    else if(b == r){
                        b += b*amount;
                        r += r*amount;
                        if(r > 255)r = 255;
                        if(b > 255)b = 255;
                    }
                    else b += b*amount;
                    if(b > 255)b = 255;
                }

                // set the rgb values of the pixel in hexadecimal system
                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }
    
    // brightness effect
    private static void brightness(double amount){
        
        // nested for loop to loop in each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                // get the rgb hexadecimal value of the pixel in (x,y)
                int p = img.getRGB(x, y);

                // get the rgb decimal values
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // increase the rgb value of each pixel
                r += amount*r;
                if(r>255)r = 255;
                g += amount*g;
                if(g > 255)g=255;
                b += amount*b;
                if(b>255)b = 255;

                // set the rgb values of the pixel in hexadecimal system
                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }

    // sunset effect
    private static void sunsetEffect(double amount) {
        
        // nested for loop to loop in each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                // get the rgb hexadecimal value of the pixel in (x,y)
                int p = img.getRGB(x, y);

                // get the rgb decimal values
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                
                // increase the red value of each pixel
                if (r + amount * r < 255) {
                    r += amount * r;
                } else {
                    r = 255;
                }

                // set the rgb values of the pixel in hexadecimal system
                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }

    // winter effect
    private static void winterEffect(double amount) {
        
        // nested for loop to loop in each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                // get the rgb hexadecimal value of the pixel in (x,y)
                int p = img.getRGB(x, y);

                // get the rgb decimal values
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // increase the blue value of each pixel
                if (b + amount * b < 255) {
                    b += amount * b;
                } else {
                    b = 255;
                }

                // set the rgb values of the pixel in hexadecimal system
                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }

    // tellscop effect
    private static void tellscopEffect(int r) {
        
        // nested for loop to loop in each pixel in the image
        for (int y = 0; y < height; y++) {
            
            int Y = (height - y) - height / 2;
            if (Y < -r || Y > r) {
                
                for (int x = 0; x < width; x++) {
                    
                    // set the rgb values of the pixel in hexadecimal system
                    img.setRGB(x, y, (255 << 24) | (0 << 16) | (0 << 8) | 0);
                }
            }
            else {
                
                double a = Math.asin((double)Y/r);
                for (int x = 0; x < width; x++) {
                    
                    int X = x - width / 2;
                    if (X < -Math.cos(a)*r|| X > Math.cos(a)*r) {
                        
                        // set the rgb values of the pixel in hexadecimal system
                        img.setRGB(x, y, (255 << 24) | (0 << 16) | (0 << 8) | 0);
                    }
                }
            }
        }
    }
    
    // Black and White effect
    private static void BWeffect() {
        
        // nested for loop to loop in each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                // get the rgb hexadecimal value of the pixel in (x,y)
                int p = img.getRGB(x, y);

                // get the rgb decimal values
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int bw = Math.max(r, Math.max(g, b));

                // set the rgb values of the pixel in hexadecimal system
                img.setRGB(x, y, (255 << 24) | (bw << 16) | (bw << 8) | bw);
            }
        }
    }

    // negative effect
    private static void negativeEffect() {
        
        // nested for loop to loop in each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                // get the rgb hexadecimal value of the pixel in (x,y)
                int p = img.getRGB(x, y);

                // get the rgb decimal values
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // set the rgb values of the pixel in hexadecimal system
                img.setRGB(x, y, (255 << 24) | (255 - r << 16) | (255 - g << 8) | 255 - b);
            }
        }
    }
    
    // rgb effect
    private static void rgbEffect(){
        
        // nested for loop to loop in each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                
                // get the rgb hexadecimal value of the pixel in (x,y)
                int p = img.getRGB(x, y);

                // get the rgb decimal values
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int hv = Math.max(r, Math.max(g, b));
                if(r == hv){
                    g = 0; b = 0;
                }
                else if(g == hv){
                    r = 0;  b = 0;
                }
                else if(b == hv){
                    r = 0; g = 0;
                }

                // set the rgb values of the pixel in hexadecimal system
                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }

}