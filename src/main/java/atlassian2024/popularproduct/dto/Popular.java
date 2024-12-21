package atlassian2024.popularproduct.dto;


public class Popular implements Comparable<Popular>{
    Integer contentId;
    Integer popularity;
    Long time;

    public Popular(Integer contentId, Integer popularity, Long time) {
        this.contentId = contentId;
        this.popularity = popularity;
        this.time = time;
    }

    public Integer getContentId() {
        return contentId;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public Long getTime() {
        return time;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Popular{" +
                "contentId=" + contentId +
                ", popularity=" + popularity +
                ", time=" + time +
                '}';
    }

    @Override
    public int compareTo(Popular o) {
        if (this.popularity == o.popularity) {
            return this.time.compareTo(o.getTime());
        }
        else {
            this.popularity.compareTo(o.popularity);
        }
        return 0;
    }
}