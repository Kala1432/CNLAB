package BFA;


import java.util.Scanner;

public class BellmanFordAlgo {
    private int D[];
    private int num_ver;
    public static final int MAX_VALUE = 999;

    // Constructor
    public BellmanFordAlgo(int n) {
        this.num_ver = n;
        D = new int[n + 1];
    }

    public void shortest(int s, int A[][]) {
        // Initialize distances
        for (int i = 1; i <= num_ver; i++) {
            D[i] = MAX_VALUE;
        }
        D[s] = 0;

        // Relax edges |V|-1 times
        for (int k = 1; k <= num_ver - 1; k++) {
            for (int i = 1; i <= num_ver; i++) {
                for (int j = 1; j <= num_ver; j++) {
                    if (A[i][j] != MAX_VALUE) {
                        if (D[i] != MAX_VALUE && D[j] > D[i] + A[i][j]) {
                            D[j] = D[i] + A[i][j];
                        }
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (int i = 1; i <= num_ver; i++) {
            for (int j = 1; j <= num_ver; j++) {
                if (A[i][j] != MAX_VALUE) {
                    if (D[i] != MAX_VALUE && D[j] > D[i] + A[i][j]) {
                        System.out.println("The Graph contains a negative edge cycle");
                        return;
                    }
                }
            }
        }

        // Print results
        for (int i = 1; i <= num_ver; i++) {
            System.out.println("Distance of source " + s + " to " + i + " is " + D[i]);
        }
    }

    public static void main(String[] args) {
        int n, s;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        n = sc.nextInt();

        int A[][] = new int[n + 1][n + 1];
        System.out.println("Enter the Weighted matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                A[i][j] = sc.nextInt();
                if (i == j) {
                    A[i][j] = 0;
                    continue;
                }
                if (A[i][j] == 0) {
                    A[i][j] = MAX_VALUE;
                }
            }
        }

        System.out.println("Enter the source vertex:");
        s = sc.nextInt();

        BellmanFordAlgo b = new BellmanFordAlgo(n);
        b.shortest(s, A);

        sc.close();
    }
}

