/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.radom;

import java.util.Random;
import org.cloudbus.cloudsim.distributions.LomaxDistribution;
import umontreal.iro.lecuyer.rng.RandomStream;

/**
 *
 * @author henrique
 */
public class LomaxRandomNumberGenerator extends RandomNumberGenerator {

    private UniformRandomNumberGenerator uniformGenerator ;

    public LomaxRandomNumberGenerator(UniformRandomNumberGenerator _uniformGenerator) {
        uniformGenerator = _uniformGenerator;
    }

    public LomaxRandomNumberGenerator() {
        this(new UniformRandomNumberGenerator());
    }

    @Override
    public double generate() {
        LomaxDistribution lomax = new LomaxDistribution(uniformGenerator.getRamdomSeed(), getParameter("alpha").getValue(), getParameter("beta").getValue(), getParameter("beta").getValue());
        return (int) lomax.sample();
    }

    @Override
    public RandomStream getRamdomStream() {
        return uniformGenerator.getRamdomStream();
    }

    @Override
    public Random getRamdomSeed() {
        return uniformGenerator.getRamdomSeed();
    }

}
