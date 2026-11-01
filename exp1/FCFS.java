import java.util.Arrays;

public class FCFS {

    public void fcfs(int[] at, int[] bt) {
        int proc = at.length;
        double avgWT = 0, avgTAT = 0;


        int[] ct = new int[proc];
        int[] tat = new int[proc];
        int[] wt = new int[proc];

        ct[0] = at[0] + bt[0];

        for (int i = 1; i < proc ; i++) {
            ct[i] = ct[i-1] + bt[i];
        }

        for(int i = 0 ; i < proc; i++){
            tat[i] = ct[i] - at[i];
            avgTAT += tat[i];
        }

        for(int i = 0; i < proc; i++){
            wt[i] = tat[i] - bt[i];
            avgWT += wt[i];
        }

        avgTAT /= proc;
        avgWT /= proc;

        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");

        for(int i = 0 ; i < proc; i++){
            System.out.println("P"+(i+1)+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]);
        }
        System.out.println("\nAverage TurnAround Time: "+avgTAT);
        System.out.println("Average Waiting Time: "+avgWT);

    }

    public static void main(String[] args) {

        FCFS obj = new FCFS();
        obj.fcfs(new int[] { 0,1,2,3 }, new int[] { 5,3,8,6});
    }
}