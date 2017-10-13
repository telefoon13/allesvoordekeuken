package be.vdab.servlets.artikels;

import be.vdab.services.ArtikelService;
import be.vdab.services.ArtikelgroepService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PerArtikelgroepServlet", urlPatterns = "/artikels/perartikelgroep.htm")
public class PerArtikelgroepServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/perartikelgroep.jsp";
	private final transient ArtikelgroepService artikelgroepService = new ArtikelgroepService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("artikelgroepen", artikelgroepService.findAll());
		String id = request.getParameter("id");


		if (id != null){
			artikelgroepService.read(Long.parseLong(id)).ifPresent(artikelgroepenEntity -> request.setAttribute("artikelgroep",artikelgroepenEntity));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);

	}
}
