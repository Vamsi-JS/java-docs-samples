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

package com.google.healthcare.fhir.resources;

// [START healthcare_create_resource]

import com.google.HealthcareQuickstart;
import com.google.api.services.healthcare.v1beta1.CloudHealthcare.Projects.Locations.Datasets.FhirStores.Fhir.CreateResource;
import com.google.api.services.healthcare.v1beta1.model.HttpBody;

import java.io.IOException;
import java.util.Optional;

public class FhirResourceCreate {
  public static void createFhirResource(
      String projectId,
      String cloudRegion,
      String datasetId,
      String fhirStoreId,
      String resourceType)
      throws IOException {

    HttpBody httpBody = new HttpBody();
    httpBody.setContentType(resourceType);
    httpBody.setData("{'resourceType': '" + resourceType + "'}");

    String parentName = String.format(
        "projects/%s/locations/%s/datasets/%s/fhirStores/%s",
        projectId,
        Optional.of(cloudRegion).orElse("us-central1"),
        datasetId,
        fhirStoreId);
    CreateResource request = HealthcareQuickstart.getCloudHealthcareClient()
        .projects()
        .locations()
        .datasets()
        .fhirStores()
        .fhir()
        .createResource(parentName, resourceType, httpBody);
    request.setAccessToken(HealthcareQuickstart.getAccessToken());
    HttpBody response = request.execute();

    System.out.println("Created FHIR resource: " + response.getData());
  }
}
// [END healthcare_create_resource]
