package neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class AltasBD {

    public static void main(String[] args) {
        // Crear instancias para almacenar en BD	
        Pais pais1 = new Pais(1, "ESPAÑA");
        Pais pais2 = new Pais(2, "FRANCIA");

        Jugador j1 = new Jugador("Maria", "voleibol", "Madrid", 14, pais1);
        Jugador j2 = new Jugador("Miguel", "tenis", "Madrid", 15, pais1);
        Jugador j3 = new Jugador("Mario", "baloncesto", "Guadalajara", 15, pais1);
        Jugador j4 = new Jugador("Alicia", "tenis", "Madrid", 14, pais1);
        Jugador j5 = new Jugador("Enzo", "padel", "Paris", 15, pais2);
        Jugador j6 = new Jugador("Michel", "padel", "Rennes", 16, pais2);
        Jugador j7 = new Jugador("Jesus", "baloncesto", "Paris", 17, pais2);
        Jugador j8 = new Jugador("Lucia", "tenis", "Pamplona", 12, pais1);

        ODB odb = ODBFactory.open("EQUIPOS.DB");	// Abrir BD

        odb.store(pais1);
        odb.store(pais2);

        odb.store(j1);
        odb.store(j2);
        odb.store(j3);
        odb.store(j4);
        odb.store(j5);
        odb.store(j6);
        odb.store(j7);
        odb.store(j8);

        odb.close(); // Cerrar BD
        System.out.println("** Creada la Base de Datos: EQUIPOS.DB");
    }
}
