package com.aheroboy.prophets.resource.stock;

import com.aheroboy.prophets.framework.Utils;
import com.aheroboy.prophets.resource.Extractor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by aheroboy on 17-2-11.
 */
public class StockExtractor implements Extractor<StockEntity> {
    public StockEntity extract(String rawData) {
        StockEntity entity = new StockEntity();
        if (StringUtils.isNotBlank(rawData) && !"null".equals(rawData)) {
            String[] columns = rawData.split(",");
            if(columns.length < 5){
                throw new RuntimeException("Invalid query...");
            }
            entity.setName(columns[0]);
            entity.setOpen(new BigDecimal(columns[1]));
            entity.setSettlement(new BigDecimal(columns[2]));
            entity.setMarketPrice(new BigDecimal(columns[3]));
            entity.setHigh(new BigDecimal(columns[4]));
            entity.setLow(new BigDecimal(columns[5]));
            entity.setComBuyPrice(new BigDecimal(columns[6]));
            entity.setComSellPrice(new BigDecimal(columns[7]));
            entity.setDoneCount( new BigDecimal(columns[8]));
            entity.setDoneAmount( new BigDecimal(columns[9]));
            entity.setBuy1Count( new BigDecimal(columns[10]));
            entity.setBuy1Price(new BigDecimal(columns[11]));
            entity.setBuy2Count( new BigDecimal(columns[12]));
            entity.setBuy2Price(new BigDecimal(columns[13]));
            entity.setBuy3Count( new BigDecimal(columns[14]));
            entity.setBuy3Price(new BigDecimal(columns[15]));
            entity.setBuy4Count( new BigDecimal(columns[16]));
            entity.setBuy4Price(new BigDecimal(columns[17]));
            entity.setBuy5Count( new BigDecimal(columns[18]));
            entity.setBuy5Price(new BigDecimal(columns[19]));
            entity.setSell1Count( new BigDecimal(columns[20]));
            entity.setSell1Price(new BigDecimal(columns[21]));
            entity.setSell2Count( new BigDecimal(columns[22]));
            entity.setSell2Price(new BigDecimal(columns[23]));
            entity.setSell3Count( new BigDecimal(columns[24]));
            entity.setSell3Price(new BigDecimal(columns[25]));
            entity.setSell4Count( new BigDecimal(columns[26]));
            entity.setSell4Price(new BigDecimal(columns[27]));
            entity.setSell5Count( new BigDecimal(columns[28]));
            entity.setSell5Price(new BigDecimal(columns[29]));
            entity.setDate(columns[30]);
            entity.setTime(columns[31]);

            /**
             * 0：	股票名字；
             1：	今日开盘价；
             2：	昨日收盘价；
             3：	当前价格；
             4：	今日最高价；
             5：	今日最低价；
             6：	竞买价，即”买一”报价；
             7：	竞卖价，即”卖一”报价；
             8：	成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
             9：	成交金额，单位为”元”为了一目了然，通常以”万元”为成交金额的单位，所以通常把该值除以一万；
             10：	“买一”申请股数；
             11：	“买一”报价；
             12：	“买二”
             13：	“买二”
             14：	“买三”
             15：	“买三”
             16：	“买四”
             17：	“买四”
             18：	“买五”
             19：	“买五”
             20：	“卖一”申报股数；
             21：	“卖一”报价, (22, 23), (24, 25), (26,27), (28, 29)分别为”卖二”至”卖四的情况”
             30：	日期;
             31：	时间;
             */
        }
        return entity;
    }

}
