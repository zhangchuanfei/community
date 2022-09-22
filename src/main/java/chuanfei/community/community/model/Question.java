package chuanfei.community.community.model;
import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;  //Java的Long对应数据库的bigint
    private Long gmtModified;
    private String creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;


}
