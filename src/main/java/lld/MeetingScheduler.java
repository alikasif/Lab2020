package lld;

import java.util.*;

class Attendee {
    String name;
    String email;
}

class Meeting {
    Long startTime;
    Long endTime;
    List<Attendee> attandeeList;

    public Meeting(Long startTime, Long endTime, List<Attendee> attandeeList) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.attandeeList = attandeeList;
    }

    public Long getEndTime() {
        return endTime;
    }

    public List<Attendee> getAttandeeList() {
        return attandeeList;
    }
}

class MeetingRoom {
    int id;
    int totalMeetings;
    Calender calender;

    public MeetingRoom(int id) {
        this.totalMeetings = 0;
        calender = new Calender();
        this.id = id;
    }

    public int getTotalMeetings() {
        return totalMeetings;
    }

    public boolean isAvailable(Date s, Date e) {
        Long prev = this.calender.getCalender().floorKey(s.getTime());
        Long next = this.calender.getCalender().ceilingKey(e.getTime());
        return (prev == null || this.calender.getCalender().get(prev).getEndTime() <= s.getTime()) && (next == null || next >= e.getTime());
    }

    public Meeting bookRoom(Date s, Date e, List<Attendee> attandeeList) {
        this.totalMeetings++;
        return this.calender.addMeeting(s, e, attandeeList);
    }
}

class Calender {
    TreeMap<Long, Meeting> calender;

    public Calender() {
        this.calender = new TreeMap<>();
    }

    public TreeMap<Long, Meeting> getCalender() {
        return calender;
    }

    public Meeting addMeeting(Date s, Date e, List<Attendee> attandeeList) {
        Meeting meeting = new Meeting(s.getTime(), e.getTime(), attandeeList);
        this.calender.put(s.getTime(), meeting);
        sendInvite(meeting);
        return meeting;
    }

    private void sendInvite(Meeting meeting) {
        sendEmail(meeting.getAttandeeList(), meeting);
    }

    private void sendEmail(List<Attendee> attandeeList, Meeting meeting) {
    }
}

public class MeetingScheduler {
    TreeSet<MeetingRoom> schedules;
    ArrayList<Map.Entry<MeetingRoom, Meeting>> history;
    private static final int MAX_HISTORY = 10;

    public MeetingScheduler() {
        this.schedules = new TreeSet<>(new ScheduleComparator());
        this.history = new ArrayList<>();
    }

    public static void main(String[] args) {
        MeetingScheduler scheduler = new MeetingScheduler();
        List<Attendee> attandeeList = new ArrayList<>();
        scheduler.bookMeeting(new Date(), new Date(System.currentTimeMillis() + 1000*60*30), attandeeList);
        scheduler.getHistory();
    }

    public Map.Entry<MeetingRoom, Meeting> bookMeeting(Date s, Date e, List<Attendee> attandeeList){
        for(MeetingRoom meetingRoom: schedules){
            if(meetingRoom.isAvailable(s, e)) {
                Meeting meeting = meetingRoom.bookRoom(s, e, attandeeList);
                Map.Entry<MeetingRoom, Meeting> entry = null;// Map.entry(meetingRoom,meeting);
                saveToHistory(entry);
                return entry;
            }
        }
        return null;
    }

    public List<Map.Entry<MeetingRoom, Meeting>> getHistory(){
        return getHistory(MAX_HISTORY);
    }

    public List<Map.Entry<MeetingRoom, Meeting>> getHistory(int limit){
        List<Map.Entry<MeetingRoom, Meeting>> history = new ArrayList<>();
        for(int i = history.size() -1 ; i>= (history.size()-1 - limit) && i>0; i-- ){
            history.add(this.history.get(i));
        }
        return history;
    }

    private void saveToHistory(Map.Entry<MeetingRoom, Meeting> entry) {
        history.add(entry);
    }

}

class ScheduleComparator implements Comparator<MeetingRoom> {
    // Sorted order of number of occupancy of Meeting Room
    @Override
    public int compare(MeetingRoom m1, MeetingRoom m2) {
        return Integer.compare(m1.getTotalMeetings(), m2.getTotalMeetings());
    }
}
