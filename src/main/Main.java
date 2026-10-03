package main;

import caretaker.Historial;
import memento.Memento;
import originator.Editor;

public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        Historial historial = new Historial();

        editor.setContenido("Hola");
        System.out.println("Contenido actual: \"" + editor.getContenido() + "\"");
        historial.guardarEstado(editor.guardar());
        System.out.println("-> Estado guardado");

        editor.setContenido("Hola, mundo");
        System.out.println("Contenido actual: \"" + editor.getContenido() + "\"");
        historial.guardarEstado(editor.guardar());
        System.out.println("-> Estado guardado");

        editor.setContenido("Hola, mundo. Adios");
        System.out.println("Contenido actual: \"" + editor.getContenido() + "\"");

        System.out.println("-> Restaurando ultimo estado guardado...");
        restaurarUltimo(editor, historial);
        System.out.println("Contenido actual: \"" + editor.getContenido() + "\"");

        editor.setContenido(editor.getContenido() + ". Nueva modificacion");
        System.out.println("Despues de modificar: \"" + editor.getContenido() + "\"");

        System.out.println("-> Restaurando otra vez...");
        restaurarUltimo(editor, historial);
        System.out.println("Contenido actual: \"" + editor.getContenido() + "\"");

        System.out.println("-> Restaurando otra vez...");
        restaurarUltimo(editor, historial);
    }

    private static void restaurarUltimo(Editor editor, Historial historial) {
        if (historial.hayEstados()) {
            Memento m = historial.obtenerUltimoEstado();
            editor.restaurar(m);
        } else {
            System.out.println("No hay estados guardados para restaurar.");
        }
    }
}
