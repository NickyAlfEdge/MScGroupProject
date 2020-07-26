'use strict';

/**
 * refresh main content
 */
function refreshProjectList(pageNum, projectName) {
    let requestURL;
    if (projectName) {
        requestURL = "project?projectName=" + projectName;
    } else if (pageNum) {
        requestURL = "project?pageNum=" + pageNum;
    } else {
        requestURL = "project";
    }
    $.get(requestURL, function (data) {
        $('#main_content').html(data);
    })
}

/** open project detail page in modal window
 * projectList title link to project detail pages
 */
function modalProjectDetail(ele) {
    $.get(ele.getAttribute('data-project-detail'), function (data) {
        $('#modal_project_detail div.modal-body').html(data);
        $('#modal_project_detail').modal();
    });
}

/**
 * show modal with edited project info
 */
function editProject(ele) {
    let $tagId = $("#tagId");
    let $modal_edit = $("#modal-lg-editProject");
    let $projectId = $("#projectId");
    let $name = $("#name");
    let $description = $("#description");
    let $status = $("#status");
    // get project data from server
    let requestPath = ele.value;
    $.get(requestPath, function (data) {
        const project = data.info;
        // update project data in modal
        $projectId.val(project.projectId);
        $name.val(project.name);
        $description.val(project.description);
        // if tag options are null set tag options
        let $options = $tagId.children();
        // choose tag
        $options.each(function () {
            // clear selected and set new selected
            this.selected = false;
            if (this.value === project.tagId) {
                this.selected = true;
            }
        });
        $status.val(project.status);
    });
    // show modal
    $modal_edit.modal();
}

/**
 * show select project modal
 */
function chooseProjectConfirm(ele) {
    let $modal = $('#modal_choose_confirm');
    $modal.find('button.btn-primary').val(ele.value);
    $modal.modal();
}

/**
 * choose one project
 */
function chooseProject(ele) {
    $.get(ele.value, function (data) {
        if (data.code === 0) {
            successModal(data);
        } else {
            failureModal(data);
        }
    })
}

/**
 * show confirm delete project modal
 */
function confirmDeleteProject(ele) {
    let $modal = $('#modal_delete_confirm');
    $modal.find('button.btn-danger').val(ele.value);
    $modal.modal();
}

/**
 * delete project event
 */
function deleteProject(ele) {
    $.get(ele.value, function (data) {
        if (data.code === 0) {
            successModal(data);
        } else {
            failureModal(data);
        }
    })
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
 * TODO: when model is hidden, refresh main content
 */
$(function () {

    let $tagId = $("#tagId");
    let $modal_edit = $("#modal-lg-editProject");
    let $editProjectForm = $("#edit_project");

    /**
     * update all project tags in form and return all options
     * @returns {jQuery|[]}
     */
    function updateTagOptions() {
        $.get("/projectTag/all", function (data) {
            for (const tag of data.info) {
                $tagId.append(
                    "<option value=\"" + tag.tagId + "\">" + tag.tagName + "</option>"
                );
            }
        })
    }

    updateTagOptions();

    /**
     * show create modal
     */
    $('#create_project').click(function () {
        $modal_edit.find('input').val('');
        $modal_edit.find('select').val('');
        $modal_edit.modal();
    });

    /**
     * project edit modal submit and alert success/fail popup
     */
    $("#save_project").click(function () {
        var json = formToJson($editProjectForm);
        if (!json.name || !json.description || !json.tagId) {
            return failureModal({message: 'please fill all blanks'});
        }
        $.ajax({
            type: 'POST',
            url: '/project/save',
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
     * update main project list after saving/updating/deleting project successfully
     */
    $('#modal-sm-success').on('hidden.bs.modal', function () {
        refreshProjectList();
    });

    /**
     * update project list content when click pageNumber
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
        if (pageNum === "Previous") {
            // find active page
            pageNum = $("li.page-item.active").children().text() - 0 - 1;
            if (pageNum <= 0) {
                return;
            }
        } else if (pageNum === "Next") {
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
    });

    /**
     * search project list by group name
     */
    $('#search_btn').click(function () {
        const projectName = $('#table_search').val();
        refreshProjectList(1, projectName);
    })
});
