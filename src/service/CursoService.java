package service;

import ui.CursoUI;
import java.awt.*;
import java.text.ParseException;

public class CursoService extends Component {

    CursoUI CursoUI = new CursoUI(); //Agregar excepción.

    public CursoService() throws ParseException {
    }

    //Validación nombre del curso y nombre del profesor= que no sea null
    public boolean validarCampos(String nombre, String profesor) {
        if (nombre.isEmpty() || profesor.isEmpty()) {
            CursoUI.mostrarMensaje("Todos los campos son obligatorios.");
            return false;
        }
        return true;
    }
}