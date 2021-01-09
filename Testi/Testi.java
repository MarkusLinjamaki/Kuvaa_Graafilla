/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tira2020.Testi;

import Tira2020.Luokat.Kaari;
import Tira2020.Luokat.Piste;
import Tira2020.Luokat.Solmu;
import Tira2020.OmaPrioriteettiJono.OmaPrioriteettiJono;
import Tira2020.apulaiset.Auttava;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Markus
 */
public class Testi{
    public static void main(String[]args){
        Piste piste1 = new Piste(1,2);
        Piste piste2 = new Piste(2,2);
        Piste piste3 = new Piste(3,2);
        Piste piste4 = new Piste(4,2);
        Piste piste5 = new Piste(5,2);

        Solmu solmu1 = new Solmu(piste1,1);
        Solmu solmu2 = new Solmu(piste2,2);
        Solmu solmu3 = new Solmu(piste3,3);
        Solmu solmu4 = new Solmu(piste4,4);
        Solmu solmu5 = new Solmu(piste5,5);

        Kaari kaari1 = new Kaari(solmu1,solmu2,10,1);
        Kaari kaari2 = new Kaari(solmu1,solmu3,25,2);
        Kaari kaari3 = new Kaari(solmu2,solmu4,5,3);
        Kaari kaari4 = new Kaari(solmu2,solmu5,25,4);
        Kaari kaari5 = new Kaari(solmu5,solmu4,15,5);
        
        OmaPrioriteettiJono<Kaari> kaariLista = new OmaPrioriteettiJono<>();
        kaariLista.lisää(kaari1);
        kaariLista.lisää(kaari2);
        kaariLista.lisää(kaari3);
        kaariLista.lisää(kaari4);
        kaariLista.lisää(kaari5);
        
        
        
        System.out.println("JOUSEMOGS");
        Queue<Kaari> lista = new PriorityQueue<>();
        lista.add(kaari1);
        lista.add(kaari2);
        lista.add(kaari3);
        lista.add(kaari4);
        lista.add(kaari5);
        while(!lista.isEmpty()){
            Kaari temp = (Kaari) lista.poll();
            System.out.println(temp.arvo());
        }
        List<Kaari> lista1 = new LinkedList<>();
        lista.add(kaari1);
        lista.add(kaari2);
        lista.add(kaari3);
        lista.add(kaari4);
        lista.add(kaari5);
        
    }

}
