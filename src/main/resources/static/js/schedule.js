'user strict'

/**
 *
 * @param table
 * @constructor
 */
function SetTableCanEdit(table){
    for(var i=2; i<table.rows.length;i++){
        SetRowCanEdit(table.rows[i]);
    }
}

/**
 *
 * @param row
 * @constructor
 */
function SetRowCanEdit(row){
    var inputs = row.getElementsByTagName("input");
    for (let j = 0; j < row.cells.length; j++) {

        let editType = row.cells[j].getAttribute("EditType");

        if (editType) {
                row.cells[j].onclick = function () {
                    if (inputs[0].checked) {
                        EditCell(this);
                    }
                }
            }

    }
}

/**
 *
 * @param element
 * @param editType
 * @constructor
 */
function EditCell(element, editType){

    var editType = element.getAttribute("EditType");

    switch(editType){
        case "TextBox":
            CreateTextBox(element, element.innerHTML);
            break;
        case "DropDownList":
            CreateDropDownList(element);
            break;
        case "TimeBox":
            CreateTimeBox(element, element.innerHTML);
            break;
        default:
            break;
    }
}

/**
 *
 * @param element
 * @param value
 * @constructor
 */
function CreateTimeBox(element, value){

    var editState = element.getAttribute("EditState");
    if(editState != "true"){

        var timeBox = document.createElement("INPUT");
        timeBox.type = "time";
        timeBox.className="EditCell_TimeBox";
        value=value.trim();

        if(!value){
            value = element.getAttribute("Value");
        }
        timeBox.value = value;

        timeBox.onblur = function (){
            CancelEditCell(this.parentNode, this.value);
        }

        ClearChild(element);
        element.appendChild(timeBox);
        timeBox.focus();
        timeBox.select();

        element.setAttribute("EditState", "true");
        element.parentNode.parentNode.setAttribute("CurrentRow", element.parentNode.rowIndex);
    }

}

/**
 *
 * @param element
 * @param value
 * @constructor
 */
function CreateTextBox(element, value){

    var editState = element.getAttribute("EditState");
    if(editState != "true"){

        var textBox = document.createElement("INPUT");
        textBox.type = "text";
        textBox.className="EditCell_TextBox";

        if(!value){
            value = element.getAttribute("Value");
        }
        textBox.value = value;


        textBox.onblur = function (){
            CancelEditCell(this.parentNode, this.value);
        }

        ClearChild(element);
        element.appendChild(textBox);
        textBox.focus();
        textBox.select();

        element.setAttribute("EditState", "true");
        element.parentNode.parentNode.setAttribute("CurrentRow", element.parentNode.rowIndex);
    }

}

/**
 *
 * @param element
 * @param value
 * @constructor
 */
function CreateDropDownList(element, value){

    var editState = element.getAttribute("EditState");
    if(editState != "true"){

        var downList = document.createElement("Select");
        downList.className="EditCell_DropDownList";


        var items = element.getAttribute("DataItems");

        if(items){
            items = eval("[" + items + "]");
            for(var i=0; i<items.length; i++){
                var oOption = document.createElement("OPTION");
                oOption.text = items[i].text;
                oOption.value = items[i].value;
                downList.options.add(oOption);
            }
        }

        if(!value){
            value = element.getAttribute("Value");
        }
        downList.value = value;

        downList.onblur = function (){
            CancelEditCell(this.parentNode, this.value, this.options[this.selectedIndex].text);
        }

        ClearChild(element);
        element.appendChild(downList);
        downList.focus();

        element.setAttribute("EditState", "true");
        element.parentNode.parentNode.setAttribute("LastEditRow", element.parentNode.rowIndex);
    }

}

/**
 *
 * @param element
 * @param value
 * @param text
 * @constructor
 */
function CancelEditCell(element, value, text){
    element.setAttribute("Value", value);
    if(text){
        element.innerHTML = text;
    }else{
        element.innerHTML = value;
    }
    element.setAttribute("EditState", "false");

}

/**
 *
 * @param element
 * @constructor
 */
function ClearChild(element){
    element.innerHTML = "";
}

/**
 * refresh main content
 */
function refreshScheduleList() {
    let requestURL = "schedule";

    $.get(requestURL, function (data) {
        $('#main_content').html(data);
    })
}
/**
 *
 * @param table
 * @returns {[]}
 * @constructor
 */
function GetSelectedRows(table){
    var rows = [];
    for(var i=2; i<table.rows.length;i++){
        var inputs = table.rows[i].getElementsByTagName("input");
        if(inputs[0].checked)
            rows.push(table.rows[i]);
    }
    return rows;
}

/**
 *
 * @param rows
 * @returns {[]}
 * @constructor
 */
