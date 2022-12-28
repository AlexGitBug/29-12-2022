package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UrlPath {
    public static final String LOGIN = "/login"; //Public
    public static final String REGISTRATION = "/registration"; //Public
    public static final String IMAGES = "/images"; //User
    public static final String LOCALE = "/locale"; //User
    public static final String ROOM_INFO = "/roominfo"; //User
    public static final String ORDER = "/order"; //User
    public static final String ORDER_DONE = "/orderdone"; //User
    public static final String MAIN_PAGE = "/mainpage"; //User
    public static final String ADMIN_PAGE = "/adminpage"; //Admin
    public static final String ADD_ROOM = "/addroom"; //Admin
    public static final String LOGOUT = "/logout"; //Admin/USER
}