import Jama.Matrix;
import domain.ImageBuider;
import javafx.application.Application;
import javafx.stage.Stage;
import pca.Pca;
import util.Util;

/**
 * Created by dragos on 6/5/17.
 */
public class Main extends Application {

    public static void main(String[] args) {
        Main.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*double[][] m = new double[][]{
                {3, 4},
                {5, 2}
        };
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(m);
        EigenDecomposition eigenDecomposition = new EigenDecomposition(realMatrix);

        System.out.println(Arrays.toString(eigenDecomposition.getRealEigenvalues()));
        System.out.println(eigenDecomposition.getV());
*/
       /* EigenvalueDecomposition result = new Matrix(m).eig();

        System.out.println(result.getV());*/

        /*Matrix matrix = new ImageBuider("file:" + Util.resource.concat("data/ABHAY PAKHARE/ABHAY_L_1.jpg"))
                .scale(32,64)
                .toGray()
                .writeImage(Util.resource.concat("/testOrig.png"))
                .toMatrixByRed();

        new ImageBuider(matrix)
                .writeImage(Util.resource.concat("/test.png"));*/

        Pca pca = new Pca(Util.resource.concat("data")).train(5,5);

    }
}
