package model;

import java.util.Objects;

public class MatrixCor {
    private int col;
    private int row;

    public MatrixCor(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;  // Son la misma instancia
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false; // No son de la misma clase
        }

        MatrixCor otraInstancia = (MatrixCor) obj; // Cast a la clase específica

        // Comparación de los campos row y col
        return this.row == otraInstancia.row && this.col == otraInstancia.col;
    }

    // También sería recomendable sobrescribir el método hashCode
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }


    @Override
    public String toString() {
        return "row" + row + " | col" + col;
    }
}
