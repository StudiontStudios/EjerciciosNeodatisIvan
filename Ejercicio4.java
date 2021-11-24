package neodatis;

import 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.*;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner a ;
        String pais = "FRANCIA";
        ODB odb = ODBFactory.open("EQUIPOS.DB");
        
        
        //SELECT * FROM JUGADORES WHERE pais = "FRANCIA"
        //DELETE FROM JUGADORES WHERE pais = "FRANCIA"
        ICriterion criterio = Where.equal("pais", pais);
        CriteriaQuery query = new CriteriaQuery(Jugador.class, criterio);
        
        Objects<Jugador> objects = odb.getObjects(query);
        Jugador j;
        while(objects.hasNext()){
            j=objects.next();
            j.setPais(null);
            odb.store(j);
            //odb.delete(objects.next());
        }
        
        ICriterion c2 = Where.equal("nombrepais", pais);
        CriteriaQuery q2 = new CriteriaQuery(Pais.class, c2);
        Pais p = (Pais) odb.getObjects(q2).getFirst();
        odb.delete(p);
        
        odb.close();
    }
}
