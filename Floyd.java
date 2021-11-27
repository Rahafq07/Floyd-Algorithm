

import java.util.Scanner;

/**
 * <h1> Compute The Distance Matrix Using Floyd Algorithm </h1>
 * 
 * The program will compute the distance matrix that indicates the length of the shortest paths between every pairs in the graph using Floyd Algorithm and Compute the run duration.
 * 
 * @author Rahaf , sarah , Somayah 
 * @version 8.2
 * @since 27-11-2021
 */

public class Floyd {

    // global variables declaration 
    
    static final int INF = Integer.MAX_VALUE; // infinite number ' ∞ '

    // The given matrix
                          //  A  B    C    D    E   F   G    H    I    J 
    static int matrix [][] = {{0, 10, INF, INF, INF, 5, INF, INF, INF, INF},    // A
                             {INF, 0, 3, INF, 3, INF, INF, INF, INF, INF},     // B
                             {INF, INF, 0, 4, INF, INF, INF, 5, INF, INF},     // C
                             {INF, INF, INF, 0, INF, INF, INF, INF, 4, INF},   // D
                             {INF, INF, 4, INF, 0, INF, 2, INF, INF, INF},     // E
                             {INF, 3, INF, INF, INF, 0, INF, INF, INF, 2},     // F
                             {INF, INF, INF, 7, INF, INF, 0, INF, INF, INF},   // G
                             {INF, INF, INF, 4, INF, INF, INF, 0, 3, INF},     // H
                             {INF, INF, INF, INF, INF, INF, INF, INF, 0, INF}, // I
                             {INF, 6, INF, INF, INF, INF, 8, INF, INF, 0},     // J    
                            };
    
    //matrix length
    static int matrixLen = matrix.length;

    // array of vertices
    static char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

    /**
     * This is the main method which print the start sentence and call Floyd method
     * then print list of vertices to let the user chose the vertex that they want to know the shortest path from it to all vertices 
     * then print it .
     * 
     * @param args is the parameter to the main Method.
     */
    public static void main(String args[]) {


        Scanner input = new Scanner(System.in);

        //start sentence
        System.out.println("------------------- WELCOME TO FLOYD-WARSHALL ALGORITHM -------------------\n\n");
        System.out.println(">> The following matrix shows the shortest path for each iteration:\n");
        
        //call Floyd method
        Floyd();

        //print list of vertices
        System.out.print("-------------------------------------------------------------------------------------------------\n\n"
                       + "Choose the vertex that you want to know the shortest path from it to all vertices ( FROM 0 TO 9 )  \n\n"
                       + "                        0    1    2    3    4    5    6    7    8    9                           \n"
                       + "                        A    B    C    D    E    F    G    H    I    J                           \n\n");

        //declaration the index of chosen vertex 
        int chosen_vertex;

        // Do - While loop to Verify that the selected index is present
        do { 
            
            System.out.print(">> YOUR INDEX IS : ");
            chosen_vertex = input.nextInt();

            System.out.println();//new line

            if (chosen_vertex <= 9 && chosen_vertex >= 0) { //if index between 0 & 9

                for (int i = 0; i < matrixLen; i++) { //for loop to Pass through all the elements of the array
                    
                    //if There is directed path 
                    if (matrix[chosen_vertex][i] != INF) { 
                        System.out.println("    >> The shortest path from " + vertices[chosen_vertex] + " to " + vertices[i] + " is " + matrix[chosen_vertex][i]);
                    } 
                    //if There is no directed path  
                    else { 
                        System.out.println("    >> There is no directed path from " + vertices[chosen_vertex] + " to " + vertices[i]);
                    }
                    
                }
                System.out.println();//new Line
                
            } else { //if index NOT between 0 & 9
                System.out.println("    >> WRONG Selection There Is No Index With This Choice \n"); //Print the ERROR Message
            }
            
            //While the index NOT between 0 & 9
        } while (chosen_vertex > 9 || chosen_vertex < 0);

    }
    
    /**
     * This is Floyd method which present the distance matrix from D(0)-D(N), 
     * where n can be any number, then calculate the time duration.
     */
    public static void Floyd(){

        double startTime = System.currentTimeMillis(); //Start time

        // printing D(0) which is equivalent to the adjacency matrix
        System.out.print("\t\t\t    -------- D(0) -------- \n\n\t");

        // for loop to print verticestices labels 
        for (int c = 0; c < matrixLen; c++) {
            System.out.print(vertices[c] + "\t");
        }

        System.out.println();//new Line
        
        for (int i = 0; i < matrixLen; i++) {
            
            // printing the verticestices labels
            System.out.print(vertices[i] + "\t");
            
            for (int j = 0; j < matrixLen; j++) {
                // Printing the matrix contents
                System.out.print((matrix[i][j] == INF ? "∞" : matrix[i][j]) + (matrixLen - 1 == j ? "\n" : "\t"));
            }
            
        }

        System.out.println();//new Line
        
        
        for (int i = 0; i < matrixLen; i++) {
            
            System.out.print("\n\t\t\t    -------- D(" + (i + 1) + ") -------- \n\n\t");
            
            // for loop to print verticestices labels 
            for (int c = 0; c < matrixLen; c++) {
                System.out.print(vertices[c] + "\t");
            }
            
            System.out.println();//new Line
            
            for (int j = 0; j < matrixLen; j++) {
                // printing the verticestices labels
                System.out.print(vertices[j] + "\t");
                for (int k = 0; k < matrixLen; k++) {
                    
                    if (matrix[j][k] > matrix[j][i] + matrix[i][k] && matrix[j][i] != INF && matrix[i][k] != INF) {
                        //update contents if need
                        matrix[j][k] = matrix[j][i] + matrix[i][k];
                    }
                    
                    //Printing the matrix contents
                    System.out.print((matrix[j][k] == INF ? "∞" : matrix[j][k]) + (matrixLen - 1 == k ? "\n" : "\t"));
                }
            }
            System.out.println();//new Line
        }

        double endTime = System.currentTimeMillis(); //End time
        double totalTime = endTime - startTime; //Total time
        
        //print Running time
        System.out.println("\n>> Running time of Floyd-Warshall algorithm is: " + totalTime + " milli seconds\n");

    }

}
