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

package com.google.healthcare.dicom.web;

// [START healthcare_dicomweb_store_instance]

import com.google.HealthcareQuickstart;
import com.google.api.services.healthcare.v1beta1.model.HttpBody;
import com.google.gson.Gson;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.File;
import java.io.IOException;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;

public class DicomWebStoreInstance {
  private static final Gson GSON = new Gson();

  public static void storeInstance(String dicomStoreName, String studyId) throws IOException {
    MultipartEntityBuilder builder = MultipartEntityBuilder.create();
    File f = new File("resources/dicom_00000001_000.dcm");
    builder.addBinaryBody("dicom", f, ContentType.create("application/dicom"), f.getName());
    ByteOutputStream out = new ByteOutputStream();
    builder.build().writeTo(out);
    HttpBody body = new HttpBody();
    body.encodeData(out.getBytes());
    HttpBody response = HealthcareQuickstart.getCloudHealthcareClient()
        .projects()
        .locations()
        .datasets()
        .dicomStores()
        .studies()
        .storeInstances(dicomStoreName, "studies/" + studyId, body)
        .execute();
    System.out.println("Stored Dicom store: " + GSON.toJson(response));
  }
}
// [END healthcare_dicomweb_store_instance]
