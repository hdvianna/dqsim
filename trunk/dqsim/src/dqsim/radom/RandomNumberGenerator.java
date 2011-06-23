package dqsim.radom;

import java.util.HashMap;
import java.util.Random;
import umontreal.iro.lecuyer.rng.RandomStream;



/**
 * Class RandomNumberGenerator */
abstract public class RandomNumberGenerator {


    private HashMap <String, Parameter> parameters = new HashMap <String, Parameter>() ;

    public RandomNumberGenerator () { };

    abstract public double generate(  );

    abstract public RandomStream getRamdomStream();

    abstract public Random getRamdomSeed();

    public void addParameter(String _name, Parameter value) {
        parameters.put(_name, value);
    }

    public Parameter getParameter(String _name) {
        return parameters.get(_name);
    }

}
