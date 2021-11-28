package cloudsim.ext.datacenter;

/**
 *
 * @author Home
 */
class Function {

    private double asistFun(double x) {
        double abs = (x >= 0 ? x : -x);
        double d = Math.sin(Math.sqrt(abs)) * (-x);
        return d;
    }

    private static final int dim = 2;

    public static int getDim() {
        return dim;
    }

    public double cumputeFitness(double position[]) {
        double sum = 0;
        for (int i = 0; i < dim; i++) {
            sum += asistFun(position[i]);
        }
        return sum;
    }

}

class Particle {

    static double Vmax = 105;
    static final double weight = 0.8;
    static final double c1 = 2.0;
    static final double c2 = 2.0;
    double velocity[];
    double position[];
    double bestPosition[];
    double fitness;
    double bestFitness;
    int funDim;

    public Particle() {
        funDim = Function.getDim();
        velocity = new double[funDim];
        position = new double[funDim];
        bestPosition = new double[funDim];
        for (int i = 0; i < funDim; i++) {
            velocity[i] = Math.random();
            position[i] = bestPosition[i] = Math.random();
        }
        Function fun = new Function();
        fitness = bestFitness = fun.cumputeFitness(position);

    }

    void updateFitness() {
        Function fun = new Function();
        fitness = fun.cumputeFitness(position);
        if (fitness > bestFitness) {
            bestFitness = fitness;
            for (int i = 0; i < funDim; i++) {
                bestPosition[i] = position[i];
            }
        }
    }

    void updateState(double globalBestPosition[]) {
        for (int i = 0; i < funDim; i++) {
            double rands = Math.random();
            velocity[i] = weight * velocity[i] + c1 * rands * (bestPosition[i] - position[i]) + c2 * rands * (globalBestPosition[i] - position[i]);
            if (velocity[i] > Vmax) {
                velocity[i] = Vmax;
            }
            if (velocity[i] < -Vmax) {
                velocity[i] = -Vmax;
            }
            position[i] += velocity[i];
        }
    }
}


public class psomain {

    public static void main(String[] args) {
        double globalBestPosition[];

        int PSONum = 100;
        int searchTime = 1000;
        PSO pso = new PSO(PSONum);
        int funDim = Function.getDim();
        System.out.println("-------------------------------------------------");
        for (int i = 1; i <= searchTime; i++) {
            pso.search();
            if (i % 100 == 0) {
                globalBestPosition = pso.getGlobalBestPosition();
                System.out.println("" + i + ",");
                System.out.println("" + pso.getGlobalBestFitness());
                System.out.println("");
                System.out.print("[");
                for (int j = 0; j < funDim; j++) {
                    if (j < funDim - 1) {
                        System.out.println(globalBestPosition[j] + ",");
                    }
                    if (j == funDim - 1) {
                        System.out.println(globalBestPosition[j]);
                    }

                }
                System.out.println("]");
                System.out.println("-------------------------------------------------");
            }
        }
    }
}
