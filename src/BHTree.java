public class BHTree {

    // specified threshold value to set the accuracy of the simulation
    private final double theta = 0.5;

    private Body body;      // single body or aggregate body stored in this node
    private Cube cube;      // cubic region that the tree represents
    private BHTree fNw;     // tree representing the front northwest octant
    private BHTree fNe;     // tree representing the front northeast octant
    private BHTree fSw;     // tree representing the front southwest octant
    private BHTree fSe;     // tree representing the front southeast octant
    private BHTree bNw;     // tree representing the behind northwest octant
    private BHTree bNe;     // tree representing the behind northeast octant
    private BHTree bSw;     // tree representing the behind southwest octant
    private BHTree bSe;     // tree representing the behind southeast octant

    public BHTree(Cube c) {
        this.cube = c;
        this.body = null;
        this.fNw = null;
        this.fNe = null;
        this.fSw = null;
        this.fSe = null;
        this.bNw = null;
        this.bNe = null;
        this.bSw = null;
        this.bSe = null;
    }

    public void insert(Body b) {
        // if this node does not contain a body, put the new Body b here
        if (body == null) {
            body = b;
            return;
        }

        if (!isExternal()) {
            // update the center-of-mass and the total mass
            body = body.plus(b);

            // recursively insert the new Body b into the appropriate octant
            putBody(b);
        } else {
            // subdivide the region further by creating eight children
            fNw = new BHTree(cube.fNw());
            fNe = new BHTree(cube.fNe());
            fSw = new BHTree(cube.fSw());
            fSe = new BHTree(cube.fSe());
            bNw = new BHTree(cube.bNw());
            bNe = new BHTree(cube.bNe());
            bSw = new BHTree(cube.bSw());
            bSe = new BHTree(cube.bSe());

            // recursively insert both this body and the new Body b into the appropriate octant
            putBody(this.body);
            putBody(b);

            // update the center-of-mass and the total mass
            body = body.plus(b);
        }
    }

    private void putBody(Body b) {
        if (b.in(cube.fNw())) {
            fNw.insert(b);
        } else if (b.in(cube.fNe())) {
            fNe.insert(b);
        } else if (b.in(cube.fSw())) {
            fSw.insert(b);
        } else if (b.in(cube.fSe())) {
            fSe.insert(b);
        } else if (b.in(cube.bNw())) {
            bNw.insert(b);
        } else if (b.in(cube.bNe())) {
            bNe.insert(b);
        } else if (b.in(cube.bSw())) {
            bSw.insert(b);
        } else if (b.in(cube.bSe())) {
            bSe.insert(b);
        }
    }

    private boolean isExternal() {
        // this tree node is external if all eight children are null
        return (fNw == null && fNe == null && fSw == null && fSe == null &&
                bNw == null && bNe == null && bSw == null && bSe == null);
    }

    public void updateForce(Body b) {
        if (body == null || b.equals(body)) {
            return;
        }

        // if this tree node is external, update the net force acting on b
        if (isExternal()) {
            b.addForce(body);
        } else {
            // length of the cubic region represented by the internal node
            double length = cube.length();

            // distance between Body b and this node's center-of-mass
            double d = body.distanceTo(b);

            // compare the ratio (leghth / d) to the threshold value theta
            if ((length / d) < theta) {
                b.addForce(body);
            } else {
                fNw.updateForce(b);
                fNe.updateForce(b);
                fSw.updateForce(b);
                fSe.updateForce(b);
                bNw.updateForce(b);
                bNe.updateForce(b);
                bSw.updateForce(b);
                bSe.updateForce(b);
            }
        }
    }

    public String toString() {
        if (isExternal()) {
            return " " + body + "\n";
        } else {
            return "*" + body + "\n" + fNw + fNe + fSw + fSe + bNw + bNe + bSw + bSe;
        }
    }

}
