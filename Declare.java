
boolean pid_hiji = false;
if (getPlayerHandSize(playerIds[i] == 1){
    pid_hiji = true;
}
while (pid_hiji){
    System.out.println("Waktunya declare HIJI!");
    long startTime;
    String input_hiji;
    startTime = System.currentTimeMillis();
    System.out.println("Ketik HIJI dalam 3 detik");
    inputhiji = input.next().toUpperCase();
    }
    if (inputhiji.equals("HIJI")){
        loop = false;
        pid_hiji = false;
        System.out.println("Declare HIJI sukses!");
    }
if (System.currentTimeMillis() - startTime > 3000){
    System.out.println("Kamu telat declare HIJI! Selamat kamu dapat tambahan hadiah 2 kartu!");
    getPlayerHand(pid).add(deck.drawCard());
    getPlayerHand(pid).add(deck.drawCard());
    player_hiji = false;
}
}
