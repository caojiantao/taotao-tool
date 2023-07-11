package com.taotao.tool.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 雪花算法
 */
public class SnowFlakeUtils {

    /**
     * 数据中心
     */
    private static int datacenterBit;
    /**
     * 工作机器
     */
    private static int workerBit;
    /**
     * 序列号
     */
    private static int sequenceBit;

    /**
     * 对比时间点 2022-10-12 时间戳
     */
    private static long epoch;

    /**
     * 时间戳（毫秒）
     */
    private static long lastTimeStamp = 0L;

    /**
     * 数据中心 ID
     */
    private static long dataCenterId = 0;
    /**
     * 工作节点 ID
     */
    private static long workId = 0;

    /**
     * 序列号
     */
    private static long sequence = 0;

    /**
     * 序列号 mask，避免溢出
     */
    private static long sequenceMask;

    static {
        long epoch = LocalDateTime.of(2022, 10, 12, 0, 0, 0)
                .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        int datacenterBit = 5;
        int workerBit = 5;
        int sequenceBit = 12;
        init(epoch, datacenterBit, workerBit, sequenceBit);
    }

    /**
     * 初始化
     *
     * @param epoch         雪花纪元
     * @param datacenterBit 数据中心位
     * @param workerBit     工作节点位
     * @param sequenceBit   序列号位
     */
    public static void init(long epoch, int datacenterBit, int workerBit, int sequenceBit) {
        SnowFlakeUtils.epoch = epoch;
        SnowFlakeUtils.datacenterBit = datacenterBit;
        SnowFlakeUtils.workerBit = workerBit;
        SnowFlakeUtils.sequenceBit = sequenceBit;
        sequenceMask = ~(-1L << SnowFlakeUtils.sequenceBit);
    }

    public static synchronized long getId() {
        long timestamp = System.currentTimeMillis() - epoch;
        if (lastTimeStamp == timestamp) {
            // 同一毫秒，序列号递增
            long temp = (sequence + 1) & sequenceMask;
            if (temp == 0) {
                // 序列号用完了
                return getId();
            }
            sequence = temp;
        } else {
            lastTimeStamp = timestamp;
            sequence = 0L;
        }
        return sequence
                | (workId << sequenceBit)
                | (dataCenterId << sequenceBit + workerBit)
                | (lastTimeStamp << (sequenceBit + workerBit + datacenterBit));
    }

    public static void main(String[] args) {
        // 11111111111111111111111111111111
        System.out.println(Integer.toBinaryString(-1));
        // 11111111111111111111000000000000
        System.out.println(Integer.toBinaryString(-1 << sequenceBit));
        // 111111111111
        System.out.println(Integer.toBinaryString(~(-1 << sequenceBit)));

        System.out.println(Long.toBinaryString(82977433493438464L));
    }
}
