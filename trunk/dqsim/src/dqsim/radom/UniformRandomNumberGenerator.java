package dqsim.radom;

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

}
