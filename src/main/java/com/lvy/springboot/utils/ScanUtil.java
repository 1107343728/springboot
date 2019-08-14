package com.lvy.springboot.utils;

import sun.awt.OSInfo;

/**
 * Description :
 * Author：          liweixiong
 * Create Date：     2019/8/7 17:30
 */
public class ScanUtil {
    public static void main(String[] args) {

    }

    /**
     * 当前虚拟机所属操作系统是否为windows系统
     * @return true是，false不是
     */
    public static boolean isWindowsOs(){
        OSInfo.OSType osType = OSInfo.getOSType();
        return osType.equals(OSInfo.OSType.WINDOWS);
    }
}
