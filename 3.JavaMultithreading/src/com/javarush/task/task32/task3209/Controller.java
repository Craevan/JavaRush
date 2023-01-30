package com.javarush.task.task32.task3209;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public static void main(String[] args) {
        View gui = new View();
        Controller controller = new Controller(gui);
        gui.setController(controller);
        gui.init();
        controller.init();
    }

    public void init(){

    }

    public void exit() {
        System.exit(0);
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        try {
            document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        } catch (ClassCastException e) {
            ExceptionHandler.log(e);
        }
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }
}
