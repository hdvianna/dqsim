/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim;

/**
 *
 * @author henrique
 */
public class TimeRecord {
    
    private double startTime=-1;
    private double endTime=-1;

    public void setStartTime(double _startTime) {
        startTime = _startTime;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setEndTime(double _endTime) {
        endTime = _endTime;
    }

    public double getEndTime() {
        return endTime;
    }
    
}
