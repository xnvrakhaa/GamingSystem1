public class ConsolePlatform implements GamePlatform {

    @Override
    public void startGame(String mode) {
        System.out.println("Console: starting " + mode);
    }

    @Override
    public void handleInput() {
        System.out.println("Controls: controller");
    }
}
