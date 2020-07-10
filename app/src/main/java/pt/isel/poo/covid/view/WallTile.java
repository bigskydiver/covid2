package pt.isel.poo.covid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import pt.isel.poo.covid.R;
import pt.isel.poo.covid.model.Hero;
import pt.isel.poo.covid.model.Wall;
import pt.isel.poo.covid.tile.Img;
import pt.isel.poo.covid.tile.Tile;

public class WallTile implements Tile {

    private Wall wall;
    private Img wallImg;
    private final Paint brush;
    public WallTile(Context ctx, Wall wall){
        brush= new Paint();
        wallImg =new Img(ctx, R.drawable.wall);
        this.wall=wall;

    }

    @Override
    public void draw(Canvas canvas, int side) {

    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
