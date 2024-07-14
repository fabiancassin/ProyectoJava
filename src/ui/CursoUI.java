package ui;

import models.Curso;
import service.CursoService;

//Importa las clases y los packages:
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; //"Oye" la acción
import java.text.ParseException; //Catch
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
// jframe es parte del paquete javaswing se utiliza para crear y manipular ventanas en aplicaciones graficas
public class CursoUI extends JFrame {
    //nombreField se refiere a un objeto de tipo JTextField.
// Un JTextField es un componente de Swing en Java que permite a los usuarios ingresar y editar texto.
    private JTextField nombreField, fechaInicioField, profesorField;
    private JTextArea outputArea;
    // arma una lista dinamica con los atributos que se van agregando
    private ArrayList <Curso> cursos = new ArrayList<>();

    // throws parseexception indica que el constructor puede lanzar una excepcion
    public CursoUI() throws ParseException {
        // llamada del constructor de la clase jframe (super clase)
        super("CRUD de Cursos");

        //Cursos pre-armados en el ArrayList:
        Date fecha1 = new SimpleDateFormat("dd/MM/yyyy").parse("12/08/2023"); //Nombre y parámetro a la fecha
        Date fecha2 = new SimpleDateFormat("dd/MM/yyyy").parse("01/09/2023"); //Nombre y parámetro a la fecha
        Date fecha3 = new SimpleDateFormat("dd/MM/yyyy").parse("03/10/2023"); //Nombre y parámetro a la fecha

        Curso curso1 = new Curso ("CAC", fecha1,"Saady Pacheco");
        Curso curso2 = new Curso ("Java", fecha2,"James Gosling");
        Curso curso3 = new Curso ("Python", fecha3,"Guido van Rossum");

        cursos.add(curso1);
        cursos.add(curso2);
        cursos.add(curso3);

        // Componentes de la interfaz gráfica
        nombreField = new JTextField(20);
        fechaInicioField = new JTextField(10);
        profesorField = new JTextField(20);

        //MENU:

        JButton btnCrear = new JButton("Crear");
        JButton btnVer = new JButton("Ver");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");
        JButton btnSalir = new JButton("Salir");

        // se refiere a una variable que almacena o representa un area de salida en la ui
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Configuración del diseño
        setLayout(new FlowLayout());

        add(new JLabel("Nombre del Curso:"));
        add(nombreField);
        add(new JLabel("Fecha de Inicio (dd/MM/yyyy):"));
        add(fechaInicioField);
        add(new JLabel("Nombre del Profesor:"));
        add(profesorField);

        //Crea las opciones del Menú:
        add(btnCrear);
        add(btnVer);
        add(btnActualizar);
        add(btnEliminar);
        add(btnBuscar);
        add(btnListar);
        add(btnSalir);

        add(scrollPane);

        // Manejadores de eventos : esta excpectante al click y llama al metodo
        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearCurso();
            }
        });

        btnVer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verCurso ();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarCurso();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarCurso();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarCurso();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarCursos();
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }
    public boolean validarCampos(String nombre, String profesor) {
        if (nombre.isEmpty() || profesor.isEmpty()) {
            if (nombre.isEmpty()) {
                mostrarMensaje("El campo 'Nombre del Curso' es obligatorio.");
            } else {
                mostrarMensaje("El campo 'Nombre del Profesor' es obligatorio.");
            }
            return false;
        }
        return true;
    }
    public void crearCurso() {
        // Se utiliza para obtener el texto ingresado por el usuario en un campo de texto (JTextField) llamado nombreField
        // getText(): Es un método de la clase JTextField que devuelve el texto actualmente ingresado en el campo de texto. En este caso
        String nombre = nombreField.getText();
        String profesor = profesorField.getText();
        Date fechaInicio;

        try {
            fechaInicio = new SimpleDateFormat("dd/MM/yyyy").parse(fechaInicioField.getText());
            // Validar campos
            if (validarCampos(nombre, profesor)) {
                Curso curso = new Curso(nombre, fechaInicio, profesor);
                cursos.add(curso);

                mostrarMensaje("Curso creado con éxito.");
                limpiarCampos();
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Utilice el formato dd/MM/yyyy.");
        }
    }

    public void verCurso() {
        int id = obtenerIdCurso();
        Curso cursoEncontrado = buscarCursoPorId(id);

        if (cursoEncontrado != null) {
            outputArea.setText(cursoEncontrado.toString());
        } else {
            mostrarMensaje("Curso no encontrado.");
        }
        limpiarCampos();
    }

    public void actualizarCurso() {
        int id = obtenerIdCurso();
        Curso cursoEncontrado = buscarCursoPorId(id);

        if (cursoEncontrado != null) {
            String nombre = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre del curso:", cursoEncontrado.getNombre());
            Date fechaInicio;
            try {
                fechaInicio = new SimpleDateFormat("dd/MM/yyyy").parse(JOptionPane.showInputDialog(this, "Ingrese la nueva fecha de inicio del curso (dd/MM/yyyy):", new SimpleDateFormat("dd/MM/yyyy").format(cursoEncontrado.getFechaInicio())));
                String profesor = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre del profesor:", cursoEncontrado.getProfesor());

                // Validar campos antes de la actualización
                if (validarCampos(nombre, profesor)) {
                    cursoEncontrado.setNombre(nombre);
                    cursoEncontrado.setFechaInicio(fechaInicio);
                    cursoEncontrado.setProfesor(profesor);

                    mostrarMensaje("Curso actualizado con éxito.");
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Utilice el formato dd/MM/yyyy.");
            }
        } else {
            mostrarMensaje("Curso no encontrado.");
        }
        limpiarCampos();
    }

    public void eliminarCurso() {
        int id = obtenerIdCurso();
        Curso cursoEncontrado = buscarCursoPorId(id);

        if (cursoEncontrado != null) {
            cursos.remove(cursoEncontrado);
            mostrarMensaje("Curso eliminado con éxito.");
        } else {
            mostrarMensaje("Curso no encontrado.");
        }
        limpiarCampos();
    }

    public void buscarCurso() {
        int id = obtenerIdCurso();
        Curso cursoEncontrado = buscarCursoPorId(id);

        if (cursoEncontrado != null) {
            //esta línea se utiliza para mostrar la información del curso encontrado en la interfaz gráfica de usuario, específicamente en un área de texto
            outputArea.setText(cursoEncontrado.toString());
        } else {
            mostrarMensaje("Curso no encontrado.");
        }
        limpiarCampos();
    }

    public void listarCursos() {
        //Esta capacidad de modificar directamente el contenido del StringBuilder
        // sin crear nuevos objetos es especialmente útil cuando estás construyendo
        //cadenas en un bucle, ya que evita la creación innecesaria de objetos String temporales.
        StringBuilder cursosStr = new StringBuilder("Lista de Cursos:\n");
        for (Curso curso : cursos) {
            cursosStr.append(curso).append("\n");
        }
        outputArea.setText(cursosStr.toString());
        limpiarCampos();
    }

    public void salir (){
        System.exit(0);
    }

    public int obtenerIdCurso() {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese el ID del curso:"));
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, ingrese un número válido.");
            return obtenerIdCurso();
        }
    }

    public Curso buscarCursoPorId(int id) {
        for (Curso curso : cursos) {
            if (curso.getId() == id) {
                return curso;
            }
        }
        return null;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        nombreField.setText("");
        fechaInicioField.setText("");
        profesorField.setText("");
    }
}