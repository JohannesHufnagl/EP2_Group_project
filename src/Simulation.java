import java.awt.Color;

public class Simulation {

    // one astronomical unit (AU) is the average distance of earth to the sun.
    private static final double AU = 150e9;

    public static void main(String[] args) {

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(-2 * AU, 2 * AU);
        StdDraw.setYscale(-2 * AU, 2 * AU);
        StdDraw.enableDoubleBuffering();


        // generate random bodies
        Body[] bodies = new Body[1001];
        for (int i = 0; i < 1000; i++) {
            bodies[i] = new Body(((Math.random() * (2e30 - (1e22))) + (1e22)),
                    new Vector3(((Math.random() * (2 * AU - (-2) * AU)) + (-2) * AU), ((Math.random() * (2 * AU - (-2) * AU)) + (-2) * AU), 0),
                    new Vector3(((Math.random() * (1e9 - (-1e9))) + (-1e9)), ((Math.random() * (1e9 - (-1e9))) + (-1e9)), 0),
                    new Color(255,255,0));
        }

        // heavy body in the middle
        bodies[1000] = new Body(6e37,
                new Vector3(0,0,0),
                new Vector3(0, 0, 0),
                new Color(255,  0, 0));


        // simulate the universe
        while(true){

            Cube cube = new Cube(0, 0, 0, 4 * AU);
            BHTree tree = new BHTree(cube);

            // build the Barnes-Hut tree
            for (int i = 0; i < 1001; i++) {
                if (bodies[i].isIn(cube)) {
                    tree.insert(bodies[i]);
                }
            }

            // update the forces, positions, velocities, and accelerations
            for (int i = 0; i < 1001; i++) {
                bodies[i].resetForce();
                tree.updateForce(bodies[i]);
                bodies[i].move();
            }

            // draw the bodies
            StdDraw.clear(StdDraw.BLACK);
            for (int i = 0; i < 1001; i++) {
                bodies[i].draw();
            }

            StdDraw.show();
            StdDraw.pause(5);
        }
    }

}
