package pt.isel.poo.covid.model;

import android.graphics.Canvas;

import pt.isel.poo.covid.Position;

public class Wall extends element{

    public Wall(Position pos) {
        super(pos);
    }
    public char  getChar(){
        character = 'X';
        return character;
    }

}
