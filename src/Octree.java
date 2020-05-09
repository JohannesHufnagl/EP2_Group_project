public class Octree {

    private Octant boundary;
    private CelestialBody bodie;
    private boolean divided;
    private Octree frontNortheast, frontNorthwest, frontSoutheast, frontSouthwest,
            backNortheast, backNorthwest, backSoutheast, backSouthwest;
    private int numberOfBodies;


    public Octree(Octant boundary) {
        this.boundary = boundary;
        this.divided = false;
    }

    public void subdivide() {
        double x = boundary.getPosition().getX();
        double y = boundary.getPosition().getY();
        double z = boundary.getPosition().getZ();
        double a = boundary.getA();

        Octant fNe = new Octant(new Vector3(x + a / 2, y - a / 2, z - a / 2), a / 2);
        frontNortheast = new Octree(fNe);
        Octant fNw = new Octant(new Vector3(x - a / 2, y - a / 2, z - a / 2), a / 2);
        frontNorthwest = new Octree(fNw);
        Octant fSe = new Octant(new Vector3(x + a / 2, y + a / 2, z - a / 2), a / 2);
        frontSoutheast = new Octree(fSe);
        Octant fSw = new Octant(new Vector3(x - a / 2, y + a / 2, z - a / 2), a / 2);
        frontSouthwest = new Octree(fSw);
        Octant bNe = new Octant(new Vector3(x + a / 2, y - a / 2, z + a / 2), a / 2);
        backNortheast = new Octree(bNe);
        Octant bNw = new Octant(new Vector3(x - a / 2, y - a / 2, z + a / 2), a / 2);
        backNorthwest = new Octree(bNw);
        Octant bSe = new Octant(new Vector3(x + a / 2, y + a / 2, z + a / 2), a / 2);
        backSoutheast = new Octree(bSe);
        Octant bSw = new Octant(new Vector3(x - a / 2, y + a / 2, z + a / 2), a / 2);
        backSouthwest = new Octree(bSw);

        divided = true;
    }

    public boolean insert(CelestialBody b) {
        if (!boundary.contains(b)) {
            return false;
        }

        if (bodie == null && !divided) {
            bodie = b;
            return true;
        } else {
            if (!divided) {
                CelestialBody containedBody = bodie;
                bodie = null;
                subdivide();
                frontNortheast.insert(containedBody);
                frontNorthwest.insert(containedBody);
                frontSoutheast.insert(containedBody);
                frontSouthwest.insert(containedBody);
                backNortheast.insert(containedBody);
                backNorthwest.insert(containedBody);
                backSoutheast.insert(containedBody);
                backSouthwest.insert(containedBody);
            }
            return (frontNortheast.insert(b) ||
                    frontNorthwest.insert(b) ||
                    frontSoutheast.insert(b) ||
                    frontSouthwest.insert(b) ||
                    backNortheast.insert(b) ||
                    backNorthwest.insert(b) ||
                    backSoutheast.insert(b) ||
                    backSouthwest.insert(b));
        }
    }
}
