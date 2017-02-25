package com.aheroboy.prophets.resource.stock;

import com.aheroboy.prophets.framework.ID;
import com.aheroboy.prophets.resource.Extractor;
import com.aheroboy.prophets.resource.Resource;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by aheroboy on 17-2-10.
 */
@Service
public class SinaStockResource {
    private String url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=PAGE_START&num=PAGE_END&sort=symbol&asc=1&node=NODE_NAME&symbol=&_s_r_a=init";
    public String get(int startIdx, int endIdx, String type) {
        RestTemplate restTemplate = new RestTemplate();
        String newUrl = url.replace("PAGE_START",startIdx + "").replace("PAGE_END",endIdx + "").replace("NODE_NAME",type);
        System.out.println(newUrl);
        return restTemplate.getForObject(newUrl,String.class);
    }
}
