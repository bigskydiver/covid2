package pt.isel.poo.covid.model;

import android.graphics.Canvas;

import pt.isel.poo.covid.MainActivity;
import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.model.Level;

public class Hero extends element {
    private Level level;
    public Boolean state = false;
    public Hero(Position pos) {
        super(pos);
    }

    public boolean isdead (){
        return state;
    }
    public char  getChar(){
        char character = '@';
        return character;}

}
