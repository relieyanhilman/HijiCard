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
 
        GenericPrint gp = new GenericPrint();
        System.out.println("Selamat Datang di Dunia Hiji!!!");
        for (int i=0; i<10; i++){
            System.out.println(mainmenu[i]);
        }


        System.out.print("Pilih menu : ");
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
                        HIJI.ListCards(HIJI.getCurrentPlayer()); // asumsi, bisa menjalankan F02 kalau sudah dilaksanakan
                                                                 // F01
                        System.out.print("pilih menu: ");
                        pilihan = sc.next();
                    } else if (pilihan.equals("F03")) {
                        HijiCard.Color colorChoosen = HijiCard.Color.Red;
                        System.out.println("Discard");
                        System.out.println("Silahkan pilih nomor kartu di list kartu: ");
                        int pilihanKartu = sc.nextInt();

                        HijiCard cardChoosen = HIJI.getPlayerCard(HIJI.getCurrentPlayer(), pilihanKartu - 1);

                        if (cardChoosen.getColors() == HijiCard.Color.Wild) {
                            System.out.println(
                                    "Silahkan pilih warna: \n 1. Red \n 2. Blue \n 3. Yellow \n 4. Green \n pilih dengan menginput warna");

                            String pilihanWarna = sc.next();
                            if (pilihanWarna.equals("Red")) {
                                colorChoosen = HijiCard.Color.Red;
                            } else if (pilihanWarna.equals("Blue")) {
                                colorChoosen = HijiCard.Color.Blue;
                            } else if (pilihanWarna.equals("Green")) {
                                colorChoosen = HijiCard.Color.Green;
                            } else if (pilihanWarna.equals("Yellow")) {
                                colorChoosen = HijiCard.Color.Yellow;
                            } else {
                                System.out.println("Input tidak sesuai, silahkan pilih warna yang sesuai: ");
                                pilihanWarna = sc.next();
                            }}

                        try   {
                            HIJI.submitPlayerCard(HIJI.getCurrentPlayer(), cardChoosen, colorChoosen);
                            System.out.println("Kartu tersubmit");}
                        catch (InvalidCardSubmissionException e){
                                System.out.println(e);
                                System.out.println("Kartu kamu tidak valid. Game akan dilanjutkan");
                                HIJI.lanjutMain();
                                System.out.print("Silahkan pilih menu: ");
                                pilihan = sc.next();
                        }   

                        System.out.print("Silahkan pilih menu: ");
                        pilihan = sc.next();
                        
                    } else if (pilihan.equals("F04")) {

                        System.out.println("Draw");

                        HIJI.submitDraw(HIJI.getCurrentPlayer());
                        System.out.println("Kartu yang kamu dapatkan adalah " + HIJI.getPlayerCard(
                                HIJI.getCurrentPlayer(), (HIJI.getPlayerHandSize(HIJI.getCurrentPlayer()) - 1)));
                        System.out.println("List kartu kamu sekarang: ");
                        HIJI.ListCards(HIJI.getCurrentPlayer());
                        System.out.println("Apakah ingin mensubmit kartu : ya / tidak");
                        String submission = sc.next();
                        if (submission.equals("Ya")) {
                            HijiCard.Color colorChoosen = HijiCard.Color.Red; // warna default
                            System.out.println("Discard");
                            System.out.println("Silahkan pilih nomor kartu di list kartu: ");
                            int pilihanKartu = sc.nextInt();
                            
                            HijiCard cardChoosen = HIJI.getPlayerCard(HIJI.getCurrentPlayer(), pilihanKartu - 1 );
                            
                            
                            if (cardChoosen.getColors() == HijiCard.Color.Wild) {
                                System.out.println(
                                        "Silahkan pilih warna: \n 1. Red \n 2. Blue \n 3. Yellow \n 4. Green \n pilih dengan menginput warna");

                                String pilihanWarna = sc.next();
                                if (pilihanWarna.equals("Red")) {
                                    colorChoosen = HijiCard.Color.Red;
                                } else if (pilihanWarna.equals("Blue")) {
                                    colorChoosen = HijiCard.Color.Blue;
                                } else if (pilihanWarna.equals("Green")) {
                                    colorChoosen = HijiCard.Color.Green;
                                } else if (pilihanWarna.equals("Yellow")) {
                                    colorChoosen = HijiCard.Color.Yellow;
                                } else {
                                    System.out.println("Input tidak sesuai, silahkan pilih warna :");
                                    pilihanWarna= sc.next();
                                }
                            }

                            try {
                                HIJI.submitPlayerCard(HIJI.getCurrentPlayer(), cardChoosen, colorChoosen);
                                System.out.println("Kartu tersubmit");
                            } catch (InvalidCardSubmissionException e) {
                                System.out.println(e);
                                System.out.println("Kartu kamu tidak valid. Game akan dilanjutkan");
                                HIJI.lanjutMain();
                                System.out.print("Silahkan pilih menu: ");
                                pilihan = sc.next();
                            }
                          

                        } else if (submission.equals("Tidak")) {
                            HIJI.lanjutMain();
                        } else {
                            System.out.println("Input antara Ya / Tidak, Silahkan input ulang!");
                            submission = sc.next();
                        }

                        System.out.print("Silahkan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F06")) {
                        System.out.println("List Players");
                        HIJI.ListPlayers();
                        System.out.print("Silahkan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F07")) {
                        System.out.println("View Player in Turn");
                        HIJI.ViewPlayerInTurn();
                        System.out.print("Silahkan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F08")) {
                        
                        gp.printAnything("Help");
                        gp.printAnything("- Game ini dapat dimainkan oleh 2 hingga 6 orang pemain");
                        gp.printAnything("- Setiap permainan dimulai (command F01 di-input), masing-masing pemain akan mendapatkan 7 buah kartu random dan akan ada satu kartu angka random untuk dijadikan sebagai kartu tengah");
                        gp.printAnything("- Urutan giliran pemain akan diacak sekali di awal permainan");
                        gp.printAnything("- Pada setiap giliran, pemain boleh mengeluarkan satu atau lebih kartu yang dapat dimainkan (terdeteksi valid) pada giliran tersebut");
                        gp.printAnything("- Apabila pemain tidak mengeluarkan kartu, ia wajib mengambil satu kartu dari deck");
                        gp.printAnything("- Apabila kartu yang baru diambil valid terhadap kartu tengah, pemain berhak memilih untuk mengeluarkan atau tidak mengeluarkan kartunya");
                        gp.printAnything("- Apabila kartu tidak valid terhadap kartu tengah, giliran pemain tersebut selesai tanpa mengeluarkan kartu");
                        gp.printAnything("- Kartu selain yang berjenis angka, memiliki powernya masing-masing");
                        gp.printAnything("- Apabila pemain memiliki sisa satu kartu, maka pemain harus melakukan declare HIJI dalam waktu 3 detik. Apabila sudaa melebihi batas waktu, pemain wajib mengambil dua kartu dari deck");
                        gp.printAnything("- Pemenang diambil dari pemain yang terlebih dahulu menghabiskan kartu yang sedang dipegang sudah habis dan permainan selesai");
                        gp.printAnything("Untuk kartu Draw 2, pemain selanjutnya akan mengambil kartu sebanyak dua kali jumlah kartu yang dikeluarkan");
                        gp.printAnything("Untuk kartu Draw 4, pemain selanjutnya akan mengambil kartu sebanyak empat kali jumlah kartu yang dikeluarkan");
                        gp.printAnything("Untuk kartu Skip, jumlah pemain yang dilewati sesuai dengan jumlah kartu Skip yang dikeluarkan");
                        gp.printAnything("Untuk kartu Reverse, urutan akan membolak-balik sesuai jumlah kartu. Jika kartu reverse yang digunakan berjumlah genap, maka urutan pemain tidak berubah");
                        gp.printAnything("Untuk kartu Wildcard, pemain tetap hanya dapat memilih satu warna");
                        System.out.print("Silahkan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F01")) {
                        System.out.print("Game sedang dijalankan, silahkan pilih menu: ");
                        pilihan = sc.next();

                        } else if (pilihan.equals("F10")) {
                        System.out.print("Kartu yang sedang dimainkan : ");
                        System.out.println(HIJI.getTopCard());
                        System.out.print("Silahkan pilih menu: ");
                        pilihan = sc.next();

                        } else {
                        System.out.println("Input tidak sesuai, Silahkan pilih menu :");
                        pilihan = sc.next();
                        } // else
                        }
                        System.out.println("Game terhenti");
                        System.exit(0);


            } else {
                System.out.println("Game belum dijalankan, silahkan jalankan game terlebih dahulu dengan meng-input F01");
                pilihan = sc.next();
            }
        }
        System.exit(0);
    }
}
    
    