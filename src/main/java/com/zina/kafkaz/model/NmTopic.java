package com.zina.kafkaz.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : lacinazina
 * @created : 12/28/20, Monday
 **/
public class NmTopic implements Comparable<NmTopic>{

    private final String name;

    private Map<Integer, NmTopicPartition> partitions = new TreeMap<>();

    private Map<String, String> config = Collections.emptyMap();

    public NmTopic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public Map<Integer, NmTopicPartition> getPartitionMap() {
        return Collections.unmodifiableMap(partitions);
    }

    public Collection<NmTopicPartition> getPartitions() {
        return Collections.unmodifiableCollection(partitions.values());
    }

    public void setPartitions(Map<Integer, NmTopicPartition> partitions) {
        this.partitions = partitions;
    }

    public Optional<NmTopicPartition> getPartition(int partitionId) {
        return Optional.ofNullable(partitions.get(partitionId));
    }

    public Collection<NmTopicPartition> getLeaderPartitions(int brokerId) {
        return partitions.values().stream()
                .filter(tp -> tp.getLeader() != null && tp.getLeader().getId() == brokerId)
                .collect(Collectors.toList());
    }

    public Collection<NmTopicPartition> getUnderReplicatedPartitions() {
        return partitions.values().stream()
                .filter(NmTopicPartition::isUnderReplicated)
                .collect(Collectors.toList());
    }

    /**
     * Returns the total number of messages published to the topic, ever
     * @return
     */
    public long getTotalSize() {
        return partitions.values().stream()
                .map(NmTopicPartition::getSize)
                .reduce(0L, Long::sum);
    }

    /**
     * Returns the total number of messages available to consume from the topic.
     * @return
     */
    public long getAvailableSize() {
        return partitions.values().stream()
                .map(p -> p.getSize() - p.getFirstOffset())
                .reduce(0L, Long::sum);
    }

    public double getPreferredReplicaPercent() {
        if (partitions.isEmpty()) {
            return 0;
        } else {
            final var preferredLeaderCount = partitions.values().stream()
                    .filter(NmTopicPartition::isLeaderPreferred)
                    .count();
            return ((double) preferredLeaderCount) / ((double) partitions.size());
        }
    }

    @Override
    public int compareTo(NmTopic that) {
        return this.name.compareTo(that.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof NmTopic) {
            final var that = (NmTopic) o;
            return Objects.equals(name, that.name);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return NmTopic.class.getSimpleName() + " [name=" + name +", partitions=" + partitions + "]";
    }
}
