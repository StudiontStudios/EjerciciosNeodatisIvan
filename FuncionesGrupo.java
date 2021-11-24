package neodatis;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.And;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class FuncionesGrupo {

    public static void main(String[] args) throws ArithmeticException {
        ODB odb = ODBFactory.open("EQUIPOS.DB");	// Abrir BD

        // Sumar las edades de los jugadores
        IValuesQuery valoresQuery = new ValuesCriteriaQuery(Jugador.class).sum("edad");
        Values valores = odb.getValues(valoresQuery);
        ObjectValues valoresObjeto = valores.nextValues();		// solo una fila
        BigDecimal valor = (BigDecimal) valoresObjeto.getByAlias("edad");
        System.out.println("Suma de las edades de los jugadores: " + valor.longValue());	  // convierte el BigDecimal a long 

        // Contar el número de jugadores
        IValuesQuery valoresQuery2 = new ValuesCriteriaQuery(Jugador.class).count("nombre");
        Values valores2 = odb.getValues(valoresQuery2);
        ObjectValues valoresObjeto2 = valores2.nextValues();
        BigInteger valor2 = (BigInteger) valoresObjeto2.getByAlias("nombre");
        System.out.println("\nN�mero de jugadores: " + valor2.intValue());		// convierte el BigInteger a int

        float media = valor.floatValue() / valor2.intValue();
        System.out.println("\nEdad media de los jugadores: " + media);

        // Edad media de los jugadores.  ¡¡ ERROR SI MUCHOS DECIMALES !!
        IValuesQuery valoresQuery3 = new ValuesCriteriaQuery(Jugador.class).avg("edad");
        Values valores3 = odb.getValues(valoresQuery3);
        ObjectValues valoresObjeto3 = valores3.nextValues();
        BigDecimal valor3 = (BigDecimal) valoresObjeto3.getByAlias("edad");
        System.out.println("\nEdad media de los jugadores: " + valor3.floatValue());		// convierte el BigDecimal a float

        // Edades m�nima y m�xima de los jugadores (PRIMERO METODO min Y LUEGO max, pues en IValuesQuery no est� definido min)
        IValuesQuery valoresQuery4 = new ValuesCriteriaQuery(Jugador.class).min("edad", "edad_min").max("edad", "edad_max");
        Values valores4 = odb.getValues(valoresQuery4);
        ObjectValues valoresObjeto4 = valores4.nextValues();
        BigDecimal valorMin = (BigDecimal) valoresObjeto4.getByAlias("edad_min");
        BigDecimal valorMax = (BigDecimal) valoresObjeto4.getByAlias("edad_max");
        System.out.println("\nEdad máxima: " + valorMax.intValue() + "\t  Edad mínima: " + valorMin.intValue());

        // N�mero de jugadores agrupando por ciudad
        // SQL: select ciudad, count(nombre) from jugador group by ciudad
        IValuesQuery valoresQuery5 = new ValuesCriteriaQuery(Jugador.class).field("ciudad").count("nombre").groupBy("ciudad");
        Values valores5 = odb.getValues(valoresQuery5);
        System.out.println("\nN�mero de jugadores agrupando por ciudad:");
        while (valores5.hasNext()) {
            ObjectValues valor5 = valores5.nextValues();
            System.out.println(valor5.getByAlias("ciudad") + " * " + valor5.getByAlias("nombre"));	// getByIndex(1)
        }

        // Nombre, edad y pais de los jugadores 
        IValuesQuery valoresQuery6 = new ValuesCriteriaQuery(Jugador.class).field("nombre").field("edad").field("pais.nombrepais");
        Values valores6 = odb.getValues(valoresQuery6);
        System.out.println("\nNombre, edad y pais de los jugadores:");
        while (valores6.hasNext()) {
            ObjectValues valor6 = valores6.nextValues();
            System.out.println(valor6.getByAlias("nombre") + " * " + valor6.getByIndex(1) + " * " + valor6.getByIndex(2));
        }

        // Edad media de los jugadores agrupando por pais.  �� ERROR SI MUCHOS DECIMALES !!
        IValuesQuery valoresQuery7 = new ValuesCriteriaQuery(Jugador.class).field("pais.nombrepais").avg("edad").groupBy("pais.nombrepais");
        Values valores7 = odb.getValues(valoresQuery7);
        System.out.println("\nEdad media de los jugadores por pais:");
        while (valores7.hasNext()) {
            ObjectValues valor7 = valores7.nextValues();
            System.out.println(valor7.getByIndex(0) + " * " + valor7.getByIndex(1));
        }

        // Nombre, edad y ciudad de los jugadores de Espa�a de 15 a�os
        ICriterion criterio = new And().add(Where.equal("pais.nombrepais", "ESPA�A")).add(Where.equal("edad", 15));
        IValuesQuery valoresQuery8 = new ValuesCriteriaQuery(Jugador.class, criterio).field("nombre").field("edad").field("ciudad");
        Values valores8 = odb.getValues(valoresQuery8);
        System.out.println("\nNombre, edad y ciudad de los jugadores de 15 a�os espa�oles:");
        while (valores8.hasNext()) {
            ObjectValues valor8 = valores8.nextValues();
            System.out.println(valor8.getByAlias("nombre") + " * " + valor8.getByIndex(1) + " * " + valor8.getByIndex(2));
        }

        // N�mero de jugadores de Espa�a de 15 a�os
        // ICriterion criterio = new And().add(Where.equal("pais.nombrepais", "ESPA�A")).add(Where.equal("edad", 15));  ES EL ANTERIOR
        IValuesQuery valoresQuery9 = new ValuesCriteriaQuery(Jugador.class, criterio).count("nombre");
        Values valores9 = odb.getValues(valoresQuery9);
        ObjectValues valoresObjeto9 = valores9.nextValues();
        BigInteger valor9 = (BigInteger) valoresObjeto9.getByAlias("nombre");
        System.out.println("\nN�mero de jugadores de Espa�a de 15 a�os: " + valor9.intValue());

        odb.close(); 	// Cerrar BD
    }
}
