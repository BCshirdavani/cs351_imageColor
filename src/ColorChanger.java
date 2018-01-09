
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

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                // grab the RGB value of a pixel at x,y coord in the image
                int rgb = raw.getRGB(x, y);
                // extract the red value

                // extract the green value

                // extract the blue value

                // use Color.RGBtoHSB() method to convert RBG to HSV

                // use Color.HSBtoRGB() method to convert the HSB to a enw RGB value

                // set the new RGB value to a pixel at x,y coordinates of the image
                processed.setRGB(x, y, newRGB);

            }
            
        }
        ImageIO.write(processed, "PNG", new File("processed.png"));
    }

}
