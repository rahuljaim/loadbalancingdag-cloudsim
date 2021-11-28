/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudsim.ext.datacenter;

/**
 *
 * @author manoj_yadav
 */
public class PSO {
     Particle particles[];
    double globalBestPosition[];
    double globalBestFitness;
    double tempBestFitness;
    int PSONum;
    int funDim;
    int tempBestIndex;
    int globalBestIndex;

    public PSO(int PSONum) {
        this.PSONum = PSONum;
        funDim = Function.getDim();
        particles = new Particle[PSONum];
        for (int i = 0; i < PSONum; i++) {
            particles[i] = new Particle();
        }
        globalBestFitness = particles[0].bestFitness;
        globalBestPosition = new double[funDim];
        for (int i = 0; i < funDim; i++) {
            globalBestPosition[i] = particles[0].position[i];
        }

    }

    public double getGlobalBestFitness() {
        return globalBestFitness;
    }

    public double[] getGlobalBestPosition() {
        return globalBestPosition;
    }

    public void search() {
        for (int i = 0; i < PSONum; i++) {
            particles[i].updateFitness();
        }

        tempBestFitness = particles[0].fitness;
        tempBestIndex = 0;
        for (int i = 1; i < PSONum; i++) {
            if (tempBestFitness < particles[i].fitness) {
                tempBestFitness = particles[i].fitness;
                tempBestIndex = i;
            }
        }

        if (tempBestFitness > globalBestFitness) {
            globalBestFitness = tempBestFitness;
            globalBestIndex = tempBestIndex;
            for (int i = 0; i < funDim; i++) {
                globalBestPosition[i] = particles[globalBestIndex].position[i];
            }
        }

        for (int i = 0; i < PSONum; i++) {
            particles[i].updateState(globalBestPosition);
        }
    }
}
