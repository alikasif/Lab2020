package atlassian;

enum Tags {
    UP_VOTE,
    DOWN_VOTE,
    TAG1,
    TAG2
}

/*
    Resources:

        one product many tag
        one tag many product
        many2many product:tag

        Jira - Id, created_by, created_at, status, title, description, product_type
        Tags - Id, value, tagged by, tagged at
        Tag to Resource Link - tagId, productId

        Tag:
            Get  - tag by id, prefix
            Post - create a tag
            Delete - by id
            No Patch or Put

        tag/{tid}/product/{pid}:
            Post - add tag to product
            Get - get tags on a product
            Get - all product based on a tag/ search based on tag
 */

public class TaggingSystem {
}
