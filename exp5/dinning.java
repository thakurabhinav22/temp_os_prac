public class dinning {

    static Object fork = new Object();

    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {

            final int id = i;

            new Thread(() -> {

                System.out.println("Philosopher " + id + " is thinking");

                synchronized (fork) {

                    System.out.println("Philosopher " + id + " is eating");

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    System.out.println("Philosopher " + id + " finished eating");
                }

            }).start();
        }
    }
}