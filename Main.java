
import java.util.*;



import java.lang.*;

public class Main {

    public static void main(String[] args) {
        
        System.out.println("Welcome to the Hiji");
        Scanner sc = new Scanner(System.in);
        System.out.print("pilih menu : ");
        String pilihan = sc.next();
        
        
       
        
        
            while (!pilihan.equals("EXIT")) {

                if (pilihan.equals("F01")) {
                    System.out.println("START GAME");

                    System.out.print("Masukkan banyaknya pemain: ");
                    int banyakPemain = sc.nextInt();
                    while (banyakPemain>6 || banyakPemain<2){
                        System.out.println("Jumlah yang dimasukkan tidak sesuai!");
                        System.out.print("Masukkan banyaknya pemain: ");
                        banyakPemain = sc.nextInt();
                    }

                    String[] Pemain = new String[banyakPemain];
                    String str = null;
                    
                    System.out.println("Masukkan nama pemain: ");
                    for (int i = 0; i< banyakPemain; i++){
                        Pemain[i] = sc.next();
                    }

                    
                    Game1 HIJI = new Game1(Pemain);

                    HIJI.start(HIJI);
                    // System.out.println("yang bermain sekarang:" + HIJI.getCurrentPlayer() + "\n");
                    
                    

                    // pilihan = sc.next();
                    System.out.print("pilih menu: ");
                    pilihan = sc.next();

                    while(!pilihan.equals("EXIT")){
                        if (pilihan.equals("F02")) {
                            System.out.println("List Cards");
                            HIJI.ListCards(HIJI.getCurrentPlayer()); // asumsi, bisa ngejalanin F02 kalau sudah dilaksanakan F01
                            System.out.print("pilih menu: ");
                            pilihan = sc.next();
                        } else if (pilihan.equals("F03"))  {
                            HijiCard.Color colorChoosen = HijiCard.Color.Red;
                            System.out.println("Discard");
                            System.out.println("silakan pilih nomor kartu di list kartu: ");
                            int pilihanKartu = sc.nextInt();
                            
                            HijiCard cardChoosen = HIJI.getPlayerCard(HIJI.getCurrentPlayer(), pilihanKartu);
                            
                           
                            if (cardChoosen.getColors() == HijiCard.Color.Wild) {
                                System.out.println("silakan pilih warna: \n 1. Red \n 2. Blue \n 3. Yellow \n 4. Green \n pilih dengan menginput warna" );
                                
                                String pilihanWarna = sc.next();
                                if (pilihanWarna.equals("Red")){
                                    colorChoosen = HijiCard.Color.Red;
                                }
                                else if (pilihanWarna.equals("Blue")){
                                    colorChoosen = HijiCard.Color.Blue;
                                }
                                else if (pilihanWarna.equals("Green")){
                                    colorChoosen = HijiCard.Color.Green;
                                }else if (pilihanWarna.equals("Yellow")){
                                    colorChoosen = HijiCard.Color.Yellow;
                                } }
                            
                        
                            try{HIJI.submitPlayerCard(HIJI.getCurrentPlayer(), cardChoosen, colorChoosen);
                                System.out.println("kartu tersubmit");    
                            }
                                catch (InvalidCardSubmissionException e) {
                                       System.out.println(e);
                                }
                                
                                
                                
                            System.out.print("silakan pilih menu: ");
                            pilihan = sc.next();
                         } else if (pilihan.equals("F04")) {
                            
                            System.out.println("Draw");
                            
                            HIJI.submitDraw(HIJI.getCurrentPlayer());
                            System.out.println("kartu yang anda dapatkan adalah " + HIJI.getPlayerCard(HIJI.getCurrentPlayer(), (HIJI.getPlayerHandSize(HIJI.getCurrentPlayer()) - 1)));
                            System.out.println("list kartu anda sekarang: ");
                            HIJI.ListCards(HIJI.getCurrentPlayer());
                            System.out.println("apakah ingin mensubmit kartu : ya / tidak");
                            String submission = sc.next();
                            if (submission.equals("ya")){
                                HijiCard.Color colorChoosen = HijiCard.Color.Red;  //warna default
                                System.out.println("Discard");
                                System.out.println("silakan pilih nomor kartu di list kartu: ");
                                int pilihanKartu = sc.nextInt();
                                
                                HijiCard cardChoosen = HIJI.getPlayerCard(HIJI.getCurrentPlayer(), pilihanKartu);
                                
                           
                                if (cardChoosen.getColors() == HijiCard.Color.Wild) {
                                    System.out.println("silakan pilih warna: \n 1. Red \n 2. Blue \n 3. Yellow \n 4. Green \n pilih dengan menginput warna" );
                                    
                                    String pilihanWarna = sc.next();
                                    if (pilihanWarna.equals("Red")){
                                        colorChoosen = HijiCard.Color.Red;
                                    }
                                    else if (pilihanWarna.equals("Blue")){
                                        colorChoosen = HijiCard.Color.Blue;
                                    }
                                    else if (pilihanWarna.equals("Green")){
                                        colorChoosen = HijiCard.Color.Green;
                                    }else if (pilihanWarna.equals("Yellow")){
                                        colorChoosen = HijiCard.Color.Yellow;
                                    } }
                                
                            
                                try{HIJI.submitPlayerCard(HIJI.getCurrentPlayer(), cardChoosen, colorChoosen);
                                    System.out.println("kartu tersubmit");    
                                }
                                    catch (InvalidCardSubmissionException e) {
                                        System.out.println(e);
                                        System.out.println("kartu anda tidak valid. Game akan dilanjutkan");
                                        HIJI.lanjutMain();
                                        System.out.print("silakan pilih menu: ");
                                        pilihan = sc.next();
                                        
                                    }
                                    
                                    
                                        
                                    

                            } else if (submission.equals("tidak")){
                                HIJI.lanjutMain();
                            } else {System.out.println("input antara ya / tidak, silakan input ulang.");
                                submission = sc.next();
                            }
                                
                            System.out.print("silakan pilih menu: ");
                            pilihan = sc.next();
                        } else if (pilihan.equals("F05")) {
                            System.out.println("Declare HIJI");

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
                            System.out.print("silakan pilih menu: ");
                            pilihan = sc.next();
                        }
                        else if (pilihan.equals("F01")){
                            System.out.print("Game sedang dijalankan, silakan pilih menu: ");
                            pilihan = sc.next(); 
                        }
                        else if (pilihan.equals("F10")) {
                            System.out.print("kartu yang sedang dimainkan : ");
                            System.out.println(HIJI.getTopCard());
                            System.out.print("silakan pilih menu: ");
                            pilihan = sc.next();
                        }
                        else {
                            System.out.println("input tidak sesuai, silakan pilih menu :");
                            pilihan = sc.next();
                        }//else 
                    } System.out.println("game terhenti");
                    System.exit(0);


                    // if (pilihanFungsi.equals("F04")){
                    //     HIJI.
                    // }
                    // if (pilihan.equals("F01")){
                    //     System.out.println("game sedang berjalan");
                    //     System.out.print("pilih menu: ");
                    //     pilihan = sc.next();
                    // }

                    
                        
                    // }else if (pilihanFungsi.equals("F03")){
                    //     System.out.println("submit player card: ");
                    //     System.out.println("nomor list kartu yang ingin dikeluarkan: ");
                    //     int pilihanKartuList = sc.nextInt();
                    //     // HIJI.submitPlayerCard(HIJI.getCurrentPlayer(), HIJI.getPlayerCard(HIJI.getCurrentPlayer(), (pilihanKartuList - 1) ), HIJI.getPlayerCard(HIJI.getCurrentPlayer(), pilihanKartuList - 1 ).getColors());
                
                    // }
                    

                } 
                else {
                    System.out.println("game belum dijalankan, silakan jalankan game terlebih dahulu dengan menginput F01");
                    pilihan = sc.next();
                }
            } System.exit(0);
        } 
    }

 