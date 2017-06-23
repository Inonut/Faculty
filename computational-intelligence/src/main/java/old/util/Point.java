package old.util;

/**
 * Created by dragos on 12.03.2017.
 */
public class Point {

    private static int i = 0;
    private String name;
    private double x;
    private double y;
    private double z;

    {
        i++;
        name = "P" + i;
    }

    public Point() {
        this(0,0,0);
    }

    public Point(Integer x) {
        this(x, 0,0);
    }

    public Point(Integer x, Integer y) {
        this(x,y,0);
    }

    public Point(Integer x, Integer y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
