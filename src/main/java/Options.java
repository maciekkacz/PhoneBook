import java.util.NoSuchElementException;

public enum Options {
    CLOSE(0, "End"),
    ADD_CONTACT(1, "Add contact"),
    EARCH_BY_NAME(2, "Search by name"),
    SEARCH_BY_TEL(3, "Search by phone"),
    DELETE(4, "Delete");


    private final int shortcut;
    private final String description;


    Options(int shortcut, String description) {
        this.shortcut = shortcut;
        this.description = description;
    }

    public int getShortcut() {
        return shortcut;
    }

    public String getDescription() {
        return description;
    }
    static Options convertToOption(int option) {
        if(option >= values().length || option < 0)
            throw new NoSuchElementException();
        return values()[option];
    }


    @Override
    public String toString() {
        return shortcut + " " + description;
    }
}
