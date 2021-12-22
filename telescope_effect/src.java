package javaapplication1;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class telescope_effect {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Enter the path of the image : ");
        String path = in.nextLine();
        
        System.out.print("Save the image as : ");
        String name = in.nextLine();
        if (!name.endsWith(".jpg")) {
            name += ".jpg";
        }

        BufferedImage img;
        File f;

        int width = 0, height = 0;

        try {
            f = new File(path);
            img = ImageIO.read(f);
            width = img.getWidth();
            height = img.getHeight();
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

        System.out.print("\nEnter the percentage of radius to width : ");
        int r = (int)(in.nextDouble()/100.0*width);
        System.out.println("=> "+r+"px");
        
        for (int y = 0; y < height; y++) {
            int Y = y - height / 2;
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

        path = path.substring(0, path.lastIndexOf('\\') + 1) + name;

        try {
            f = new File(path);
            ImageIO.write(img, "jpg", f);
            System.out.println("\nImage saved in " + path + "!!");
        } catch (IOException e) {
            System.out.println(e);
            return;
        }

    }
}
