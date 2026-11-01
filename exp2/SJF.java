import java.util.Arrays;

public class SJF {

    public void sjf(int[] at, int[] bt) {
        int[] proc = new int[at.length];
        int[] ct = new int[at.length];
        int[] tat = new int[at.length];
        int[] wt = new int[at.length];

        double AvgTAT = 0, AvgWT = 0;

        for (int i = 0; i < proc.length; i++) {
            proc[i] = (i + 1);
        }

        for (int i = 0; i < bt.length; i++) {
            for (int j = i + 1; j < bt.length; j++) {
                if (bt[i] > bt[j]) {
                    int bttemp = bt[j];
                    bt[j] = bt[i];
                    bt[i] = bttemp;

                    int tempat = at[j];
                    at[j] = at[i];
                    at[i] = tempat;

                    int tempProc = proc[j];
                    proc[j] = proc[i];
                    proc[i] = tempProc;

                }
            }
        }

        ct[0] = at[0] + bt[0];

        for (int i = 1; i < at.length; i++) {
            ct[i] = ct[i - 1] + bt[i];
        }

        for (int i = 0; i < at.length; i++) {
            tat[i] = ct[i] - at[i];
            AvgTAT += tat[i];
        }

        for (int i = 0; i < at.length; i++) {
            wt[i] = tat[i] - bt[i];
            AvgWT += wt[i];
        }

        AvgTAT /= at.length;
        AvgWT /= at.length;

        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");

        for (int i = 0; i < at.length; i++) {
            System.out
                    .println("P" + (proc[i]) + "\t" + at[i] + "\t" + bt[i] + "\t" + ct[i] + "\t" + tat[i] + "\t" + wt[i]);
        }
        System.out.println("\nAverage TurnAround Time: " + AvgTAT);
        System.out.println("Average Waiting Time: " + AvgWT);

    }

    public static void main(String[] args) {
        SJF obj = new SJF();
        obj.sjf(new int[] { 0, 0, 0, 0 }, new int[] { 6, 2, 8, 3 });
    }
}
