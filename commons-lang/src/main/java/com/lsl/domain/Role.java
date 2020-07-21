package com.lsl.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Title: TRS 统一信息资源管理（TRS URIB） Description: Copyright: Copyright (c) 2004-2017 TRS信息技术有限公司 Company:
 * TRS信息技术有限公司(www.trs.com.cn) CreateDate: 2019/7/31
 *
 * @author yang.cao
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {
    private Integer id;
    private String name;

}
