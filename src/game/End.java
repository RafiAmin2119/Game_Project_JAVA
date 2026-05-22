package game;

public abstract class End {
<<<<<<< Updated upstream
    protected final String gameId;
    public End(String id) {
        this.gameId = id;
    }
=======
    // Final key variable naming id system (Criterion 4)
    protected final String gameId;

    public End(String id) {
        this.gameId = id;
    }

>>>>>>> Stashed changes
    public abstract void finish();
}
