package com.netflix.config.sources;

import com.netflix.config.PollResult;
import com.netflix.config.PolledConfigurationSource;
import com.rackspacecloud.client.service_registry.Client;
import com.rackspacecloud.client.service_registry.objects.ConfigurationValue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceRegistryConfigurationProvider implements PolledConfigurationSource {

    private static final int HEARTBEAT_TIMEOUT = 15;
    private final Client client;

    public ServiceRegistryConfigurationProvider(Client client) {
        this.client = client;
    }

    @Override
    public PollResult poll(boolean initial, Object checkPoint)
            throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        List<ConfigurationValue> values = client.configuration.list(null);
        for (ConfigurationValue value : values) {
            map.put(value.getId(), value.getValue());
        }
        return PollResult.createFull(map);
    }

    @Override
    public String toString() {
        return "ServiceRegistryConfigurationProvider [client=" + client.toString() + "]";
    }
}
