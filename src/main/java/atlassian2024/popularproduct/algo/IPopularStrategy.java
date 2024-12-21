package atlassian2024.popularproduct.algo;

import atlassian2024.popularproduct.dto.Content;

import java.util.Collection;

public interface IPopularStrategy {

    Content mostPopular(Collection<Content> values);
}
