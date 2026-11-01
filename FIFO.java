import java.util.LinkedList;
import java.util.Queue;

public class FIFO {

    public void fifo(String refStr, int framesCount) {
        Queue<Character> q = new LinkedList<>();
        int fault = 0, hit = 0;

        System.out.println("Page\tFrame1\tFrame2\tFrame3\t Status");

        for (int i = 0; i < refStr.length(); i++) {
            if (q.isEmpty()) {
                q.add(refStr.charAt(i));
                System.out.println(refStr.charAt(i) + "\t" + q.peek() + "\t-\t-\t Fault");
                fault++;
            } else {
                if (q.size() < framesCount) {
                    if (q.contains(refStr.charAt(i))) {
                        hit++;
                        Object[] obj = q.toArray();
                        System.out
                                .println(refStr.charAt(i) + "\t" + q.peek() + "\t" + obj[1] + "\t" + obj[2] + "\t Hit");
                    } else {
                        q.add(refStr.charAt(i));
                        fault++;
                        System.out.println(refStr.charAt(i) + "\t" + q.peek() + "\t-\t-\t Fault");
                    }
                } else {
                    if (q.contains(refStr.charAt(i))) {
                        hit++;
                        Object[] obj = q.toArray();
                        System.out
                                .println(refStr.charAt(i) + "\t" + q.peek() + "\t" + obj[1] + "\t" + obj[2] + "\t Hit");
                    } else {
                        fault++;
                        q.poll();
                        q.add(refStr.charAt(i));
                        Object[] obj = q.toArray();
                        System.out
                                .println(refStr.charAt(i) + "\t" + q.peek() + "\t" + obj[1] + "\t" + obj[2] + "\t Fault");

                    }
                }
            }
        }
        System.out.println("Total hit "+hit+"/" + refStr.length());
        System.out.println("Total Fault "+fault+"/" + refStr.length());
    }

    public static void main(String[] args) {
        FIFO obj = new FIFO();
        obj.fifo("123412512345", 3);
    }
}