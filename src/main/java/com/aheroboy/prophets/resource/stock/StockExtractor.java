package com.aheroboy.prophets.resource.stock;

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
            String[] columns = rawData.split("=")[1].split(",");
            entity.setName(columns[0]);
            entity.setOpen(new BigDecimal(columns[1]));
            entity.getSettlement()
        }
        return entity;
    }

}
