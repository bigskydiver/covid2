package pt.isel.poo.covid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import pt.isel.poo.covid.R;
import pt.isel.poo.covid.model.Virus;
import pt.isel.poo.covid.model.Wall;
import pt.isel.poo.covid.tile.Img;
import pt.isel.poo.covid.tile.Tile;

public class VirusTile implements Tile {
    private Virus virus;
    private Img virusimg;
    private final Paint brush;
    public VirusTile(Context ctx, Virus virus){
        brush = new Paint();
        virusimg = new Img(ctx, R.drawable.virus);
        this.virus=virus;

    }



    @Override
    public void draw(Canvas canvas, int side) {
        virusimg.draw(canvas, side, side,0,brush);
    }

    @Override
    public boolean setSelect(boolean selected) {
        return false;
    }
}
