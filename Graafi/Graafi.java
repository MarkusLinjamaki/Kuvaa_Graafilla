package Tira2020.Graafi;

import Tira2020.Luokat.GraafinOsa;
import Tira2020.Luokat.Kaari;
import Tira2020.Luokat.Piste;
import Tira2020.Luokat.Solmu;
import Tira2020.Tietorakenteet.Jono;
import Tira2020.Tietorakenteet.Pino;
import Tira2020.OmaPrioriteettiJono.OmaPrioriteettiJono;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Konkreettinen graafi Luokka, joka tallentaa OmaLista luokan avulla GraafinOsa
 * luokan periviä olioita, Solmuja ja Kaaria
 * <p>
 * @see Solmu,Piste,Omalista & GraafinOsa
 * <p>
 * @author Markus Linjamäki (markus.linjamaki@tuni.fi)
 */
public class Graafi {

    // yksityiset vakiot, jotka määrittelevät
    // millä tavalla graafi muodostuu
    private final int SYVYYSHAKU = 1;
    private final int LEVEYSHAKU = 2;
    private final int PIENINVIRITTAVA = 3;

    // sekvenssi jonne talletetaan solmut
    private List<Solmu> solmut;
    private List<Kaari> kaaret;
    

    // kaksiulotteinen lista johon merkataan jos solmu kuuluu graafiin
    private boolean solmuTaulu[][];

    public Graafi() {
        solmut =  new LinkedList<>();
        kaaret =  new LinkedList<>();
        solmuTaulu = new boolean[1080][1080];
    }

