import Jama.Matrix;
import util.ImageUtil;

import java.io.IOException;

// $example off$
// $example on$


/**
 * Created by dragos on 18.06.2017.
 */
public class PCAMain {

    public static void main(String[] args) throws IOException {

        Matrix data = ImageUtil.readImage("ABHAY_L_1.jpg");
        Matrix data2 = ImageUtil.readImage("test.jpg");

        ImageUtil.writeImage("test.jpg", data);
    }
}
