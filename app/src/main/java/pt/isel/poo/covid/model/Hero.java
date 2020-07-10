package pt.isel.poo.covid.model;

import android.graphics.Canvas;

import pt.isel.poo.covid.MainActivity;
import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.model.Level;
public class Hero extends element {
    public Hero(Position pos) {
        super(pos);
    }


    public Position getsuperPos(){
        return getPos() ;

    }

}
