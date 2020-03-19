package cn.chzu.buildingmaterials.manage.model;

import java.util.List;

/**
 * @description:
 * @author: zhu_hao
 * @date: Created in 2020/3/11 16:32
 * @version: 1.0.0
 * @modified By:
 */
public class StoreAnalysisVO1 {
    //店铺名称
    private String storeName;

    //信息封装
    private List<StoreAnalysisVO> storeAnalysisVO;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<StoreAnalysisVO> getStoreAnalysisVO() {
        return storeAnalysisVO;
    }

    public void setStoreAnalysisVO(List<StoreAnalysisVO> storeAnalysisVO) {
        this.storeAnalysisVO = storeAnalysisVO;
    }

    @Override
    public String toString() {
        return "StoreAnalysisVO1{" +
                "storeName='" + storeName + '\'' +
                ", storeAnalysisVO=" + storeAnalysisVO +
                '}';
    }
}
