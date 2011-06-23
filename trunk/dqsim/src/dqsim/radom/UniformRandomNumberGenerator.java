package dqsim.radom;

import java.util.Random;
import umontreal.iro.lecuyer.rng.RandomStream;

/**
 * Esta classe utiliza o método de geração de valores uniformes chamada
 * Mersenne Twister
 */
public class UniformRandomNumberGenerator extends RandomNumberGenerator {

  private long seed;
  private MTRandom randGenerator;
  
    public UniformRandomNumberGenerator (long seed) {
        randGenerator = new MTRandom (seed);
    }

    public UniformRandomNumberGenerator () {
        randGenerator = new MTRandom ();
    }

    @Override
    public double generate() {
        return randGenerator.nextDouble();
    }

    @Override
    public RandomStream getRamdomStream() {
        return randGenerator;
    }

    @Override
    public Random getRamdomSeed() {
        return randGenerator;
    }

}
