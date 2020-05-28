public class BHTree {

    private final double theta = 1;

    private Body body;
    private Cube cube;
    private BHTree[] cubes = new BHTree[8];

    public BHTree(Cube c) {
        this.cube = c;
    }

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

    private void putBody(Body b) {
        for (int i = 0; i < cubes.length; i++) {
            if (b.isIn(cube.buildCube(i))) {
                cubes[i].insert(b);
            }
        }
    }

    private boolean isExternal() {
        for (BHTree cube : cubes) {
            if (cube != null) {
                return false;
            }
        }
        return true;
    }

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
