
import java.awt.*;
import java.awt.color.ColorSpace;
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
                // extract the blue value
                int blue = ((rgb >> 0) & 0xff); // 0b000000000000000000000000
                // extract the green value
                int green = ((rgb >> 8) & 0xff); //0xff
                // extract the red value
                int red = ((rgb >> 16) & 0xff); // 0b000000000000000011111111
                // extract the alpha value
                int alph = ((rgb >> 24) & 0xff); // 0b000000000000000011111111

                // convert the original RGB values to have all the same
                // mean values, so that it will be gray scale
                int greyR;
                int greyG;
                int greyB;
                greyB = greyG = greyR = (red + green + blue)/3;

                // use Color.RGBtoHSB() method to convert RBG to HSB
                hsb = Color.RGBtoHSB(greyR,greyG,greyB,null); //hsb = Color.RGBtoHSB(red,green,blue,null);
                float old_hue = hsb[0];
                float old_sat = hsb[1];
                float old_brt = hsb[2];
                // use Color.HSBtoRGB() method to convert the HSB to a new RGB value
                newRGB = Color.HSBtoRGB(old_hue,old_sat,old_brt);
                // set the new RGB value to a pixel at x,y coordinates of the image
                processed.setRGB(x, y, newRGB);

                // test at pixel 1,1 --------------------------------------------------- TEST
                if (x == 1 && y == 1){
                    // RGB values from my shift and mask method:
                    System.out.println("Old Red was: " + red);
                    System.out.println("Old Green was: " + green);
                    System.out.println("Old BLue was: " + blue);
                    System.out.println("Old Alpha was: " + alph);
                    // their HSB values are then:
                    System.out.println("Hue is: " + hsb[0]);
                    System.out.println("Sat is: " + hsb[1]);
                    System.out.println("Brightness is: " + hsb[2]);
                    // but the TRUE RGB values were actually:
                    Color c = new Color(raw.getRGB(x,y));
                    int TRUE_red = c.getRed();
                    int TRUE_green = c.getGreen();
                    int TRUE_blue = c.getBlue();
                    int TRUE_alpha = c.getAlpha();
                    System.out.println("TRUE Red was: " + TRUE_red);
                    System.out.println("TRUE Green was: " + TRUE_green);
                    System.out.println("TRUE BLue was: " + TRUE_blue);
                    System.out.println("TRUE Alpha was: " + TRUE_alpha);
                //----------------------------------------------------------------------
                }
            }
        }
        ImageIO.write(processed, "PNG", new File("processed.png"));
    }
}
