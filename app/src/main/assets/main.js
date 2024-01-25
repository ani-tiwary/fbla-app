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

var data = '[{"Name":"Martin Luther King Jr.","Organization":"Civil Rights Movement","Position":"Leader","Education":"Boston University School of Theology","Skills":"Speaking, Leadership, Activism","Honors":"Noble Prize, NAACP Spingarn Medal, Presidential Medal of Freedom","References":"Malcolm X, Rosa Parks, John F. Kennedy","EmploymentStatus":"N\/A","imageURL":"https:\/\/upload.wikimedia.org\/wikipedia\/commons\/0\/05\/Martin_Luther_King%2C_Jr..jpg"}, {"Name":"Rosa Parks","Organization":"Civil Rights Movement","Position":"Activist","Education":"Alabama State Teachers College","Skills":"Activism, Leadership","Honors":"Congressional Gold Medal, Presidential Medal of Freedom","References":"Martin Luther King Jr., Malcolm X, John F. Kennedy","EmploymentStatus":"N\/A","imageURL":"https:\/\/upload.wikimedia.org\/wikipedia\/commons\/c\/c4\/Rosaparks.jpg"}, {"Name":"Elon Musk","Organization":"Tesla","Position":"CEO","Education":"University of Pennsylvania","Skills":"Leadership, Engineering","Honors":"Very Rich Man","References":"Jeff Bezos, Bill Gates, Steve Jobs","EmploymentStatus":"Employed","imageURL":"https:\/\/upload.wikimedia.org\/wikipedia\/commons\/9\/99\/Elon_Musk_Colorado_2022_%28cropped2%29.jpg"}, {"Name":"Dr. Wiener","Organization":"AIT","Position":"Future Principal","Education":"Harvard \/ Stanford \/ MIT \/ Columbia","Skills":"Teaching, Leadership, Programming","Honors":"Teacher of the Year","References":"Paul Defrancesco, Ani Tiwary, Akash Dubey","EmploymentStatus":"Employed","imageURL":"pictures\/wiener.jpeg"}, {"Name":"Oskar Iwaniuk","Organization":"DoorDash","Position":"Delivery Driver","Education":"AIT \/ Future Harvard Student","Skills":"Soccer, Track, Programming","Honors":"Driver of the Month","References":"Ana Obradovic, Sohem Rai, Barack Obama","EmploymentStatus":"Employed","imageURL":"pictures\/oskar.jpeg"}, {"Name":"Dylan Lad Intwala","Organization":"NJTransit","Position":"Fan","Education":"None","Skills":"None","Honors":"None","References":"Son Heung-min","EmploymentStatus":"Unemployable","imageURL":"pictures\/dylan.jpeg"}, {"Name":"Sohem Rai","Organization":"Popeye\'s","Position":"Head Chef","Education":"Culinary and Military School","Skills":"Cooking, Rizz, Rapping","Honors":"Day 3, Cured Zika, Nobel Peace Prize","References":"Kendrick Lamar, Michelle Obama, Megan Fox","EmploymentStatus":"Employed","imageURL":"pictures\/sohem.jpeg"}, {"Name":"Akash Dubey","Organization":"Math League","Position":"Student","Education":"AIT","Skills":"Programming, Math, Leading","Honors":"A Cash Money","References":"Dr. Wiener, Abdd Sharma, Megan Fox","EmploymentStatus":"N\/A","imageURL":"pictures\/akash.jpeg","Coolness":"Very Cool"}, {"Name":"Ani Tiwary","Organization":"Math League","Position":"Student","Education":"AIT","Skills":"Programming, Math, Leading","Honors":"Cool Guy","References":"Dr. Wiener, Michelle Obama, Megan Fox","EmploymentStatus":"Employed","imageURL":"pictures\/ani.jpeg"}, {"Name":"Ayan Zia","Organization":"Math League","Position":"Student","Education":"AIT","Skills":"Programming, Database, Leading","Honors":"Just Chill","References":"Akash Dubey, Shray Sachdeva, Archana Maktal","EmploymentStatus":"Employed","imageURL":"pictures\/ayan.jpeg"}, {"Name":"Anusha Iyer","Organization":"Newspaper Club \/ NHS","Position":"Head","Education":"AIT","Skills":"Writing, Organizing, Leading","Honors":"Physics God","References":"Dr. Fang, Mark Raquet, Caraline Koellhoffer","EmploymentStatus":"Employed","imageURL":"pictures\/anusha.jpeg"}, {"Name":"Maya Balakumaran","Organization":"Newspaper Club","Position":"Student","Education":"AIT \/ JHU","Skills":"Editing, Leading, Fencing","Honors":"Master Economist","References":"Anusha Iyer, Michelle Obama, Caren Villareal","EmploymentStatus":"Employed","imageURL":"pictures\/maya.jpeg"}, {"Name":"Siri Challa","Organization":"Math League","Position":"Part-time Member","Education":"AIT \/ University of Pennsylvania","Skills":"Public Speaking, English, Leading","Honors":"Master Debater","References":"Dr. Wiener, Michelle Obama, Megan Fox","Employment":"Employed","imageURL":"pictures\/siri.jpeg"}]'

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
    var color = "white_gradient";
    var textColor = "text-dark";
    if (data.EmploymentStatus == "Employed") {
        color = "green_gradient";
    } else if (data.EmploymentStatus == "Unemployed") {
        color = "red_gradient";
    }
    
    div.innerHTML += `
    <div class="card ${color} ${textColor} mb-3 mr-2 ml-2 mt-1 pt-3" onclick="openPortfolio(${i})" style="cursor: pointer">
    <div class="row no-gutters">
      <div class="col-md-4 text-center">
        <img src="${data.imageURL}" class="card-img text-center" alt="${data.Name}" style="border-radius: 5%; width: 50%;">
      </div>
      <div class="col-md-8">
        <div class="card-body">
          <h2 class="card-title ${textColor}">${data.Name}</h2>
          <p class="card-text"><i class="fas fa-building"></i> ${data.Organization}</p>
          <p class="card-text"><i class="fas fa-briefcase"></i> ${data.Position}</p>
          <p class="card-text"><i class="fas fa-university"></i> ${data.Education}</p>
          <p class="card-text"><i class="fas fa-cogs"></i> ${data.Skills}</p>
          <p class="card-text"><i class="fas fa-medal"></i> ${data.Honors}</p>
          <p class="card-text"><i class="fas fa-users"></i> ${data.References}</p>
        </div>
      </div>
    </div>
  </div>
    `;
}