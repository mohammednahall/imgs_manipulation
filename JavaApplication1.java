package javaapplication1;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class JavaApplication1 {

    static BufferedImage img = null;
    static File f = null;
    static int width = 0, height = 0;

    public static void main(String[] args) {

        //get the path from the user
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the path of the image : ");
        String path = in.nextLine();
        
        //read image
        try {
            f = new File(path);
            img = ImageIO.read(f);
            width = img.getWidth();
            height = img.getHeight();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

        //get the name of the edited img that the user want to save
        System.out.print("Save the image as : ");
        String name = in.nextLine();
        if (!name.endsWith(".jpg") && !name.endsWith(".png")) {
            name += ".jpg";
        }

        //print all effects that user can choose from it
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

        if (chsnEfct == 1) {
            System.out.print("\nEnter the effect's strong from 1 to 10 : ");
            double amount = in.nextDouble()/100.0;
            contrast(amount);            
        } else if (chsnEfct == 2) {
            System.out.print("\nEnter the effect's strong from 1 to 10 : ");
            double amount = in.nextDouble()/100.0;         
            brightness(amount);
        } else if (chsnEfct == 3) {
            System.out.print("\nEnter the effect's strong from 1 to 10 : ");
            double amount = in.nextDouble()/100.0;         
            sunsetEffect(amount);
        } else if (chsnEfct == 4) {
            System.out.print("\nEnter the effect's strong from 1 to 10 : ");
            double amount = in.nextDouble()/100.0;         
            winterEffect(amount);
        } else if (chsnEfct == 5) {
            System.out.print("\nEnter the percentage of radius to width : ");
            int r = (int)(in.nextDouble()/100.0*width);
            System.out.println("=> "+r+"px");
            tellscopEffect(r);
        }else if (chsnEfct == 6){
            BWeffect();
        }else if (chsnEfct == 7){
            negativeEffect();
        }else if (chsnEfct == 8){
            rgbEffect();
        }

        //save the img in the same pathof the original img
        path = path.substring(0, path.lastIndexOf('\\') + 1) + name;

        //write image
        try {
            f = new File(path);
            ImageIO.write(img, "jpg", f);
            System.out.println("\nImage saved in " + path + "!!");
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

    }
    
    private static void contrast(double amount){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

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

                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }
        
    private static void brightness(double amount){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                ////increase the rgb value of each pixel
                r += amount*r;
                if(r>255)r = 255;
                g += amount*g;
                if(g > 255)g=255;
                b += amount*b;
                if(b>255)b = 255;

                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }

    private static void sunsetEffect(double amount) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                
                //increase the red value of each pixel
                if (r + amount * r < 255) {
                    r += amount * r;
                } else {
                    r = 255;
                }

                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }

    private static void winterEffect(double amount) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                //increase the blue value of each pixel
                if (b + amount * b < 255) {
                    b += amount * b;
                } else {
                    b = 255;
                }

                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }

    private static void tellscopEffect(int r) {
        for (int y = 0; y < height; y++) {
            int Y = (height - y) - height / 2;
            if (Y < -r || Y > r) {
                for (int x = 0; x < width; x++) {
                    img.setRGB(x, y, (255 << 24) | (0 << 16) | (0 << 8) | 0);
                }
            }
            else {
                double a = Math.asin((double)Y/r);
                for (int x = 0; x < width; x++) {
                    int X = x - width / 2;
                    if (X < -Math.cos(a)*r|| X > Math.cos(a)*r) {
                        img.setRGB(x, y, (255 << 24) | (0 << 16) | (0 << 8) | 0);
                    }
                }
            }
        }
        /*for (int y = 0; y < height; y++) {
            int Y = y - (int) (0.5 * height);
            if (Y < -r * width || Y > r * width) {
                for (int x = 0; x < width; x++) {
                    img.setRGB(x, y, (255 << 24) | (0 << 16) | (0 << 8) | 0);
                }
            }
            for (int x = 0; x < width; x++) {
                int X = x - width / 2;
                if (X < -Math.sqrt((r * width) * (r * width) - Y * Y)
                        || X > Math.sqrt((r * width) * (r * width) - Y * Y)) {
                    img.setRGB(x, y, (255 << 24) | (0 << 16) | (0 << 8) | 0);
                }
            }
        }*/
    }
    
    private static void BWeffect() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int bw = Math.max(r, Math.max(g, b));

                img.setRGB(x, y, (255 << 24) | (bw << 16) | (bw << 8) | bw);
            }
        }
    }

    private static void negativeEffect() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                img.setRGB(x, y, (255 << 24) | (255 - r << 16) | (255 - g << 8) | 255 - b);
            }
        }
    }
   
    private static void rgbEffect(){
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = img.getRGB(x, y);

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

                img.setRGB(x, y, (255 << 24) | (r << 16) | (g << 8) | b);
            }
        }
    }

}
