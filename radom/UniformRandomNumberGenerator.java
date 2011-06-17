package dqsim.radom;



/**
 * Class UniformRandoNumberGenerator */
public class UniformRandomNumberGenerator extends RandomNumberGenerator {

  //
  // Fields
  //

  
  //
  // Constructors
  //
  public UniformRandomNumberGenerator () { };

    @Override
    public double generate() {
        return java.lang.Math.random();
    }
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  //
  // Other methods
  //

}
