public class Octree {

    private Vector3 boundary;
    private CelestialBody body;
    private boolean divided;
    private Octree frontNortheast, frontNorthwest, frontSoutheast, frontSouthwest,
            backNortheast, backNorthwest, backSoutheast, backSouthwest;
    private int numberOfBodies;

    public Octree(Vector3 boundary) {
        this.boundary = boundary;
        divided = false;
    }

    public void subdivide() {
        double x = boundary.getX();
        double y = boundary.getY();
        double z = boundary.getZ();
        double a = boundary.getA();

        Vector3 fNe = new Vector3(x + a / 2, y + a / 2, z - a / 2, a / 2);
        frontNortheast = new Octree(fNe);

        Vector3 fNw = new Vector3(x - a / 2, y + a / 2, z - a / 2, a / 2);
        frontNorthwest = new Octree(fNw);

        Vector3 fSe = new Vector3(x + a / 2, y - a / 2, z - a / 2, a / 2);
        frontSoutheast = new Octree(fSe);

        Vector3 fSw = new Vector3(x - a / 2, y - a / 2, z - a / 2, a / 2);
        frontSouthwest = new Octree(fSw);

        Vector3 bNe = new Vector3(x + a / 2, y + a / 2, z + a / 2, a / 2);
        backNortheast = new Octree(bNe);

        Vector3 bNw = new Vector3(x - a / 2, y + a / 2, z + a / 2, a / 2);
        backNorthwest = new Octree(bNw);

        Vector3 bSe = new Vector3(x + a / 2, y - a / 2, z + a / 2, a / 2);
        backSoutheast = new Octree(bSe);

        Vector3 bSw = new Vector3(x - a / 2, y - a / 2, z + a / 2, a / 2);
        backSouthwest = new Octree(bSw);

        divided = true;
    }

    public boolean insert(CelestialBody b) {
        if (!boundary.contains(b)) {
            return false;
        }

        if (body == null && !divided) {
            body = b;
            return true;
        } else {
            if (!divided) {
                CelestialBody containedBody = body;
                body = null;
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
