public class Octant {

    private double x, y, z, a;

    public Octant(double x, double y, double z, double a) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.a = a;
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

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public boolean contains(CelestialBody b){
        return (b.getPosition().getX() > x - a &&
                b.getPosition().getX() < x + a &&
                b.getPosition().getY() > y - a &&
                b.getPosition().getY() < y + a &&
                b.getPosition().getZ() > z - a &&
                b.getPosition().getZ() < z + a);

    }
}
