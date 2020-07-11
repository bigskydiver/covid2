package pt.isel.poo.covid.model;

import android.graphics.Canvas;

import pt.isel.poo.covid.Position;

public class TrashCan extends element {
    public TrashCan(Position pos) {
        super(pos);
    }
    public char  getChar(){
        character = 'V';
        return character;
    }
    boolean kills (){
        return true;
    }
}
