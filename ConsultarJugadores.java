package neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Or;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class ConsultarJugadores {

    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");	// Abrir BD

        // Juegan Tenis
        CriteriaQuery query = new CriteriaQuery(Jugador.class, Where.equal("deporte", "tenis"));
        query.orderByAsc("nombre,edad"); 	// orden ascendente por nombre y edad, sin espacios en blanco				 	
        Objects<Jugador> jugones = odb.getObjects(query);
        System.out.println(jugones.size() + " Jugadores de Tenis (orden de nombre y edad):");
        while (jugones.hasNext()) {
            Jugador jugon = (Jugador) jugones.next();
            System.out.println("\t" + jugon.getNombre() + " * " + jugon.getDeporte() + " * " + jugon.getEdad() + " * " + jugon.getPais().getNombrepais());
        }

        // Primero que juega Tenis
        Jugador jugon1 = (Jugador) odb.getObjects(query).getFirst();
        System.out.println("\nPrimero de Tenis: " + jugon1);

        // Condicion l�gica AND
        ICriterion criterio2 = new And().add(Where.like("nombre", "M%")).add(Where.gt("edad", 14));
        CriteriaQuery query2 = new CriteriaQuery(Jugador.class, criterio2);
        Objects<Jugador> jugones2 = odb.getObjects(query2);
        System.out.println("\nJugadores mayores de 14 a�os cuyo nombre empieza por M:");
        while (jugones2.hasNext()) {
            Jugador jugon = (Jugador) jugones2.next();
            System.out.println(jugon);
        }

        // Condicion l�gica OR
        ICriterion criterio3 = new Or().add(Where.equal("ciudad", "Madrid")).add(Where.ge("edad", 15));
        CriteriaQuery query3 = new CriteriaQuery(Jugador.class, criterio3);
        Objects<Jugador> jugones3 = odb.getObjects(query3);
        System.out.println("\nJugadores mayores de 14 a�os o de Madrid:");
        while (jugones3.hasNext()) {
            Jugador jugon = (Jugador) jugones3.next();
            System.out.println(jugon);
        }

        // Nombre y ciudad de todos los jugadores
        IValuesQuery valoresQuery = new ValuesCriteriaQuery(Jugador.class).field("nombre").field("ciudad");
        valoresQuery.orderByAsc("ciudad,nombre");
        Values valores = odb.getValues(valoresQuery);		// recuperamos Valores
        System.out.println("\nNombre y ciudad de los jugadores (orden de ciudad y nombre):");
        while (valores.hasNext()) {
            ObjectValues valoresObjeto = valores.nextValues();
            System.out.println(valoresObjeto.getByAlias("nombre") + " * " + valoresObjeto.getByIndex(1));	// �ndice 1: 2� campo
        }

        odb.close(); 	// Cerrar BD
    }
}
