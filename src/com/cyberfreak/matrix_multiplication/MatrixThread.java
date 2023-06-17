package com.cyberfreak.matrix_multiplication;

public class MatrixThread extends Thread{
    private final int start, end;
    private final int[][] array1, array2;
    private int[][] result;

    public MatrixThread(int start, int end, int[][] array1, int[][] array2, int[][] result) {
        this.start = start;
        this.end = end;
        this.array1 = array1;
        this.array2 = array2;
        this.result = result;
    }

    private int cellCalculate(int r, int c) {
        int tmp = 0;
        int columns = array2[0].length;
        for (int i = 0; i < columns; i++) {
            tmp += array1[r][i] * array2[i][c];
        }
        return tmp;
    }

    @Override
    public void run() {
        int columns = array2[0].length;
        for (int i = start; i < end; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = cellCalculate(i, j);
            }
        }
    }
}
