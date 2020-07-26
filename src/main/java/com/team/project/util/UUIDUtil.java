package com.team.project.util;

import java.util.UUID;

/**
 * 获取 32位UUID
 *
 * @author zhang.qijia
 * @date 2018/3/1 16:21
 */
public class UUIDUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 32);
    }

    public static String getShortUUID() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 5);
    }
}
