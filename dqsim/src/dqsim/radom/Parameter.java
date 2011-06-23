/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim.radom;

/**
 *
 * @author henrique
 */
public class Parameter {
    private double value;

    public Parameter(double _value) {
        setValue(_value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double _value) {
        value = _value;
    }

}
