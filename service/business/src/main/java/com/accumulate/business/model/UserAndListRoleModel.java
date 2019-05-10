package com.accumulate.business.model;

import lombok.Data;

import java.util.List;

/**
 * Created by hukj
 */
@Data
public class UserAndListRoleModel {
    private Long userId;
    private List<Long> roleIdList;
}
