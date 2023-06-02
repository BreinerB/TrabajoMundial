package proyectomundial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import proyectomundial.DAO.SeleccionDAO;
import proyectomundial.model.Seleccion;

public class GUIManual extends JFrame {

    SeleccionDAO seleccionDAO = new SeleccionDAO();

    // Matrix que permite almancenar la información de las selecciones futbol cargadas
    public String[][] selecciones = null;

    // Matriz que permite almacenar los resultados de los partidos cargardos
    public String[][] resultados = null;

    // Elementos de bara Lateral
    private JPanel jPanelLeft;

    private JPanel jPanelIconFIFA;
    private JLabel iconFIFA;

    // Elementos de opciones de Menú
    private JPanel jPanelMenu;

    private JPanel jPanelMenuHome;
    private JLabel btnHome;

    private JPanel jPanelMenuSelecciones;
    private JLabel btnSelecciones;

    private JPanel jPanelMenuResultados;
    private JLabel btnResultados;

    private JPanel jPanelMenuDashboardSel;
    private JLabel btnDashboardSel;

    private JPanel jPanelMenuDashboardRes;
    private JLabel btnDashboardRes;

    private JPanel jPanelMenuauditorio;
    private JLabel btnauditorio;
    

    // Elementos de panel de contenido
    private JPanel jPanelRight;
    private JPanel jPanelLabelTop;
    private JLabel jLabelTop;

    private JPanel jPanelMain;

    public GUIManual() {

        // Se inician los componentes gráficos
        initComponents();

        // Se configuran propiedades de nuestra Ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        // Se llama la función home para que al momento de iniciar la aplicacoón, por defecto se muestre el home
        accionHome();

    }

    private void initComponents() {

        // Inicializamos componentes del Menu Lateral
        jPanelLeft = new JPanel();

        jPanelIconFIFA = new JPanel();
        iconFIFA = new JLabel();
        jPanelMenu = new JPanel();

        jPanelMenuHome = new JPanel();
        btnHome = new JLabel();

        jPanelMenuSelecciones = new JPanel();
        btnSelecciones = new JLabel();

        jPanelMenuResultados = new JPanel();
        btnResultados = new JLabel();

        jPanelMenuDashboardSel = new JPanel();
        btnDashboardSel = new JLabel();

        jPanelMenuDashboardRes = new JPanel();
        btnDashboardRes = new JLabel();

        jPanelMenuauditorio = new JPanel();
        btnauditorio = new JLabel();

        // Pinta el logo de la aplicación
        pintarLogo();

        // Pinta la opción de menú del Home
        pintarMenuHome();

        // Pinta la opción de Menú de las Selecciones
        pintarMenuSelecciones();

        // Pinta la opción de Menú de los resultados
        pintarMenuResultados();

        // Pinta la opción de Menú del dashboard de equipo
        pintarMenuDashboardSel();

        // Pinta la opción de Menú del dahboard de resultados
        pintarMenuDashboardRes();
        
        pintarMenuAuditoria();

        // Pinta y ajuste diseño del contenedor del panel izquierdo
        pintarPanelIzquierdo();


        // Inicializa los componentes del panel derecho de los contenidos
        jPanelRight = new JPanel();
        jPanelLabelTop = new JPanel();
        jPanelMain = new JPanel();

        // Pinta la barra superrior de color azul claro, del panel de contenido
        pintarLabelTop();

        // Pinta y ajusta diseño del contenedor de contenidos
        pintarPanelDerecho();

        setTitle("Mundial");
        pack();
        setVisible(true);
    }

