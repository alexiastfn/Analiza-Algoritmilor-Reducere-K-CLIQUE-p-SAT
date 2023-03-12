import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        // File containing the input
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);

        // Read the number of nodes from the first line
        int numNodes = sc.nextInt();
      //  System.out.println("Number of nodes: " + numNodes);
        int k = sc.nextInt();
      //  System.out.println("k: " + k);
        sc.nextLine();

        // Initialize the adjacency matrix with zeroes
        int[][] adjMatrix = new int[numNodes + 1][numNodes + 1];

        // Read the input file and fill in the adjacency matrix
        for (int i = 1; i <= numNodes; i++) {
            String[] connections = sc.nextLine().split(" ");
            for (String s : connections) {
                if(s.equals("")){
                    continue;
                }
                int j = Integer.parseInt(s);
                adjMatrix[i][j] = 1;
                adjMatrix[j][i] = 1;
            }
        }

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        ArrayList<String> allLines = new ArrayList<String>();
        int increment = 1;

        for(int i= 1; i <= k; i++){
            for (int j = 1; j <= numNodes; j++){
                hashMap.put("x" + i + "_" + j, increment);
                increment++;
            }
        }

        // primul punctulet

        for(int i = 1; i <= k; i++) {
            String line = "";
            for (int v = 1; v <= numNodes; v++) {
                line += hashMap.get("x" + i + "_" + v) + " ";
            }
            line += "0";
            allLines.add(line);
        }

        // al doilea punctulet:

       for(int i = 1; i <= k;  i++){
           for(int j = i + 1; j <= k; j++){
                String line = "";
               for(int v = 1; v <= numNodes; v++){
                   allLines.add("-" + hashMap.get("x" + i + "_" + v) + " -" + hashMap.get("x" + j + "_" + v) + " 0");
               }
           }
       }

       // al treilea punctulet:

        for(int i = 1; i <= k;  i++){
            for(int j = i + 1; j <= k; j++){
                String line = "";
                for(int v = 1; v <= numNodes; v++){
                    for(int u = 1; u <= numNodes; u++){
                        if(adjMatrix[v][u] == 1){
                            continue;
                        }
                            allLines.add("-" + hashMap.get("x" + i + "_" + v) + " -" + hashMap.get("x" + j + "_" + u) + " 0");

                    }
                }
            }
        }

        // output-ul:

        File outputFile = new File(args[1]);
        FileWriter fw = new FileWriter(outputFile);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("p cnf " + hashMap.size() + " " + allLines.size());
        bw.newLine();
        for (String s : allLines) {
            bw.write(s);
            bw.newLine();
        }
        bw.close();

    }


}
