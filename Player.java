public class Player {
    private String nama;
    private Card[] kartu;
    
    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public int getJumlahKartu(){
        return kartu.length();
    }
}
