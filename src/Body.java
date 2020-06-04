import java.awt.Color;

public class Body {

    // gravitational constant
    private static final double G = 6.6743e-11;

    private double mass;
    private Vector3 position;
    private Vector3 velocity;
    private Vector3 force;
    private Color color;

    // Constructor, creates and initializes a new body object
    public Body(double mass, Vector3 position, Vector3 velocity, Color color) {
        this.mass = mass;
        this.position = position;
        this.velocity = velocity;
        this.color = color;
    }

    // moves a body object accordingly to their current position, current velocity, mass and
    // their influencing force by other objects
    public void move() {
        position = position.plus(velocity).plus(force.dividedBy(mass));
        velocity = velocity.plus(force.dividedBy(mass));
    }

    // Calculates and returns the euclidean distance to another body object
    public double distanceTo(Body b) {
        return position.distanceTo(b.position);
    }

    // Resets the currently influencing force of the body object to zero
    public void resetForce() {
        force = new Vector3();
    }

    // Computes the net force acting between the invoking body and 'b', and
    // adds this to the net force acting on the invoking Body.
    public void addForce(Body b) {
        Vector3 dir = b.position.minus(position);
        double dist = dir.length();
        dir.normalize();
        double f = (G * mass * b.mass) / (dist * dist);
        force = force.plus(dir.times(f));
    }

    // Draws a body object with their color on to the canvas
    public void draw() {
        StdDraw.setPenRadius(0.0005);
        StdDraw.setPenColor(color);
        position.draw();
    }

    // Returns 'true' if the body's coordinates match with the coordinates of an cube (octant),
    // i.e. the body is in the octant
    // Returns 'false' if the body's coordinates DONT match with the coordinates of an cube (octant),
    // i.e. the body is NOT in the octant
    public boolean isIn(Cube c) {
        return position.in(c);
    }

    // Returns a new body object, that represents the center of gravity of the invoking body and the body 'b'
    public Body unite(Body b) {
        double m = mass + b.mass;
        Vector3 p = (position.times(mass).plus(b.position.times(b.mass))).dividedBy(m);
        Vector3 v = (velocity.times(mass).plus(b.velocity.times(b.mass))).dividedBy(m);
        return new Body(m, p, v, color);
    }

    public String toString() {
        return mass + " kg, position: " + position.toString() + " m, " + "movement: " + velocity.toString() + " m/s.";
    }
}
