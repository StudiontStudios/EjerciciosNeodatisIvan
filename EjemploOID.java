package neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.core.oid.OIDFactory;

public class EjemploOID {

    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");	// Abrir BD   

        OID oid5 = OIDFactory.buildObjectOID(5); 	//obtener OID 5

        Jugador jugon = (Jugador) odb.getObjectFromId(oid5);    //obtener objeto con OID 5

        System.out.println(jugon);
        odb.close(); // Cerrar BD	   		
    }
}
