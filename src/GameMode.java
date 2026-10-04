public abstract class GameMode {

    private GamePlatform platform;

    public GameMode(GamePlatform platform) {
        this.platform = platform;
    }

    public void setPlatform(GamePlatform platform) {
        this.platform = platform;
    }

    protected void start(String mode) {
        platform.startGame(mode);
        platform.handleInput();
    }

    public abstract void play();
}