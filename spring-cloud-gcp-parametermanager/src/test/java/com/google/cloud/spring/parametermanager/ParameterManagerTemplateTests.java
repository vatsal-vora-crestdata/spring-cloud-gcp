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

package com.google.cloud.spring.parametermanager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.google.api.gax.rpc.NotFoundException;
import com.google.cloud.parametermanager.v1.CreateParameterRequest;
import com.google.cloud.parametermanager.v1.GetParameterRequest;
import com.google.cloud.parametermanager.v1.LocationName;
import com.google.cloud.parametermanager.v1.Parameter;
import com.google.cloud.parametermanager.v1.ParameterFormat;
import com.google.cloud.parametermanager.v1.ParameterManagerClient;
import com.google.cloud.parametermanager.v1.ParameterVersionName;
import com.google.cloud.parametermanager.v1.ParameterVersionPayload;
import com.google.cloud.parametermanager.v1.ParameterVersion;
import com.google.protobuf.ByteString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ParameterManagerTemplateTests {

  private ParameterManagerClient client;
  private ParameterManagerTemplate parameterManagerTemplate;

  @BeforeEach
  void setupMocks() {
    this.client = mock(ParameterManagerClient.class);
//    when(this.client.getParameterVersion(any(ParameterVersionName.class)))
//           .thenReturn(ParameterVersion.newBuilder()
//                   .setPayload(
//                           ParameterVersionPayload.newBuilder().setData(ByteString.copyFromUtf8("get after it.")).build())
//                   .build());
    this.parameterManagerTemplate = new ParameterManagerTemplate(this.client, () -> "my-project");
  }

  @Test
  void testProjectId() {
    assertThat(this.parameterManagerTemplate.getProjectId()).isEqualTo("my-project");
  }

  @Test
  void testCreateParameterIfMissing() {
      String parameterId = "my-parameter";
      String versionId = "v1";
      String payload = "{'message': 'hello world!'}";

      GetParameterRequest expectedRequest = GetParameterRequest.newBuilder()
        .setName("projects/custom-project/locations/custom-location/parameters/my-parameter")
        .build();

      // Mock the getParameter method to throw NotFoundException for this specific request
      doThrow(new NotFoundException("Parameter not found", null, null, false))
          .when(this.client)
          .getParameter(expectedRequest);
      this.parameterManagerTemplate.createParameter(parameterId, versionId, payload.getBytes(), ParameterFormat.JSON, "custom-location", "custom-project");

      verifyCreateParameterRequest(parameterId, versionId, ParameterFormat.JSON, "custom-project", "custom-location");
  }

//    @Test
//    void testAccessParameterString() {
//        String parameterId = "my-parameter";
//        String versionId = "v1";
//        String expectedValue = "get after it.";
//
//        ParameterVersion parameterVersion = ParameterVersion.newBuilder()
//            .setPayload(ParameterPayload.newBuilder().setData(ByteString.copyFromUtf8(expectedValue)).build())
//            .build();
//        when(this.parameterManagerClient.getParameterVersion(any(GetParameterVersionRequest.class)))
//            .thenReturn(parameterVersion);
//
//        String result = this.parameterManagerTemplate.getParameterString(parameterId + "/" + versionId);
//        verify(this.parameterManagerClient).getParameterVersion(any(GetParameterVersionRequest.class));
//        assertThat(result).isEqualTo(expectedValue);
//    }
//
//    @Test
//    void testAccessParameterBytes() {
//        String parameterId = "my-parameter";
//        String versionId = "v1";
//        String expectedValue = "get after it.";
//
//        ParameterVersion parameterVersion = ParameterVersion.newBuilder()
//            .setPayload(ParameterPayload.newBuilder().setData(ByteString.copyFromUtf8(expectedValue)).build())
//            .build();
//        when(this.parameterManagerClient.getParameterVersion(any(GetParameterVersionRequest.class)))
//            .thenReturn(parameterVersion);
//
//        byte[] result = this.parameterManagerTemplate.getParameterBytes(parameterId + "/" + versionId);
//        verify(this.parameterManagerClient).getParameterVersion(any(GetParameterVersionRequest.class));
//        assertThat(result).isEqualTo(expectedValue.getBytes());
//    }
//
//    @Test
//    void testAccessNonExistentParameterStringWhenDefaultIsNotAllowed() {
//        when(this.parameterManagerClient.getParameterVersion(any(GetParameterVersionRequest.class)))
//            .thenThrow(NotFoundException.class);
//
//        assertThatThrownBy(() -> this.parameterManagerTemplate.getParameterString("fake-parameter/v1"))
//            .isExactlyInstanceOf(NotFoundException.class);
//    }
//
//    @Test
//    void testAccessNonExistentParameterStringWhenDefaultIsAllowed() {
//        when(this.parameterManagerClient.getParameterVersion(any(GetParameterVersionRequest.class)))
//            .thenThrow(NotFoundException.class);
//
//        this.parameterManagerTemplate.setAllowDefaultParameterValue(true);
//        String result = this.parameterManagerTemplate.getParameterString("fake-parameter/v1");
//        assertThat(result).isNull();
//    }
//
//    @Test
//    void testDeleteParameter() {
//        String parameterId = "my-parameter";
//        String locationId = "global";
//
//        this.parameterManagerTemplate.deleteParameter(parameterId, locationId);
//        verify(this.parameterManagerClient).deleteParameter(any(DeleteParameterRequest.class));
//    }
//
//    @Test
//    void testEnableParameterVersion() {
//        String parameterId = "my-parameter";
//        String versionId = "v1";
//        String locationId = "global";
//
//        this.parameterManagerTemplate.enableParameterVersion(parameterId, versionId, locationId);
//        verify(this.parameterManagerClient).updateParameterVersion(any(UpdateParameterVersionRequest.class));
//    }
//
//    @Test
//    void testDisableParameterVersion() {
//        String parameterId = "my-parameter";
//        String versionId = "v1";
//        String locationId = "global";
//
//        this.parameterManagerTemplate.disableParameterVersion(parameterId, versionId, locationId);
//        verify(this.parameterManagerClient).updateParameterVersion(any(UpdateParameterVersionRequest.class));
//    }

  private void verifyCreateParameterRequest(String parameterId, String versionId, ParameterFormat format, String projectId, String locationId) {
    LocationName locationName = LocationName.of(projectId, locationId);
    Parameter parameter = Parameter.newBuilder()
        .setFormat(format)
        .build();

    CreateParameterRequest request = CreateParameterRequest.newBuilder()
        .setParent(locationName.toString())
        .setParameterId(parameterId)
        .setParameter(parameter)
        .build();
    verify(this.client).createParameter(request);
  }
}