package com.slinger;

import java.util.Scanner;

public class Matrix {
    private final int rows;
    private final int cols;
    private final double[][] matrix;
    private final Scanner scanner;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
        this.scanner = new Scanner(System.in);
    }

    //used for testing
    public Matrix() {
        this.rows = 3;
        this.cols = 3;
        this.matrix = new double[rows][cols];
        this.scanner = new Scanner(System.in);

        matrix[0][0] = 2;
        matrix[0][1] = -1;
        matrix[0][2] = 0;
//        matrix[0][3] = 4;
//        matrix[0][4] = 5;


        matrix[1][0] = 0;
        matrix[1][1] = 1;
        matrix[1][2] = 2;
//        matrix[1][3] = 4;
//        matrix[1][4] = 3;


        matrix[2][0] = 1;
        matrix[2][1] = 1;
        matrix[2][2] = 0;
//        matrix[2][3] = 1;
//        matrix[2][4] = 5;


//        matrix[3][0] = 1;
//        matrix[3][1] = 3;
//        matrix[3][2] = 9;
//        matrix[3][3] = 8;
//        matrix[3][4] = 7;

//        matrix[4][0] = 5;
//        matrix[4][1] = 8;
//        matrix[4][2] = 4;
//        matrix[4][3] = 7;
//        matrix[4][4] = 11;

    }


    public void fillMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    public Matrix add(Matrix b) {
        Matrix result = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[i][j] = b.matrix[i][j] + matrix[i][j];
            }
        }

        return result;
    }

    public Matrix scalarMultiplication(double scalar) {
        Matrix result = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.matrix[i][j] = scalar * matrix[i][j];
            }
        }

        return result;
    }

    public Matrix matrixMultiplication(Matrix b) {
        Matrix result = new Matrix(rows, b.cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < b.cols; j++) {

                double sum = 0;

                for (int k = 0; k < b.rows; k++) {
                    sum += matrix[i][k] * b.matrix[k][j];
                }

                result.matrix[i][j] = sum;
            }
        }

        return result;
    }

    public Matrix mainDiagonalTranspose() {
        Matrix transposed = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed.matrix[i][j] = matrix[j][i];
            }
        }

        return transposed;
    }

    public Matrix sideDiagonalTranspose() {
        Matrix transposed = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed.matrix[i][j] = matrix[cols - 1 - j][rows - 1 - i];
            }
        }

        return transposed;
    }

    public Matrix verticalLineTranspose() {
        Matrix transposed = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed.matrix[i][j] = matrix[i][cols - 1 - j];
            }
        }

        return transposed;
    }

    public Matrix horizontalLineTranspose() {
        Matrix transposed = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed.matrix[i][j] = matrix[rows - i - 1][j];
            }
        }

        return transposed;
    }

    public double getDeterminant() {
        return getDeterminant(this);
    }

    private double getDeterminant(Matrix a) {
        double determinant = 0;

        if (a.rows == 2) {
            determinant = twoByTwoDeterminant(a);
            return determinant;
        }

        for (int i = 0; i < a.rows; i++) {
            Matrix sub = createSubMatrix(a, 0,  i);
            determinant += a.matrix[0][i] * Math.pow(-1, i) * getDeterminant(sub);
        }

        return determinant;
    }

    private double twoByTwoDeterminant(Matrix a) {
        return a.matrix[0][0] * a.matrix[1][1] - a.matrix[0][1] * a.matrix[1][0];
    }

    public Matrix createSubMatrix(Matrix a, int row, int col) {
        Matrix result = new Matrix(a.rows - 1, a.cols - 1);
        int rowIdx = 0;
        int colIdx = 0;

        for (int i = 0; i < a.rows; i++) {
            if (row == i) {
                continue;
            }

            for (int j = 0; j < a.rows; j++) {
                if (j != col) {
                    result.matrix[rowIdx][colIdx++] = a.matrix[i][j];
                }
            }
            rowIdx++;
            colIdx = 0;
        }

        return result;
    }

    public Matrix getInverse() {
        Matrix adjoint = new Matrix(rows, cols);

        //create adjoint matrix
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Matrix sub = createSubMatrix(this, i, j);
                double det = Math.pow(-1, i + j) * getDeterminant(sub);
                adjoint.matrix[i][j] = det;
            }
        }

        //get transpose of adjoint matrix
        Matrix transpose = adjoint.mainDiagonalTranspose();

        //reciprocal of det
        double reciprocal = 1 / getDeterminant();

        //multiply transpose by reciprocal
        Matrix inverse = transpose.scalarMultiplication(reciprocal);

        return inverse;
    }


    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] % 1 != 0) {
                    System.out.print(String.format("%.2f",matrix[i][j]) + " ");
                } else {
                    System.out.print(matrix[i][j] + " ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Matrix getMatrix() {
        return this;
    }
}
