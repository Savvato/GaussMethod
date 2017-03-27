package main;

import main.method.GaussMethod;

public class Application
{
    private double[][] coefficients;
    private double[] values;

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        GaussMethod method = new GaussMethod(coefficients, values);
        method.run();
    }

}
