package chuanfei.community.community.model;
import lombok.Data;

@Data
public class Deal {
    private Integer dealId;
    private Integer entrustId;
    private Long orderId;
    private Float dealPrice;
    private Integer dealNumber;
    private String dealTime;

}
