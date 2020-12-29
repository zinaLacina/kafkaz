package com.zina.kafkaz.model;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author : lacinazina
 * @created : 12/28/20, Monday
 **/
public class NmConsumerTopic {
    private final String topic;
    private final Map<Integer, NmConsumerPartition> offsets = new TreeMap<>();

    public NmConsumerTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void addOffset(NmConsumerPartition offset) {
        offsets.put(offset.getPartitionId(), offset);
    }

    public long getLag() {
        return offsets.values().stream()
                .map(NmConsumerPartition::getLag)
                .filter(lag -> lag >= 0)
                .reduce(0L, Long::sum);
    }

    public Collection<NmConsumerPartition> getPartitions() {
        return offsets.values();
    }
}
