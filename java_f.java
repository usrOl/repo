package football;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Football {

    public static void main(String[] args) {
        ArrayList<Meccs> adatok = read();
        
        //3. Feladat: Határozza meg és írja ki a képernyőre a forrásállományban szereplő mérkőzések számát.

        System.out.println("3. Feladat: " + adatok.size() + "db meccset tartalmaz a fájl");
        
        //4. Feladat: Határozza meg és írja ki a képernyőre hány döntetlen mérkőzés szerepel a forrásállományban.
        int dontetlenek = 0;
        for(Meccs m : adatok){
            if(m.getEredmeny() == 'D') dontetlenek++;
        }
        
        System.out.println("4. Feladat: A fájl " + dontetlenek + " db döntetlen meccset tartalmaz");
        
        
        //5. Feladat: Határozza meg és írja ki a képernyőre annak a meccsnek az adatait, amelyiken a legtöbb gól született. Amennyiben több ilyen van, úgy bármelyiket kiírathatja (de nincs).
        Meccs maxGol = adatok.get(0);
        for(Meccs m : adatok){
            if(m.getLott() + m.getKapott() > maxGol.getLott() + maxGol.getKapott()) maxGol = m;
        }
        System.out.println("5. Feladat: A legtöbb gól az alábbi meccsen született: ");
        System.out.println(maxGol);
        
        
        //6. Feladat: Írja ki a képernyőre minden csapat nevét, akik részt vettek a mérkőzésen. Minden név csak egyszer szerepeljen a kiírásban.
        HashSet<String> csapatok = new HashSet<>();
        for(Meccs m : adatok){
            csapatok.add(m.getHazai());
        }
        System.out.println("6. Feladat: A bajnokságon résztvevő csapatok listája: ");
        System.out.println(csapatok);
        
        //7. Feladat: Írja ki a konzolra annak a mérkőzésnek az adatait, amelyik a legkorábban zajlott (dátum és idő szerint)
        Meccs legutobbi = adatok.get(0);
        for(Meccs m : adatok){
            if(m.getIdopont().isAfter(legutobbi.getIdopont())) legutobbi = m;
        }
        
        System.out.println("7. feladat: A bajnokság legutóbbi meccse az alábbi volt: ");
        System.out.println(legutobbi);
        
        //8. Feladat: Készítsen egy tabellát, amelyben a csapatok pontszámai találhatóak
        HashMap<String, Integer> tabella = new HashMap<>();
        for(Meccs m : adatok){
            
            //A csapat 0 pontot kap, ha vesztes mérkőzést játszik
            int h = 0; 
            int v = 0;
            
            //A csapat 1 pontot kap, ha döntetlent játszik.
            //...
            //Például, ha 2 csapat döntetlent játszik, akkor mindkét csapat pontszáma növekedjen meg 1-el.
            if(m.getLott() == m.getKapott()){
                h = 1;
                v = 1;
            }else if(m.getLott() > m.getKapott()){
                h = 3; //A csapat 3 pontot kap, amennyiben megnyeri a mérkőzést.
            }else{
                v = 3; //A csapat 3 pontot kap, amennyiben megnyeri a mérkőzést.
            }
            
            if(tabella.containsKey(m.getHazai())){
                tabella.put(m.getHazai(), tabella.get(m.getHazai()) + h);
            }else{
                tabella.put(m.getHazai(), h);
            }
            
            if(tabella.containsKey(m.getVendeg())){
                tabella.put(m.getVendeg(), tabella.get(m.getVendeg()) + v);
            }else{
                tabella.put(m.getVendeg(), v);
            }
        }
        System.out.println("8. Feladat: Tabella:");
        tabella.forEach((k,v) -> System.out.println(k + ":" + v)); 
    }
    
    //2. feladat: Olvassa be a „football.txt” állomány adatait és tárolja el egy olyan adatszerkezetben, ami a következő feladatok megoldására alkalmas.
    public static ArrayList<Meccs> read(){
        ArrayList<Meccs> adatok = new ArrayList<>();
        try {
            
            File file = new File("football.txt");
            Scanner sc = new Scanner(file);
            sc.nextLine(); //Első sor átugrása, mert abban a fejlécek szerepelnek
            while(sc.hasNextLine()){
                adatok.add(new Meccs(sc.nextLine()));
            }
            
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        return adatok;
    }
    
}
