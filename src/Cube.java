public class Cube {

    private double x;
    private double y;
    private double z;
    private double length;

    public Cube(double x, double y, double z, double length) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    public boolean contains(double x, double y, double z) {
        double halfLength = this.length / 2.0;
        return (x >= this.x - halfLength && x < this.x + halfLength &&
                y >= this.y - halfLength && y < this.y + halfLength &&
                z >= this.z - halfLength && z < this.z + halfLength);
    }

    public Cube buildCube(int i) {
        double x = i % 2 == 0 ? this.x - this.length / 4.0 : this.x + this.length / 4.0;
        double y = i > 1 && i < 4 || i > 5 ? this.y - this.length / 4.0 : this.y + this.length / 4.0;
        double z = i < 4 ? this.z - this.length / 4.0 : this.z + this.length / 4.0;
        double length = this.length / 2.0;
        return new Cube(x, y, z, length);
    }

    public void draw() {
        StdDraw.square(x, y, length / 2.0);
    }

}
