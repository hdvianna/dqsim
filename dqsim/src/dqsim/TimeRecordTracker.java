/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dqsim;

import java.util.ArrayList;

/**
 *
 * @author henrique
 */
public class TimeRecordTracker {
    
    private ArrayList<ArrayList<TimeRecord>> timeTracker =new ArrayList<ArrayList<TimeRecord>>();

    public TimeRecordTracker(double _clock) {
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setStartTime(_clock);
        ArrayList<TimeRecord> zeroArrayList = new ArrayList<TimeRecord>();
        zeroArrayList.add(timeRecord);
        timeTracker.add(0, zeroArrayList);
    }

    public void updateTimeRecordTracker(int _oldSize, int _newSize, double _clock) {
        TimeRecord lastTimeRecord = timeTracker.get(_oldSize).get(timeTracker.get(_oldSize).size()-1);
        lastTimeRecord.setEndTime(_clock);

        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setStartTime(_clock);
        
        ArrayList<TimeRecord> timeRecordArray;
        if (timeTracker.size()-1 < _newSize) {
            timeRecordArray = new ArrayList<TimeRecord>();
            timeTracker.add(_newSize, timeRecordArray);
        } else {
            timeRecordArray = timeTracker.get(_newSize);
        }
        timeRecordArray.add(timeRecord);        
    }

    public ArrayList<ArrayList<TimeRecord>> getTimeTracker() {
        return timeTracker;
    }

}
