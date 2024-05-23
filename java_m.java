package football;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Meccs {
    private String Hazai;
    private String Vendeg;
    private int Lott;
    private int Kapott;
    private LocalDateTime Idopont;
    private char Eredmeny;

    public Meccs(String row) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"); //Dátum formázásához kell. A fájlban szereplő 2024.04.29 15:48 typusú dátummal nem tud dolgozni a LocalDateTime
        
        String[] splitted = row.split(";"); //Sorok darabolása ";" mentén
        this.Hazai = splitted[0];
        this.Vendeg = splitted[1];
        this.Lott = Integer.parseInt(splitted[2]);
        this.Kapott = Integer.parseInt(splitted[3]);
        this.Idopont = LocalDateTime.parse(splitted[4], formatter); //Formatter felhasználásával a DateTime típus létrehozása
        
        // A beolvasás során tárolja el azt az adatot is, hogy melyik mérkőzésnek mi lett a kimenetele. 
        //Ha a hazai csapat nyert, akkor egy ‘H’ értéket. 
        //Ha a vendég csapat nyert, akkor egy ‘V’ értéket. 
        //Amennyiben az állás döntetlen, úgy a ‘D’ értéket tárolja el
        this.Eredmeny = Lott > Kapott ? 'H' : Kapott > Lott ? 'V' : 'D';
    }
    
    

    public String getHazai() {
        return Hazai;
    }

    public void setHazai(String Hazai) {
        this.Hazai = Hazai;
    }

    public String getVendeg() {
        return Vendeg;
    }

    public void setVendeg(String Vendeg) {
        this.Vendeg = Vendeg;
    }

    public int getLott() {
        return Lott;
    }

    public void setLott(int Lott) {
        this.Lott = Lott;
    }

    public int getKapott() {
        return Kapott;
    }

    public void setKapott(int Kapott) {
        this.Kapott = Kapott;
    }

    public LocalDateTime getIdopont() {
        return Idopont;
    }

    public void setIdopont(LocalDateTime Idopont) {
        this.Idopont = Idopont;
    }

    public char getEredmeny() {
        return Eredmeny;
    }

    public void setEredmeny(char Eredmeny) {
        this.Eredmeny = Eredmeny;
    }

    @Override
    public String toString() {
        return "Meccs{" + "Hazai=" + Hazai + ", Vendeg=" + Vendeg + ", Lott=" + Lott + ", Kapott=" + Kapott + ", Idopont=" + Idopont + '}';
    }
    
    
}
