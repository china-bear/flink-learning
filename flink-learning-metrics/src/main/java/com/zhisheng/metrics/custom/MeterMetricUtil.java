package com.zhisheng.metrics.custom;


import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.dropwizard.metrics.DropwizardMeterWrapper;
import org.apache.flink.metrics.Counter;
import org.apache.flink.metrics.Meter;
import org.apache.flink.metrics.Metric;

public class MeterMetricUtil extends MetricUtil  {

    public MeterMetricUtil() {
    }

    public MeterMetricUtil(RuntimeContext runtimeContext, String appTag, String metricName) {
        super(runtimeContext, appTag, metricName);
    }

    public Meter getMetricHD() {
        Meter meter = runtimeContext.getMetricGroup()
                .addGroup(appTag)
                .meter(metricName, new DropwizardMeterWrapper(new com.codahale.metrics.Meter()));

        return meter;
    }
}
