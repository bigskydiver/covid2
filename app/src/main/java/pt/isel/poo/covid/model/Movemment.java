package pt.isel.poo.covid.model;
import pt.isel.poo.covid.Position;

public class Movemment {
    private final element[][] model;
    public Movemment(element[][] model){

      this.model = model;

    }

    public boolean Elem(Direction dir , element elem){
        Position location = null;
        boolean moved = false;

        if ( elem instanceof Hero){
            location = getHeroLocation();
            assert location != null;
            checkIfCollided(location,dir);
        }

        if ( elem instanceof Virus)location = elem.getPos();
        assert location != null;

        if(model[location.x  - dir.x ][location.y - dir.y].getElement() instanceof Space){
           model[location.x - dir.x ][location.y - dir.y ]= model[location.x][location.y];
           model[location.x][location.y] = new Space(new Position(location.x,location.y));
           model[location.x- dir.x ][location.y - dir.y].updatePos(new Position(location.x - dir.x,location.y -dir.y));
            moved= true;
       }

        return moved;
    }






    public void checkIfCollided(Position location,Direction dir){

        if(model[location.x  - dir.x ][location.y - dir.y].getElement() instanceof Virus){

            Elem(dir,(model[location.x  - dir.x ][location.y - dir.y].getElement()));
            Elem(dir,model[location.x][location.y].getElement());
        }

    }
    public void appplyGravity (){
        Position pos ;
        Direction dir = new Direction(0,1);
        while (Elem(dir, new Hero(null)));
        for ( int i = 0 ; i< model.length;++i) {
            for (int z = 0; z < model.length; ++z) {
                pos = new Position(i,z);
                if (checkIfVirusLocation(pos)) while( Elem(dir, model[i][z].getElement())  );

            }
        }
    }



    private Position getHeroLocation(){
        for ( int i = 0 ; i< model.length;++i){
            for ( int z = 0 ; z< model.length;++z){
                    if(model[i][z].getElement() instanceof Hero) return new Position(i,z) ;


            }

        }
    return null;
    }
    private Boolean checkIfVirusLocation(Position pos){
        return model[pos.x][pos.y].getElement() instanceof Virus;
    }



}
