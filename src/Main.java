public class Main {

    public static void main(String[] args) {
        GamePlatform pc = new PCPlatform();
        GamePlatform console = new ConsolePlatform();
        GameMode story = new StoryMode(pc);
        story.play();

        System.out.println("\nSwitch Story Mode to Console:");
        story.setPlatform(console);
        story.play();

        System.out.println("\nCompetitive Mode on Console:");
        GameMode competitive = new CompetitiveMode(console);
        competitive.play();

        System.out.println("\nSwitch Competitive Mode to PC:");
        competitive.setPlatform(pc);
        competitive.play();
    }
}