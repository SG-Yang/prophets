package com.aheroboy.prophets.actor;

import com.aheroboy.prophets.framework.AbstractActor;
import com.aheroboy.prophets.resource.MarketCategory;

public class CategoryActor extends AbstractActor<MarketCategory> {
    private boolean leaf;
    private String url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=PAGE_START&num=PAGE_END&sort=symbol&asc=1&node=NODE_NAME&symbol=&_s_r_a=init";

    public CategoryActor(MarketCategory entity) {
        super(entity);
    }

    @Override
    public void init(String actorName, boolean isRoot) {
        super.init(actorName, isRoot);
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public void update(){}

}
