package atlassian2024.popularproduct;

public interface IMostPopular {
    void increasePopularity(Integer contentId);
    Integer mostPopular();
    void decreasePopularity(Integer contentId);
}
