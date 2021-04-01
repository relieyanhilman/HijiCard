public  class Game{
    public  static String[] mainmenu = {"Pilih menu program di bawah ini!"
    , "1 - START GAME"
    , "2 - LIST CARD"
    , "3 - DISCARD"
    , "4 - DRAW"
    , "5 - DECLARE HIJI"
    , "6 - LIST PLAYER"
    , "7 - VIEW PLAYER IN TURN"
    , "8 - RULES"
    , "9 - CARD EFFECT"
    , "10 - END GAME"};

    public static String[] rules =  {"HIJI dimainkan oleh 2-6 pemain."
    , "Di awal permainan, semua pemain akan mendapatkan 7 buah kartu, dan satu kartu angka dipilih secara acak untuk dijadikan kartu awal."
    , "Pemain yang akan memulai giliran pertama akan diacak"
    , "Aturan permainan adalah sebagai berikut:"
    , "a) Pada setiap giliran, pemain boleh menngeluarkan satu atau lebih kartu yang dapat dimainkan pada giliran tersebut"
    , "Beberapa jenis kartu memiliki sisa satu kartu, maka pemain harus melakukan Declare HIJI dalam waktu 3 detik. Apabila tidak, pemain wajib mengambil dua kartu dari deck."
    , "Pemain dinyatakan menang apabila kartu yang dipegang sudah habis dan permainan selesai"};

    public static String[] cardEffect =
    {"Untuk kartu Draw 2, pemain selanjutnya akan mengambil kartu sebanyak dua kali jumlah kartu yang dikeluarkan"
    , "Untuk kartu Draw 4, pemain selanjutnya akan mengambil kartu sebanyak empat kali jumlah kartu yang dikeluarkan"
    , "Untuk kartu Skip, jumlah pemain yang dilewati sesuai dengan jumlah kartu Skip yang dikeluarkan"
    , "Untuk kartu Reverse, urutan akan membolak-balik sesuai jumlah kartu. Jika kartu reverse yang digunakan berjumlah genap, maka urutan pemain tidak berubah"
    , "Untuk kartu Wildcard, pemain tetap hanya dapat memilih satu warna"};

    public Game(){
    }
    
    public static void menu(){
        for (int i=0; i<11; i++){
            System.out.println(mainmenu[i]);
        }
    }

    public static void printRules(){
        for (int i=0; i<8; i++){
            System.out.print(i+1 + ". ");
            System.out.println(rules[i]);
        }
    }

    public static void printCardEffect(){
        for (int i=0; i<5; i++){
            System.out.print(i+1 +". ");
            System.out.println(cardEffect[i]);
        }
    }
}