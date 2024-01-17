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

var data = '{"Name":"Martin Luther King Jr.","Organization":"Civil Rights Movement","Position":"Leader","Education":"Boston University School of Theology","Skills":"Speaking, Leadership, Activism","Honors":"Noble Prize, NAACP Spingarn Medal, Presidential Medal of Freedom","References":"Malcolm X, Rosa Parks, John F. Kennedy","EmploymentStatus":"N/A","imageURL":"https://upload.wikimedia.org/wikipedia/commons/0/05/Martin_Luther_King%2C_Jr..jpg"}'

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
    document.getElementById("img").src = data.imageURL;
    document.getElementById("Organization").innerHTML = '<i class="fas fa-building"></i> ' + data.Organization;
    document.getElementById("Position").innerHTML = '<i class="fas fa-briefcase"></i> '+data.Position;
    document.getElementById("Education").innerHTML = '<i class="fas fa-university"></i> '+data.Education;
    document.getElementById("Skills").innerHTML = '<i class="fas fa-cogs"></i> '+data.Skills;
    document.getElementById("Honors").innerHTML = '<i class="fas fa-medal"></i> '+data.Honors;
    document.getElementById("References").innerHTML = '<i class="fas fa-users"></i> '+data.References;
}