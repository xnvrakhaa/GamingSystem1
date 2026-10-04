public class StoryMode extends GameMode {

    public StoryMode(GamePlatform platform) {
        super(platform);
    }

    @Override
    public void play() {
        System.out.println("Mission: complete the story");
        start("Story Mode");
    }
}