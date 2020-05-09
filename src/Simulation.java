import java.awt.*;

public class Simulation {

    // one astronomical unit (AU) is the average distance of earth to the sun.
    private static final double AU = 150e9;

    // all quantities are based on units of kilogram respectively second and meter.

    // The main simulation method using instances of other classes.
    public static void main(String[] args) {


        Octant boundary = new Octant(200, 200, 200, 200);
        Octree ot = new Octree(boundary);

        for (int i = 0; i < 10; i++) {
            CelestialBody test = new CelestialBody("test" + i, Math.random() * 1e24, Math.random() * 1e3,
                    new Vector3((Math.random() * 200), (Math.random() * 200), (Math.random() * 200)),
                    new Vector3(0, 0, 0),
                    new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
            System.out.println(ot.insert(test));
        }
        System.out.println("Finished!");
    }
}