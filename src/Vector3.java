
// This class represents vectors in a 3D vector space.
public class Vector3 {

    private double x;
    private double y;
    private double z;

    public Vector3() {
    }

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Returns the sum of this vector and vector 'v'.
    public Vector3 plus(Vector3 v) {
        return new Vector3(x + v.x, y + v.y, z + v.z);
    }

    // Returns the difference of this vector and vector 'v'.
    public Vector3 minus(Vector3 v) {
        return new Vector3(x - v.x, y - v.y, z - v.z);
    }

    // Returns the product of this vector and 'd'.
    public Vector3 times(double d) {
        return new Vector3(x * d, y * d, z * d);
    }

    // Returns the quotient of this vector and 'd'.
    public Vector3 dividedBy(double d) {
        return new Vector3(x / d, y / d, z / d);
    }

    // Returns the Euclidean distance of this vector
    // to the specified vector 'v'.
    public double distanceTo(Vector3 v) {
        return minus(v).length();
    }

    // Returns the length (norm) of this vector.
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    // Normalizes this vector: changes the length of this vector such that it becomes one.
    // The direction and orientation of the vector is not affected.
    public void normalize() {
        double length = length();
        x /= length;
        y /= length;
        z /= length;
    }

    // Draws a point with the center at (x,y) coordinates of this vector
    // in the existing StdDraw canvas. The z-coordinate is not used.
    public void draw() {
        StdDraw.point(x, y);
    }

    public boolean in(Cube c) {
        return c.contains(x, y, z);
    }

    // Returns the coordinates of this vector in brackets as a string
    // in the form "[x,y,z]", e.g., "[1.48E11,0.0,0.0]".
    public String toString() {
        return "[" + x + "," + y + "," + z + "]";
    }

    // Prints the coordinates of this vector in brackets to the console (without newline)
    // in the form [x,y,z], e.g.,
    // [1.48E11,0.0,0.0]
    public void print() {
        System.out.print(toString());
    }

}
