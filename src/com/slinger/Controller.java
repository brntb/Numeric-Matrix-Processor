package com.slinger;

import java.util.Scanner;

public class Controller {

    private final Scanner scanner;

    public Controller(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            System.out.print("Your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addMatrices();
                    break;

                case "2":
                    scalarMultiplication();
                    break;

                case "3":
                    multiplyMatrices();
                    break;

                case "4":
                    transposeMatrix();
                    break;

                case "5":
                    calculateDeterminant();
                    break;

                case "6":
                    calculateInverse();

                case "0":
                    isRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice! Try again!");

            }
        }
    }

    private void addMatrices() {
        System.out.print("Enter size of first matrix: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        Matrix a = new Matrix(rows, cols);
        System.out.println("Enter first matrix");
        a.fillMatrix();

        System.out.print("Enter size of second matrix: ");
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        Matrix b = new Matrix(rows, cols);
        System.out.println("Enter second matrix");
        b.fillMatrix();

        //check for same size matrices
        if (a.getCols() != b.getCols() && a.getRows() != b.getRows()) {
            System.out.println("The operation cannot be performed.");
            return;
        }

        Matrix c = a.add(b);
        System.out.println("The result is:");
        c.print();
    }

    private void scalarMultiplication() {
        System.out.print("Enter size of matrix: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        Matrix a = new Matrix(rows, cols);
        System.out.println("Enter matrix:");
        a.fillMatrix();

        System.out.print("Enter constant: ");
        double constant = scanner.nextDouble();

        Matrix b = a.scalarMultiplication(constant);
        System.out.println("The result is:");
        b.print();
    }

    private void multiplyMatrices() {
        System.out.print("Enter size of first matrix: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        Matrix a = new Matrix(rows, cols);
        System.out.println("Enter first matrix");
        a.fillMatrix();

        System.out.print("Enter size of second matrix: ");
        rows = scanner.nextInt();
        cols = scanner.nextInt();
        Matrix b = new Matrix(rows, cols);
        System.out.println("Enter second matrix");
        b.fillMatrix();

        //check if we can multiply them
        if (a.getCols() != b.getRows()) {
            System.out.println("The operation cannot be performed");
            return;
        }

        Matrix c = a.matrixMultiplication(b);
        System.out.println("The result is:");
        c.print();
    }

    private void transposeMatrix() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line");

        System.out.print("Your choice: ");
        String choice = scanner.nextLine();

        System.out.print("Enter matrix size: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        Matrix matrix = new Matrix(rows, cols);

        System.out.println("Enter matrix:");
        matrix.fillMatrix();

        Matrix transposed;

        switch (choice) {
            case "1":
                transposed = matrix.mainDiagonalTranspose();
                break;

            case "2":
                transposed = matrix.sideDiagonalTranspose();
                break;

            case "3":
                transposed = matrix.verticalLineTranspose();
                break;

            case "4":
                transposed = matrix.horizontalLineTranspose();
                break;

            default:
                System.out.println("Unknown Command!");
                return;
        }

        transposed.print();
    }

    private void calculateDeterminant() {
        System.out.print("Enter matrix size: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        if (rows != cols) {
            System.out.println("Error! Not a square matrix!");
            return;
        }

        Matrix a = new Matrix(rows, cols);
        System.out.println("Enter matrix:");
        a.fillMatrix();

        double determinant = a.getDeterminant();
        System.out.println("The result is:\n" + determinant + "\n");
    }


    private void calculateInverse() {
        System.out.print("Enter matrix size: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        if (rows != cols) {
            System.out.println("Error! Not a square matrix!");
            return;
        }

        Matrix a = new Matrix(rows, cols);
        System.out.println("Enter matrix:");
        a.fillMatrix();

        if (a.getDeterminant() == 0) {
            System.out.println("Matrix has no inverse!");
            return;
        }

        Matrix inverse = a.getInverse();
        inverse.print();
    }

    private void printMenu() {
        System.out.println("1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "0. Exit");
    }
}
