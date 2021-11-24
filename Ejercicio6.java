package neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class Ejercicio6 {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");
               
        IQuery query = new CriteriaQuery(Jugador.class); //SELECT * FROM jugadores;
        query.orderByAsc("deporte");
        
        Objects<Jugador> objects = odb.getObjects(query);
        
        String aux;
        while(objects.hasNext()){
            System.out.println(objects.next().toString());
        }
    }
}
