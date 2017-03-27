package main;

import main.method.GaussMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Application
{
    private static final int NUMBER_OF_ARGUMENTS = 3;

    private double[][] coefficients = new double[NUMBER_OF_ARGUMENTS][NUMBER_OF_ARGUMENTS];
    private double[] values = new double[NUMBER_OF_ARGUMENTS];
    private double[] arguments;

    private GaussMethod method;

    public static void main(String[] args) throws IOException {
        Application app = new Application();
        app.run();
    }

    public void run() throws IOException {
        read();
        method = new GaussMethod(coefficients, values);
        method.run();
        arguments = method.getArguments();
        writeArguments();
        writeSystem();
    }

    private void read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintStream writer = System.out;
        writer.println("==============Enter coefficients==============");
        for (int i = 0; i < coefficients.length; i++) {
            for (int j = 0; j < coefficients[i].length; j++) {
                writer.print("a" + Integer.toString(i + 1) + Integer.toString(j + 1) + " = ");
                coefficients[i][j] = Double.parseDouble(reader.readLine());
                writer.println();
            }
        }
        writer.println("============================");
        writer.println("==============Enter values==============");
        for (int i = 0; i < values.length; i++) {
            writer.print("b" + Integer.toString(i + 1) + " = ");
            values[i] = Double.parseDouble(reader.readLine());
            writer.println();
        }
    }

    /**
     * Вывод аргументов уравнения
     */
    private void writeArguments() {
        PrintStream writer = System.out;
        writer.println("==============Writing arguments==============");
        for (int i = 0; i < arguments.length; i++) {
            writer.println("x" + Integer.toString(i + 1) + " = " + arguments[i]);
        }
        writer.println("============================");
    }

    /**
     * Вывод решенной системы уравнений
     */
    private void writeSystem() {
        PrintStream writer = System.out;
        writer.println("==============System of equations==============");
        for (int i = 0; i < coefficients.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < coefficients[i].length; j++) {
                stringBuilder.append(" + ").append(coefficients[i][j]).append("*").append(arguments[j]);
            }
            stringBuilder.append(" = ").append(values[i]);
            writer.println(stringBuilder.toString());
        }
    }
}
