package com.taotao.tool.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadUtils {

    private static final ExecutorService POOL_UPLOAD = Executors.newFixedThreadPool(32);

    public static void sync(List<IVoidTask> taskList) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(taskList.size());
        log.info("act=ThreadUtils.sync type=start");
        for (IVoidTask task : taskList) {
            Runnable runnable = () -> {
                log.info("act=ThreadUtils.sync type=taskStart");
                try {
                    task.run();
                } catch (Exception e) {
                    // 不能响应异常
                    log.error("出现异常", e);
                } finally {
                    log.info("act=ThreadUtils.sync type=taskEnd");
                    latch.countDown();
                }
            };
            POOL_UPLOAD.submit(runnable);
        }
        latch.await();
        log.info("act=ThreadUtils.sync type=end");
    }

    public interface IVoidTask {

        void run() throws Exception;
    }
}
