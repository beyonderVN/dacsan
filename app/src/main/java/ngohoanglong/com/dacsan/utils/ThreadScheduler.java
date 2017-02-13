package ngohoanglong.com.dacsan.utils;

import rx.Scheduler;

public interface ThreadScheduler {
    Scheduler subscribeOn();
    Scheduler observeOn();
}