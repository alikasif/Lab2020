package atlassian2024.popularproduct;

import atlassian2024.popularproduct.algo.IPopularStrategy;
import atlassian2024.popularproduct.dto.Content;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PopularityTracker implements IMostPopular{

    Map<Integer, Content> contentMap = new ConcurrentHashMap<>();
    IPopularStrategy popularStrategy = null;

    public PopularityTracker(IPopularStrategy popularStrategy){
        this.popularStrategy = popularStrategy;
    }

    @Override
    public void increasePopularity(Integer contentId) {
        contentMap.putIfAbsent(contentId, new Content(contentId));
        Content content = contentMap.get(contentId);
        content.upVote(Instant.now().toEpochMilli());
    }

    @Override
    public void decreasePopularity(Integer contentId) {
        Content content = contentMap.get(contentId);
        if (content == null)
            throw new IllegalArgumentException("content id "+ contentId +" not found");
        content.downVote(Instant.now().toEpochMilli());
        if (content.getTotalUpVotes() == 0)
            contentMap.remove(contentId);
    }

    @Override
    public Integer mostPopular() {
        if (contentMap.isEmpty())
            return null;
        Content content = popularStrategy.mostPopular(contentMap.values());
        return content.getContentId();
    }
}
