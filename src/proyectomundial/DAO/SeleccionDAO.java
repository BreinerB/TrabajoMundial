/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import proyectomundial.model.Seleccion;
import proyectomundial.util.BasedeDatos;
import static proyectomundial.util.BasedeDatos.ejecutarSQL;

/**
 *
 * @author miguelropero
 */
public class SeleccionDAO {

    public SeleccionDAO() {
        BasedeDatos.conectar();
    }

    public boolean registrarSeleccion(Seleccion seleccion) {

        String sql = "INSERT INTO b_bautista4.seleccion (nombre, continente, dt, nacionalidad) values("
                + "'" + seleccion.getNombre() + "', "
                + "'" + seleccion.getContinente() + "', "
                + "'" + seleccion.getDt() + "', "
                + "'" + seleccion.getNacionalidad() + "')";

        //BasedeDatos.conectar();
        boolean registro = BasedeDatos.ejecutarActualizacionSQL(sql);
        //BasedeDatos.desconectar();
        return registro;
    }

    public List<Seleccion> getSelecciones() {

        String sql = "SELECT nombre, continente, dt, nacionalidad FROM b_bautista4.seleccion";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombre"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public List<Seleccion> getSeleccionesBuscado(String buscado) {

        String sql = "SELECT * FROM b_bautita4.seleccion WHERE nombre LIKE '%" + buscado + "%' OR continente LIKE '%" + buscado + "%' OR dt LIKE '%" + buscado + "%' OR nacionalidad LIKE '%" + buscado + "%';";
        List<Seleccion> selecciones = new ArrayList<Seleccion>();

        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result != null) {

                while (result.next()) {
                    Seleccion seleccion = new Seleccion(result.getString("nombre"), result.getString("continente"), result.getString("dt"), result.getString("nacionalidad"));
                    selecciones.add(seleccion);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones");
        }

        return selecciones;
    }

    public String[][] getSeleccionesMatriz() {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSelecciones();

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public String[][] getSeleccionesMatrizBuscado(String buscado) {

        String[][] matrizSelecciones = null;
        List<Seleccion> selecciones = getSeleccionesBuscado(buscado);

        if (selecciones != null && selecciones.size() > 0) {

            matrizSelecciones = new String[selecciones.size()][4];

            int x = 0;
            for (Seleccion seleccion : selecciones) {

                matrizSelecciones[x][0] = seleccion.getNombre();
                matrizSelecciones[x][1] = seleccion.getContinente();
                matrizSelecciones[x][2] = seleccion.getDt();
                matrizSelecciones[x][3] = seleccion.getNacionalidad();
                x++;
            }
        }

        return matrizSelecciones;
    }

    public int getCantidadSelecciones() {
        String sql = "SELECT COUNT (*) as COUNT FROM b_bautista4.seleccion";
        int resultado = -1;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result.next()) {
                resultado = result.getInt("COUNT");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando Selecciones");
        }

        return resultado;
    }
    
    public int getCantidadNacionalidad() {
        String sql = "SELECT COUNT(DISTINCT nacionalidad) AS cantidad FROM b_bautista4.seleccion WHERE dt IS NOT NULL;";
        int resultado = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result.next()) {
                resultado = result.getInt("cantidad");
                System.out.println("CANTIDAD: "+resultado);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando Nacionalidades");
        }

        return resultado;
    }
    
    public int getCantidadpartidos() {
        String sql = "SELECT COUNT(*) AS n_partidos FROM b_bautista4.partidos;";
        int resultado = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result.next()) {
                resultado = result.getInt("n_partidos");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Contando Partidos");
        }

        return resultado;
    }
    
     public float getPromedio_goles_partido() {
        String sql = "    SELECT AVG(goles_local + goles_visitante) AS promedio_goles_partido FROM b_bautista4.partidos;";
        float resultado = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);

            if (result.next()) {
                resultado = result.getFloat("promedio_goles_partido");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error Haciendo El Promedio");
        }

        return resultado;
    }

     public DefaultTableModel getPartido_menos_y_mas_goles() throws Exception{
String[] columnas = {"partido_con_mas_goles", "partido_con_menos_goles"};
        ArrayList<Object[]> filas = new ArrayList<>();
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT" +
                    "  (SELECT CONCAT(local, ' vs ', visitante) FROM b_bautista4.partidos WHERE goles_local + goles_visitante = (SELECT MAX(goles_local + goles_visitante) FROM b_bautista4.partidos) LIMIT 1) AS partido_con_mas_goles,\n" +
                    "  (SELECT CONCAT(local, ' vs ', visitante) FROM b_bautista4.partidos WHERE goles_local + goles_visitante = (SELECT MIN(goles_local + goles_visitante) FROM b_bautista4.partidos) LIMIT 1) AS partido_con_menos_goles;");

            while (result.next()) {
                String partido_con_mas_goles = result.getString("partido_con_mas_goles");
                String partido_con_menos_goles = result.getString("partido_con_menos_goles");
                Object[] fila = {partido_con_mas_goles, partido_con_menos_goles};
                filas.add(fila);
            }
        } catch (SQLException e) {
            // Manejar la excepción cerrando la conexión a la base de datos
            e.printStackTrace();
        }

        Object[][] matrizResultados = new Object[filas.size()][columnas.length];
        for (int i = 0; i < filas.size(); i++) {
            matrizResultados[i] = filas.get(i);
        }

        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(columnas);
        for (Object[] fila : matrizResultados) {
            modeloTabla.addRow(fila);
        }
        return modeloTabla;
    }
