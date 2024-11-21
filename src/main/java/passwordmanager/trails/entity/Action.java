package passwordmanager.trails.entity;

public enum Action {
    LOG_IN,
    RESET_PASSWORD,
    LOGGED_OUT,
    UPDATE_PROFILE,
    CHANGE_PASSWORD,
    DELETE_ACCOUNT;

    @Override
    public String toString() {
        switch(this) {
            case LOG_IN: return "Log In";
            case RESET_PASSWORD: return "Reset Password";
            case LOGGED_OUT: return "Logged Out";
            case UPDATE_PROFILE: return "Update Profile";
            case CHANGE_PASSWORD: return "Change Password";
            case DELETE_ACCOUNT: return "Delete Account";
            default: throw new IllegalArgumentException();
        }
    }
}
