package pt.isel.poo.covid.model;

import android.graphics.Canvas;

import pt.isel.poo.covid.Position;

public class Space extends element{
    public Space(Position pos) {
        super(pos);
    }
    public char  getChar(){
        character = '.';
        return character;
    }

}
