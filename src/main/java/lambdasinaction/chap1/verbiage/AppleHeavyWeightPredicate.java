package lambdasinaction.chap1.verbiage;

/**
 * @author matchfu
 */
public class AppleHeavyWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
