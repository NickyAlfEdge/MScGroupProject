'use strict';

/**
 * StudentGroupTable table datatables configuration
 */
$(document).ready(function () {
    // Setup - add a text input to each footer cell
    $('.studentGroupTable tfoot th').each(function () {
        const title = $(this).text();
        if (title === "ID" || title === "Group Name" || title === "Facilitator ID") {
            $(this).html('<input class="colSearch" type="text" placeholder="Search ' + title + '" />');
        }
    });

    const table = $('.studentGroupTable').DataTable({
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
 * Student Group Table table
 */
$(document).ready(function () {
    $('.studentGroupList').DataTable({
        "paging": false,
        "searching": false,
        "info": false,
        "ordering": false
    });
    $('.dataTables_length').addClass('bs-select');
});


/**
 * show success modal
 * @param data
 */
function successModal(data) {
    let modalSuccessMsg = $('#modal-sm-success');
    let message;
    message = data.toString();
    modalSuccessMsg.find("span.prompt_message").text(message);
    modalSuccessMsg.modal();
}

/**
 * show failure modal
 * @param data
 */
function failureModal(data) {
    let modalFailureMsg = $('#modal-sm-failure');
    let message;
    message = data.toString();
    modalFailureMsg.find("span.prompt_message").text(message);
    modalFailureMsg.modal();
}

/**
 * get previous rubric cell content and replace the cells content with it on close
 */
$('.studentGroupTable').on('click', '.details', function() {
    let groupId = $(this).parent().find('.details').val();
    let groupDetails = $('#group-detail');
    groupDetails.modal();

    $.ajax({
        type: "GET",
        url: "/groupMembers",
        data: { groupId: groupId },
        success: function(data) {
            if (data.length > 0) {
                $.each(data, function (index, item) {
                    let table = $('.studentGroupList').DataTable();
                    table.row.add([
                        data[index][0],
                        data[index][1],
                        data[index][2],
                        data[index][3]]).draw();
                });
            } else {
                failureModal("This group has 0 members.");
            }
        },
        error: function(xhr) {
            failureModal("Error in transmission, please alert the administrator");
        }
    });
});


/**
 * refresh studentList on modal close
 */
$('#group-detail').on('hidden.bs.modal', function () {
    let table = $('.studentGroupList').DataTable();
    table.clear().draw();
});

/**
 *
 */
function autoAllocation() {
    let modalDelete = $('#modal-sm-delete');
    modalDelete.find("span.prompt_message").text("Would you like to allocate students to their group automatically?" +
        " Notice: this only allocate students with no group to new group and will not change existing group." +
        "It may cost several seconds, don't worry");
    modalDelete.modal();

    $('#modal-sm-delete #confirmDelete').click(function () {
        $.ajax({
            type: 'GET',
            url: '/groupPreference/autoAllocation',
            contentType: "application/json",
            success: function (data) {
                if (data.code === 0) {
                    successModal("Groups allocated");
                } else {
                    failureModal("Groups have already been allocated for each Student");
                }
            },
            error: function (xhr, status, error) {
                failureModal("Error");
            }
        });
    });
}

/**
 * Show clear modal and clear data for student grades content
 */
$('.studentGroupTable .delete').click(function () {

    let groupId = $(this).parent().find('.delete').val();
    let json = { groupId: groupId };
    let modalDelete = $('#modal-sm-delete');
    modalDelete.find("span.prompt_message").text("Are you sure you would like to delete this Group?");
    modalDelete.modal();

    $('#modal-sm-delete #confirmDelete').click(function () {

        $.ajax({
            type: 'POST',
            url: '/groupList/deleteGroup',
            contentType: "application/json",
            data: JSON.stringify(json),
            success: function (data) {
                if (data.code === 0) {
                    successModal("Success");
                } else {
                    failureModal( "Group contains active members, cannot delete.")
                }
            },
            error: function (xhr, status, error) {
                failureModal("Error while deleting group, contact administrator");
            }
        });
    });
});

/**
 * reload page contents on post success
 */
function refreshPage() {
    let requestURL;
    requestURL = "groupList";
    $.get(requestURL, function (data) {
        $('#main_content').html(data);
    })
}

/**
 * refresh page on success modal close
 */
$('#modal-sm-success').on('hidden.bs.modal', function () {
    refreshPage();
});
