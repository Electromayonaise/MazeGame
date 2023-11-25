package main_module.model.util;

/**
 * The {@code Vector} class represents a 2D vector with components {@code x} and {@code y}.
 */
public class Vector {
    private double x; // The x-component of the vector
    private double y; // The y-component of the vector

    /**
     * Constructs a vector with the specified x and y components.
     *
     * @param x The x-component of the vector.
     * @param y The y-component of the vector.
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-component of the vector.
     *
     * @return The x-component of the vector.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-component of the vector.
     *
     * @param x The new x-component of the vector.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets the y-component of the vector.
     *
     * @return The y-component of the vector.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-component of the vector.
     *
     * @param y The new y-component of the vector.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Normalizes the vector, ensuring its magnitude is 1.
     */
    public void normalize() {
        double norm = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        if (norm != 0) {
            x /= norm;
            y /= norm;
        }
    }

    /**
     * Scales the vector by a specified speed factor.
     *
     * @param speed The speed factor to scale the vector by.
     */
    public void setSpeed(double speed) {
        x *= speed;
        y *= speed;
    }

    /**
     * Returns a string representation of the vector.
     *
     * @return A string representation of the vector.
     */
    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Creates a clone of the vector.
     *
     * @return A new vector with the same x and y components.
     */
    public Vector clone() {
        return new Vector(x, y);
    }
}

