package com.aheroboy.prophets.resource.stock;

import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import com.aheroboy.prophets.framework.AbstractEntity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
@Document(collection = "stock")
@EqualsAndHashCode(exclude = {"date,time"})
public class StockEntity extends AbstractEntity {
    private String symbol;
    private String code;
    private BigDecimal open;
    private BigDecimal settlement;
    private BigDecimal trade;
    private BigDecimal marketPrice;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal comBuyPrice;
    private BigDecimal comSellPrice;
    private BigDecimal doneCount;
    private BigDecimal doneAmount;
    private BigDecimal buy1Count;
    private BigDecimal buy1Price;
    private BigDecimal buy2Count;
    private BigDecimal buy2Price;
    private BigDecimal buy3Count;
    private BigDecimal buy3Price;
    private BigDecimal buy4Count;
    private BigDecimal buy4Price;
    private BigDecimal buy5Count;
    private BigDecimal buy5Price;
    private BigDecimal sell1Count;
    private BigDecimal sell1Price;
    private BigDecimal sell2Count;
    private BigDecimal sell2Price;
    private BigDecimal sell3Count;
    private BigDecimal sell3Price;
    private BigDecimal sell4Count;
    private BigDecimal sell4Price;
    private BigDecimal sell5Count;
    private BigDecimal sell5Price;
    private String date;
    private String time;
    //
    private BigDecimal pricechange;
    private BigDecimal changepercent;
    private BigDecimal buy;
    private BigDecimal sell;
    private Long volume;
    private Long amount;
    private Time ticktime;
    private BigDecimal per;
    private BigDecimal pb;
    private BigDecimal mktcap;
    private BigDecimal nmc;
    private BigDecimal turnoverratio;

}
