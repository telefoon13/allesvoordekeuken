package be.vdab.servlets.artikels;

import be.vdab.services.ArtikelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(name = "KortingenServlet", urlPatterns = "/artikels/korting.htm")
public class KortingenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/korting.jsp";
	private final transient ArtikelService artikelService = new ArtikelService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("artikels", artikelService.findAll());
		String id = request.getParameter("id");
		if (id != null){
			artikelService.read(Long.parseLong(id)).ifPresent(artikel -> request.setAttribute("artikel", artikel));
		}

		request.getRequestDispatcher(VIEW).forward(request,response);
	}
}
