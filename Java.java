package neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Java {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");
        IQuery query = new CriteriaQuery(Jugador.class);
        
        Objects<Jugador> objects = odb.getObjects(query);
        System.out.println(objects.toString());
    }
}
