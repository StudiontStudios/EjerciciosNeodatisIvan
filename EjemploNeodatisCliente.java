package neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBServer;
import org.neodatis.odb.Objects;

public class EjemploNeodatisCliente {

    public static void main(String[] args) {
        ODB odb = null;
        ODBServer server = null;
        try {
            // Crea el servidor en el Puerto 8000
            server = ODBFactory.openServer(8000);
            // BD a usar y el nombre que se usará para refererirse a ella
            server.addBase("base", "EQUIPOS.DB");
            // Se ejecuta el servidor
            server.startServer(true);

            // Se abre la BD
            odb = server.openClient("base");

            // Se llama al método que visualize los datos
            visualizaDatos(odb);

        } finally {
            if (odb != null) {
                // Primero se cierra el cliente
                odb.close();
            }
            if (server != null) {
                // Y luego el servidor
                server.close();
            }
        }
    }

    static void visualizaDatos(ODB odb) {
        // Recuperamos todos los objetos Jugador
        Objects<Jugador> jugones = odb.getObjects(Jugador.class);
        System.out.println(jugones.size() + " Jugadores:");

        int i = 1;
        // Visualizar los objetos
        while (jugones.hasNext()) {
            Jugador jugon = jugones.next();
            System.out.println((i++) + "\t: " + jugon);
        }
    }
}
