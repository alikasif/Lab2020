package google2024.medium;

import java.util.*;

public class StackOverFlow {
    public static void main(String[] args) {

        Map<String, List<String>> tagtoquestionmap = new HashMap<>();
        Map<String, List<String>> tagtovolunteermap = new HashMap<>();

        Map<String, String> answermap = new HashMap<>();

        Set<String> busyVolunteer = new HashSet<>();
        Set<String> assignedQuestion = new HashSet<>();

        for(Map.Entry<String, List<String>> qm : tagtoquestionmap.entrySet()) {
            boolean found = false;
            for(String qid : qm.getValue()) {

                if (!assignedQuestion.contains(qid)) {
                    assignedQuestion.add(qid);

                    List<String> volunteers = tagtovolunteermap.get(qm.getKey());
                    for(String volunteer: volunteers) {
                        if(!busyVolunteer.contains(volunteer)) {
                            busyVolunteer.add(volunteer);
                            answermap.put(qid, volunteer);
                            break;
                        }
                    }
                }
            }
        }
    }
}
