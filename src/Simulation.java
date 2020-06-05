import java.awt.Color;

public class Simulation {

    // one astronomical unit (AU) is the average distance of earth to the sun.
    private static final double AU = 150e9;

    public static void main(String[] args) {
        long timeStart, timeEnd;

        // Set up canvas for visualization
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-2 * AU, 2 * AU);
        StdDraw.setYscale(-2 * AU, 2 * AU);
        StdDraw.enableDoubleBuffering();

        int numberOfBodies = 7500;

        // generate random bodies
        Body[] bodies = new Body[numberOfBodies];
        for (int i = 0; i < numberOfBodies; i++) {
            double minPosition = -2 * AU + i * 3e7;
            double maxPosition = 2 * AU - i * 3e7;
            double minMass = i < numberOfBodies / 3 ? 1e29 : i < 2 * numberOfBodies / 3 ? 5e29 : 1e30;
            double maxMass = i < numberOfBodies / 3 ? 1e30 : i < 2 * numberOfBodies / 3 ? 5e30 : 1e31;

            bodies[i] = new Body(
                    (Math.random() * (maxMass - minMass)) + minMass,
                    new Vector3(((Math.random() * (maxPosition - minPosition)) + minPosition), ((Math.random() * (maxPosition - minPosition)) + minPosition), 0),
                    new Vector3(((Math.random() * (1e8 - (-1e8))) + (-1e8)), ((Math.random() * (1e8 - (-1e8))) + (-1e8)), 0),
                    new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255))
            );
        }

        // simulate the universe
        while (true) {
            timeStart = System.currentTimeMillis();

            Cube cube = new Cube(0, 0, 0, 4 * AU);
            BHTree tree = new BHTree(cube);

            // build the Barnes-Hut tree
            for (int i = 0; i < numberOfBodies; i++) {
                if (bodies[i].isIn(cube)) {
                    tree.insert(bodies[i]);
                }
            }

            // update the forces, positions, velocities, and accelerations
            for (int i = 0; i < numberOfBodies; i++) {
                bodies[i].resetForce();
                tree.updateForce(bodies[i]);
                bodies[i].move();
            }

            // draw the bodies
            StdDraw.clear(StdDraw.BLACK);
            for (int i = 0; i < numberOfBodies; i++) {
                bodies[i].draw();
            }

            timeEnd = System.currentTimeMillis();
            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.text(0, 1.8 * AU, (timeEnd - timeStart) + " ms");

            StdDraw.show();
        }
    }
}
