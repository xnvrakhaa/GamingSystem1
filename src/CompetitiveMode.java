public class CompetitiveMode extends GameMode {

    public CompetitiveMode(GamePlatform platform) {
        super(platform);
    }

    @Override
    public void play() {
        System.out.println("Goal: win the ranked match");
        start("Competitive Mode");
    }
}
