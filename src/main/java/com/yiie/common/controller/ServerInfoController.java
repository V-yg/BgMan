package com.yiie.common.controller;

import com.yiie.aop.annotation.LogAnnotation;
import com.yiie.entity.ChartBean;
import com.yiie.entity.ServerInfo;
import com.yiie.utils.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.*;
import oshi.util.FormatUtil;
import oshi.util.Util;

import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

/**
 * Time：2020-1-15 15:58
 * Email：yiie315@163.com
 * Desc：TODO
 *
 * @Author：yiie
 * @Version：1.0.0
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "系统管理-服务器性能")
public class ServerInfoController {

    @PostMapping("/serverinfo")
    @ApiOperation(value = "监控服务器信息")
    @LogAnnotation(title = "服务监控",action = "服务器监控信息接口")
    public DataResult<ServerInfo> getServerInfo(){
        DataResult<ServerInfo> result=DataResult.success();
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        ServerInfo info = new ServerInfo();
        /*内存  swap*/
        info.setMemTotal(FormatUtil.formatBytes(hal.getMemory().getTotal()));
        info.setMemFree(FormatUtil.formatBytes(hal.getMemory().getAvailable()));
        info.setMemUsed(FormatUtil.formatBytes(hal.getMemory().getTotal()-hal.getMemory().getAvailable()));
        info.setMemUsedRate((((hal.getMemory().getTotal()-hal.getMemory().getAvailable()) / (float)hal.getMemory().getTotal() * 100) + "").substring(0,4)+"%");
        info.setSwapTotal(FormatUtil.formatBytes(hal.getMemory().getSwapTotal()));
        info.setSwapFree(FormatUtil.formatBytes(hal.getMemory().getSwapTotal()-hal.getMemory().getSwapUsed()));
        info.setSwapUsed(FormatUtil.formatBytes(hal.getMemory().getSwapUsed()));
        info.setSwapUsedRate((hal.getMemory().getSwapUsed() / (float)hal.getMemory().getSwapTotal()+"")+"%");

        /*CPU*/
        long[] prevTicks = hal.getProcessor().getSystemCpuLoadTicks();
        Util.sleep(1000);
        long[] ticks = hal.getProcessor().getSystemCpuLoadTicks();
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + iowait + irq + softirq + steal;
        info.setCpuNum(hal.getProcessor().getLogicalProcessorCount());
        info.setTotal(totalCpu);
        info.setUsed(user);
        info.setSys(sys);
        info.setFree(idle);
        info.setWait(iowait);
        result.setData(info);
        return result;
    }
}
