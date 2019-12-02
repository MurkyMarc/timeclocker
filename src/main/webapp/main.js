document.getElementById('add').addEventListener('submit', function(e){
    e.preventDefault();     // stops expecting a page refresh
    let new_timesheet = {
        "monday": document.getElementById('monday').value,
        "tuesday": document.getElementById('tuesday').value,
        "wednesday": document.getElementById('wednesday').value,
        "thursday": document.getElementById('thursday').value,
        "friday": document.getElementById('friday').value,
        "saturday": document.getElementById('saturday').value,
        "sunday": document.getElementById('sunday').value,
        "weekEndDate": "2019-01-01",
        //"weekEndDate": document.getElementById('week').value,
        "statusId": 1,
        "userId": getCookie("UserId")
    }
    
    let promise = axios.post('http://localhost:8080/timeclocker/api/timesheet', new_timesheet);
    // 1 callback for a successful response
    promise.then(function(response){
        // 2xx
        console.log(response.data);
        // add to the table
        let rows = document.getElementById("list").rows.length;
        appendTimesheet(response.data, rows);

        // clear the form
        document.getElementById('add').reset();
    }).catch(function(){
        console.log(response);
    });
});

document.addEventListener('DOMContentLoaded', function() {
    let userId = getCookie("UserId");
    let promise = axios('http://localhost:8080/timeclocker/api/timesheet?id=' + userId);
    // callback after a successful response
    promise.then(function(response){
        console.log(response);
        createTimesheetRow(response.data);
    }).catch(function(response){
        console.log(response);
    });
});

function createTimesheetRow(list) {
    var i = 1;
    for(let new_timesheet of list){
        appendTimesheet(new_timesheet, i);
        i++;
    }
};

