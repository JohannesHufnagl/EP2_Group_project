public class Octant {

    private double a;
    private Vector3 position;

    public Octant(Vector3 position, double a) {
        this.position = position;
        this.a = a;
    }

    public Vector3 getPosition(){
        return position;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public boolean contains(CelestialBody b){

        return (b.getPosition().getX() > this.position.getX() - a &&
                b.getPosition().getX() < this.position.getX() + a &&
                b.getPosition().getY() > this.position.getY() - a &&
                b.getPosition().getY() < this.position.getY() + a &&
                b.getPosition().getZ() > this.position.getZ() - a &&
                b.getPosition().getZ() < this.position.getZ() + a);
    }
}