package lambdasinaction.chap1.verbiage;

/**
 * @author matchfu
 */
public class AppleGreenColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
