// defines the different GameModes with their attributes
public enum GameMode {
    Normal("Normal Mode", Modifier.None),
    Obstacle("Obstacle Mode", Modifier.Obstacles);

    private final String name;
    private final Modifier modifier;

    GameMode(String name, Modifier modifier) {
        this.name = name;
        this.modifier = modifier;
    }

    // get methods
    public String getName() {
        return name;
    }

    public Modifier getModifier() {
        return modifier;
    }
}