function GetSelectedRowsData(rows){
    var tableData = [];
    for(var i=0; i<rows.length;i++){
        tableData.push.apply(tableData,GetRowData(rows[i]));
    }

    return tableData;
}

/**
 *
 * @param row
 * @returns {[]}
 * @constructor
 */
function GetRowData(row){
    var rowData = [];
    for (let i=5;i<row.cells.length; i++)// 16 rows
    {
        let cellData = {};
        for(let j=1;j<5; j++){
            let name = tableId.rows[0].cells[j].getAttribute("Name");

            if(name){
                let value = row.cells[j].getAttribute("Value");
                if(!value){
                    value = row.cells[j].innerHTML;
                }
                cellData[name] = parseInt(value.trim());
            }
        }

        let value = row.cells[i].getAttribute("Value");
        if(!value){
            value = row.cells[i].innerHTML;
        }
        let valueTime = value.trim().split(":")
        value = new Date(0,0,1,parseInt(valueTime[0],10),parseInt(valueTime[1],10),0,0);

        if (i == 5){
            cellData["weekNo"] = 1;
            cellData["meetingType"] = 1;
            cellData["meetingTime"] = value;
        }
        else if (i == 6){
            cellData["weekNo"] = 1;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }
        else if (i == 7){
            cellData["weekNo"] = 2;
            cellData["meetingType"] = 1;
            cellData["meetingTime"] = value;
        }
        else if (i == 8){
            cellData["weekNo"] = 2;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }
        else if (i == 9){
            cellData["weekNo"] = 3;
            cellData["meetingType"] = 1;
            cellData["meetingTime"] = value;
        }
        else if (i == 10){
            cellData["weekNo"] = 3;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }
        else if (i == 11){
            cellData["weekNo"] = 4;
            cellData["meetingType"] = 1;
            cellData["meetingTime"] = value;
        }
        else if (i == 12){
            cellData["weekNo"] = 4;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }
        else if (i == 13){
            cellData["weekNo"] = 5;
            cellData["meetingType"] = 1;
            cellData["meetingTime"] = value;
        }
        else if (i == 14){
            cellData["weekNo"] = 5;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }
        else if (i == 15){
            cellData["weekNo"] = 6;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }
        else if (i == 16){
            cellData["weekNo"] = 7;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }
        else if (i == 17){
            cellData["weekNo"] = 8;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }
        else if (i == 18){
            cellData["weekNo"] = 9;
            cellData["meetingType"] = 1;
            cellData["meetingTime"] = value;
        }
        else if (i == 19){
            cellData["weekNo"] = 9;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }
        else if (i == 20){
            cellData["weekNo"] = 11;
            cellData["meetingType"] = 2;
            cellData["meetingTime"] = value;
        }

        rowData.push(cellData)
    }

    return rowData;
}

/**
 * show success modal
 * @param data
 */
function successModal(data) {
    let $modalSuccessMsg = $('#modal-sm-success');
    let message;
    if (data) {
        message = data.message;
    } else {
        message = "success";
    }
    $modalSuccessMsg.find("span.prompt_message").text(message);
    $modalSuccessMsg.modal();
}

/**
 * show failure modal
 * @param data
 */
function failureModal(data) {
    let $modalFailureMsg = $('#modal-sm-failure');
    let message;
    if (data) {
        message = data.message;
    } else {
        message = "failure";
    }
    $modalFailureMsg.find("span.prompt_message").text(message);
    $modalFailureMsg.modal();
}

/**
 *
 */
$("input[type='checkbox']").change(function() {
    SetTableCanEdit(tableId);
});

/**
 * update main project list after saving/updating/deleting project successfully
 */
$('#modal-sm-success').on('hidden.bs.modal', function () {
    refreshScheduleList();
});

/**
 * show confirm generate modal
 */
function confirmGenerateSchedules(ele) {
    let $modal = $('#modal_generate_confirm');
    $modal.find('button.btn-danger').val(ele.value);
    $modal.modal();
}

/**
 * generate event
 */
function generateSchedules(ele) {
    $.get(ele.value, function (data) {
        if (data.code === 0) {
            successModal(data);
        } else {
            failureModal(data);
        }
    })

}

/**
 * update the selected rows
 */
$("#update").click(function () {
    var rows = GetSelectedRows(tableId);

    var json = GetSelectedRowsData(rows);

    $.ajax({
        type: 'POST',
        url: '/schedule/update',
        data: JSON.stringify(json),
        contentType: "application/json",
        success: function (data) {
            if (data.code === 0) {
                successModal(data);
            } else {
                failureModal(data);
            }
        },
        error: function (xhr, status, error) {
            failureModal();
        }
    });
})


