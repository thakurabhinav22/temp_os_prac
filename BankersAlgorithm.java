import java.util.*;

public class BankersAlgorithm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Processes: ");  int n = sc.nextInt();
        System.out.print("Resources: ");  int m = sc.nextInt();

        int[][] alloc = new int[n][m], max = new int[n][m], need = new int[n][m];
        int[] avail = new int[m];

        System.out.println("Allocation matrix (" + n + "x" + m + "):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                alloc[i][j] = sc.nextInt();

        System.out.println("Max matrix (" + n + "x" + m + "):");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                max[i][j]  = sc.nextInt();
                need[i][j] = max[i][j] - alloc[i][j];
            }

        System.out.println("Available resources:");
        for (int j = 0; j < m; j++) avail[j] = sc.nextInt();

        // Safety check
        boolean[] done = new boolean[n];
        List<String> seq = new ArrayList<>();

        for (int count = 0; count < n; ) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (done[i]) continue;
                boolean ok = true;
                for (int j = 0; j < m; j++)
                    if (need[i][j] > avail[j]) { ok = false; break; }
                if (ok) {
                    for (int j = 0; j < m; j++) avail[j] += alloc[i][j];
                    done[i] = true;
                    seq.add("P" + i);
                    count++;
                    found = true;
                }
            }
            if (!found) break;
        }

        if (seq.size() == n)
            System.out.println("\nSAFE state. Sequence: " + String.join(" -> ", seq));
        else
            System.out.println("\nUNSAFE state (deadlock possible).");
    }
}
