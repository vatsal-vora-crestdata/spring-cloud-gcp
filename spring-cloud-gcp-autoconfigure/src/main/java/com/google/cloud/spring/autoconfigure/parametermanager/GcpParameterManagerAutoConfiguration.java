/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.spring.autoconfigure.parametermanager;

import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.parametermanager.v1.ParameterManagerClient;
import com.google.cloud.parametermanager.v1.ParameterManagerSettings;
import com.google.cloud.spring.core.GcpProjectIdProvider;
import com.google.cloud.spring.core.UserAgentHeaderProvider;
import com.google.cloud.spring.parametermanager.ParameterManagerTemplate;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@AutoConfiguration
@EnableConfigurationProperties(GcpParameterManagerProperties.class)
@ConditionalOnClass(ParameterManagerTemplate.class)
@ConditionalOnProperty(value = "spring.cloud.gcp.parametermanager.enabled", matchIfMissing = true)
public class GcpParameterManagerAutoConfiguration {
    private final GcpProjectIdProvider gcpProjectIdProvider;
    private final GcpParameterManagerProperties properties;
    private final CredentialsProvider credentialsProvider;

    public GcpParameterManagerAutoConfiguration(
            CredentialsProvider credentialsProvider,
            GcpParameterManagerProperties properties,
            GcpProjectIdProvider projectIdProvider) {
        this.gcpProjectIdProvider = properties.getProjectId() != null
                ? properties::getProjectId
                : projectIdProvider;;
        this.properties = properties;
        this.credentialsProvider = credentialsProvider;
    }

  @Bean
  @ConditionalOnMissingBean
  public ParameterManagerClient parameterManagerClient() throws IOException {
    ParameterManagerSettings settings = ParameterManagerSettings.newBuilder()
        .setCredentialsProvider(this.credentialsProvider)
        .setHeaderProvider(
            new UserAgentHeaderProvider(GcpParameterManagerAutoConfiguration.class)
        ).build();

    return ParameterManagerClient.create(settings);
  }

  @Bean
  @ConditionalOnMissingBean
  public ParameterManagerTemplate parameterManagerTemplate(ParameterManagerClient client) {
    return new ParameterManagerTemplate(client, this.gcpProjectIdProvider)
        .setAllowDefaultParameterValue(this.properties.isAllowDefaultParameter());
  }
}
