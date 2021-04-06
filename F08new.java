public  class Game{
    public static String[] mainmenu = 
    {"Pilih menu program di bawah ini!"
    , "F01 - START GAME"
    , "F02 - LIST CARD"
    , "F03 - DISCARD"
    , "F04 - DRAW"
    , "F05 - DECLARE HIJI"
    , "F06 - LIST PLAYER"
    , "F07 - VIEW PLAYER IN TURN"
    , "F08 - HELP"
    , "EXIT"};

    if (pilihan.equals("F08")){
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
    }

    
    public static void menu(){
        for (int i=0; i<10; i++){
            System.out.println(mainmenu[i]);
        }
    }
}
}

