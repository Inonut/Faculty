import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;

/**
 * Created by dragos on 6/5/17.
 */
public class Main {

    public static void main(String[] args) {
        double[][] m = new double[][]{
                {3, 4},
                {5, 2}
        };
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(m);
        EigenDecomposition eigenDecomposition = new EigenDecomposition(realMatrix);

        System.out.println(Arrays.toString(eigenDecomposition.getRealEigenvalues()));
        System.out.println(eigenDecomposition.getV());

       /* EigenvalueDecomposition result = new Matrix(m).eig();

        System.out.println(result.getV());*/

    }
}
