package ui;

import javax.swing.*;
import java.awt.*;

//scales imageIcon
public class ImageTool {
    private static Integer width;
    private static Integer height;
    private static ImageIcon img;

    //Modifies: this
    //EFFECT: initiates object
    ImageTool(ImageIcon img) {
        this.width = img.getIconWidth();
        this.height = img.getIconHeight();
        this.img = img;

    }

    //Modifies: this
    //EFFECT: scales image in ratio with given dimensions
    public ImageIcon scaleImageRatio(Integer w, Integer h) {
        Integer nw = 0;
        Integer nh = 0;

        if (width > w) {
            nw = w;
            nh = (nw * height / width);
        }

        if (nh > h) {
            nh = h;
            nw = (width * nh) / height;
        }
        return new ImageIcon(img.getImage().getScaledInstance(nw, nh, Image.SCALE_SMOOTH));
    }

    //Modifies: this
    //EFFECT: scales GIF in ratio with given dimensions
    public ImageIcon scaleGifRatio(Integer w, Integer h) {
        Integer nw = 0;
        Integer nh = 0;

        if (width > w) {
            nw = w;
            nh = (nw * height / width);
        }

        if (nh > h) {
            nh = h;
            nw = (width * nh) / height;
        }
        return new ImageIcon(img.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }

    //Modifies: this
    //EFFECT: scales image with given dimensions
    public ImageIcon scaleImage(Integer w, Integer h) {

        //ImageIcon -> Image -> ImageIcon
        Image toScaleImage = img.getImage().getScaledInstance(w, h,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon scaledImage = new ImageIcon(toScaleImage);
        return scaledImage;
    }

}

