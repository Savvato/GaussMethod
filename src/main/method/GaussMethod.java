package main.method;

public class GaussMethod
{
    public double[][] coefficients;
    public double[] values;

    public double x1;
    public double x2;
    public double y;

    public GaussMethod(double[][] coefficients, double[] values) {
        this.coefficients = coefficients;
        this.values = values;
    }

    public void run() {
        for (int k = 0; k < coefficients.length - 1; k++) {
            checkDiagonal();
            for (int i = k + 1; i < coefficients.length; i++) {
                double m = coefficients[i][k] / coefficients[k][k];
                coefficients[i][k] = 0;

                for (int j = k + 1; j < coefficients.length; j++) {
                    coefficients[i][j] -= m * coefficients[k][j];
                }
                values[i] -= m * values[k];
            }
        }
        print();
    }

    private void print() {
        for (double[] rows : coefficients) {
            for (double element : rows) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private void checkDiagonal() {
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i][i] == 0) {
                rebaseRows(i);
                break;
            }
        }


    }

    private void rebaseRows(int rowIndex) {
        int nextRowIndex;
        if (rowIndex < coefficients.length - 1) {
            nextRowIndex = rowIndex + 1;
        }
        else {
            nextRowIndex = 0;
        }
        for (int columnIndex = 0; columnIndex < coefficients[rowIndex].length; columnIndex++) {
            double buffer = coefficients[rowIndex][columnIndex];
            coefficients[rowIndex][columnIndex] = coefficients[nextRowIndex][columnIndex];
            coefficients[nextRowIndex][columnIndex] = buffer;

            buffer = values[rowIndex];
            values[rowIndex] = values[nextRowIndex];
            values[nextRowIndex] = buffer;
        }
    }

}
