import ui.CursoUI;

import javax.swing.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            // es un metodo de la biblioteca swing de java se utiliza para ejecutar operaciones relacionadas con ui
            SwingUtilities.invokeLater(() -> {
                // maneja excepciones por si hay un problema al analizar la fecha
                try {
                    new CursoUI();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}