package pt.isel.poo.covid.view;

import pt.isel.poo.covid.Position;
import pt.isel.poo.covid.model.Hero;
import pt.isel.poo.covid.model.Level;
import pt.isel.poo.covid.model.Space;
import pt.isel.poo.covid.model.TrashCan;
import pt.isel.poo.covid.model.Virus;
import pt.isel.poo.covid.model.Wall;
import pt.isel.poo.covid.model.element;
import pt.isel.poo.covid.tile.Tile;
import pt.isel.poo.covid.tile.TilePanel;
import pt.isel.poo.covid.view.HeroTile;

public class LevelView {
    private final TilePanel panel;
    private  final Level model;
    public LevelView(TilePanel panel , Level model){
        this.panel = panel;
        this.model = model;


    }
    public void updateLevel() {
        for(int x = 0; x < model.height; ++x) {
            for(int y = 0; y < model.width; ++y) {
                Position pos = new Position(x,y);
                final element elem = model.getElement(pos);
                updateTile(pos,createTile(elem));
            }
        }
    }

    public void updateTile (Position pos , Tile tile){
        panel.setTile(pos.x, pos.y, tile);
    }


    public Tile createTile(element elem){
        Tile tile = null;

        if ( elem instanceof  Hero) tile = new HeroTile(panel.getContext(), (Hero) elem) ;
        if ( elem instanceof Wall) tile  = new WallTile(panel.getContext(),(Wall) elem) ;
        if ( elem instanceof Virus) tile = new VirusTile(panel.getContext(),(Virus) elem) ;
        if ( elem instanceof TrashCan) tile = new TrashTile(panel.getContext(),(TrashCan) elem);
        return tile;
    }



}
