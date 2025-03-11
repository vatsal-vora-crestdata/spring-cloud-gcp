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

package com.example;

import com.google.cloud.parametermanager.v1.ParameterFormat;
import com.google.cloud.spring.parametermanager.ParameterManagerTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ParameterManagerWebController {
  private static final String INDEX_PAGE = "index.html";

  private final ParameterManagerTemplate parameterManagerTemplate;

  public ParameterManagerWebController(ParameterManagerTemplate parameterManagerTemplate) {
    this.parameterManagerTemplate = parameterManagerTemplate;
  }

  @GetMapping("/getParameter")
  @ResponseBody
  public String getParameter(@RequestParam String parameterId, @RequestParam String versionId, @RequestParam(required = false) String projectId, @RequestParam(required = false) String locationId, ModelMap map) {
    String parameterPayload;
    String parameterIdentifier;
    if (StringUtils.isEmpty(projectId)) {
      if (StringUtils.isEmpty(locationId)) {
        parameterIdentifier = "pm@" + parameterId + "/" + versionId;
      } else {
        parameterIdentifier = "pm@locations/" + locationId + "/" + parameterId + "/" + versionId;
      }
    } else {
      if (StringUtils.isEmpty(locationId)) {
        parameterIdentifier = "pm@" + projectId + "/" + parameterId + "/" + versionId;
      } else {
        parameterIdentifier = "pm@" + projectId + "/" + locationId + "/" + parameterId + "/" + versionId;
      }
    }
    parameterPayload = this.parameterManagerTemplate.getParameterString(parameterIdentifier);
    return "Parameter ID: " + HtmlUtils.htmlEscape(parameterId) + " | Value: " + parameterPayload + "<br/><br/><a href='/'>Go back</a>";
  }

  @PostMapping("/createParameter")
  public ModelAndView createParameter(
      @RequestParam String parameterId,
      @RequestParam String versionId,
      @RequestParam String parameterPayload,
      @RequestParam(required = false) String projectId,
      @RequestParam(required = false) String locationId,
      ModelMap map) {
    ParameterFormat format = ParameterFormat.JSON;
    if (StringUtils.isEmpty(projectId)) {
      if (StringUtils.isEmpty(locationId)) {
        this.parameterManagerTemplate.createParameter(parameterId, versionId, parameterPayload, format);
      } else {
        this.parameterManagerTemplate.createParameter(parameterId, versionId, parameterPayload, format, locationId);
      }
    } else {
      if (StringUtils.isEmpty(locationId)) {
        this.parameterManagerTemplate.createParameter(parameterId, versionId, parameterPayload, format, "global", projectId);
      } else {
        this.parameterManagerTemplate.createParameter(parameterId, versionId, parameterPayload, format, locationId, projectId);
      }
    }

    map.put("message", "Parameter created!");
    return new ModelAndView(INDEX_PAGE, map);
  }

  @PostMapping("/deleteParameter")
  public ModelAndView deleteParameter(
      @RequestParam String parameterId,
      @RequestParam(required = false) String projectId,
      @RequestParam(required = false) String locationId,
      ModelMap map) {
    if (StringUtils.isEmpty(projectId)) {
      if (StringUtils.isEmpty(locationId)) {
        this.parameterManagerTemplate.deleteParameter(parameterId);
      } else {
        this.parameterManagerTemplate.deleteParameter(parameterId, locationId);
      }
    } else {
      if (StringUtils.isEmpty(locationId)) {
        this.parameterManagerTemplate.deleteParameter(parameterId, "global", projectId);
      } else {
        this.parameterManagerTemplate.deleteParameter(parameterId, locationId, projectId);
      }
    }

    map.put("message", "Parameter deleted!");
    return new ModelAndView(INDEX_PAGE, map);
  }

  @PostMapping("/deleteParameterVersion")
  public ModelAndView deleteParameterVersion(
      @RequestParam String parameterId,
      @RequestParam String versionId,
      @RequestParam(required = false) String projectId,
      @RequestParam(required = false) String locationId,
      ModelMap map) {
    if (StringUtils.isEmpty(projectId)) {
      if (StringUtils.isEmpty(locationId)) {
        this.parameterManagerTemplate.deleteParameterVersion(parameterId, versionId);
      } else {
        this.parameterManagerTemplate.deleteParameterVersion(parameterId, versionId,locationId);
      }
    } else {
      if (StringUtils.isEmpty(locationId)) {
        this.parameterManagerTemplate.deleteParameterVersion(parameterId, versionId, "global", projectId);
      } else {
        this.parameterManagerTemplate.deleteParameterVersion(parameterId, versionId, locationId, projectId);
      }
    }

    map.put("message", "Parameter Version deleted!");
    return new ModelAndView(INDEX_PAGE, map);
  }

}
