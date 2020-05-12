import java.awt.Color;

public class Body {

    // gravitational constant
    private static final double G = 6.6743e-11;

    private double mass;            // mass
    private Vector3 position;       // position
    private Vector3 velocity;       // velocity
    private Vector3 force;          // force
    private Color color;            // color

    public Body(double mass, Vector3 position, Vector3 velocity, Color color) {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.color = color;
    }

    public void update() {
        position = position.plus(velocity).plus(force.dividedBy(mass));
        velocity = velocity.plus(force.dividedBy(mass));

    }

    public double distanceTo(Body b) {
        return position.distanceTo(b.position);
    }

    public void resetForce() {
        force = new Vector3();
    }

    public void addForce(Body b) {
        Vector3 dir = b.position.minus(position);
        double dist = dir.length();
        dir.normalize();
        double f = (G * mass * b.mass) / (dist * dist);
        force = force.plus(dir.times(f));
    }

    public void draw() {
        StdDraw.setPenColor(color);
        position.draw();
    }

    public String toString() {
        return mass + " kg, position: " + position.toString() + " m, " + "movement: " + velocity.toString() + " m/s.";
    }

    public boolean in(Cube c) {
        return position.in(c);
    }

    public Body plus(Body b) {
        double m = mass + b.mass;
        Vector3 p = (position.times(mass).plus(b.position.times(b.mass))).dividedBy(m);
        return new Body(m, p, velocity, color);
    }
}
