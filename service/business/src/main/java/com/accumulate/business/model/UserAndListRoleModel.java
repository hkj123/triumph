package com.accumulate.business.model;

import lombok.Data;

import java.util.List;

/**
 * Created by hukj
 */
@Data
public class UserAndListRoleModel {
    private Long userid;
    private List<Long> roleidList;
}
