package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/17.
 */

import java.io.Serializable;

public class MyWalletModel implements Serializable {

    /**
     * availableAmount : 20000000.00
     * allAssets : 20000000.00
     * totalInterest : 0.00
     */

    private String availableAmount;//可用
    private String allAssets;//所有资产
    private String totalInterest;//总利息

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getAllAssets() {
        return allAssets;
    }

    public void setAllAssets(String allAssets) {
        this.allAssets = allAssets;
    }

    public String getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(String totalInterest) {
        this.totalInterest = totalInterest;
    }
}
