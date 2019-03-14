/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.healthcare.hl7v2.messages;

// [START healthcare_list_hl7v2_messages]

import com.google.HealthcareQuickstart;
import com.google.api.services.healthcare.v1beta1.model.ListMessagesResponse;

import java.io.IOException;
import java.util.List;

public class HL7v2MessageList {
  public static void listHL7v2Messages(String projectId, String cloudRegion, String datasetId, String hl7v2StoreId)
      throws IOException {
    String parentName = String.format(
        "projects/%s/locations/%s/datasets/%s/hl7V2Stores/%s",
        projectId,
        cloudRegion,
        datasetId,
        hl7v2StoreId);
    ListMessagesResponse response = HealthcareQuickstart.getCloudHealthcareClient()
        .projects()
        .locations()
        .datasets()
        .hl7V2Stores()
        .messages()
        .list(parentName)
        .execute();
    List<String> messages = response.getMessages();
    if (messages == null) {
      System.out.println("Retrieved 0 HL7v2 messages");
      return;
    }
    System.out.println("Retrieved " + messages.size() + " HL7v2 messages");
    for (int i = 0; i < messages.size(); i++) {
      System.out.println("  - " + messages.get(i));
    }
  }
}
// [END healthcare_list_hl7v2_messages]