public int[] Partidos_Gan_Emp() {
        int partidos_Emp = 0;
        int partidosGanados = 0;
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT\n" +
"    SUM(CASE WHEN goles_local > goles_visitante OR goles_local < goles_visitante THEN 1 ELSE 0 END) AS partidos_con_ganador,\n" +
"    SUM(CASE WHEN goles_local = goles_visitante THEN 1 ELSE 0 END) AS partidos_con_empate\n" +
"FROM\n" +
"    b_bautista4.partidos;");
            if (result.next()) {
                partidosGanados = result.getInt("partidos_con_ganador");
                partidos_Emp = result.getInt("partidos_con_empate");
            }
        } catch (Exception e) {
            // Manejar la excepción o propagarla hacia arriba
        }
        return new int[]{partidosGanados, partidos_Emp};
    }
 public DefaultTableModel seleccion_mas_y_menosgoles() {
         String[] columnas = {"Seleccion", "Goles"};
    Object[][] matrizResultados = new Object[2][columnas.length];
    try {
        ResultSet result = BasedeDatos.ejecutarSQL("SELECT local AS seleccion, SUM(goles_local) AS total_goles\n" +
            "FROM b_bautista4.partidos\n" +
            "GROUP BY local\n" +
            "ORDER BY total_goles DESC\n" +
            "LIMIT 1;");
        ResultSet result2 = BasedeDatos.ejecutarSQL("SELECT local AS seleccion, SUM(goles_local) AS total_goles\n" +
            "FROM b_bautista4.partidos\n" +
            "GROUP BY local\n" +
            "ORDER BY total_goles ASC\n" +
            "LIMIT 1;");

        if (result.next()) {
            String seleccionMasGoles = result.getString("seleccion");
            int golesMas = result.getInt("total_goles");
            Object[] filaMas = {seleccionMasGoles, golesMas};
            matrizResultados[0] = filaMas;
        }

        if (result2.next()) {
            String seleccionMenosGoles = result2.getString("seleccion");
            int golesMenos = result2.getInt("total_goles");
            Object[] filaMenos = {seleccionMenosGoles, golesMenos};
            matrizResultados[1] = filaMenos;
        }

    } catch (Exception e) {
        // Manejar la excepción cerrando la conexión a la base de datos
    }
    DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
    return modeloTabla;
}
 public DefaultTableModel continente_mas_y_menosgoles() {
         String[] columnas = {"Continente", "Goles"};
    Object[][] matrizResultados = new Object[2][columnas.length];
    try {
        ResultSet result = BasedeDatos.ejecutarSQL("SELECT continente_local AS continente, SUM(goles_local) AS total_goles\n" +
        "FROM b_bautista4.partidos\n" +
        "GROUP BY continente_local\n" +
        "ORDER BY total_goles DESC\n" +
        "LIMIT 1;");
        ResultSet result2 = BasedeDatos.ejecutarSQL("SELECT continente_local AS continente, SUM(goles_local) AS total_goles\n" +
        "FROM b_bautista4.partidos\n" +
        "GROUP BY continente_local\n" +
        "ORDER BY total_goles ASC\n" +
        "LIMIT 1;");

        if (result.next()) {
            String continenteMasGoles = result.getString("continente");
            int golesMas = result.getInt("total_goles");
            Object[] filaMas = {continenteMasGoles, golesMas};
            matrizResultados[0] = filaMas;
        }

        if (result2.next()) {
            String continenteMenosGoles = result2.getString("continente");
            int golesMenos = result2.getInt("total_goles");
            Object[] filaMenos = {continenteMenosGoles, golesMenos};
            matrizResultados[1] = filaMenos;
        }

    } catch (Exception e) {
        // Manejar la excepción cerrando la conexión a la base de datos
    }
    DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
    return modeloTabla;
}
  public DefaultTableModel seleccion_mas_y_menospuntos() {

   String[] columnas = {"Seleccion", "Puntos"};
Object[][] matrizResultados = new Object[2][columnas.length];
try {
    ResultSet result = BasedeDatos.ejecutarSQL("SELECT local AS seleccion, SUM(goles_local) * 3 AS puntos\n" +
            "FROM b_bautista4.partidos\n" +
            "GROUP BY local\n" +
            "ORDER BY puntos DESC\n" +
            "LIMIT 1;");
    ResultSet result2 = BasedeDatos.ejecutarSQL("SELECT local AS seleccion, SUM(goles_local) * 3 AS puntos\n" +
            "FROM b_bautista4.partidos\n" +
            "GROUP BY local\n" +
            "ORDER BY puntos ASC\n" +
            "LIMIT 1;");

    if (result.next()) {
        String seleccionMasPuntos = result.getString("seleccion");
        int puntosMas = result.getInt("puntos"); 
        Object[] filaMas = {seleccionMasPuntos, puntosMas};
        matrizResultados[0] = filaMas;
    }

    if (result2.next()) {
        String seleccionMenosPuntos = result2.getString("seleccion");
        int puntosMenos = result2.getInt("puntos");  
        Object[] filaMenos = {seleccionMenosPuntos, puntosMenos};
        matrizResultados[1] = filaMenos;
    }

} catch (Exception e) {
    // Manejar la excepción cerrando la conexión a la base de datos
}

DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
return modeloTabla;
  }

