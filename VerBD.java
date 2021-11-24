package neodatis;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class VerBD {

    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");	// Abrir BD

        // Recuperamos todos los objetos: Los Paises
        Objects<Pais> paises = odb.getObjects(Pais.class);
        System.out.println(paises.size() + " Paises:");

        for (Pais pais : paises) {
            System.out.println("\t" + pais.getId() + " - " + pais.getNombrepais());
        }

        /*		while (paises.hasNext()) {
			Pais pais = paises.next();
			System.out.println("\t" + pais.getId() + " - " + pais.getNombrepais());
		}
         */
        // Los Jugadores
        Objects<Jugador> jugones = odb.getObjects(Jugador.class);
        System.out.println(jugones.size() + " Jugadores:");

        while (jugones.hasNext()) {
            Jugador jugon = (Jugador) jugones.next();
            System.out.println("\t" + jugon.getNombre() + " * " + jugon.getDeporte() + " * " + jugon.getEdad() + " * " + jugon.getPais().getNombrepais());
        }

        odb.close(); // Cerrar BD
    }
}
