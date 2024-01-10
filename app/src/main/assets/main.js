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

var data = '[{"Name":"Akaash Dubey","Organization":"Anikash","Position":"Student","Education":"AITSchool","Skills":"Program","Honors":"sadasd","References":"", "EmploymentStatus":"Employed"}, {"Name":"asdasd","Organization":"sdcvrw","Position":"fvr","Education":"dvrwSchool","Skills":"dvsrf","Honors":"dvsd","References":"johnny", "EmploymentStatus":"Unemployed"}]'

function getData() {
    // This JSON files is hosted over the web
    try {
        data = Android.getDataString();
    } catch (e) {
        console.log("No Android Studio")
    }
   	
    // Parse the JSON string into an object
    data = JSON.parse(data);
    // Iterate over the object
    for (let i in data) {
        // Insert a row into the HTML table
        insertRow(data[i], i);
    }
}

function openPortfolio(id) {
    try {
        console.log(id)
        Android.showPage(id);
    } catch (e) {
        console.log("No Android Studio")
    }
}

function insertRow(data, i) {
    // get the portfolio-list
    var div = document.getElementById("portfolio-list");
    // insert new code
    color = "white"
    if (data.EmploymentStatus == "Employed") {
        color = "green"
    } else if (data.EmploymentStatus == "Unemployed") {
        color = "red"
    }
    
    div.innerHTML += `
    <div class="portfolio-item ${color}" onclick="openPortfolio(${i})">
      <div class="portfolio-item-header">
        <h2 class="portfolio-item-title">${data.Name}</h2>
      </div>
      <div class="portfolio-item-body">
        <p class="portfolio-item-description">${data.Organization}</p>
        <p class="portfolio-item-amount">${data.Position}</p>
        <p class="portfolio-item-amount">${data.Education}</p>
      </div>
      <div class="portfolio-item-body">
        <p class="portfolio-item-description">${data.Skills}</p>
        <p class="portfolio-item-amount">${data.Honors}</p>
        <p class="portfolio-item-amount">${data.References}</p>
      </div>
    </div>
    `;


}