package finan.heng.com.apps.model;
/*
 * Created by hhm on 2017/5/11.
 */

import finan.heng.com.apps.http.BusinessEnum;
import finan.heng.com.apps.http.InterfaceService;
import finan.heng.com.apps.http.RequestData;

public class FinanceTitleRequest extends RequestData {
    @Override
    public BusinessEnum getBusinessType() {
        return BusinessEnum.BUSINESS_MANAGE;
    }

    @Override
    public String getInterfaceName() {
        return InterfaceService.FINANCE_TITLE;
    }

    @Override
    public boolean isNeedCache() {
        return false;
    }

    @Override
    public long getCachePeriod() {
        return 0;
    }

    @Override
    public String getRequestKey() {
        return null;
    }
}
