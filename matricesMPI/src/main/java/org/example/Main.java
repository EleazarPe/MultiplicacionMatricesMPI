import mpi.MPI;
import mpi.MPIException;

public class Main {
    public static void main(String[] args)  throws MPIException {
        boolean secuencialOpt = true;
        final int N = 200;
        int[][] A = new int[N][N];
        int[][] B = new int[N][N];
        int[][] C = new int[N][N];
        if (secuencialOpt) {
            System.out.println("EJECUCION SECUENCIAL");
            System.out.println("Matriz A:");
            iniciar(A);
            System.out.println();
            System.out.println("Matriz B:");
            iniciar(B);
            long startTime = System.nanoTime();
            secuencial(A,B,C);
            long endTime = System.nanoTime();
            long tiempoFinal = endTime - startTime;
            System.out.println("Matriz C:");
            imprimir(C);
            System.out.println("Tiempo de duracion: "+tiempoFinal+" ns");
        }else {
            MPI.Init(args);

            int rank = MPI.COMM_WORLD.Rank();
            int size = MPI.COMM_WORLD.Size();
            long startTime = 0;
            if (rank == 0) {
                System.out.println("EJECUCION PARALELA");
                System.out.println("Matriz A:");
                iniciar(A);
                System.out.println();
                System.out.println("Matriz B:");
                iniciar(B);
            }
            MPI.COMM_WORLD.Bcast(A, 0, N, MPI.OBJECT, 0);
            MPI.COMM_WORLD.Bcast(B, 0, N, MPI.OBJECT, 0);

            int divisorProceso = N / size;
            int[][] localC = new int[divisorProceso][N];
            if (rank == 0) {
                startTime = System.nanoTime();
            }
            for (int i = 0; i < divisorProceso; i++) {
                for (int j = 0; j < N; j++) {
                    localC[i][j] = 0;
                    for (int k = 0; k < N; k++) {
                        localC[i][j] += A[i + rank * divisorProceso][k] * B[k][j];
                    }
                }
            }

            MPI.COMM_WORLD.Gather(localC, 0, divisorProceso, MPI.OBJECT, C, 0, divisorProceso, MPI.OBJECT, 0);

            if (rank == 0) {
                long endTime = System.nanoTime();
                long tiempoFinal = endTime - startTime;
                System.out.println("Matriz C:");
                imprimir(C);
                System.out.println("Tiempo de duracion: "+tiempoFinal+" ns");
            }

            MPI.Finalize();
        }
    }
    private static void iniciar(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = (int) (Math.random() * 10);
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("\n");
        }
    }
    private static void secuencial(int[][] A, int[][] B, int[][] C) {
        int N = A.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                C[i][j] = 0;
                for (int k = 0; k < N; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
    }
    private static void imprimir(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}