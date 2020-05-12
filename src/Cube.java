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

    public double length() {
        return length;
    }

    public boolean contains(double x, double y, double z) {
        double halfLength = this.length / 2.0;
        return (x >= this.x - halfLength && x < this.x + halfLength &&
                y >= this.y - halfLength && y < this.y + halfLength &&
                z >= this.z - halfLength && z < this.z + halfLength);
    }

    public Cube fNw() {
        double x = this.x - this.length / 4.0;
        double y = this.y + this.length / 4.0;
        double z = this.z - this.length / 4.0;
        double length = this.length / 2.0;
        Cube fNw = new Cube(x, y, z, length);
        return fNw;
    }

    public Cube fNe() {
        double x = this.x + this.length / 4.0;
        double y = this.y + this.length / 4.0;
        double z = this.z - this.length / 4.0;
        double length = this.length / 2.0;
        Cube fNe = new Cube(x, y, z, length);
        return fNe;
    }

    public Cube fSw() {
        double x = this.x - this.length / 4.0;
        double y = this.y - this.length / 4.0;
        double z = this.z - this.length / 4.0;
        double length = this.length / 2.0;
        Cube fSw = new Cube(x, y, z, length);
        return fSw;
    }

    public Cube fSe() {
        double x = this.x + this.length / 4.0;
        double y = this.y - this.length / 4.0;
        double z = this.z - this.length / 4.0;
        double length = this.length / 2.0;
        Cube fSe = new Cube(x, y, z, length);
        return fSe;
    }

    public Cube bNw() {
        double x = this.x - this.length / 4.0;
        double y = this.y + this.length / 4.0;
        double z = this.z + this.length / 4.0;
        double length = this.length / 2.0;
        Cube bNw = new Cube(x, y, z, length);
        return bNw;
    }

    public Cube bNe() {
        double x = this.x + this.length / 4.0;
        double y = this.y + this.length / 4.0;
        double z = this.z + this.length / 4.0;
        double length = this.length / 2.0;
        Cube bNe = new Cube(x, y, z, length);
        return bNe;
    }

    public Cube bSw() {
        double x = this.x - this.length / 4.0;
        double y = this.y - this.length / 4.0;
        double z = this.z + this.length / 4.0;
        double length = this.length / 2.0;
        Cube bSw = new Cube(x, y, z, length);
        return bSw;
    }

    public Cube bSe() {
        double x = this.x + this.length / 4.0;
        double y = this.y - this.length / 4.0;
        double z = this.z + this.length / 4.0;
        double length = this.length / 2.0;
        Cube bSe = new Cube(x, y, z, length);
        return bSe;
    }

    public void draw() {
        StdDraw.square(x, y, length / 2.0);
    }

    public String toString() {
        String ret = "\n";
        for (int row = 0; row < this.length; row++) {
            for (int col = 0; col < this.length; col++) {
                if (row == 0 || col == 0 || row == this.length - 1 || col == this.length - 1)
                    ret += "*";
                else
                    ret += " ";
            }
            ret += "\n";
        }
        return ret;
    }

}