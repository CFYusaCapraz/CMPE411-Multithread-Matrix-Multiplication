package com.cyberfreak.matrix_multiplication;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class App {
    private int size;
    private int matrix1[][];
    private int matrix2[][];
    private int result[][];

    public App(int size) {
        this.size = size;
        this.matrix1 = new int[size][size];
        this.matrix2 = new int[size][size];
        this.result = new int[size][size];
    }

    public void generateRandom() {
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix1[i][j] = rand.nextInt(size);
                matrix2[i][j] = rand.nextInt(size);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];
        App app = new App(100);
        app.generateRandom();
        for (int i = 0; i < 10; i++) {
            int s = i * 10;
            int e = s + 10;
            threads[i] = new MatrixThread(s, e, app.matrix1, app.matrix2, app.result);
            threads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            threads[i].join();
        }

        String outFileName;
        try {
            outFileName = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            outFileName = "result.txt";
        }
        File f = new File(outFileName);
        try (FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw)) {
            for (int[] row : app.result) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                    pw.print(cell + " ");
                }
                pw.println();
                System.out.println();
            }
            pw.close();
            fw.close();
        }
    }
}
