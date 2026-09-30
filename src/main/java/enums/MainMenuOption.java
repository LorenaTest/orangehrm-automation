package enums;

public enum MainMenuOption {

    PIM("PIM"),
    DASHBOARD("Dashboard");

    private final String text;

    MainMenuOption(String text) {
        this.text = text;
    }

    public String asString(){
        return text;
    }
}
