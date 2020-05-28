public class BHTree {

    private final double theta = 1;

    private Body body;
    private Cube cube;
    private BHTree[] cubes = new BHTree[8];

    // Constructor, creates and initializes a new BHTree object, i.e. a new octtree
    public BHTree(Cube c) {
        this.cube = c;
    }

    // Inserts a body object, which is not 'null', into the octtree
    // While doing so, method checks if the octant where the new body will be inserted is empty.
    // If the octant is empty, the body simply gets added to the tree.
    // If the octant is not empty, it gets subdivided into eight octants and both the old and the new body are added
    // to the tree in their matching octant.
    // Also the method calculates the representative body for the octree, by uniting the bodies from the leaves upwards.
    public void insert(Body b) {
        if (body == null) {
            body = b;
            return;
        }
        if (!isExternal()) {
            body = body.unite(b);
            putBody(b);
        } else {
            for (int i = 0; i < cubes.length; i++) {
                cubes[i] = new BHTree(cube.buildCube(i));
            }
            putBody(this.body);
            putBody(b);
            body = body.unite(b);
        }
    }

    // Method decides, in which octant the body 'b' should be added, by calculating the positions of the octants and
    // comparing them to the position of the body object 'b'
    // If a octant with matching coordinates is found, the body object 'b' will be inserted into it.
    private void putBody(Body b) {
        for (int i = 0; i < cubes.length; i++) {
            if (b.isIn(cube.buildCube(i))) {
                cubes[i].insert(b);
            }
        }
    }

    // Returns 'true' if every octant of the octtree is empty.
    // Returns 'false' if any octant of the octtree contains a body.
    private boolean isExternal() {
        for (BHTree cube : cubes) {
            if (cube != null) {
                return false;
            }
        }
        return true;
    }

    // Approximates the net force acting on Body 'b' from all bodies
    // in the invoking Barnes-Hut tree, and updates 'b's force accordingly.
    public void updateForce(Body b) {
        if (body == null || b.equals(body)) {
            return;
        }
        if (isExternal()) {
            b.addForce(body);
        } else {
            double length = cube.getLength();
            double d = body.distanceTo(b);
            if ((length / d) < theta) {
                b.addForce(body);
            } else {
                for (BHTree cube : cubes) {
                    cube.updateForce(b);
                }
            }
        }
    }
}