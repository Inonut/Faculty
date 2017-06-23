package old.pca;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import old.domain.ImageBuider;
import javafx.util.Pair;
import old.util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dragos on 6/9/17.
 */
public class Pca {

    private final File dir;
    private EigenvalueDecomposition eig;
    List<Pair<String, Matrix>> weight;

    public Pca(File dir){
        this.dir = dir;
    }

    public Pca(String url){
        this.dir = new File(url);
    }

    public Pca train(int nbClasses, int nbImages){
        //get first n classes
        List<Pair<String, Matrix>> matrixList = this.walk(this.dir.toPath()).limit(nbClasses)
                //get first k images from each classes than parse to ImageBuilder
                .flatMap(path -> this.walk(path).limit(nbImages).map(img -> new ImageBuider("file:" + img.toFile().getAbsolutePath())))
                //scale img
                .map(imageBuider-> imageBuider.scale(Util.widthScale, Util.heigtScale))
                // parse img to matrix
                .map(imageBuider -> new Pair<>(imageBuider.getName(), imageBuider.toMatrixByRed()))
                .collect(Collectors.toList());


        Matrix middleMatrix = matrixList.stream().map(Pair::getValue).reduce(Matrix::plus).orElse(null).times(1./matrixList.size());

        List<Pair<String, Matrix>> centerMatrixList = matrixList.stream().map(pair -> new Pair<>(pair.getKey(), pair.getValue().minus(middleMatrix))).collect(Collectors.toList());

        Matrix covarianceMatrix = processMatrix(centerMatrixList.stream().map(Pair::getValue).collect(Collectors.toList())).transpose();

        Matrix scatterMatrix = covarianceMatrix.times(covarianceMatrix.transpose()).times(1./matrixList.size());


        eig = scatterMatrix.eig();

//        centerMatrixList.stream().map(ImageBuider::new).limit(3).forEach(m->m.writeImage(Util.resource.concat("/").concat(m.getName())));

        weight = centerMatrixList.stream().map(pair -> new Pair<>(pair.getKey(), processMatrix(pair.getValue()).times(eig.getV()))).collect(Collectors.toList());

        return this;
    }

    public String classify(ImageBuider imageBuider) {

        Matrix matrix = processMatrix(imageBuider.scale(Util.widthScale, Util.heigtScale).toMatrixByRed()).times(eig.getV());

        Pair<Integer, Pair<String, Matrix>> result = weight.stream().map(pair -> new Pair<>(pair.getKey(), pair.getValue().minus(matrix)))
                .map(pair -> {
                    int count = 0;
                    for (double el : pair.getValue().getRowPackedCopy()) {
                        count += Math.abs(el);
                    }
                    return new Pair<>(count, pair);
                }).min(Comparator.comparingInt(Pair::getKey)).orElse(null);

        return result.getValue().getKey();
    }

    private Matrix processMatrix(Matrix matrix){
        return processMatrix(Arrays.asList(matrix));
    }

    private Matrix processMatrix(List<Matrix> matrixList){
        double[][] covariance = new double[matrixList.size()][];
        for(int i = 0;i<matrixList.size(); i++){
            covariance[i] = matrixList.get(i).getColumnPackedCopy();
        }
        return new Matrix(covariance);
    }

    public static Stream<Path> walk(Path path) {
        try {
            return Files.walk(path, 1).skip(1).sorted();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.of();
    }
}
