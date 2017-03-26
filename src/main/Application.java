package main;

import main.method.GaussMethod;

public class Application
{

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        /*
          DEBUG
         */
        GaussMethod method = new GaussMethod(
                new double[][] {
                        {2, 5, 3},
                        {2, 8 ,5},
                        {4, 7, 7}
                },
                new double[]
                        {5, 5, 5}
        );
        method.run();
        /*
          DEBUG_END
         */
    }

}
