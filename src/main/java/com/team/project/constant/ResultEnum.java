package com.team.project.constant;

/**
 * Enum used as response code
 */
public enum ResultEnum {

    /**
     * unknown error
     */
    UNKNOWN_ERROR(-500, "Unknown Error, please contact the administrator"),
    HAS_CHOSEN_PROJECT(-7, "Your group has already chosen a project"),
    NO_GROUP(-6, "You have not selected a group"),
    PROJECT_OCCUPIED(-5, "This project has already been allocated"),
    ILLEGAL_USER(-4, "Illegal User"),
    /**
     * up load too big file
     */
    BIG_UPLOAD_FILE(-3, "The file uploaded has exceeded the upload limit"),
    /**
     * params are illegal
     */
    PARAMS_ERROR(-2, "the entered parameters are incorrect, please enter valid information"),
    FAILURE(-1, "Failure"),
    /**
     * success
     */
    SUCCESS(0, "Success");
    /**
     * response code
     */
    private final Integer code;
    /**
     * response message
     */
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
