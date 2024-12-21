package atlassian2024.popularproduct.algo;

import atlassian2024.popularproduct.dto.Content;

import java.util.Collection;
import java.util.List;

public class PopularByCountAndLastVoteTime implements IPopularStrategy{
    @Override

    public Content mostPopular(Collection<Content>  contentList) {

        if (contentList == null || contentList.isEmpty())
            return null;

        List<Content> sortedList = contentList.stream().sorted((e1, e2) ->
                e1.getTotalUpVotes().equals(e2.getTotalUpVotes()) ?
                e2.getLastUpVoteTime().compareTo(e1.getLastUpVoteTime()) :
                e2.getTotalUpVotes().compareTo(e1.getTotalUpVotes())
        ).toList();
        return sortedList.get(0);
    }
}
