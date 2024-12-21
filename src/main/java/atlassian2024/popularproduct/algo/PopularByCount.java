package atlassian2024.popularproduct.algo;

import atlassian2024.popularproduct.dto.Content;

import java.util.Collection;
import java.util.List;

public class PopularByCount implements IPopularStrategy {

    @Override
    public Content mostPopular(Collection<Content> contentList) {

        if (contentList == null || contentList.isEmpty())
            return null;

        List<Content> sortedContentIds = contentList.stream().sorted((e1, e2) -> e2.getTotalUpVotes().compareTo(e1.getTotalUpVotes())).toList();
        return sortedContentIds.get(0);
    }
}
