package com.xztl.serverarchive;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocumentReader {

    private String dc;
    private String FOLDER = "docs";

    public void insert(String path) throws CommandException {

        if (path == null || path.isBlank()) {

            throw new CommandException("SYNTAX");

        } else {
            if (path.matches("[/\\\\]|\\.\\.|$(?<!\\.txt)")) {
                throw new CommandException("SYNTAX");
            }
        }

        dc = path;
    }

    public String read() throws CommandException {

        File file = new File(FOLDER, dc);

        Path percorso = Path.of(dc);
        String contenuto = null;

        try {
            contenuto = Files.readString(percorso);
        } catch (IOException e) {
            throw new CommandException("READ");
        }

        return contenuto;
    }

}