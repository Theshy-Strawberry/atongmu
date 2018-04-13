package com.atongmu.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ��������Ψһ���û�ID
 * @author Ning
 *
 */
public class MakeUnqID {
    private static final long ONE_STEP = 10;
    private static final Lock LOCK = new ReentrantLock();
    private static long lastTime = System.currentTimeMillis();
    private static short lastCount = 0;
    private static int count = 0;

    @SuppressWarnings("finally")
    public static String nextId()
    {
        LOCK.lock();
        try {
            if (lastCount == ONE_STEP) {
                boolean done = false;
                while (!done) {
                    long now = System.currentTimeMillis();
                    if (now == lastTime) {
                        try {
                            Thread.currentThread();
                            Thread.sleep(1);
                        } catch (java.lang.InterruptedException e) {
                        	e.printStackTrace();
                        }
                        continue;
                    } else {
                        lastTime = now;
                        lastCount = 0;
                        done = true;
                    }
                }
            }
            count = lastCount++;
        }
        finally
        {
            LOCK.unlock();
            return lastTime+""+String.format("%03d",count);
        }
    }

	public static int randomNumber() {
		int n = 0;
		n = (int) (Math.random() * 100000);
		while (n < 10000) {
			n = (int) (Math.random() * 100000);
		}
		return n;
	}



}
