/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
function sendAndroidMessage() {
    /* In this implementation, only the single-arg version of postMessage is supported. As noted
     * in the WebViewCompat reference doc, the second parameter, MessagePorts, is optional.
     * Also note that onmessage, addEventListener and removeEventListener are not supported.
     */
	jsObject.postMessage("The weather in " + `${document.getElementById("title").innerText}` + " today is " +
	`${document.getElementById("shortDescription").innerText} `);
}

var data = '{"Name":"Akaash Dubey","Organization":"Anikash","Position":"Student","Education":"AITSchool","Skills":"Program","Honors":"sadasd","References":"", "EmploymentStatus":"Unemployed"}'

function getData() {
    // This JSON files is hosted over the web
    try {
        data = Android.getDataString();
    } catch (e) {
        console.log("No Android Studio")
    }
   	
    // Parse the JSON string into an object
    data = JSON.parse(data);
    addData(data);
}

function addData(data) {
    // insert new code
    console.log(data)

    document.getElementById("name").innerText = data.Name;
    document.getElementById("Organization").innerText = data.Organization;
    document.getElementById("Position").innerText = data.Position;
    document.getElementById("Education").innerText = data.Education;
    document.getElementById("Skills").innerText = data.Skills;
    document.getElementById("Honors").innerText = data.Honors;
    document.getElementById("References").innerText = data.References;
}