import java.util.*;

public class RR {

    public void rr(int[] at, int[] bt, int tq) {

        int proc = at.length;
        int remainingProc = proc;
        List<Integer> queue = new ArrayList<>();
        int procPointer = 0;
        int currentProc = 0;

        for (int i = 0; i < proc; i++) {
            queue.add(i);
        }

        System.out.println(queue);

        while (remainingProc > 0) {
            for (int i = 0; i < tq; i++) {
                bt[currentProc] -= 1;
            }
            if (bt[currentProc] != 0) {
                queue.add(currentProc);
                procPointer++;
            } else {
                remainingProc--;
            }
        }

    }

    public static void main(String[] args) {
        RR obj = new RR();
        obj.rr(new int[] { 0, 1, 2 }, new int[] { 10, 5, 8 }, 3);
    }
}
