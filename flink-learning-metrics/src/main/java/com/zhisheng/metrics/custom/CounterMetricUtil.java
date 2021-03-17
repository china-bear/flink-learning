package com.zhisheng.metrics.custom;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.metrics.Counter;


public class CounterMetricUtil extends MetricUtil {

    public CounterMetricUtil() {
    }

    public CounterMetricUtil(RuntimeContext runtimeContext, String appTag, String metricName) {
        super(runtimeContext, appTag, metricName);
    }

    public Counter getMetricHD() {
        Counter counter = runtimeContext.getMetricGroup()
                .addGroup(appTag)
                .counter(metricName);

        return counter;
    }

}
