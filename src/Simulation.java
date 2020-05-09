import java.awt.*;
import java.util.ArrayList;

public class Simulation {

    // one astronomical unit (AU) is the average distance of earth to the sun.
    private static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {

        Octant boundary = new Octant(new Vector3(4 * AU, 4 * AU, 4 * AU), 4 * AU);
        Octree ot = new Octree(boundary);
        ArrayList<CelestialBody> bodies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            CelestialBody test = new CelestialBody("test" + i, Math.random() * 1e24, Math.random() * 1e3,
                    new Vector3((Math.random() * 4 * AU), (Math.random() * 4 * AU), (Math.random() * 4 * AU)),
                    new Vector3(0, 0, 0),
                    new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
            System.out.println(ot.insert(test));
            bodies.add(test);
        }
        System.out.println("Finished!");

        StdDraw.setCanvasSize(500, 500);
        StdDraw.setXscale(0, 4 * AU);
        StdDraw.setYscale(0, 4 * AU);
        double pixelWidth = 4 * AU / 400;
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);

        for (int i = 0; i < bodies.size(); i++) {
            bodies.get(i).draw();
        }

        // show new positions
        StdDraw.show();

    }
}