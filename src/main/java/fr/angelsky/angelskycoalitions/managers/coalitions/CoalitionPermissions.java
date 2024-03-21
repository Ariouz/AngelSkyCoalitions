package fr.angelsky.angelskycoalitions.managers.coalitions;

public enum CoalitionPermissions {

    ACCESS("angelsky.coalitions.access"),

    ;

    private final String permission;

    CoalitionPermissions(String permission)
    {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
