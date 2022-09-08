package chuanfei.community.community.dto;
import chuanfei.community.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;  //Java的Long对应数据库的bigint
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private User user;
}
