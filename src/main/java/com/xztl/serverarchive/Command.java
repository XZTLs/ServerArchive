package com.xztl.serverarchive;

import java.util.Map;
import java.util.HashMap;

public class Command {
    private Map<String, Integer> valid = new HashMap<>();
    private String[] command;

    public Command() {
        valid.put("LEGGI", 2);
    }

    public void insert(String cmd) throws CommandException {
        cmd = cmd.trim();

        String[] c = cmd.split(" ");
        if (!valid.containsKey(c[0])) throw new CommandException("UNKNOWNCMD");
        if (c.length != valid.get(c[0])) throw new CommandException("SYNTAX");

        this.command = c;
        System.out.println(c[0] + "," + c[1]);
    }

    public String getCmdPart(int i) { return command[i]; }
    public int getCmdMaxArgs() { return valid.get(command[0]); }
}