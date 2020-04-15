import java.util.Locale;

// Body represents a body or object in space with 3D-position, velocity and mass.
// This class will be used by the executable class 'Space'. A body moves through space according to
// its inertia and - if specified - an additional force exerted on it (for example the
// gravitational force of another mass).
public class Body {
    // TODO: class definition.

    /*************************************************************************************
     class Body: Represents a body or object in space with 3D-position, velocity and mass.
     private variables:
        double[] position;
        double[] velocityVector;
        double mass;
     public methods:
        void move();
        void move(double fx, double fy, double fz);
        void move(int seconds, double fx, double fy, double fz);
        void setPosition(double x, double y, double z);
        double getZPosition();
        void setVelocityVector(double vx, double vy, double vz);
        double getZVelocityVector();
        void setMass(double mass);
        double getMass();
        void printPosition();
     *************************************************************************************/

    private double[] position = new double[3];
    private double[] velocityVector = new double[3];
    private double mass;

    // This method should perform new positioning of a body or object in space.
    // This results from the current position of the body or object plus the values of
    // the 3D-velocity-vector (x + vx, y + vy, z + vz).
    // If no other forces act on the body or object, the velocity-vector (vx, vy, vz) remains unchanged
    // due to the inertia of the body or object.
    public void move() {
        position[0] += velocityVector[0];
        position[1] += velocityVector[1];
        position[2] += velocityVector[2];
    }

    // This method positions a body or object like move(), with the difference that (fx, fy, fz) corresponds
    // to a 3D-force-vector (unit: Newton) and the new velocity-vector results from the current velocity-vector
    // plus (fx, fy, fz) divided by the mass of the body or object. The new position is determined with this vector.
    // The current velocity-vector for the next move() or move(fx, fy, fz) call also changes accordingly.
    public void move(double fx, double fy, double fz) {
        velocityVector[0] += fx / mass;
        velocityVector[1] += fy / mass;
        velocityVector[2] += fz / mass;
        move();
    }

    // Additional question:
    // This method positions a body or object like move(fx, fy, fz), with the difference
    // that several positioning steps (corresponds to the number of move(fx, fy, fz) calls)
    // are carried out in a certain time in seconds.
    // Important is, that the values of the velocity-vector have to be reset to the original value
    // after every move(fx, fy, fz) call, because the external force acting on the body or object
    // does not change with the time.
    public void move(int seconds, double fx, double fy, double fz) {
        double[] previousVelocityVector = getVelocityVector();
        if (seconds > 0){
            move(fx, fy, fz);
            setVelocityVector(previousVelocityVector[0],previousVelocityVector[1],previousVelocityVector[2]);
            move(--seconds, fx, fy, fz);
        }
    }

    // This method determines the position of a body or object.
    // This results in a 3D-position-vector (vx, vy, vz), that is assigned to every body or object.
    // (x, y, z) are formal parameters, that indicate the position of the body or object.
    public void setPosition(double x, double y, double z) {
        position[0] = x;
        position[1] = y;
        position[2] = z;
    }

    // This method returns the current z-coordinate of the 3D-position-vector of a body or object.
    public double getZPosition() {
        return position[2];
    }

    // This method determines the velocity of a body or object.
    // This results in a 3D-velocity-vector (vx, vy, vz), that is assigned to every body or object.
    // (vx, vy, vz) are formal parameters, that indicate the velocity of the body or object.
    public void setVelocityVector(double vx, double vy, double vz) {
        velocityVector[0] = vx;
        velocityVector[1] = vy;
        velocityVector[2] = vz;
    }

    // This method returns the current x-, y, and z-coordinates of the 3D-velocity-vector of a body or object.
    public double[] getVelocityVector() {
        return velocityVector;
    }

    // This method returns the current z-coordinate of the 3D-velocity-vector of a body or object.
    public double getZVelocityVector() {
        return velocityVector[2];
    }

    // This method determines the mass of a body or object.
    // (mass) is a formal parameter, that indicate the mass of the body or object.
    public void setMass(double mass) {
        this.mass = mass;
    }

    // This method returns the current mass of a body or object.
    public double getMass() {
        return mass;
    }

    // This method outputs the current position of a body or object on the console.
    public void printPosition() {
        System.out.println(String.format(Locale.US,"Position: [%.2f, %.2f, %.2f]", position[0], position[1], position[2]));
    }
}
