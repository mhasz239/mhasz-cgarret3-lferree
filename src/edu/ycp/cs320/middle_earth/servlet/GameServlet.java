package edu.ycp.cs320.middle_earth.servlet;

public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Game Servlet: doGet");

        // call JSP to generate empty form
        req.getRequestDispatcher("/_view/Game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Game Servlet: doPost");

        Numbers model = new Game();

        GameController controller = new GameController();

        controller.setModel(model);

        // holds the error message text, if there is any
        String errorMessage = null;

        String command = getString(req, "command");


        model.setCommand(command);


        //if(req.getParameter("submit") != null){
        //    model.setResult(result);
        //}
        if (command_checker) {
            errorMessage = "Invalid Command";
            model.setError(errorMessage);
        }

        if (command == "inventory") {
            req.getRequestDispatcher("/_view/Inventory.jsp").forward(req, resp);
        }
        req.setAttribute("Game", model);


        // now call the JSP to render the new page
        req.getRequestDispatcher("/_view/Game.jsp").forward(req, resp);

    }
}
