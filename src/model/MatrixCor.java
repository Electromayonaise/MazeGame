package model;

import java.util.Objects;

/**
 * The {@code MatrixCor} class represents a matrix coordinate with row and column values.
 * It provides methods to get and set the row and column values, as well as implementations for
 * {@code equals}, {@code hashCode}, and {@code toString}.
 */
public class MatrixCor {
    /**
     * The column value of the matrix coordinate.
     */
    private int col;

    /**
     * The row value of the matrix coordinate.
     */
    private int row;

    /**
     * Constructs a {@code MatrixCor} with the specified row and column values.
     *
     * @param row The row value of the matrix coordinate.
     * @param col The column value of the matrix coordinate.
     */
    public MatrixCor(int row, int col) {
        this.col = col;
        this.row = row;
    }

    /**
     * Gets the column value of the matrix coordinate.
     *
     * @return The column value.
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the column value of the matrix coordinate.
     *
     * @param col The new column value.
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Gets the row value of the matrix coordinate.
     *
     * @return The row value.
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row value of the matrix coordinate.
     *
     * @param row The new row value.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj The reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  // They are the same instance
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false; // They are not of the same class
        }

        MatrixCor otherInstance = (MatrixCor) obj; // Cast to the specific class

        // Comparison of the row and col fields
        return this.row == otherInstance.row && this.col == otherInstance.col;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object, including the row and column values.
     */
    @Override
    public String toString() {
        return "row" + row + " | col" + col;
    }
}

