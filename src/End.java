package game;

public abstract class End {
    // Final key variable naming id system (Criterion 4)
    protected final String gameId;

    public End(String id) {
        this.gameId = id;
    }

    public abstract void finish();
}
