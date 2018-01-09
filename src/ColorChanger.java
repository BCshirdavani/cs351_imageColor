
import java.awt.*;
import java.io.*;

import javax.imageio.*;
import java.awt.image.*;

public class ColorChanger {

    public static void main(String args[])throws IOException{
        BufferedImage raw, processed;
        raw = ImageIO.read(new File("flower.png"));

        int width = raw.getWidth();
        int height = raw.getHeight();

        processed = new BufferedImage(width, height, raw.getType());
        float hue = 90/360.0f;                                          // hard coded hue value
        float [] hsb;
        int newRGB;


        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                // grab the RGB value of a pixel at x,y coord in the image
                int rgb = raw.getRGB(x, y);
                // extract the red value
                int red = ((rgb >> 8) & 000000000000000011111111);
                // extract the green value
                int green = ((rgb >> 16) & 000000000000000011111111);
                // extract the blue value
                int blue = ((rgb >> 24) & 000000000000000011111111); //000000000000000000000000
                // use Color.RGBtoHSB() method to convert RBG to HSV
                hsb = Color.RGBtoHSB(red,green,blue,null);
                float old_hue = hsb[0];
                float old_sat = hsb[1];
                float old_brt = hsb[2];
                // use Color.HSBtoRGB() method to convert the HSB to a new RGB value
                newRGB = Color.HSBtoRGB(old_hue,old_sat,old_brt);
                // set the new RGB value to a pixel at x,y coordinates of the image
                processed.setRGB(x, y, newRGB);
                if (x == 1 && y == 1){
                    System.out.println("Old Red was: " + red);
                    System.out.println("Old Green was: " + green);
                    System.out.println("Old BLue was: " + blue);
                    System.out.println("Hue is: " + hsb[0]);
                    System.out.println("Sat is: " + hsb[1]);
                    System.out.println("Brightness is: " + hsb[2]);
                }
            }

        }
        ImageIO.write(processed, "PNG", new File("processed.png"));
    }

}
