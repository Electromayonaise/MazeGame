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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatrixCor matrixCor = (MatrixCor) o;
        return col == matrixCor.col && row == matrixCor.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(col, row);
    }

    @Override
    public String toString(){
        return "row"+row+" | col"+col;
    }
}
