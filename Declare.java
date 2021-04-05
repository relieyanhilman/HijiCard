import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Declare {
    static volatile boolean isTimeCopleted = true;

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                if (isTimeCopleted) {
                    System.out.println("Waktu kamu habis, kamu akan mendapat tambahan 2 kartu!");
                    // tambah kartu
                }
            }
        };
        timer.schedule(task, 3000);

        System.out.println("Ketik HIJI dalam 3 detik!");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        isTimeCopleted = false;


        System.out.println("Berhasil!");
    }
}