package com.yiie.vo.response;

import com.yiie.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * Time：2020-1-3 23:40
 * Email： yiie315@163.com
 * Desc：
 *
 * @author： yiie
 * @version：1.0.0
 */
@Data
public class UserOwnRoleRespVO {

    @ApiModelProperty("所有角色集合")
    private List<Role> allRole;
    @ApiModelProperty(value = "用户所拥有角色集合")
    private List<String> ownRoles;
}
