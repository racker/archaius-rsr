package com.netflix.config.sources;

import com.rackspacecloud.client.service_registry.Client;
import com.rackspacecloud.client.service_registry.objects.ConfigurationValue;
import com.rackspacecloud.client.service_registry.objects.Service;

import java.util.List;

public class ServiceRegistryClientProvider implements ServiceRegistryClient {
    private final Client client;

    public ServiceRegistryClientProvider(String user, String key, String region) {
        client = new Client(user, key, region);
    }
    @Override
    public List<Service> getServices(String tag) throws Exception {
        return client.services.list(null, tag);
    }

    @Override
    public List<ConfigurationValue> getConfiguration() throws Exception {
        return client.configuration.list(null);
    }
}
