'use strict';

/**
 * UserManagement table datatables configuration
 */
$(document).ready(function () {
    // Setup - add a text input to each footer cell
    $('.userTable tfoot th').each( function () {
        const title = $(this).text();
        if (title === "ID" || title === "Forename" || title === "Surname" || title === "Email Address" || title === "User Type") {
            $(this).html( '<input class="colSearch" type="text" placeholder="Search '+title+'" />' );
        }
    });

    const table = $('.userTable').DataTable({
        initComplete: function () {
            // Apply the search
            this.api().columns().every(function () {
                const that = this;

                $('input', this.footer()).on('keyup change clear', function () {
                    if (that.search() !== this.value) {
                        that
                            .search(this.value)
                            .draw();
                    }
                });
            });
        },
    });
});

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

function refreshUserList(pageNum, userName) {
    let requestURL;
    if (userName) {
        requestURL = "usermanagement?userName=" + userName;
    } else if (pageNum) {
        requestURL = "usermanagement?pageNum=" + pageNum;
    } else {
        requestURL = "usermanagement";
    }
    $.get(requestURL, function (data) {
        $('#main_content').html(data);
    })
}

/**
 * show confirm delete user modal
 */
function confirmDeleteUser(ele) {
    let $modal = $('#modal_delete_confirm');
    $modal.find('button.btn-danger').val(ele.value);
    $modal.modal();
}

/**
 * delete user event
 */
function deleteUser(ele) {
    $.get(ele.value, function (data) {
        if (data.code === 0) {
            successModal(data);
        } else {
            failureModal(data);
        }
    })
}

/**
 * show modal with edited project info
 */
function editUser(ele) {
    let $userId = $("#userId");
    let $surname = $("#surname");
    let $forename = $("#forename");
    let $email = $("#email");
    let $password = $("#password");
    let $userType = $("#userType");
    let $modal_edit = $("#createUserModal");
    // get user data from server
    let requestPath = ele.value;
    //console.log(requestPath);
    $.get(requestPath,function (data) {
        const user = data.info;
        //console.log(user);
        //update user data in modal
        $userId.val(user.userId);
        $surname.val(user.surname);
        $forename.val(user.forename);
        $email.val(user.email);
        $password.val(user.password);
        $userType.val(user.userType);

    });
    // show modal
    $modal_edit.modal();
}

/**
 * user create modal submit and alert success/fail popup
 */
/*
$("#createUserButton").click(function () {
    var json = formToJson($createUserForm);
    $.ajax({
        type: 'POST',
        url: '/usermanagement/create',
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
*/

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

    let $modal_edit = $("#createUserModal");
    let $createUserForm = $("#createUserForm");

    /**
     * show create modal
     */
    $('#createUser').click(function () {
        $modal_edit.find('input').val('');
        $modal_edit.find('select').val('');
        $modal_edit.modal();
    });

    /**
     * user edit modal submit and alert success/fail popup
     */
    $("#createUserButton").click(function () {
        //var json = formToJson($createUserForm);
        let userId = $modal_edit.find('#userId').val();
        let surname = $modal_edit.parent().find('#surname').val();
        let forename = $modal_edit.parent().find('#forename').val();
        let email = $modal_edit.parent().find('#email').val();
        let password = $modal_edit.parent().find('#password').val();
        let userType = $modal_edit.parent().find('#userType').val();
        //console.log(userId);
        let json = { userId:userId, surname:surname,forename:forename,email:email,password:password,userType:userType};

        $.ajax({
            type: 'POST',
            url: '/usermanagement/create',
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
     * update main user list after creating/updating/deleting user successfully
     */
    $('#modal-sm-success').on('hidden.bs.modal', function () {
        refreshUserList();
    });

});
