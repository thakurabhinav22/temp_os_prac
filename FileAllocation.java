import java.util.*;

public class FileAllocation {
    static final int SIZE = 50;
    static boolean[] used = new boolean[SIZE];
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Sequential  2. Indexed  3. Linked  4. Exit");
            System.out.print("Choice: ");
            switch (sc.nextInt()) {
                case 1 -> {
                    System.out.print("Start block and length: ");
                    int s = sc.nextInt(), len = sc.nextInt();
                    if (s < 0 || s + len > SIZE) {
                        System.out.println("Out of range.");
                        break;
                    }
                    boolean ok = true;
                    for (int i = s; i < s + len; i++)
                        if (used[i]) {
                            System.out.println("Block " + i + " taken.");
                            ok = false;
                            break;
                        }
                    if (ok) {
                        for (int i = s; i < s + len; i++)
                            used[i] = true;
                        System.out.println("Allocated: blocks " + s + " to " + (s + len - 1));
                    }
                }
                case 2 -> {
                    System.out.print("Index block: ");
                    int idx = sc.nextInt();
                    if (idx < 0 || idx >= SIZE || used[idx]) {
                        System.out.println("Index block invalid.");
                        break;
                    }
                    System.out.print("Number of data blocks: ");
                    int[] b = new int[sc.nextInt()];
                    System.out.println("Enter block numbers:");
                    boolean ok = true;
                    for (int i = 0; i < b.length; i++) {
                        b[i] = sc.nextInt();
                        if (b[i] < 0 || b[i] >= SIZE || used[b[i]] || b[i] == idx) {
                            System.out.println("Block " + b[i] + " unavailable.");
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        used[idx] = true;
                        for (int x : b)
                            used[x] = true;
                        System.out.println("Allocated. Index " + idx + " -> " + Arrays.toString(b));
                    }
                }
                case 3 -> {
                    System.out.print("Number of blocks: ");
                    int[] b = new int[sc.nextInt()];
                    System.out.println("Enter block numbers in order:");
                    boolean ok = true;
                    for (int i = 0; i < b.length; i++) {
                        b[i] = sc.nextInt();
                        if (b[i] < 0 || b[i] >= SIZE || used[b[i]]) {
                            System.out.println("Block " + b[i] + " unavailable.");
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        StringBuilder sb = new StringBuilder();
                        for (int x : b) {
                            used[x] = true;
                            sb.append(x).append(" -> ");
                        }
                        System.out.println("Allocated: " + sb + "null");
                    }
                }
                default -> {
                    return;
                }
            }
        }
    }
}
