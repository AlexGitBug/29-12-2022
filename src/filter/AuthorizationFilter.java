package filter;

import dto.CreateDto.CreateUserDto;
import dto.UserInfoDto;
import entity.Enum.RoleEnum;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static util.UrlPath.*;


@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, LOCALE, LOGOUT);
    private static final Set<String> USER_PATH = Set.of(IMAGES, ROOM_INFO, ORDER, MAIN_PAGE);
    private static final Set<String> ADMIN_PATH = Set.of(IMAGES, ROOM_INFO, ADD_ROOM, ADMIN_PAGE, MAIN_PAGE);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublicPath(uri) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isAdminPath(uri) && isAdminLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (isUserPath(uri) && isRegUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            var prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : LOGIN);
        }

    }

    private boolean isUserPath(String uri) {
        return USER_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isAdminLoggedIn(ServletRequest servletRequest) {
        var user = (UserInfoDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user.getRole().getRank().equals(RoleEnum.ADMIN.name());
    }

    private boolean isAdminPath(String uri) {
        return ADMIN_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        var user = (UserInfoDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isRegUserLoggedIn(ServletRequest servletRequest) {
        var user = (UserInfoDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user.getRole().getRank().equals(RoleEnum.USER.name());
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }
}