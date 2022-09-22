package chuanfei.community.community.model;

import lombok.Data;

@Data
public class Entrust {
    private Integer entrustId;
    private Long orderId;
    private Float singleEntrustPrice;
    private Integer singleEntrustNumber;
    private Long entrustTime;
}
