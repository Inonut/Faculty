import com.github.wihoho.Trainer;
import com.github.wihoho.constant.FeatureType;
import com.github.wihoho.jama.Matrix;
import com.github.wihoho.training.CosineDissimilarity;
import com.github.wihoho.training.FileManager;
import domain.ImageBuider;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.Test;
import pca.Pca;
import util.Util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by dragos on 11.06.2017.
 */
public class PP extends Application{

    public static void main(String[] args){
        PP.launch(args);
    }



    @Override
    public void start(Stage primaryStage) throws Exception {
        // Build a trainer
        Trainer trainer = Trainer.builder()
                .metric(new CosineDissimilarity())
                .featureType(FeatureType.PCA)
                .numberOfComponents(3)
                .k(1)
                .build();

        String john1 = Util.resource.concat("all/ABHAY_L_1.jpg");
        String john2 = Util.resource.concat("all/ABHAY_L_2.jpg");
        String john3 = Util.resource.concat("all/ABHAY_L_3.jpg");
        String john4 = Util.resource.concat("all/ABHAY_L_4.jpg");

        String smith1 = Util.resource.concat("all/AJAYSING_L_1.jpg");
        String smith2 = Util.resource.concat("all/AJAYSING_L_2.jpg");
        String smith3 = Util.resource.concat("all/AJAYSING_L_3.jpg");
        String smith4 = Util.resource.concat("all/AJAYSING_L_4.jpg");

        // add training data
        trainer.add(convertToMatrix(john1), "john");
        trainer.add(convertToMatrix(john2), "john");
        trainer.add(convertToMatrix(john3), "john");

        trainer.add(convertToMatrix(smith1), "smith");
        trainer.add(convertToMatrix(smith2), "smith");
        trainer.add(convertToMatrix(smith3), "smith");

        // train
        trainer.train();

        // recognize
        System.out.println(trainer.recognize(convertToMatrix(john4)));
        System.out.println(trainer.recognize(convertToMatrix(smith4)));
    }

    private Matrix convertToMatrix(String fileAddress) throws IOException {
        File file = new File(fileAddress);

        final Matrix[] matrix = {null};


        matrix[0] = new Matrix(new ImageBuider("file:" + fileAddress).toGray().toMatrixByRed().getArray());


        return vectorize(matrix[0]);
    }

    //Convert a m by n matrix into a m*n by 1 matrix
    static Matrix vectorize(Matrix input) {
        int m = input.getRowDimension();
        int n = input.getColumnDimension();

        Matrix result = new Matrix(m * n, 1);
        for (int p = 0; p < n; p++) {
            for (int q = 0; q < m; q++) {
                result.set(p * m + q, 0, input.get(q, p));
            }
        }
        return result;
    }

}
