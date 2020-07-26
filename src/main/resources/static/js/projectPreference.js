'use strict'

/**
 * show edit group project modal
 */
function modalEditGroupProject(ele) {
    // get group Id from edit button
    const groupId = $(ele).attr("data-groupId");
    const groupName = $(ele).attr("data-groupName");
    const projectId = $(ele).attr("data-projectId");
    // get html elements
    let $modal_edit = $("#modal_editGroupProject");
    let $groupId = $("#groupId");
    let $groupName = $("#groupName");
    let $projectId = $("#projectId");
    //set group Id
    $groupId.val(groupId);
    $groupName.val(groupName);
    if (projectId) {
        $projectId.val(projectId);
    } else {
        $projectId.val("");
    }
    // show modal
    $modal_edit.modal();
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

$(function () {
    /**
     * submit group project edit form modal and alert success/fail popup
     */
    $("#save_group_project").click(function () {
        let $modal_edit = $("#modal_editGroupProject");
        let json = formToJson($("#edit_group_project"));
        $.ajax({
            type: 'POST',
            url: '/projectPreference/save',
            data: JSON.stringify(json),
            contentType: "application/json",
            success: function (data) {
                if (data.code === 0) {
                    $modal_edit.modal('hide');
                    successModal(data);
                } else {
                    failureModal(data);
                }
            },
            error: function (xhr, status, error) {
                failureModal();
            }
        });
    });
    /**
     * update main  list after saving/updating/deleting project successfully
     */
    $('#modal-sm-success').on('hidden.bs.modal', function () {
        refreshProjectList();
    })
    /**
     * update group list content when click pageNumber
     */
    $("ul a.page-link").click(function () {
        // if this is current page, return
        const $parent = $(this).parent();
        let className = $parent[0].className;
        if (className.includes('active')) {
            return;
        }
        // calculate page number
        let pageNum = $(this).text();
        if (pageNum === "«") {
            // find active page
            pageNum = $("li.page-item.active").children().text() - 0 - 1;
            if (pageNum <= 0) {
                return;
            }
        } else if (pageNum === "»") {
            // find active page
            pageNum = $("li.page-item.active").children().text() - 0 + 1;
            const lastPage = $("li.page-item:last").children().text();
            if (pageNum > lastPage) {
                return;
            }
        } else {
            // pageNum is current page
        }
        // update main content
        refreshProjectList(pageNum);
    })

    /**
     * search group project list by group name
     */
    $('#search_btn').click(function () {
        const groupName = $('#table_search').val();
        refreshProjectList(1, groupName);
    })
})

/**
 * refresh main content
 */
function refreshProjectList(pageNum, groupName) {
    let requestURL;
    if (pageNum && groupName) {
        requestURL = "projectPreference/list?pageNum=".concat(pageNum).concat("&groupName=").concat(groupName);
    } else if (pageNum) {
        requestURL = "projectPreference/list?pageNum=" + pageNum;
    } else {
        requestURL = "projectPreference/list";
    }
    console.log(requestURL);
    $.get(requestURL, function (data) {
        $('#main_content').html(data);
    })
}
