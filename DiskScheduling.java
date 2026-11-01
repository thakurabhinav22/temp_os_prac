import java.util.Arrays;
import java.util.Scanner;

public class DiskScheduling {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of requests: ");
        int n = sc.nextInt();
        int[] req = new int[n];
        System.out.println("Enter requests:");
        for (int i = 0; i < n; i++)
            req[i] = sc.nextInt();
        System.out.print("Initial head position: ");
        int head = sc.nextInt();
        System.out.print("Disk size: ");
        int size = sc.nextInt();
        System.out.print("Direction (1=high, 0=low): ");
        int dir = sc.nextInt();

        // Split into lo[] and up[] relative to head
        int lc = 0, uc = 0;
        for (int r : req)
            if (r < head)
                lc++;
            else
                uc++;
        int[] lo = new int[lc], up = new int[uc];
        lc = uc = 0;
        for (int r : req)
            if (r < head)
                lo[lc++] = r;
            else
                up[uc++] = r;
        Arrays.sort(lo);
        Arrays.sort(up);

        // FCFS
        show("FCFS", req, n, head);

        // SSTF
        int[] sstf = new int[n];
        boolean[] done = new boolean[n];
        int cur = head;
        for (int k = 0; k < n; k++) {
            int best = -1;
            for (int i = 0; i < n; i++)
                if (!done[i] && (best == -1 || Math.abs(req[i] - cur) < Math.abs(req[best] - cur)))
                    best = i;
            done[best] = true;
            cur = req[best];
            sstf[k] = cur;
        }
        show("SSTF", sstf, n, head);

        int[] rlo = rev(lo), rup = rev(up);

        if (dir == 1) {
            show("SCAN", join(up, new int[] { size - 1 }, rev(lo)), head);
            show("C-SCAN", join(up, new int[] { size - 1, 0 }, lo), head);
            show("LOOK", join(up, rlo), head);
            show("C-LOOK", join(up, lo), head);
        } else {
            show("SCAN", join(rlo, new int[] { 0 }, up), head);
            show("C-SCAN", join(rlo, new int[] { 0, size - 1 }, rup), head);
            show("LOOK", join(rlo, up), head);
            show("C-LOOK", join(rlo, rup), head);
        }
    }

    static void show(String name, int[] path, int len, int head) {
        int cur = head, total = 0;
        StringBuilder sb = new StringBuilder("" + head);
        for (int i = 0; i < len; i++) {
            total += Math.abs(path[i] - cur);
            cur = path[i];
            sb.append(" -> ").append(path[i]);
        }
        System.out.println("\n" + name + ": " + sb + " | Movement = " + total);
    }

    // Overload for variable-length path (full array)
    static void show(String name, int[] path, int head) {
        show(name, path, path.length, head);
    }

    static int[] rev(int[] a) {
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i++)
            r[i] = a[a.length - 1 - i];
        return r;
    }

    static int[] join(int[]... parts) {
        int total = 0;
        for (int[] p : parts)
            total += p.length;
        int[] r = new int[total], idx = { 0 };
        for (int[] p : parts) {
            System.arraycopy(p, 0, r, idx[0], p.length);
            idx[0] += p.length;
        }
        return r;
    }
}