function appendTimesheet(timesheet, i) {

    let tr = document.createElement('tr');
    tr.setAttribute("timesheetId", timesheet.timesheetId);

    let rowNumber = document.createElement('td');
    rowNumber.setAttribute("timesheetId", timesheet.timesheetId);
    rowNumber.innerText = i;
    tr.appendChild(rowNumber);

    let monday = document.createElement('td');
    monday.innerText = timesheet.monday;
    tr.appendChild(monday);

    let tuesday = document.createElement('td');
    tuesday.innerText = timesheet.tuesday;
    tr.appendChild(tuesday);

    let wednesday = document.createElement('td');
    wednesday.innerText = timesheet.wednesday;
    tr.appendChild(wednesday);

    let thursday = document.createElement('td');
    thursday.innerText = timesheet.thursday;
    tr.appendChild(thursday);

    let friday = document.createElement('td');
    friday.innerText = timesheet.friday;
    tr.appendChild(friday);

    let saturday = document.createElement('td');
    saturday.innerText = timesheet.saturday;
    tr.appendChild(saturday);

    let sunday = document.createElement('td');
    sunday.innerText = timesheet.sunday;
    tr.appendChild(sunday);

    let weekEndDate = document.createElement('td');
    weekEndDate.innerText = timesheet.weekEndDate;
    tr.appendChild(weekEndDate);

    let totalHours = document.createElement('td');
    totalHours.innerText = 
        timesheet.sunday + 
        timesheet.monday + 
        timesheet.tuesday + 
        timesheet.wednesday + 
        timesheet.thursday + 
        timesheet.friday + 
        timesheet.saturday;
    tr.appendChild(totalHours);

    let statusId = document.createElement('td');
    let statusValue = timesheet.statusId;
    let status = "";
    switch (statusValue) {
        case 1:
            status = "saved";
            break;
        case 2:
            status = "submitted";
            break;
        case 3:
            status = "approved";
            break;
        case 4:
            status = "denied";
            break;
    }
    statusId.innerText = status;
    tr.appendChild(statusId);

    // if status is 'saved' or 'denied', allow the edit options
    if(statusValue == 1 || statusValue == 4) {
        let editButton = document.createElement('button');
        editButton.innerHTML = "Edit";
        editButton.setAttribute("timesheetId", timesheet.timesheetId);
        editButton.setAttribute("row", i - 1);
        editButton.addEventListener("click", function() {
            for(j=1;j<9;j++) {
                var cell = document.getElementById("list").rows[i].cells.item(j);
                cell.contentEditable = true;
                cell.classList.add("editable");
            }
        });
        tr.appendChild(editButton);

        let saveButton = document.createElement('button');
        saveButton.innerHTML = "Save";
        saveButton.setAttribute("timesheetId", timesheet.timesheetId);
        saveButton.addEventListener("click", function() {
            for(j=1;j<9;j++) {
                let cell = document.getElementById("list").rows[i].cells.item(j);
                cell.contentEditable = false;
                cell.classList.remove("editable");
            }

            let new_timesheet = {
                "timesheetId": Number(document.getElementById("list").rows[i].getAttribute("timesheetid")),
                "monday": Number(document.getElementById("list").rows[i].cells.item(1).innerText),
                "tuesday": Number(document.getElementById("list").rows[i].cells.item(2).innerText),
                "wednesday": Number(document.getElementById("list").rows[i].cells.item(3).innerText),
                "thursday": Number(document.getElementById("list").rows[i].cells.item(4).innerText),
                "friday": Number(document.getElementById("list").rows[i].cells.item(5).innerText),
                "saturday": Number(document.getElementById("list").rows[i].cells.item(6).innerText),
                "sunday": Number(document.getElementById("list").rows[i].cells.item(7).innerText),
                "weekEndDate": "2019-12-08",
                //"weekEndDate": document.getElementById('week').value,
            }
            // console.log(new_timesheet);
    
            let promise = axios.put('http://localhost:8080/timeclocker/api/timesheet', new_timesheet);

            promise.then(function(response){
                // console.log(response.data);
            }).catch(function(){
                console.log(response);
            });
        });
        tr.appendChild(saveButton);

        let submitButton = document.createElement('button');
        submitButton.innerHTML = "Submit";
        submitButton.setAttribute("timesheetId", timesheet.timesheetId);
        submitButton.addEventListener("click", function() {
            // update the table text to say submitted
            document.getElementById("list").rows[i].cells.item(10).innerText = "submitted";
            // delete the buttons. submitted timesheets can't be edited
            for(a=1; a<5;a++) {
                document.getElementById("list").rows[i].children[11].remove();
            }
            
            let new_timesheet = {
                "timesheetId": Number(document.getElementById("list").rows[i].getAttribute("timesheetid")),
                "statusId": 2
            }
            console.log(new_timesheet);
            let promise = axios.put('http://localhost:8080/timeclocker/api/timesheet', new_timesheet);

            promise.then(function(response){
                console.log(response.data);
            }).catch(function(){
                console.log(response);
            });
        });
        tr.appendChild(submitButton);

        // UNFINISHED!!!! - CONSECUTIVE DELETES MAY DELETE THE WRONG ROW
        // row counter is being changed after a deletion. need to revise
        let deleteButton = document.createElement('button');
        deleteButton.innerHTML = "Delete";
        deleteButton.setAttribute("timesheetId", timesheet.timesheetId);
        // console.log("here" + i);
        deleteButton.setAttribute("row", i);
        deleteButton.addEventListener("click", function() {
            deleteId = Number(document.getElementById("list").rows[i].getAttribute("timesheetid"));
            // console.log("the deleteid is:" + deleteId);
            let promise = axios.delete('http://localhost:8080/timeclocker/api/timesheet?id=' + deleteId);

            promise.then(function(response){
                console.log(response.data);
            }).catch(function(){
                console.log(response);
            });

            // delete the row
            document.getElementById("list").rows[i].remove();
        });
        tr.appendChild(deleteButton);
    }

    document.getElementById('list').appendChild(tr);
}

function getCookie(cookiename) {
    var name = cookiename + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }

function todaysDate() {
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0');
    var yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;
    return today;
}
