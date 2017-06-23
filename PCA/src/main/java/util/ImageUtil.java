package util;


import Jama.Matrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * Created by dragos on 20.06.2017.
 */
public class ImageUtil {

    public static Matrix readImage(String relativePath) throws IOException {
        return ImageUtil.readImage(new File(Util.resource + "/" + relativePath));
    }

    public static Matrix readImage(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);

        Raster raster = bufferedImage.getData();
        int width = raster.getWidth();
        int height = raster.getHeight();

        return new Matrix(raster.getSamples(0,0, width, height, 0, new double[width*height]), width);
    }

    public static void writeImage(String relativePath, Matrix data) throws IOException {
        ImageUtil.writeImage(new File(Util.resource + "/" + relativePath), data);
    }

    public static void writeImage(File file, Matrix matrix) throws IOException {
        int width = matrix.getRowDimension();
        int height = matrix.getColumnDimension();

        double[] matrixData = matrix.getRowPackedCopy();

        double[] data = new double[width * height];
        for (int i=0;i<data.length; i++){
            data[i] = ((int)matrixData[i] << 16) | ((int)matrixData[i] << 8) | (int)matrixData[i];
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster raster = (WritableRaster) image.getData();
        raster.setSamples(0,0, width, height, 0, data);
        image.setData(raster);

        ImageIO.write(image, file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.') + 1), file);
    }
}
