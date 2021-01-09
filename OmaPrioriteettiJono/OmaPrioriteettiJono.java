/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Markus
 */
package Tira2020.OmaPrioriteettiJono;

import Tira2020.apulaiset.Helpottava;
import Tira2020.apulaiset.Ooperoiva;
import java.util.PriorityQueue;

public class OmaPrioriteettiJono<E> extends PriorityQueue<E> implements Ooperoiva<E>, Helpottava<E>{    
    @Override
    public void lisää(E uusi) throws IllegalArgumentException {
        if(uusi == null){
            throw new IllegalArgumentException();
        }
        else{
            add(uusi);
        }
    }

    @Override
    public E hae() throws IllegalArgumentException {
        if(size() != 0){
            return poll();
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean onkoTyhja() {
        return(size()==0);
    }

}
