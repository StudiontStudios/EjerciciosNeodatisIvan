package neodatis;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class Ejercicio2 {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("EQUIPOS.DB");
        Faker f = new Faker();
        
        Jugador j1;
        Pais p;
        
        ArrayList list = new ArrayList();
        
        for(int i=0;i<5;i++){
            p = new Pais();
            p.setId(i);
            p.setNombrepais(f.country().name());
            list.add(p);
        }       
        
        for(int i=0;i<100;i++){
            j1 = new Jugador();
            j1.setCiudad(f.country().capital());
            j1.setDeporte(f.esports().game());
            j1.setEdad(f.number().numberBetween(18, 35));
            j1.setNombre(f.name().firstName());

            j1.setPais((Pais) list.get(i%5));
            odb.store(j1);
        }
        odb.close();
    }
}
