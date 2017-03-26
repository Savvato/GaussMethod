package main.method;

public class GaussMethod
{
    private double[][] coefficients;
    private double[] values;

    private double[] arguments;

    public GaussMethod(double[][] coefficients, double[] values) {
        this.coefficients = coefficients;
        this.values = values;
    }

    public void run() {
        straightRun();
        reversalRun();
        printSystem();
    }

    private void straightRun() {
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
    }

    private void reversalRun() {
        arguments = new double[coefficients.length];
        for (int k = coefficients.length - 1; k >= 0; k--) {
            double buferSum = 0;
            for (int i = k; i < coefficients.length; i++) {
                buferSum += coefficients[k][i] * arguments[i];
            }
            arguments[k] = (values[k] - buferSum) / coefficients[k][k];
        }
    }

    private void print() {
        for (double[] rows : coefficients) {
            for (double element : rows) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    private void printSystem() {
        for (int rowIndex = 0; rowIndex < coefficients.length; rowIndex++) {
            String equation = "";
            for (int columnIndex = 0; columnIndex < coefficients[rowIndex].length; columnIndex++) {
                equation += " + " + coefficients[rowIndex][columnIndex] + "*" + arguments[columnIndex];
            }
            equation += " = " + values[rowIndex];
            System.out.println(equation);
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
