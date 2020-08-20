/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mage.deck;

import mage.cards.decks.Constructed;

/**
 *
 * @author NinthWorld
 */
public class MarvelBlock extends Constructed {

    public MarvelBlock() {
        super("Constructed Custom - Marvel the Gathering Block");
        setCodes.add(mage.sets.Marvel.getInstance().getCode());
    }

}
