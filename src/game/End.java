package game;

public abstract class End {
    protected final String gameId;
    public End(String id) {
        this.gameId = id;
    }
    public abstract void finish();
}
