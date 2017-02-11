package com.aheroboy.prophets.resource.stock;

import org.springframework.data.mongodb.core.mapping.Document;

import com.aheroboy.prophets.framework.AbstractEntity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

@Data
@Document(collection = "stock")
public class StockEntity extends AbstractEntity {
    private String symbol;
    private String code;
    private BigDecimal trade;
    private BigDecimal pricechange;
    private BigDecimal changepercent;
    private BigDecimal buy;
    private BigDecimal sell;
    private BigDecimal settlement;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private Long volume;
    private Long amount;
    private Time ticktime;
    private BigDecimal per;
    private BigDecimal pb;
    private BigDecimal mktcap;
    private BigDecimal nmc;
    private BigDecimal turnoverratio;
    /**
     * {
     "symbol": "sh600176",
     "code": "600176",
     "name": "中国巨石",
     "trade": "10.460",
     "pricechange": "0.180",
     "changepercent": "1.751",
     "buy": "10.450",
     "sell": "10.460",
     "settlement": "10.280",
     "open": "10.280",
     "high": "10.630",
     "low": "10.280",
     "volume": 45054316,
     "amount": 470053069,
     "ticktime": "15:00:00",
     "per": 9.285,
     "pb": 2.397,
     "mktcap": 2544036.780564,
     "nmc": 2544036.780564,
     "turnoverratio": 1.85244
     }
     */

}
