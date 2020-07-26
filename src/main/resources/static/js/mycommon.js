'use strict';

/**
 *  convert form data to json
 * @param form  jquery object
 * @returns {{}}
 */
function formToJson(form) {
    let json = {};
    const formArr = form.serializeArray();
    for (const ele of formArr) {
        json[ele['name']] = ele['value'];
    }
    return json;
}

/**
 * logout system
 */
function logout() {
    $.get("/logout", function () {
        window.location = "login";
    })
}
