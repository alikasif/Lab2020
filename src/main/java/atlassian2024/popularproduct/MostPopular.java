package atlassian2024.popularproduct;

import atlassian2024.popularproduct.dto.Popular;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * popularityTracker.increasePopularity(7);
 *
 *   popularityTracker.increasePopularity(8);
 *
 *   popularityTracker.mostPopular();        // returns 8
 *
 *   popularityTracker.increasePopularity(8);
 *
 *   popularityTracker.increasePopularity(7);
 *
 *   popularityTracker.mostPopular();        // returns 7
 */
public class MostPopular implements IMostPopular {

    private Map<Integer, Integer> contentMap;

    private Map<Integer, Popular> contentMapWithTime;

    //7--1, T1
    //8--1, T2

    private Integer mostPopular;

    public MostPopular() {
        this.contentMap = new HashMap<>();
        this.contentMapWithTime = new HashMap<>();
    }

    @Override
    public void increasePopularity(Integer contentId) {
        Long millis = Instant.now().toEpochMilli();
        contentMap.put(contentId,contentMap.getOrDefault(contentId,0)+1);
        contentMapWithTime.putIfAbsent(contentId, new Popular(contentId,0, millis));
        contentMapWithTime.get(contentId).setPopularity(contentMapWithTime.get(contentId).getPopularity()+1);
        contentMapWithTime.get(contentId).setTime(millis);
    }

    @Override
    public Integer mostPopular() {
        System.out.println(contentMapWithTime);
        if(!contentMapWithTime.isEmpty()) {
            List<Map.Entry<Integer, Popular>> sortedContentIds = new ArrayList<>(contentMapWithTime.entrySet());
            sortedContentIds = sortedContentIds.stream().sorted((e1, e2) ->
                    e2.getValue().getPopularity() == e1.getValue().getPopularity()?
                            e2.getValue().getTime().compareTo(e1.getValue().getTime()):
                            e2.getValue().getPopularity()-e1.getValue().getPopularity()).collect(Collectors.toList());
            return sortedContentIds.get(0).getKey();
        }
        return -1;
    }

    @Override
    public void decreasePopularity(Integer contentId) {
        Long millis = Instant.now().toEpochMilli();
        if(contentMap.containsKey(contentId)){
            contentMap.put(contentId,contentMap.get(contentId)-1);
            if(contentMap.get(contentId)==0)
                contentMap.remove(contentId);
        }else{
            throw new RuntimeException("Invalid Content Id!!");
        }
    }
}
