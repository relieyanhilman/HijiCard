public class Main2 {
    public static void main(String[] args) {
        int jumlahKartuSubmit =2;
        boolean gameDirection = false;
        int currentPlayer = 1;
        int length = 3;

        if (jumlahKartuSubmit % 2 == 1){
            gameDirection ^= true;
            if (gameDirection == true) {
                currentPlayer = (currentPlayer -2) % length;
                if (currentPlayer == -1){
                    currentPlayer = length -1 ;
                }

                if (currentPlayer == -2) {
                    currentPlayer = length -2;
                }
            }
            else if (gameDirection == false) {
                currentPlayer = (currentPlayer + 2) % length;
            }
            
        }
        System.out.println("hasil akhir = " + currentPlayer);
    }
}
