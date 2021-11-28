/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudsim.ext.datacenter;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Dimension;

/**
 *
 * @author Home
 */
public class GA {
    
       bestindival bd = null;
        String[] ipop = new String[10];
        int gernation = 0;
        boolean packFrame = false;
        public GA() {
      this.ipop = inialPops();
     }
     double calculatefitnessvalue(String str) { 
         String str1 = str.substring(0, 18);  
          String str2 = str.substring(18);   
       int b1 = Integer.parseInt(str1, 2);  
           int b2 = Integer.parseInt(str2, 2);  
      
      double x1 = -3.0 + b1 * (12.1 - (-3.0)) / (Math.pow(2, 18) - 1); 
     
      double x2 = 4.1 + b2 * (5.8 - 4.1) / (Math.pow(2, 15) - 1);
           double fitness = 21.5 + x1 * Math.sin(4 * 3.1415926 * x1) + x2
        * Math.sin(20 * 3.1415926 * x2);
           return fitness;
     }
     String inialPop() { 
      String res = "";
      for (int i = 0; i < 33; i++) {
       if (Math.random() > 0.5) {
        res += "0";
       } else {
        res += "1";
       }   }
      return res;
     }
     String[] inialPops() {
      String[] ipop = new String[10];
      for (int i = 0; i < 10; i++) {
       ipop[i] = inialPop();
      }
      return ipop;  }
  void select() {
      double evals[] = new double[10];
      double p[] = new double[10];
      double q[] = new double[10];
      double F = 0;
      for (int i = 0; i < 10; i++) {
       evals[i] = calculatefitnessvalue(ipop[i]);
       if (bd == null) {
        bd = new bestindival();
        bd.fitness = evals[i];
        bd.generations = 0;
        bd.str = ipop[i];
       } else {
        if (evals[i] > bd.fitness)
        {
         bd.fitness = evals[i];
         bd.generations = gernation;
         bd.str = ipop[i];
        }    }
       F = F + evals[i];
   }
      for (int i = 0; i < 10; i++) { 
       p[i] = evals[i] / F;
       if (i == 0)
        q[i] = p[i];
       else {
        q[i] = q[i - 1] + p[i];
       }
      }
      for (int i = 0; i < 10; i++) { double r = Math.random();
       if (r <= q[0]) {
        ipop[i] = ipop[0];    } else {
        for (int j = 1; j < 10; j++) {
         if (r < q[j]) {
          ipop[i] = ipop[j];
          break;
         }
        }
       }
      }  } ;
  void cross() {
      String temp1, temp2;
      for (int i = 0; i < 10; i++) {
       if (Math.random() < 0.25) {
        double r = Math.random();
        int pos = (int) (Math.round(r * 1000)) % 33;
                                                  

        if (pos == 0) {
         pos = 1;
        }
        temp1 = ipop[i].substring(0, pos)
          + ipop[(i + 1) % 10].substring(pos);
        temp2 = ipop[(i + 1) % 10].substring(0, pos)
          + ipop[i].substring(pos);
        ipop[i] = temp1;
        ipop[(i + 1) / 10] = temp2;    }   }
     }
     void mutation() {
          for (int i = 0; i < 4; i++) {
          int num = (int) (Math.random() * 330 + 1);
          int chromosomeNum = (int) (num / 33) + 1; 
          int mutationNum = num - (chromosomeNum - 1) * 33; 
          if (mutationNum == 0)
              mutationNum = 1;
        
          chromosomeNum = chromosomeNum - 1;
          if (chromosomeNum >= 10)
              chromosomeNum = 9;
         
          String temp;
          if (ipop[chromosomeNum].charAt(mutationNum - 1) == '0') {
              if (mutationNum == 1) {
                  temp = "1" + ipop[chromosomeNum].substring(mutationNum);
              } else {
                  if (mutationNum != 33) {
                      temp = ipop[chromosomeNum]
                             .substring(0, mutationNum - 1)
                             + "1"
                             + ipop[chromosomeNum].substring(mutationNum);
                  } else {
                      temp = ipop[chromosomeNum]
                             .substring(0, mutationNum - 1)
                             + "1";
                  }
              }
          } else {
              if (mutationNum == 1) {
                  temp = "0" + ipop[chromosomeNum].substring(mutationNum);
              } else {
                  if (mutationNum != 33) {
                      temp = ipop[chromosomeNum]
                             .substring(0, mutationNum - 1)
                             + "0"
                             + ipop[chromosomeNum].substring(mutationNum);
                  } else {
                      temp = ipop[chromosomeNum]
                             .substring(0, mutationNum - 1)
                             + "1";
                  }
              }
          }
          ipop[chromosomeNum] = temp;
         
      }  }

          void process() {
              for (int i = 0; i < 1000000; i++) {
                  select();
                  cross();
                  mutation();
                  gernation = i;
              }
              System.out.println("" + bd.fitness + "," + bd.generations);
          }
public static void main(String args[]) {
  GA j = new GA();

  j.process();
          }
         class bestindival {

        public int generations;
        public String str;
        public double fitness;
    }
      }

   