    /**
     * Parametrillen rakentaja joka muodostaa graafin joko syvyyshaulla tai leveyshaulla.
     * G
     * @param int[][] greyList parametrina saatu kaksiuloitteinen lista, jossa kuvapikseleiden harmaasävy
     * arvot. Kahden solmun välinen harmaasävyerotus toimii kaaren painona
     * @param Piste p aloitus piste graafissa, saadaan kuvaa klikkaamalla
     * @param int intensiteettiEro  parametrina saatu int arvo, joka kuvaa kuinka paljon harmaasävyero
     * voi kahden solmun välillä olla että niiden välille voi muodostua kaari
     * @param int kokonaisEro parametrina saatu int arvo, joka kuvaa suurinta eroa, mikä alkupisteen ja sen
     * hetkisen pisteen välinen harmaasävy ero voi olla että niiden väliin voi muodostua kaari
     * @param int metodi parametrina saaatu int arvo, joka kuvaa millä metodilla graafi muodostuu
     */
    public Graafi(int[][] greyList, Piste p, int intensiteettiEro, int kokonaisEro, int metodi) {
        solmut = new LinkedList<>();
        kaaret = new LinkedList<>();
        // solmutaulun alustus, aluksi yhtään solmua ei ole lisättty
        solmuTaulu = new boolean[greyList.length][greyList[0].length];
        for (int a = 0; a < greyList.length; a++) {
            for (int e = 0; e < greyList.length; e++) {
                solmuTaulu[a][e] = false;
            }
        }

        // korkeus ja leveysindeksille oikeat arvot
        int x = p.x();
        int y = p.y();

        Solmu alkuSolmu;
        Solmu loppuSolmu;
        Piste loppuPiste;
        Kaari kaari;

        // alkusolmu
        alkuSolmu = new Solmu(p, 0);
        //lisätään graafiin
        solmut.add(alkuSolmu);
        // merkataan solmu jo käydyksi
        solmuTaulu[y][x] = true;

        // alustetaan Solmu laskuri
        int solmuLaskuri = 1;
        // alustetaan kaari laskuri
        int kaariLaskuri = 0;



        boolean seuraavaLoydetty;
        // jatketaa niin kauan kuin polkua riittää
        switch (metodi) {
            
            case SYVYYSHAKU:
                // polku jota pitkin kuljetaan
                Pino pinoX = new Pino();
                Pino pinoY = new Pino();

                // lisätään aloituspaikka polulle
                pinoX.push(p.x());
                pinoY.push(p.y());
                while (!pinoX.isEmpty()) {
                    seuraavaLoydetty = false;
                    for (int a = -1; a < 2; a++) {
                        // jos silmukka löydettiin, voidaan poistua loopista
                        for (int e = -1; e < 2; e++) {
                            // katsotaan vain indeksejä jotka neliön sisällä
                            if (0 <= (y + a) && (y + a) < greyList.length && 0 <= (x + e) && (x + e) < greyList[0].length) {
                                // uusi piste
                                loppuPiste = new Piste(x + e, y + a);
                                // uusi solmu
                                loppuSolmu = new Solmu(loppuPiste, solmuLaskuri);

                                // uusi kaari muodostunut
                                kaari = new Kaari(alkuSolmu, loppuSolmu, Math.abs(greyList[y + a][x + e] - greyList[y][x]), kaariLaskuri);

                                // harmaasävy ehto toteutuu voidaan yrittöö
                                // alkaa muodostaa solmuja ja särmiä
                                if (Math.abs(greyList[y + a][x + e] - greyList[p.y()][p.x()]) < kokonaisEro && Math.abs(greyList[y + a][x + e] - greyList[y][x]) < intensiteettiEro && !solmuTaulu[y + a][x + e]) {
                                    // koitetaan lisätä mahdollinen uusi solmu graafiin
                                    try {
                                        solmut.add(loppuSolmu);
                                        // jos lisäys onnitui, voidaa tehdä tarvittavat päivitykset
                                        // päivitetään solmu laskuria
                                        solmuTaulu[y + a][x + e] = true;
                                        //System.out.println("(" + (y + a) + "," + (x+e) + ")");
                                        solmuLaskuri++;

                                        // siirrytään polulla, loppusolmu alkusolmuksi
                                        alkuSolmu = loppuSolmu;
                                        // lisätään uusi paikka polkuun
                                        pinoX.push(new Integer(x + e));
                                        pinoY.push(new Integer(y + a));
                                        // päivitetään myös indeksejä
                                        x = x + e;
                                        y = y + a;
                                        // ollaan edetty polulla, ei tarvitse peruuttaa
                                        seuraavaLoydetty = true;

                                        try {
                                            // lisätään kaari graafiin
                                            kaaret.add(kaari);
                                            // päivitetään kaarilaskuria
                                            kaariLaskuri++;
                                        } 
                                        catch (Exception kiinni) {

                                        }
                                    } 
                                    catch (Exception kii) {

                                    }
                                }
                            }
                        }
                    }
                    // jos matka ei enää jatku polulla otetaan yksi askel taaksepäin
                    if (!seuraavaLoydetty) {
                        x = (Integer) pinoX.pop();
                        y = (Integer) pinoY.pop();
                        //Piste vanhaPiste = new Piste(x,y);
                        // ollaan vanhassa pisteessä, jote tätä ei lisätä graafiin
                        //alkuSolmu = etsi(vanhaPiste);
                    }
                }
                break;
            case LEVEYSHAKU:
                // Jono tietorakenne linkitetyllä listalla
                Queue<Solmu> solmuJono = new LinkedList<>();
                solmuJono.add(alkuSolmu);
                // lisätään aloituspaikka polulle
                
                int vanhatSolmutLKM = 1;
                int uudetSolmutLKM = 0;

                while (!solmuJono.isEmpty()) {
                    // jokaiselle solmulle pitää erikseen käydä mahdolliset paikat
                    for (int i = 0; i < vanhatSolmutLKM; i++) {
                        alkuSolmu = solmuJono.remove();
                        x = (Integer) alkuSolmu.piste().x();
                        y = (Integer) alkuSolmu.piste().y();       
              
                        for (int a = -1; a < 2; a++) {
                            for (int e = -1; e < 2; e++) {
                                if (0 <= (y + a) && (y + a) < greyList.length && 0 <= (x + e) && (x + e) < greyList[0].length) {
                                    // harmaasävy ehto toteutuu voidaan yrittöö
                                    // alkaa muodostaa solmuja ja särmiä
                                    if (Math.abs(greyList[y + a][x + e] - greyList[p.y()][p.x()]) < kokonaisEro && Math.abs(greyList[y + a][x + e] - greyList[y][x]) < intensiteettiEro && !solmuTaulu[y + a][x + e]) {
                                        // uusi piste
                                        loppuPiste = new Piste(x + e, y + a);
                                        // uusi solmu
                                        loppuSolmu = new Solmu(loppuPiste, solmuLaskuri);

                                        // uusi kaari 
                                        kaari = new Kaari(alkuSolmu, loppuSolmu, Math.abs(greyList[y + a][x + e] - greyList[y][x]), kaariLaskuri);
                                        try {
                                            solmut.add(loppuSolmu);
                                            solmuTaulu[y + a][x + e] = true;
                                            solmuLaskuri++;
                                            uudetSolmutLKM++;
                                            solmuJono.add(loppuSolmu);
                                            // siirrytään polulla, loppusolmu alkusolmuksi
                                            alkuSolmu = loppuSolmu;
                                            try {

                                                kaaret.add(kaari);
                                                kaariLaskuri++;
                                            } catch (Exception ezz) {

                                            }
                                        } catch (Exception trr) {

                                        }
                                    }
                                }
                            }
                        }
                    }
                    vanhatSolmutLKM = uudetSolmutLKM;
                    uudetSolmutLKM = 0;
                }
                break;
            case PIENINVIRITTAVA:
                int laskuri = 0;
                Queue<Kaari> kaariLista = new PriorityQueue<>();
                do{
                    for (int a = -1; a < 2; a++) {
                        for (int e = -1; e < 2; e++) {
                            // ollaan neliön sisällä
                            if (0 <= (y + a) && (y + a) < greyList.length && 0 <= (x + e) && (x + e) < greyList[0].length) {
                                // harmaasävy ehto toteutuu voidaan yrittää muodostaa särmää
                                // lisätään kaikki kaaret, jotka neliön sisällä
                                if (Math.abs(greyList[y + a][x + e] - greyList[p.y()][p.x()]) < kokonaisEro && Math.abs(greyList[y + a][x + e] - greyList[y][x]) < intensiteettiEro && !solmuTaulu[y + a][x + e]){
                                    // uusi piste
                                    loppuPiste = new Piste(x + e, y + a);
                                    // uusi solmu
                                    loppuSolmu = new Solmu(loppuPiste, solmuLaskuri);

                                    // uusi kaari muodostunut
                                    kaari = new Kaari(alkuSolmu, loppuSolmu, Math.abs(greyList[y + a][x + e] - greyList[y][x]), kaariLaskuri);
                                    // lisätään uusi kaari prioriteettijonoon
                                    kaariLista.add(kaari);
                                }
                            }
                        }
                    }
                    laskuri++;
                    System.out.println(laskuri);
                    // kaaret lisätty, valitaan arvoltaan pienin kaari
                    Kaari pieninKaari = (Kaari) kaariLista.poll();
                    // jos kaari ei tyhjä eikä kaaren loppusolmu ole graafissa
                    if(pieninKaari != null && !solmuTaulu[pieninKaari.loppuSolmu().piste().y()][pieninKaari.loppuSolmu().piste().x()]){
                        // Pienimmän kaaren loppusolmusta tehdään uusi haku
                        Solmu uusi = pieninKaari.loppuSolmu();
                        solmut.add(uusi);
                        solmuLaskuri++;
                        kaaret.add(pieninKaari);
                        kaariLaskuri++;
                        x = uusi.piste().x();
                        y = uusi.piste().y();
                        solmuTaulu[y][x] = true;
                    }
                }
                while(laskuri < 100000);
                
                break;
            default:
                System.out.println("ERROR");
        }
    }      
    // palauttaa true jos graafissa on jo kyseinen solmu
    public boolean solmuTaulu(int x, int y) {
        return solmuTaulu[y][x];
    }
    
    // tulostaa kaikki graafin solmut
    public void tulostaSolmut() {
        Iterator<Solmu> iteraattori = solmut.iterator();
        while (iteraattori.hasNext()) {
            Solmu s = iteraattori.next();
            System.out.println("(" + s.piste().x() + "," + s.piste().y() + ")");
        }
    }
    
    // tulostaa kaikki graafin kaaret
    public void tulostaKaaret() {
        Iterator<Kaari> iteraattori = kaaret.iterator();
        while (iteraattori.hasNext()) {
            Kaari k = iteraattori.next();
            Solmu alku = k.alkuSolmu();
            Solmu loppu = k.loppuSolmu();
            System.out.println("( " + alku.piste().x() + "," + alku.piste().y() + ")  --> ("
                    + loppu.piste().x() + "," + loppu.piste().y() + ")");
        }
    }
}
