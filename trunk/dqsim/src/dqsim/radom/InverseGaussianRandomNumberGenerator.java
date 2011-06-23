/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.radom;

import java.util.Random;
import umontreal.iro.lecuyer.randvar.InverseGaussianGen;
import umontreal.iro.lecuyer.rng.RandomStream;

/**
 *
 * @author henrique
 */
public class InverseGaussianRandomNumberGenerator extends RandomNumberGenerator {

    private UniformRandomNumberGenerator uniformGenerator ;
    InverseGaussianGen inverseGaussian;

    public InverseGaussianRandomNumberGenerator(UniformRandomNumberGenerator _uniformGenerator) {
        uniformGenerator = _uniformGenerator;
    }

    public InverseGaussianRandomNumberGenerator() {
        this(new UniformRandomNumberGenerator());
    }

    @Override
    public double generate() {
        inverseGaussian = new InverseGaussianGen(uniformGenerator.getRamdomStream(), getParameter("mu").getValue(), getParameter("lambda").getValue());
        return (int) inverseGaussian.nextDouble();
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
