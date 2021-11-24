package neodatis;

import java.util.Iterator;
import java.util.Scanner;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

public class Ejercicio5 implements DAO{
    private ODB odb;
    private String nombre;
    private String nombreNuevo;
    private String deporte;
    private int edad;
    private Pais pais;
    
    public Ejercicio5() {
        //SELECT
        odb = ODBFactory.open("EQUIPOS.DB");
        select();
    }  
    
    public Ejercicio5(String nombre){
        //DELETE
        odb = ODBFactory.open("EQUIPOS.DB");
        this.nombre=nombre;
        delete();
        select();
    }

    public Ejercicio5(String nombre, String deporte, int edad, String pais) {
        //INSERT
        odb = ODBFactory.open("EQUIPOS.DB");
        this.nombre = nombre;
        this.deporte = deporte;
        this.edad = edad;
        this.pais = new Pais(500, pais);
        insert();
        select();
    }
    

    
    public Ejercicio5(String nombre, String nombreNuevo){
        //UPDATE
        odb = ODBFactory.open("EQUIPOS.DB");
        this.nombre=nombre;
        this.nombreNuevo=nombreNuevo;
        update();
        select();
    }    
    
    public static void main(String[] args) {
        //Ejercicio5 e5 = new Ejercicio5();
        //Ejercicio5 e5 = new Ejercicio5("Flossie");
        //Ejercicio5 e5 = new Ejercicio5("Adalberto", "Eufrasio");
        Ejercicio5 e5 = new Ejercicio5("Bonifacio", "Ajedrez", 300, "Wakanda");
    }
    
    @Override
    public boolean select() {
        IQuery query = new CriteriaQuery(Jugador.class);
        Objects<Jugador> jugadores = odb.getObjects(query);
        
        while(jugadores.hasNext()){
            System.out.println(jugadores.next().toString());
        }
        odb.close();
        return true;
    }

    @Override
    public boolean delete() {
        ICriterion criterio = Where.equal("nombre",nombre);
        CriteriaQuery query = new CriteriaQuery(Jugador.class, criterio);
        
        Objects<Jugador> jugadores = odb.getObjects(query);
        while(jugadores.hasNext()){
            odb.delete(jugadores.next());
        }
        odb.close();
        return true;
    }

    @Override
    public boolean update() {
        IQuery query = new CriteriaQuery(Jugador.class, Where.equal("nombre", nombre));
        Objects<Jugador> objetos = odb.getObjects(query);
        
        Jugador j = objetos.getFirst();
        j.setNombre(nombreNuevo);
        odb.store(j);
        
        odb.close();
        return true;
    }

    @Override
    public boolean insert() {
        Jugador j = new Jugador();
        j.setNombre(nombre);
        j.setDeporte(deporte);
        j.setEdad(edad);
        j.setPais(pais);
        odb.store(j);
        
        odb.close();
        return true;
    }
    
}
