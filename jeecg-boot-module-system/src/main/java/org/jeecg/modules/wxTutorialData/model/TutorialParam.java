package org.jeecg.modules.wxTutorialData.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.PageParam;

@Data
public class TutorialParam extends PageParam {

    @ApiModelProperty(value = "资料名称")
    private String dataName;

    @ApiModelProperty(value = "查询推荐 0.全部 1.推荐")
    private Integer recommend = 0;
}
