
import java.util.*;

import java.lang.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] mainmenu = 
        {"Pilih menu program di bawah ini!"
        , "F01 - START GAME"
        , "F02 - LIST CARD"
        , "F03 - DISCARD"
        , "F04 - DRAW"
        , "F05 - DECLARE HIJI"
        , "F06 - LIST PLAYER"
        , "F07 - VIEW PLAYER IN TURN"
        , "F08 - HELP"
        , "F10 - VIEW MIDDLE CARD"
        , "EXIT"};
 

        System.out.println("Welcome to the Hiji");
        for (int i=0; i<10; i++){
            System.out.println(mainmenu[i]);
        }


        System.out.print("pilih menu : ");
        String pilihan = sc.next();

        while (!pilihan.equals("EXIT")) {

            if (pilihan.equals("F01")) {
                System.out.println("START GAME");

                System.out.print("Masukkan banyaknya pemain: ");
                int banyakPemain = sc.nextInt();
                while (banyakPemain > 6 || banyakPemain < 2) {
                    System.out.println("Jumlah yang dimasukkan tidak sesuai!");
                    System.out.print("Masukkan banyaknya pemain: ");
                    banyakPemain = sc.nextInt();
                }

                String[] Pemain = new String[banyakPemain];
                String str = null;

                System.out.println("Masukkan nama pemain: ");
                for (int i = 0; i < banyakPemain; i++) {
                    Pemain[i] = sc.next();
                }

                Game1 HIJI = new Game1(Pemain);

                HIJI.start(HIJI);
                // System.out.println("yang bermain sekarang:" + HIJI.getCurrentPlayer() +
                // "\n");

                // pilihan = sc.next();
                System.out.print("pilih menu: ");
                pilihan = sc.next();

                while (!pilihan.equals("EXIT")) {
                    if (pilihan.equals("F02")) {
                        System.out.println("List Cards");
                        HIJI.ListCards(HIJI.getCurrentPlayer()); // asumsi, bisa ngejalanin F02 kalau sudah dilaksanakan
                                                                 // F01
                        System.out.print("pilih menu: ");
                        pilihan = sc.next();
                    } else if (pilihan.equals("F03")) {
                        HijiCard.Color colorChoosen = HijiCard.Color.Red;
                        System.out.println("Discard");
                        System.out.println("silakan pilih nomor kartu di list kartu: ");
                        int pilihanKartu = sc.nextInt();

                        HijiCard cardChoosen = HIJI.getPlayerCard(HIJI.getCurrentPlayer(), pilihanKartu);

                        if (cardChoosen.getColors() == HijiCard.Color.Wild) {
                            System.out.println(
                                    "silakan pilih warna: \n 1. Red \n 2. Blue \n 3. Yellow \n 4. Green \n pilih dengan menginput warna");

                            String pilihanWarna = sc.next();
                            if (pilihanWarna.equals("Red")) {
                                colorChoosen = HijiCard.Color.Red;
                            } else if (pilihanWarna.equals("Blue")) {
                                colorChoosen = HijiCard.Color.Blue;
                            } else if (pilihanWarna.equals("Green")) {
                                colorChoosen = HijiCard.Color.Green;
                            } else if (pilihanWarna.equals("Yellow")) {
                                colorChoosen = HijiCard.Color.Yellow;
                            }
                        }

                        try {
                            HIJI.submitPlayerCard(HIJI.getCurrentPlayer(), cardChoosen, colorChoosen);
                            System.out.println("kartu tersubmit");
                        } catch (InvalidCardSubmissionException e) {
                            System.out.println(e);
                        }

                        System.out.print("silakan pilih menu: ");
                        pilihan = sc.next();
                    } else if (pilihan.equals("F04")) {

                        System.out.println("Draw");

                        HIJI.submitDraw(HIJI.getCurrentPlayer());
                        System.out.println("kartu yang anda dapatkan adalah " + HIJI.getPlayerCard(
                                HIJI.getCurrentPlayer(), (HIJI.getPlayerHandSize(HIJI.getCurrentPlayer()) - 1)));
                        System.out.println("list kartu anda sekarang: ");
                        HIJI.ListCards(HIJI.getCurrentPlayer());
                        System.out.println("apakah ingin mensubmit kartu : ya / tidak");
                        String submission = sc.next();
                        if (submission.equals("ya")) {
                            HijiCard.Color colorChoosen = HijiCard.Color.Red; // warna default
                            System.out.println("Discard");
                            System.out.println("silakan pilih nomor kartu di list kartu: ");
                            int pilihanKartu = sc.nextInt();

                            HijiCard cardChoosen = HIJI.getPlayerCard(HIJI.getCurrentPlayer(), pilihanKartu);

                            if (cardChoosen.getColors() == HijiCard.Color.Wild) {
                                System.out.println(
                                        "silakan pilih warna: \n 1. Red \n 2. Blue \n 3. Yellow \n 4. Green \n pilih dengan menginput warna");

                                String pilihanWarna = sc.next();
                                if (pilihanWarna.equals("Red")) {
                                    colorChoosen = HijiCard.Color.Red;
                                } else if (pilihanWarna.equals("Blue")) {
                                    colorChoosen = HijiCard.Color.Blue;
                                } else if (pilihanWarna.equals("Green")) {
                                    colorChoosen = HijiCard.Color.Green;
                                } else if (pilihanWarna.equals("Yellow")) {
                                    colorChoosen = HijiCard.Color.Yellow;
                                }
                            }

                            try {
                                HIJI.submitPlayerCard(HIJI.getCurrentPlayer(), cardChoosen, colorChoosen);
                                System.out.println("kartu tersubmit");
                            } catch (InvalidCardSubmissionException e) {
                                System.out.println(e);
                                System.out.println("kartu anda tidak valid. Game akan dilanjutkan");
                                HIJI.lanjutMain();
                                System.out.print("silakan pilih menu: ");
                                pilihan = sc.next();

                            }

                        } else if (submission.equals("tidak")) {
                            HIJI.lanjutMain();
                        } else {
                            System.out.println("input antara ya / tidak, silakan input ulang.");
                            submission = sc.next();
                        }

                        System.out.print("silakan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F06")) {
                        System.out.println("List Players");
                        HIJI.ListPlayers();
                        System.out.print("silakan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F07")) {
                        System.out.println("View Player in Turn");
                        HIJI.ViewPlayerInTurn();
                        System.out.print("silakan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F08")) {
                        System.out.println("Help");
                        System.out.println("- Game ini dapat dimainkan oleh 2 hingga 6 orang pemain");
                        System.out.println("- Setiap permainan dimulai (command F01 di-input), masing-masing pemain akan mendapatkan 7 buah kartu random dan akan ada satu kartu angka random untuk dijadikan sebagai kartu tengah");
                        System.out.println("- Urutan giliran pemain akan diacak sekali di awal permainan");
                        System.out.println("- Pada setiap giliran, pemain boleh mengeluarkan satu atau lebih kartu yang dapat dimainkan (terdeteksi valid) pada giliran tersebut");
                        System.out.println("- Apabila pemain tidak mengeluarkan kartu, ia wajib mengambil satu kartu dari deck");
                        System.out.println("- Apabila kartu yang baru diambil valid terhadap kartu tengah, pemain berhak memilih untuk mengeluarkan atau tidak mengeluarkan kartunya");
                        System.out.println("- Apabila kartu tidak valid terhadap kartu tengah, giliran pemain tersebut selesai tanpa mengeluarkan kartu");
                        System.out.println("- Kartu selain yang berjenis angka, memiliki powernya masing-masing");
                        System.out.println("- Apabila pemain memiliki sisa satu kartu, maka pemain harus melakukan declare HIJI dalam waktu 3 detik. Apabila sudaa melebihi batas waktu, pemain wajib mengambil dua kartu dari deck");
                        System.out.println("- Pemenang diambil dari pemain yang terlebih dahulu menghabiskan kartu yang sedang dipegang sudah habis dan permainan selesai");
                        System.out.println("Untuk kartu Draw 2, pemain selanjutnya akan mengambil kartu sebanyak dua kali jumlah kartu yang dikeluarkan");
                        System.out.println("Untuk kartu Draw 4, pemain selanjutnya akan mengambil kartu sebanyak empat kali jumlah kartu yang dikeluarkan");
                        System.out.println("Untuk kartu Skip, jumlah pemain yang dilewati sesuai dengan jumlah kartu Skip yang dikeluarkan");
                        System.out.println("Untuk kartu Reverse, urutan akan membolak-balik sesuai jumlah kartu. Jika kartu reverse yang digunakan berjumlah genap, maka urutan pemain tidak berubah");
                        System.out.println("Untuk kartu Wildcard, pemain tetap hanya dapat memilih satu warna");
                        System.out.print("silakan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F01")) {
                        System.out.print("Game sedang dijalankan, silakan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F10")) {
                        System.out.print("kartu yang sedang dimainkan : ");
                        System.out.println(HIJI.getTopCard());
                        System.out.print("silakan pilih menu: ");
                        pilihan = sc.next();

                        } else {
                        System.out.println("input tidak sesuai, silakan pilih menu :");
                        pilihan = sc.next();
                        } // else
                        }
                        System.out.println("game terhenti");
                        System.exit(0);


            } else {
                System.out.println("game belum dijalankan, silakan jalankan game terlebih dahulu dengan menginput F01");
                pilihan = sc.next();
            }
        }
        System.exit(0);
    }
}
