/**
 * Student MyMark table
 */
$(document).ready(function () {
    $('.studentGradeTable').DataTable({
        "paging": false,
        "searching": false,
        "info": false,
        "ordering": false
    });
    $('.dataTables_length').addClass('bs-select');
});

/**
 * Admin student table datatables configuration
 */
$(document).ready(function () {
    // Setup - add a text input to each footer cell
    $('.studentTable tfoot th').each( function () {
        const title = $(this).text();
        if (title === "Forename" || title === "Surname" || title === "Email Address" || title === "Group") {
            $(this).html( '<input class="colSearch" type="text" placeholder="Search '+title+'" />' );
        }
    });
    $('.studentTable tr').each( function () {
        $('.editGrade').hide();
    });

    const table = $('.studentTable').DataTable({
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
 * rubric table datatables configuration
 */
$(document).ready(function () {
    $('.rubricTable').DataTable({
        "paging": false,
        "searching": false,
        "info": false,
        "ordering": false
    });
});

/**
 * rubric table edit functionality toggle and styling
 */
$(document).ready(function () {
    $('.postCriterion').hide();
    $('.closeCriterion').hide();

    $('.rubricTable').on('click', '.edit', function() {
        let criterion = $(this).parent().parent().find('.criterionText');
        let value = $(criterion).attr('contenteditable');

        $(this).parent().find('.postCriterion').toggle();
        $(this).parent().find('.closeCriterion').toggle();
        if (value === 'false') {
            $(criterion).attr('contenteditable','true');
            criterion.css('border', '1px solid #c2bfca');
        } else {
            $(criterion).attr('contenteditable','false');
            criterion.css('border', 'none');
        }
    });
});

/**
 * get previous rubric cell content and replace the cells content with it on close
 */
$(document).ready(function () {
    $('.rubricTable').on('click', '.closeCriterion', function() {
        let criterion = $(this).parent().parent().find('.criterionText');
        let criterionId = $(this).parent().find('.criterionID').val();
        $(this).parent().find('.postCriterion').toggle();
        $(this).parent().find('.closeCriterion').toggle();
        criterion.css('border', 'none');

        $.ajax({
            type: "GET",
            url: "/oldCriterion",
            data: { criterionId: criterionId },
            success: function(data) {
                criterion.text(data);
            },
            error: function(xhr) {
                failureModal("Error in transmission, please alert the administrator");
            }
        });
        $(criterion).attr('contenteditable','false');
    });
});

/**
 * edit rubric content and post function, with modal display for success/failure
 */
$(document).ready(function () {
    $('.rubricTable').on('click', '.postCriterion', function() {
        let criterionId = $(this).parent().find('.criterionID').val();
        let criterionContent = $(this).parent().parent().find('.criterionText').text();
        $.trim(criterionContent);
        let json = { criterionId: criterionId, criterionName: criterionContent};

        if(criterionContent.length <= 350) {
            $.ajax({
                type: 'POST',
                url: '/assessment/updateRubric',
                contentType: "application/json",
                data: JSON.stringify(json),
                success: function (data) {
                    if (data.code === 0) {
                        successModal("Success");
                    } else {
                        failureModal( "Error while Posting grade")
                    }
                },
                error: function (xhr, status, error) {
                    failureModal("Error while Posting grade");
                }
            });
        } else {
            failureModal("Criterion have a max character count of 350");
        }
    });
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
 * upload grade and post function, with modal display for success/failure
 */
$(".gradeField button").click(function () {
    let mark = $(this).parent().find('#studentGrade').val();
    let id = $(this).parent().find('#studentID').val();
    let json = { mark: mark, studentId: id };

    if((mark.length > 0) &&  (mark.length <= 3) && (mark <= 100) && (id >= 1)) {
        $.ajax({
            type: 'POST',
            url: '/assessment/new',
            contentType: "application/json",
            data: JSON.stringify(json),
            success: function (data) {
                if (data.code === 0) {
                    successModal("Success");
                } else {
                    failureModal( "Error while Posting grade")
                }
            },
            error: function (xhr, status, error) {
                failureModal("Error while Posting grade");
            }
        });
    } else if ((mark.length === 0) && (id >= 1)) {
        failureModal("Please enter a valid Grade");
    } else if ((mark.length > 3) && (id >= 1)) {
        failureModal("Please enter a valid Grade");
    } else {
        failureModal("The Student ID is not valid. Please contact an Administrator");
    }
});

/**
 * reload page contents on post success
 */
function refreshPage() {
    let requestURL;
    requestURL = "assessment";
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


/**
 * grade button edit utility display toggle
 */
$('.studentTable').on('click', '.edit', function () {
    let oldGrade = $(this).parent().find('.grade');
    let editGrade = $(this).parent().find('.editGrade');
    $(oldGrade).toggle();
    $(editGrade).toggle();
});


/**
 * edit current grade and post function, with modal display for success/failure
 */
$(".editGrade button").click(function () {
    let mark = $(this).parent().find('#studentGrade').val();
    let id = $(this).parent().find('#studentID').val();
    let markId = $(this).parent().find('#markID').val().replace(',', "");
    let json = { id: markId, mark: mark, studentId: id };

    if((mark.length > 0) &&  (mark.length <= 3) && (mark <= 100) && (id >= 1)) {
        $.ajax({
            type: 'POST',
            url: '/assessment/edit',
            contentType: "application/json",
            data: JSON.stringify(json),
            success: function (data) {
                if (data.code === 0) {
                    successModal("Success");
                } else {
                    failureModal( "Error while Posting grade")
                }
            },
            error: function (xhr, status, error) {
                failureModal("Error while Posting grade");
            }
        });
    } else if ((mark.length === 0) && (id >= 1)) {
        failureModal("Please enter a valid Grade");
    } else if ((mark.length > 3) && (id >= 1)) {
        failureModal("Please enter a valid Grade");
    } else {
        failureModal("The Student ID is not valid. Please contact an Administrator");
    }
});

/**
 * Show clear modal and clear data for student grades content
 */
$('.studentMark #clearStudentMarks').click(function () {

    let modalSuccessMsg = $('#modal-sm-clear');
    modalSuccessMsg.find("span.prompt_message").text("Are you sure you would like to clear all marks within the table?");
    modalSuccessMsg.modal();

    $('#modal-sm-clear #confirmDelete').click(function () {

        $.ajax({
            type: 'POST',
            url: '/assessment/clearMarks',
            contentType: "application/json",
            success: function (data) {
                if (data.code === 0) {
                    successModal("Success");
                } else {
                    failureModal( "Error while clearing grades, contact administrator")
                }
            },
            error: function (xhr, status, error) {
                failureModal("Error while clearing grades, contact administrator");
            }
        });
    });
});

/**
 * Show clear modal and clear data for rubric content
 */
$('.rubric #clearRubricContent').click(function () {

    let modalSuccessMsg = $('#modal-sm-clear');
    modalSuccessMsg.find("span.prompt_message").text("Are you sure you would like to clear all data within the rubric?");
    modalSuccessMsg.modal();

    $('#modal-sm-clear #confirmDelete').click(function () {

        $.ajax({
            type: 'POST',
            url: '/assessment/clearRubric',
            contentType: "application/json",
            success: function (data) {
                if (data.code === 0) {
                    successModal("Success");
                } else {
                    failureModal( "Error while clearing grades, contact administrator")
                }
            },
            error: function (xhr, status, error) {
                failureModal("Error while clearing rubric, contact administrator");
            }
        });
    });
});

/**
 * Show evaluation Modal
 *
 * - To be implemented -
 */
$('.studentEvaluation a').click(function () {

    let modalSuccessMsg = $('#modal-lg-groupEval');
    modalSuccessMsg.find("span.prompt_message").text("Please enter evaluation scores for each member of your group.");
    modalSuccessMsg.modal();
});