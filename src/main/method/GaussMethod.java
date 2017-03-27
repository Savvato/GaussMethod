package main.method;

public class GaussMethod
{
    /**
     * Матрица коэффициентов системы уравнений
     */
    private double[][] coefficients;

    /**
     * Массив значений системы уравнений
     */
    private double[] values;

    /**
     * Массив значений аргументов системы уравнений
     */
    private double[] arguments;

    public GaussMethod(double[][] coefficients, double[] values) {
        this.coefficients = coefficients;
        this.values = values;
    }

    public void run() {
        straightRun();
        reversalRun();
    }

    /**
     * Прямой ход алгоритма
     * Приведение к треугольной системе уравнений
     */
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

    /**
     * Обратный ход алгоритма
     * Нахождение аргументов системы уравнений
     */
    private void reversalRun() {
        arguments = new double[coefficients.length];
        for (int k = coefficients.length - 1; k >= 0; k--) {
            double bufferSum = 0;
            for (int i = k; i < coefficients.length; i++) {
                bufferSum += coefficients[k][i] * arguments[i];
            }
            arguments[k] = (values[k] - bufferSum) / coefficients[k][k];
        }
    }

    /**
     * Проверка на наличие на диагонали нулевых элементов
     */
    private void checkDiagonal() {
        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i][i] == 0) {
                rebaseRows(i);
                break;
            }
        }
    }

    /**
     * Перемещение строк
     * @param rowIndex перемещаемая строка
     */
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

    public double[][] getCoefficients() {
        return coefficients;
    }

    public double[] getValues() {
        return values;
    }

    public double[] getArguments() {
        return arguments;
    }
}