public DefaultTableModel Clasificados() {
            String[] columnas = {"Grupo", "Seleccion"};
    ArrayList<Object[]> filas = new ArrayList<>();
    try {
        ResultSet result = BasedeDatos.ejecutarSQL("SELECT grupo, local AS seleccion\n" +
            "FROM (\n" +
            "    SELECT grupo, local, ROW_NUMBER() OVER (PARTITION BY grupo ORDER BY goles_local DESC) AS posicion\n" +
            "    FROM b_bautista4.partidos\n" +
            ") subquery\n" +
            "WHERE posicion <= 2\n" +
            "ORDER BY grupo, posicion;");
        
        while (result.next()) {
            String grupo = result.getString("grupo");
            String seleccion = result.getString("seleccion");
            Object[] fila = {grupo, seleccion};
            filas.add(fila);
        }
    } catch (Exception e) {
        // Manejar la excepción cerrando la conexión a la base de datos
    }
    
    Object[][] matrizResultados = new Object[filas.size()][columnas.length];
    for (int i = 0; i < filas.size(); i++) {
        matrizResultados[i] = filas.get(i);
    }
    
    DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
    return modeloTabla;
}
    public DefaultTableModel seleccionesContinente() {
        String[] columnas = {"Continente", "Cantidad"};
        Object[][] matrizResultados = new Object[0][columnas.length];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT continente, COUNT(*) AS cantidad_selecciones FROM b_bautista4.seleccion GROUP BY continente;");
            while (result.next()) {
                String continente = result.getString("continente");
                int cantSele = result.getInt("cantidad_selecciones");
                Object[] fila = {continente, cantSele};
                matrizResultados = Arrays.copyOf(matrizResultados, matrizResultados.length + 1);
                matrizResultados[matrizResultados.length - 1] = fila;
            }
        } catch (Exception e) {
            // Manejar la excepción cerrando la conexión a la base de datos
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
        return modeloTabla;
    }

     public DefaultTableModel nacionalidadesDT() {
        String[] columnas = {"Nacionalidad", "Cantidad"};
        Object[][] matrizResultados = new Object[0][columnas.length];
        try {
            ResultSet result = BasedeDatos.ejecutarSQL("SELECT nacionalidad, COUNT(DISTINCT dt) AS cantidad_directores FROM b_bautista4.seleccion GROUP BY nacionalidad;");
            while (result.next()) {
                String continente = result.getString("nacionalidad");
                int cantSele = result.getInt("cantidad_directores");
                Object[] fila = {continente, cantSele};
                matrizResultados = Arrays.copyOf(matrizResultados, matrizResultados.length + 1);
                matrizResultados[matrizResultados.length - 1] = fila;
            }
        } catch (Exception e) {
            // Manejar la excepción cerrando la conexión a la base de datos
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(matrizResultados, columnas);
        return modeloTabla;
    }

    public void accionhome(String metodo) {
        String sql = "update b_bautista4.auditoria set contador = contador + 1 where pagina = '" + metodo + "';";
        try {
            BasedeDatos.ejecutarSQL(sql);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public List<Seleccion> tabla() {

        List<Seleccion> seleciones = new ArrayList<Seleccion>();
        String sql = "select * from b_bautista4.auditoria;";
        try {
            ResultSet result = BasedeDatos.ejecutarSQL(sql);
            if (result != null) {

                while (result.next()) {
                    String nombres = result.getString("pagina");
                    int cont = result.getInt("contador");
                    System.out.println(nombres + " : " + cont);
                    Seleccion seleccion = new Seleccion(result.getString("pagina"), result.getInt("contador"));

                    seleciones.add(seleccion);

                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error consultando selecciones continentes");
        }

        return seleciones;

    }
}
