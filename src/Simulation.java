import java.awt.Color;

public class Simulation {

    // one astronomical unit (AU) is the average distance of earth to the sun.
    private static final double AU = 149.5979e9;

    public static void main(String[] args) {

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-2 * AU, 2 * AU);
        StdDraw.setYscale(-2 * AU, 2 * AU);
        StdDraw.enableDoubleBuffering();


        Body[] bodies = new Body[1000];
        for (int i = 0; i < 1000; i++) {
            bodies[i] = new Body(Math.random() * 1e25,
                    new Vector3(((Math.random() * (2 * AU - (-2) * AU)) + (-2) * AU), ((Math.random() * (2 * AU - (-2) * AU)) + (-2) * AU), ((Math.random() * (2 * AU - (-2) * AU)) + (-2) * AU)),
                    new Vector3(((Math.random() * (1e8 - (-1e8))) + (-1e8)), ((Math.random() * (1e8 - (-1e8))) + (-1e8)), 0),
                    new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
        }

        // simulate the universe
        while (true) {

            Cube cube = new Cube(0, 0, 0, 4 * AU);
            BHTree tree = new BHTree(cube);

            // build the Barnes-Hut tree
            for (int i = 0; i < 1000; i++) {
                if (bodies[i].in(cube)) {
                    tree.insert(bodies[i]);
                }
            }

            // update the forces, positions, velocities, and accelerations
            for (int i = 0; i < 1000; i++) {
                bodies[i].resetForce();
                tree.updateForce(bodies[i]);
                bodies[i].update();
            }

            // draw the bodies
            StdDraw.clear(StdDraw.BLACK);
            for (int i = 0; i < 1000; i++) {
                bodies[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }
    }

}
