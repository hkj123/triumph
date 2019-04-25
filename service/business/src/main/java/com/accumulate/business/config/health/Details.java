package com.accumulate.business.config.health;
import lombok.Data;

/**
 * 用户表
 */
@Data
public class Details {
   private Redis redis;
   private Db db;
}
