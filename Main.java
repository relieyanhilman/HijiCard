
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
                    while (banyakPemain>6 && banyakPemain<2){
                        System.out.println("Jumlah yang dimasukkan tidak sesuai!");
                        System.out.print("Masukkan banyaknya pemain: ");
                        banyakPemain = sc.nextInt();
                    }

                    String[] Pemain = new String[banyakPemain];
                    String str = null;
                    boolean submitted = false;
                    boolean udahDraw = false;
                    System.out.println("Masukkan nama pemain: ");
                    for (int i = 0; i< banyakPemain; i++){
                        Pemain[i] = sc.next();
                    }

                    
                    Game1 HIJI = new Game1(Pemain);

                    HIJI.start(HIJI);
                    System.out.println("yang bermain sekarang:" + HIJI.getCurrentPlayer());
                    // System.out.println(HIJI.deck.cardsInDeck); ini apa ya?

                    System.out.println("kartu yang " + HIJI.getCurrentPlayer() + "punya sekarang " + HIJI.getPlayerHand(HIJI.getCurrentPlayer()));
                    

                    // pilihan = sc.next();
                    System.out.print("pilih menu: ");
                    pilihan = sc.next();

                    while(!pilihan.equals("F01")){
                        if (pilihan.equals("F02")) {
                            System.out.println("List Cards");
                            HIJI.ListCards(HIJI.getCurrentPlayer()); // asumsi, bisa ngejalanin F02 kalau sudah dilaksanakan F01
                            pilihan = sc.next();
                        } else if (pilihan.equals("F03"))  {
                            HijiCard.Color colorChoosen = HijiCard.Color.Red;
                            System.out.println("Discard");
                            System.out.println("silakan pilih nomor kartu di list kartu: ");
                            int pilihanKartu = sc.nextInt();
                            
                            HijiCard cardChoosen = HIJI.getPlayerCard(HIJI.getCurrentPlayer(), pilihanKartu);
                            
                           
                            if (cardChoosen.getColors() == HijiCard.Color.Wild) {
                                System.out.println("silakan pilih warna: \n 1. Red \n 2. Blue \n 3. Yellow \n 4. Green");
                                
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
                            
                            

                            System.out.println("apakah aman?");
                        
                            try{HIJI.submitPlayerCard(HIJI.getCurrentPlayer(), cardChoosen, colorChoosen);
                                HIJI.getPlayerHand(HIJI.getCurrentPlayer());}
                                catch (InvalidColorSubmissionException e) {
                                       System.out.println(e);
                                }
                                catch (InvalidPlayerTurnException e) {
                                       System.out.println(e);
                                }
                                catch (InvalidValueSubmissionException e) {
                                       System.out.println(e);
                                }
                            
                            pilihan = sc.next();
                         } else if (pilihan.equals("F04")) {
                            System.out.println("Draw");
                            
                            HIJI.submitDraw(HIJI.getCurrentPlayer());
                            
                            
                            pilihan = sc.next();
                            if (pilihan.equals("F09")){
                                System.out.println("lanjut main");
                                
                                HIJI.lanjutMain();
                            }
                        } else if (pilihan.equals("F05")) {
                            System.out.println("Declare HIJI");
                            pilihan = sc.next();
                        } else if (pilihan.equals("F06")) {
                            System.out.println("List Players");
                            HIJI.ListPlayers();
                            pilihan = sc.next();
                        } else if (pilihan.equals("F07")) {
                            System.out.println("View Player in Turn");
                            HIJI.ViewPlayerInTurn();
                            pilihan = sc.next();
                        } else if (pilihan.equals("F08")) {
                            System.out.println("Help");
                            pilihan = sc.next();
                        }
                        else if (pilihan.equals("EXIT")){
                            System.exit(0);
                        } //else 
                    }


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
            } System.exit(0);
        } 
    }

