import java.awt.*;

// This class represents celestial bodies like stars, planets, asteroids, etc..
public class CelestialBody {

    private String name;
    private double mass;
    private double radius;
    private Vector3 position; // position of the center.
    private Vector3 currentMovement;
    private Color color; // for drawing the body.

    public CelestialBody() {
    }

    public CelestialBody(String name, double mass, double radius, Vector3 position, Vector3 currentMovement, Color color) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.currentMovement = currentMovement;
        this.color = color;
    }

    public CelestialBody(CelestialBody body, Vector3 position, Vector3 velocity) {
        this.name = body.name;
        this.mass = body.mass;
        this.radius = body.radius;
        this.position = position;
        this.currentMovement = velocity;
        this.color = body.color;
    }

    // Returns the name of this celestial body.
    public String getName(){
        return name;
    }

    // Returns the distance between this celestial body and the specified 'body'.
    public double distanceTo(CelestialBody body) {
        return this.position.distanceTo(body.position);
    }

    // Returns a vector representing the gravitational force exerted by 'body' on this celestial body.
    public Vector3 gravitationalForce(CelestialBody body) {
        Vector3 direction = body.position.minus(this.position);
        double r = direction.length();
        direction.normalize();
        double force = Simulation.G * this.mass * body.mass / (r * r);
        return direction.times(force);
    }

    // Moves this body to a new position, according to the specified force vector 'force' exerted
    // on it, and updates the current movement accordingly.
    // (Movement depends on the mass of this body, its current movement and the exerted force.)
    public void move(Vector3 force) {
        Vector3 newPosition = this.position.plus(force.times(1 / this.mass)).plus(this.currentMovement); // F = m*a -> a = F/m
        Vector3 newMovement = newPosition.minus(this.position); // new minus old position.
        this.position = newPosition;
        this.currentMovement = newMovement;
    }

    // Returns a string with the information about this celestial body including
    // name, mass, radius, position and current movement. Example:
    // "Earth, 5.972E24 kg, radius: 6371000.0 m, position: [1.48E11,0.0,0.0] m, movement: [0.0,29290.0,0.0] m/s."
    public String toString() {
        return this.name + ", " + this.mass + " kg, radius: " + this.radius + " m, position: " + this.position.toString() + " m, movement: " + this.currentMovement.toString() + " m/s.";
    }

    // Prints the information about this celestial body including
    // name, mass, radius, position and current movement, to the console (without newline).
    // Earth, 5.972E24 kg, radius: 6371000.0 m, position: [1.48E11,0.0,0.0] m, movement: [0.0,29290.0,0.0] m/s.
    public void print() {
        System.out.print(toString());
    }

    // Draws the celestial body to the current StdDraw canvas as a dot using 'color' of this body.
    // The radius of the dot is in relation to the radius of the celestial body
    // (use a conversion based on the logarithm as in 'Simulation.java').
    public void draw() {
        this.position.drawAsDot(1e9 * Math.log10(this.radius), this.color);
        // use log10 because of large variation of radii.
    }

}