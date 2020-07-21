package com.lsl.domain;

import lombok.Data;

import java.lang.ref.WeakReference;

/**
 * Title: TRS 统一信息资源管理（TRS URIB） Description: Copyright: Copyright (c) 2004-2017 TRS信息技术有限公司 Company:
 * TRS信息技术有限公司(www.trs.com.cn) CreateDate: 2019/7/31
 *
 * @author yang.cao
 * @version 1.0
 */
@Data
public class IdCard extends WeakReference<Role> {
    private Integer id;

    public IdCard(Role referent) {
        super(referent);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("IdCard 被回收");
        super.finalize();
    }
}
