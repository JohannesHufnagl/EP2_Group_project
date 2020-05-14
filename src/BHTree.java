public class BHTree {

    private final double theta = 1;

    private Body body;
    private Cube cube;
    private BHTree fNw;
    private BHTree fNe;
    private BHTree fSw;
    private BHTree fSe;
    private BHTree bNw;
    private BHTree bNe;
    private BHTree bSw;
    private BHTree bSe;

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
        if (body == null) {
            body = b;
            return;
        }

        if (!isExternal()) {
            body = body.unite(b);

            putBody(b);
        } else {
            fNw = new BHTree(cube.fNw());
            fNe = new BHTree(cube.fNe());
            fSw = new BHTree(cube.fSw());
            fSe = new BHTree(cube.fSe());
            bNw = new BHTree(cube.bNw());
            bNe = new BHTree(cube.bNe());
            bSw = new BHTree(cube.bSw());
            bSe = new BHTree(cube.bSe());

            putBody(this.body);
            putBody(b);

            body = body.unite(b);
        }
    }

    private void putBody(Body b) {
        if (b.isIn(cube.fNw())) {
            fNw.insert(b);
        } else if (b.isIn(cube.fNe())) {
            fNe.insert(b);
        } else if (b.isIn(cube.fSw())) {
            fSw.insert(b);
        } else if (b.isIn(cube.fSe())) {
            fSe.insert(b);
        } else if (b.isIn(cube.bNw())) {
            bNw.insert(b);
        } else if (b.isIn(cube.bNe())) {
            bNe.insert(b);
        } else if (b.isIn(cube.bSw())) {
            bSw.insert(b);
        } else if (b.isIn(cube.bSe())) {
            bSe.insert(b);
        }
    }

    private boolean isExternal() {
        return (fNw == null && fNe == null && fSw == null && fSe == null &&
                bNw == null && bNe == null && bSw == null && bSe == null);
    }

    public void updateForce(Body b) {
        if (body == null || b.equals(body)) {
            return;
        }

        if (isExternal()) {
            b.addForce(body);
        } else {
            double length = cube.length();

            double d = body.distanceTo(b);

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

}
