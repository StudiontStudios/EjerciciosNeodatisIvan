package neodatis;

import neodatis.Jugador;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.util.ArrayList;

public class Ejercicio3 {
    public static void main(String[] args) {
        String pais="Belarus", deporte="Hearthstone";
        buscador(pais, deporte);
    }

    public static void buscador(String pais, String deporte){
        ODB odb = ODBFactory.open("EQUIPOS.DB");

        ICriterion criterio = new And().add(Where.equal("pais.nombrepais", pais))  //se pone pais.nombrepais porque es un campo de esta tabla
                                       .add(Where.equal("deporte",deporte));

        IQuery query = new CriteriaQuery(Jugador.class, criterio); //se hace sobre jugador y hay que tener en cuenta que hay que hacerlo sobre pais
        

       // ArrayList jugadores = new ArrayList();
        
        //jugadores.addAll(odb.getObjects(query));
        Objects<Jugador> jugadores = odb.getObjects(query);
        


        if(jugadores.size()==0){
            System.out.println("------------\nno existen jugadores\n------------");
        }else{
            Jugador j;
            System.out.println("------------\nListado: ");
            
            for(int i=0;i<jugadores.size();i++){
                System.out.println(jugadores.next().toString());
            }
        }

    }
}
