package com.epam.web.command;

public class CommandResult {

    private final String page;
    private final boolean isRedirect;

    public CommandResult(String page, boolean isRedirected) {
        this.page = page;
        this.isRedirect = isRedirected;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getPage() {
        return this.page;
    }

    public boolean isRedirect() {
        return this.isRedirect;
    }

}
