package domain;

import Jama.Matrix;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by dragos on 6/8/17.
 */
public class ImageBuider {
    private Image image;

    public ImageBuider(String url) {
        this.image = new Image(url);
    }

    public ImageBuider(Image image) {
        this.image = image;
    }

    public ImageBuider(Matrix matrix) {
        int width = matrix.getColumnDimension();
        int height = matrix.getRowDimension();
        double[][] data = matrix.getArray();

        WritableImage writableImage = new WritableImage(width, height);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                writableImage.getPixelWriter().setColor(w, h, new Color(data[w][h], data[w][h], data[w][h], 0));
            }
        }

        this.image = writableImage;
    }

    public ImageBuider scale(double width, double height){
        ImageView imageView = new ImageView(this.image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        Image image = imageView.snapshot(null, null);

        return new ImageBuider(image);
    }

    public ImageBuider toGray() {

        ColorAdjust desaturate = new ColorAdjust();
        desaturate.setSaturation(-1);

        ImageView imageView = new ImageView(this.image);
        imageView.setEffect(desaturate);

        Image image = imageView.snapshot(null, null);

        return new ImageBuider(image);
    }

    public Matrix toMatrixByRed(){
        int width = (int)this.image.getWidth();
        int height = (int)this.image.getHeight();
        double[][] data = new double[height][width];

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                data[h][w] = image.getPixelReader().getColor(w, h).getRed();
            }
        }

        return new Matrix(data);
    }

    public ImageBuider writeImage(String url){
        File outputFile = new File(url);
        BufferedImage bImage = SwingFXUtils.fromFXImage(this.image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    public Image getImage() {
        return image;
    }
}
