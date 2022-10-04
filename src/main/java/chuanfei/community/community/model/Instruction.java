package chuanfei.community.community.model;

import lombok.Data;

@Data
public class Instruction {
    private Long orderId;
    private String objectId;
    private String market;
    private String objectAbbreviation;
    private Float objectPrice;
    private Integer objectNumber;
    private String direction;
    private String orderStatus;
    private Integer transactionNumber;
    private String createTime;  //Java的Long对应数据库的bigint
    private String modifyTime;
    private Integer entrustNumber;
}
