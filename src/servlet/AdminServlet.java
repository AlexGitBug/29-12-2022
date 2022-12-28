package servlet;

import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import entity.Enum.RoomStatusEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.OrderService;
import service.RoomService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.ADMIN_PAGE;
import static util.UrlPath.MAIN_PAGE;

@WebServlet(ADMIN_PAGE)
public class AdminServlet extends HttpServlet {

    private final RoomService roomService = RoomService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("numbers", NumberRoomEnum.values());
        req.setAttribute("floors", FloorEnum.values());
        req.setAttribute("statuses", RoomStatusEnum.values());
        req.setAttribute("roomlist", roomService.findAll());

        req.getRequestDispatcher(JspHelper.getPath("rooms"))
                .forward(req, resp);
        req.getRequestDispatcher(JspHelper.getPath("roomfindall"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var name = req.getParameter("name");

    }

}