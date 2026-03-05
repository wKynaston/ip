package parser;

import actions.Task;
import exceptions.commandException;
import exceptions.commandParser;

public class Parser {

    public static ParsedCommand parse(String fullCommand) throws commandException {
        commandParser.validateNotEmpty(fullCommand);

        String trimmed = fullCommand.trim();
        String[] parts = trimmed.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String rest = (parts.length == 2) ? parts[1].trim() : "";

        return new ParsedCommand(cmd, rest);
    }

    public static Task parseTask(String cmd, String rest) throws commandException {
        return commandParser.parseTask(cmd, rest);
    }

    public static int parseTaskNumber(String rest, String cmd) throws commandException {
        return commandParser.parseTaskNumber(rest, cmd);
    }

    public static class ParsedCommand {
        public final String cmd;
        public final String rest;

        public ParsedCommand(String cmd, String rest) {
            this.cmd = cmd;
            this.rest = rest;
        }
    }
}