    private void pintarLogo() {
        jPanelIconFIFA.add(iconFIFA);
        jPanelIconFIFA.setOpaque(false);
        jPanelIconFIFA.setPreferredSize((new java.awt.Dimension(220, 80)));
        jPanelIconFIFA.setMaximumSize(jPanelIconFIFA.getPreferredSize());
        iconFIFA.setIcon(new ImageIcon(getClass().getResource("/resources/Easports_fifa_logo.svg.png")));
        jPanelLeft.add(jPanelIconFIFA, BorderLayout.LINE_START);

    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación del HOME Define estilos, etiquetas, iconos que
     * decoran la opción del Menú. Esta opción de Menu permite mostrar la página
     * de bienvenida de la aplicación
     */
    private void pintarMenuHome() {
        btnHome.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnHome.setText("Home");
        btnHome.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuHome.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuHome.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuHome.setLayout(new BorderLayout(15, 0));
        jPanelMenuHome.add(vacioHome, BorderLayout.WEST);
        jPanelMenuHome.add(btnHome, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuHome);

        btnHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Home");
                accionHome();
            }
        });
    }
    


    /**
     * Función que se ejecuta cuando el usuario hacer click sobre la opción de
     * navegación Home Permite modificar la etiqueta de Navegación en Home,
     * remover los elementos que hay en el panel de contenidos y agregar la
     * imagen de inicio de la aplicación
     */
    private void accionHome() {
        jLabelTop.setText("Home");
        //jLabelTopDescription.setText("Bievenido al sistema de gestión de mundiales de fútbol");
        jPanelMain.removeAll();
        seleccionDAO.accionhome("Home");
        JPanel homePanel = new JPanel();
        JLabel imageHome = new JLabel();
        imageHome.setIcon(new ImageIcon(getClass().getResource("/resources/home.jpg"))); // NOI18N
        //imageHome.setPreferredSize(new java.awt.Dimension(810, 465));
        homePanel.add(imageHome);

        jPanelMain.add(homePanel, BorderLayout.CENTER);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    private void accionAuditoria() {

        jLabelTop.setText("Auditorio");
        JPanel a = new JPanel();
        JLabel label = new JLabel();
        Dimension dimension2 = new Dimension(8000, 8000);
        label.setPreferredSize(dimension2);
        label.setBackground(Color.lightGray);
        btnauditorio.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnauditorio.setForeground(new java.awt.Color(255, 255, 255));
        List<Seleccion> seleccionesBusqueda = seleccionDAO.tabla();
        String texto = "";
        for (Seleccion seleccion : seleccionesBusqueda) {
            texto += "\n" + seleccion.getBoton() + ": " + seleccion.getContador() + System.lineSeparator();

        }
        class CustomTableModel extends AbstractTableModel {

            private List<Seleccion> data;
            private String[] columnNames = {"Boton", "Contador"};

            public CustomTableModel(List<Seleccion> data) {
                this.data = data;
            }

            @Override
            public int getRowCount() {
                return data.size();
            }

            @Override
            public int getColumnCount() {
                return columnNames.length;
            }

            @Override
            public String getColumnName(int column) {
                return columnNames[column];
            }

            @Override
            public Object getValueAt(int row, int column) {
                switch (column) {
                    case 0:
                        return data.get(row).getBoton();
                    case 1:
                        return data.get(row).getContador();
                    default:
                        return null;
                }
            }
        }

        TableModel model = new CustomTableModel(seleccionesBusqueda);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        JPanel container = new JPanel();
        container.add(scrollPane);
        a.add(container);
        System.out.print(texto);
        a.add(label);
        Dimension dimension = new Dimension(200, 200);
        label.setPreferredSize(dimension);
        label.setForeground(Color.black);
        label.setBackground(Color.red);

        jPanelMain.removeAll();
        jPanelMain.add(a);
        a.add(label);
        jPanelMain.add(label);
        jPanelMain.repaint();
        jPanelMain.revalidate();

    }

    private void pintarMenuAuditoria() {

         btnauditorio.setIcon(new ImageIcon(getClass().getResource("/resources/icons/home.png"))); // NOI18N
        btnauditorio.setText("Auditoria");
         JLabel vacioAuditoria = new JLabel();
        btnauditorio.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioHome = new JLabel();
        jPanelMenuauditorio.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuauditorio.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuauditorio.setLayout(new BorderLayout(15, 0));
        jPanelMenuauditorio.add(vacioAuditoria, BorderLayout.WEST);
        jPanelMenuauditorio.add(btnauditorio, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuauditorio);

        btnauditorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Auditoria");
                accionAuditoria();
            }
        });
    
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de SELECCIONES Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar las
     * selecciones de futbol cargadas en la aplicación
     */
    private void pintarMenuSelecciones() {
        btnSelecciones.setIcon(new ImageIcon(getClass().getResource("/resources/icons/selecciones.png"))); // NOI18N
        btnSelecciones.setText("Selecciones");
        btnSelecciones.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioSelecciones = new JLabel();
        jPanelMenuSelecciones.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuSelecciones.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuSelecciones.setLayout(new BorderLayout(15, 0));
        jPanelMenuSelecciones.add(vacioSelecciones, BorderLayout.WEST);
        jPanelMenuSelecciones.add(btnSelecciones, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuSelecciones);

        btnSelecciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Selecciones");
                accionSelecciones();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Selecciones Permite ver la lista de selecciones que se
     * encuentran cargadas en la aplicación. Si la lista de selecciones en
     * vacía, muestra un botón que permite cargar un archivo CSV con la
     * información de las selelecciones
     */
    private void accionSelecciones() {
        jLabelTop.setText("Selecciones");
        seleccionDAO.accionhome("Selecciones");
        selecciones = seleccionDAO.getSeleccionesMatriz();
      
        // Si no hay selecciones cargadas, muestra el botón de carga de selecciones
        if (selecciones == null) {
            jPanelMain.removeAll();
            JPanel seleccionesPanel = new JPanel();

            JLabel notSelecciones = new JLabel();
            notSelecciones.setText("No hay selecciones cargadas, por favor cargue selecciones \n\n");
            seleccionesPanel.add(notSelecciones);

            JButton cargarFile = new JButton();
            cargarFile.setText("Seleccione el archivo");
            seleccionesPanel.add(cargarFile);
            cargarFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    cargarFileSelecciones();
                }
            });

            jPanelMain.add(seleccionesPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay selecciones cargadas, llama el método que permite pintar la tabla de selecciones
        else {
            pintarTablaSelecciones();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de RESULTADOS Define estilos, etiquetas, iconos
     * que decoran la opción del Menú. Esta opción de Menu permite mostrar los
     * diferentes resultados de los partidos de la fase de grupos de un mundial
     */
    private void pintarMenuResultados() {
        btnResultados.setIcon(new ImageIcon(getClass().getResource("/resources/icons/resultados.png"))); // NOI18N
        btnResultados.setText("Resultados");
        btnResultados.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioResultados = new JLabel();
        jPanelMenuResultados.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuResultados.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuResultados.setLayout(new BorderLayout(15, 0));
        jPanelMenuResultados.add(vacioResultados, BorderLayout.WEST);
        jPanelMenuResultados.add(btnResultados, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuResultados);

        btnResultados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accionResultados();
            }
        });
    }

    /**
     * Función que se ejecuta cuando el usuario hace click sobre la opción de
     * navegación Resultados Permite ver la lista de resultados que se
     * encuentran cargadas en la aplicación. Si la lista de resultados en vacía,
     * muestra un botón que permite cargar un archivo CSV con la información de
     * los resultados
     */
    private void accionResultados() {
        jLabelTop.setText("Resultados");
        seleccionDAO.accionhome("Resultados");
        // Si no hay resultados cargados, muestra el botón de carga de resultados
        if (resultados == null) {
            jPanelMain.removeAll();
            JPanel resultadosPanel = new JPanel();

            if (resultados == null) {

                JLabel notResultados = new JLabel();
                notResultados.setText("No hay resultados, por favor cargue resultados \n\n");
                resultadosPanel.add(notResultados);

                JButton cargarFile = new JButton();
                cargarFile.setText("Seleccione el archivo");
                resultadosPanel.add(cargarFile);
                cargarFile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        cargarFileResultados();
                    }
                });
            }

            jPanelMain.add(resultadosPanel);
            jPanelMain.repaint();
            jPanelMain.revalidate();
        } // Si hay ressultados cargados, llama el método que permite pintar la tabla de resultados
        else {
            pintarTablaSelecciones();
        }
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Selecciones Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de las selecciones de futbol que fueron cargadas
     */
    private void pintarMenuDashboardSel() {
        btnDashboardSel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_selecciones.png")));
        btnDashboardSel.setText("Dash Selecciones");
        btnDashboardSel.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardSelecciones = new JLabel();
        jPanelMenuDashboardSel.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardSel.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardSel.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardSel.add(vacioDashboardSelecciones, BorderLayout.WEST);
        jPanelMenuDashboardSel.add(btnDashboardSel, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardSel);

        btnDashboardSel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Selecciones");
                accionDashboardSel();
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardSel() {
        seleccionDAO.accionhome("Dash Selecciones");
        /*JTextArea a = new JTextArea();
        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de selecciones \n"
                + "se deben mostrar los siguientes datos:\n\n"
                + "1. Total de selecciones Cargadas \n"
                
                + "2. Número de selecciones por continente (Se puede usar una tabla para pintar esto) \n"
                + "3. Cantidad de nacionalidades diferentes de los directores técnicos \n"
                + "4. Ranking de nacionalidades de directores técnicos \n\n"
                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");*/
    //// punto 1
        
        JPanel cantidad_selecciones = new JPanel();
        JLabel texto_selecciones = new JLabel();
        JLabel t_seleccion = new JLabel();
        
        t_seleccion.setText("Cantidad de selecciones");
        texto_selecciones.setText("" + seleccionDAO.getCantidadSelecciones());
        cantidad_selecciones.add(t_seleccion);
        cantidad_selecciones.add(texto_selecciones);
        cantidad_selecciones.setPreferredSize(new Dimension(140, 50));
        cantidad_selecciones.setBackground(Color.cyan);
        //punto 2
        
                JPanel punto2 = new JPanel();
                JTable tablaPunto2=new JTable();
                 JLabel textP2 = new JLabel();
                textP2.setText("Cantidad de selecciones");
                tablaPunto2.setModel(seleccionDAO.seleccionesContinente());
        punto2.add(textP2);
        punto2.add(tablaPunto2);
        punto2.setPreferredSize(new Dimension(200, 200));
        punto2.setBackground(Color.cyan);
///punto 3

 JPanel punto3 = new JPanel();
                JTable tablaPunto3=new JTable();
                 JLabel textP3 = new JLabel();
                textP3.setText("Cantidad de DT por nacionalidad");
                tablaPunto3.setModel(seleccionDAO.nacionalidadesDT());
        punto3.add(textP3);
        punto3.add(tablaPunto3);
        punto3.setPreferredSize(new Dimension(200, 450));
        punto3.setBackground(Color.cyan);
        
        //punto 4
    
        JPanel cantidad_nacionalidades = new JPanel();
        JLabel texto_nacionalidades = new JLabel();
        JLabel t_nacionalidad= new JLabel();
        
        t_nacionalidad.setText("Cantidad de nacionalidades");
        texto_nacionalidades.setText("" + seleccionDAO.getCantidadNacionalidad());
        cantidad_nacionalidades.add(t_nacionalidad);
        cantidad_nacionalidades.add(texto_nacionalidades);
        cantidad_nacionalidades.setPreferredSize(new Dimension(160, 50));
        cantidad_nacionalidades.setBackground(Color.cyan);
        
        jPanelMain.removeAll();
        
        cantidad_selecciones.add(t_seleccion);
        cantidad_selecciones.add(texto_selecciones);
           jPanelMain.add(cantidad_nacionalidades);
        jPanelMain.add(cantidad_selecciones);
        
        jPanelMain.add(punto2);
        jPanelMain.add(punto3);
     
       // jPanelMain.add(a);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    

        
    }
    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    private void pintarMenuDashboardRes() {
        btnDashboardRes.setIcon(new ImageIcon(getClass().getResource("/resources/icons/dashboard_resultados.png")));
        btnDashboardRes.setText("Dash Resultados");
        btnDashboardRes.setForeground(new java.awt.Color(255, 255, 255));

        JLabel vacioDashboardResultados = new JLabel();
        jPanelMenuDashboardRes.setBackground(new java.awt.Color(17, 41, 63));
        jPanelMenuDashboardRes.setPreferredSize((new java.awt.Dimension(220, 35)));
        jPanelMenuDashboardRes.setLayout(new BorderLayout(15, 0));
        jPanelMenuDashboardRes.add(vacioDashboardResultados, BorderLayout.WEST);
        jPanelMenuDashboardRes.add(btnDashboardRes, BorderLayout.CENTER);
        jPanelMenu.add(jPanelMenuDashboardRes);

        btnDashboardRes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Dashboard Resultados");
                try {
                    accionDashboardRes();
                } catch (Exception ex) {
                    Logger.getLogger(GUIManual.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    /**
     * TRABAJO DEL ESTUDIANTE Se debe módificar este método para poder calcular
     * y pintar las diferentes informaciones que son solicitadas Revise el
     * proceso que se siguen en los demás métodos para poder actualizar la
     * información de los paneles
     */
    private void accionDashboardRes() throws Exception {
        seleccionDAO.accionhome("Dash Resultados");
//        JTextArea a = new JTextArea();
//        a.setText("En esta sección, teniendo en cuenta los datos que fueron cargados en la matriz de resultados \n"
//                + "se deben mostrar los siguientes datos:\n\n"
//                + "1. Número de partidos cargados \n"
//                + "2. Promedio de goles por partido \n"
//                + "3. Partido con más goles y partido con menos goles \n"
//                + "4. Número de partidos dónde hubo un ganador y número de partidos dónde hubo empate \n"
//                + "5. Selcción o selecciones con más goles y con menos goles \n"
//                + "6. Selección con más puntos y menos puntos \n"
//                + "7. Continente o continentes con más goles y menos goles \n"
//                + "8. Clasificados por cada grupo (Clasifican los dos primeros equipos de cada grupo) \n\n"
//                + "Utilice los diferentes componentes gráficos para construir un dashboard lo más estético posible");
 

//punto 1
    JPanel cantidad_partidos = new JPanel();
        JLabel texto_partidos = new JLabel();
        JLabel t_partidos= new JLabel();
        
        t_partidos.setText("Cantidad de Partidos");
        texto_partidos.setText("" + seleccionDAO.getCantidadpartidos());
        cantidad_partidos.add(t_partidos);
        cantidad_partidos.add(texto_partidos);
        cantidad_partidos.setPreferredSize(new Dimension(160, 50));
        cantidad_partidos.setBackground(Color.cyan);
//punto 2
    JPanel promedio = new JPanel();
        JLabel texto_promedio = new JLabel();
        JLabel t_promedio= new JLabel();
        
        t_promedio.setText("Promedio De Goles Por Partido");
        texto_promedio.setText("" + seleccionDAO.getPromedio_goles_partido());
        promedio.add(t_promedio);
        promedio.add(texto_promedio);
        promedio.setPreferredSize(new Dimension(180, 50));
        promedio.setBackground(Color.cyan);  
///punto 3

//         JPanel punto3 = new JPanel();
//                JTable tablaPunto3=new JTable();
//                 JLabel textP3 = new JLabel();
//                textP3.setText("Partido Con Mas Y Menos Goles");
//                tablaPunto3.setModel(seleccionDAO.getPartido_menos_y_mas_goles());
//        punto3.add(textP3);
//        punto3.add(tablaPunto3);
//        punto3.setPreferredSize(new Dimension(200, 50));
//        punto3.setBackground(Color.CYAN);
JPanel partidoMasMenosGolesPanel = new JPanel();
partidoMasMenosGolesPanel.setBackground(Color.CYAN);
partidoMasMenosGolesPanel.setPreferredSize(new Dimension(300, 100));
partidoMasMenosGolesPanel.setLayout(new BorderLayout());

JLabel partidoMasMenosGolesLabel = new JLabel("Partido con más y menos goles:");
partidoMasMenosGolesPanel.add(partidoMasMenosGolesLabel, BorderLayout.PAGE_START);

JTable partidoMasMenosGolesTable = new JTable(seleccionDAO.getPartido_menos_y_mas_goles());
JScrollPane scrollPane = new JScrollPane(partidoMasMenosGolesTable);
scrollPane.setPreferredSize(new Dimension(350, 150)); // Establece las dimensiones deseadas para el JScrollPane
scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // Muestra siempre la barra de desplazamiento horizontal si es necesario
scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Muestra siempre la barra de desplazamiento vertical si es necesario

partidoMasMenosGolesPanel.add(scrollPane, BorderLayout.CENTER);
//punto4

JPanel panel3 = new JPanel();
        JLabel label3 = new JLabel("Partidos ganados: 0, Partidos empatados: 0");
        panel3.setBackground(Color.CYAN);
        panel3.setPreferredSize(new Dimension(340, 40));
        label3.setHorizontalAlignment(JLabel.CENTER);
        label3.setFont(new Font("Arial", Font.BOLD, 13));
        label3.setForeground(Color.BLACK);
        int[] resultados = seleccionDAO.Partidos_Gan_Emp();
        int partidosGanados = resultados[0];
        int partidosEmpatados = resultados[1];
        label3.setText("Partidos ganados: " + partidosGanados + ", Partidos empatados: " + partidosEmpatados);
        panel3.add(label3);
//punto5

JPanel punto5 = new JPanel();
                JTable tablaPunto5=new JTable();
                 JLabel textP5 = new JLabel();
                textP5.setText("Selecciones con mas y menos goles");
                tablaPunto5.setModel(seleccionDAO.seleccion_mas_y_menosgoles());
        punto5.add(textP5);
        punto5.add(tablaPunto5);
        punto5.setPreferredSize(new Dimension(270, 70));
        punto5.setBackground(Color.CYAN);

//punto 6

JPanel punto6 = new JPanel();
                JTable tablaPunto6=new JTable();
                 JLabel textP6 = new JLabel();
                textP6.setText("Selecciones con mas y menos puntos");
                tablaPunto6.setModel(seleccionDAO.seleccion_mas_y_menospuntos());
        punto6.add(textP6);
        punto6.add(tablaPunto6);
        punto6.setPreferredSize(new Dimension(270, 70));
        punto6.setBackground(Color.CYAN);
        
//punto 7

JPanel punto7 = new JPanel();
                JTable tablaPunto7=new JTable();
                 JLabel textP7 = new JLabel();
                textP7.setText("Selecciones con mas y menos puntos");
                tablaPunto7.setModel(seleccionDAO.continente_mas_y_menosgoles());
        punto7.add(textP7);
        punto7.add(tablaPunto7);
        punto7.setPreferredSize(new Dimension(270, 70));
        punto7.setBackground(Color.CYAN);
        
//punto 8

JPanel punto8 = new JPanel();
                JTable tablaPunto8=new JTable();
                 JLabel textP8 = new JLabel();
                textP8.setText("Selecciones Clasificadas Por Grupo");
                tablaPunto8.setModel(seleccionDAO.Clasificados());
        punto8.add(textP8);
        punto8.add(tablaPunto8);
        punto8.setPreferredSize(new Dimension(300, 300));
        punto8.setBackground(Color.cyan);


        jPanelMain.removeAll();
        jPanelMain.add(cantidad_partidos);
        jPanelMain.add(promedio);
        jPanelMain.add(partidoMasMenosGolesPanel);
        jPanelMain.add(panel3);
        jPanelMain.add(punto5);
        jPanelMain.add(punto6);
        jPanelMain.add(punto7);
        jPanelMain.add(punto8);
//        jPanelMain.add(a);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que se encarga de ajustar los elementos gráficos que componente
     * la opción de navegación de Dashboard de Resultados Define estilos,
     * etiquetas, iconos que decoran la opción del Menú. Esta opción de Menu
     * permite mostrar los diferentes datos que será extraidos de la información
     * de los resultados de los partidos que fueron cargados
     */
    
    private void pintarPanelIzquierdo() {
        // Se elimina el color de fondo del panel del menú
        jPanelMenu.setOpaque(false);

        // Se agrega un border izquierdo, de color blanco para diferenciar el panel de menú del panel de contenido
        jPanelLeft.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, Color.WHITE));

        // Se define un BoxLayot de manera vertical para los elementos del panel izquierdo
        jPanelLeft.setLayout(new BoxLayout(jPanelLeft, BoxLayout.Y_AXIS));
        jPanelLeft.setBackground(new java.awt.Color(0, 24, 47));
        getContentPane().add(jPanelLeft, java.awt.BorderLayout.LINE_START);
        jPanelLeft.add(jPanelMenu);
        jPanelLeft.setPreferredSize((new java.awt.Dimension(220, 540)));
        jPanelLeft.setMaximumSize(jPanelLeft.getPreferredSize());
    }

    /**
     * Función que permite leer un archivo y procesar el contenido que tiene en
     * cada una de sus líneas El contenido del archivo es procesado y cargado en
     * la matriz de selecciones. Una vez la información se carga en la atriz, se
     * hace un llamado a la función pintarTablaSelecciones() que se encarga de
     * pintar en la interfaz una tabla con la información almacenada en la
     * matriz de selecciones
     */
    public void cargarFileSelecciones() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {

            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Permite que el sistema se salte la léctura de los encabzados del archivo CSV
            entrada.nextLine();

            // Se leen cada unas de las líneas del archivo
            while (entrada.hasNext()) {
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                Seleccion seleccion = new Seleccion(columns[1], columns[2], columns[3], columns[4]);
                if (seleccionDAO.registrarSeleccion(seleccion)) {
                    System.out.println("Seleccion " + seleccion.getNombre() + " registrada");
                } else {
                    System.out.println("Error " + seleccion.getNombre());
                }
            }

            selecciones = seleccionDAO.getSeleccionesMatriz();
            pintarTablaSelecciones();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pinta la tabla con la información de las
     * selelceciones que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"ID","Selección", "Continente",
     * "DT", "Nacionalidad DT"} Columnas que se corresponden son la información
     * que fue leida desde el archivo csv
     */
    public void pintarTablaSelecciones() {

        String[] columnNames = {"Selección", "Continente", "DT", "Nacionalidad DT"};
        JTable table = new JTable(selecciones, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Equipos");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que tiene la lógica que permite leer un archivo CSV de resultados
     * y cargarlo sobre la matriz resultados que se tiene definida cómo variable
     * global. Luego de cargar los datos en la matriz, se llama la función
     * pintarTablaResultados() que se encarga de visulizar el contenido de la
     * matriz en un componente gráfico de tabla
     */
    public void cargarFileResultados() {

        JFileChooser cargarFile = new JFileChooser();
        cargarFile.showOpenDialog(cargarFile);

        Scanner entrada = null;
        try {
            // Se obtiene la ruta del archivo seleccionado
            String ruta = cargarFile.getSelectedFile().getAbsolutePath();

            // Se obtiene el archivo y se almancena en la variable f
            File f = new File(ruta);
            entrada = new Scanner(f);

            // Se define las dimensiones de la matriz de selecciones
            resultados = new String[48][7];
            entrada.nextLine();

            int i = 0;
            // Se iteran cada una de las líneas del archivo
            while (entrada.hasNext()) {
                System.out.println(i);
                String line = entrada.nextLine();
                String[] columns = line.split(",");

                for (int j = 0; j < columns.length; j++) {
                    resultados[i][j] = columns[j];
                }
                i++;
            }

            pintarTablaResultados();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    /**
     * Función que se encarga de pintar la tabla con la información de los
     * resultados que fue cargada previamente La tabla tiene definido un
     * encabezado con las siguentes columnas: {"Grupo","Local", "Visitante",
     * "Continente L", "Continente V", "Goles L", "Goles V"} Columnas que se
     * corresponden son la información que fue leida desde el archivo csv
     */
    public void pintarTablaResultados() {

        String[] columnNames = {"Grupo", "Local", "Visitante", "Continente L", "Continente V", "Goles L", "Goles V"};
        JTable table = new JTable(resultados, columnNames);
        table.setRowHeight(30);

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(4, 1, 0, 0));

        JLabel label = new JLabel();
        label.setText("Busqueda de Resultados");
        form.add(label);

        JTextField field = new JTextField();
        form.add(field);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 30, 0));

        JButton buscar = new JButton();
        buscar.setText("Buscar");
        panelBotones.add(buscar);

        JButton limpiar = new JButton();
        limpiar.setText("Ver Todos");
        panelBotones.add(limpiar);
        form.add(panelBotones);

        JPanel seleccionesPanel = new JPanel();
        seleccionesPanel.setLayout(new BoxLayout(seleccionesPanel, BoxLayout.Y_AXIS));
        seleccionesPanel.setPreferredSize((new java.awt.Dimension(620, 410)));
        seleccionesPanel.setMaximumSize(jPanelRight.getPreferredSize());

        JScrollPane scrollPane = new JScrollPane(table);
        seleccionesPanel.add(form);
        seleccionesPanel.add(scrollPane);

        jPanelMain.removeAll();
        jPanelMain.add(seleccionesPanel, BorderLayout.PAGE_START);
        jPanelMain.repaint();
        jPanelMain.revalidate();
    }

    /**
     * Función que permite darle estilos y agregar los componentes gráficos del
     * contendor de la parte derecha de la interfaz, dónde se visulaiza de
     * manera dinámica el contenido de cada una de las funciones que puede
     * realizar el usuario sobre la aplicación.
     */
 private void pintarPanelDerecho() {
    // Define las dimensiones del panel
    jPanelMain.setPreferredSize(new java.awt.Dimension(800, 700));

    getContentPane().add(jPanelRight, java.awt.BorderLayout.CENTER);
    jPanelRight.setLayout(new BorderLayout());
    jPanelRight.add(jPanelLabelTop, BorderLayout.PAGE_START);
    jPanelRight.add(new JScrollPane(jPanelMain), BorderLayout.CENTER);
    jPanelRight.setPreferredSize(new java.awt.Dimension(800, 700));
}

    /**
     * Función que permite pinta la barra azul del contenedor de contenidos.
     * Barra azul que permite indicar en que sección que se encuentra navegando
     * el usuario.
     */
private void pintarLabelTop() {
    jLabelTop = new JLabel("Home");
    jLabelTop.setFont(new Font("Liberation Sans", Font.BOLD, 36));
    jLabelTop.setForeground(Color.WHITE);

    jPanelLabelTop.setLayout(new BorderLayout(15, 0));
    jPanelLabelTop.setBackground(new Color(18, 119, 217));
    jPanelLabelTop.setPreferredSize(new Dimension(620, 120));

    JLabel vacioTopLabel = new JLabel();
    jPanelLabelTop.add(vacioTopLabel, BorderLayout.WEST);
    jPanelLabelTop.add(jLabelTop, BorderLayout.CENTER);
}
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIManual().setVisible(true);
            }
        });
    }
}
