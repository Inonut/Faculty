package pca;

import Jama.Matrix;
import domain.ImageBuider;
import util.Util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dragos on 6/9/17.
 */
public class Pca {

    private final File dir;

    public Pca(File dir){
        this.dir = dir;
    }

    public Pca(String url){
        this.dir = new File(url);
    }

    public Pca train(int nbClasses, int nbImages){
        //get first n classes
        List<Matrix> matrixList = this.walk(this.dir.toPath()).limit(nbClasses)
                //get first k images from each classes than parse to ImageBuilder
                .flatMap(path -> this.walk(path).limit(nbImages).map(img -> new ImageBuider("file:" + img.toFile().getAbsolutePath())))
                //scale img
                .map(imageBuider-> imageBuider.scale(Util.widthScale, Util.heigtScale))
                // parse img to matrix
                .map(ImageBuider::toMatrixByRed)
                .collect(Collectors.toList());


        Matrix middleMatrix = matrixList.stream().reduce(Matrix::plus).orElse(new Matrix(Util.widthScale, Util.heigtScale)).times(1./matrixList.size());

        List<Matrix> centerMatrixList = matrixList.stream().map(matrix -> matrix.minus(middleMatrix)).collect(Collectors.toList());


//        centerMatrixList.stream().map(ImageBuider::new).limit(3).forEach(m->m.writeImage(Util.resource.concat("/").concat(m.getName())));

        return this;
    }

    private Stream<Path> walk(Path path) {
        try {
            return Files.walk(path, 1).skip(1).sorted();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Stream.of();
    }
}
