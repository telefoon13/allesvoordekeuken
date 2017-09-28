package be.vdab.servlets.artikels;

import be.vdab.repositories.ArtikelRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(name = "ZoekServlet", urlPatterns = "/artikels/zoekopnummer.htm")
public class ZoekServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/zoeken.jsp";
	private final transient ArtikelRepository artikelRepository = new ArtikelRepository();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getQueryString() != null){
			try{
				artikelRepository.read(Long.parseLong(request.getParameter("id"))).ifPresent(artikel -> request.setAttribute("artikel", artikel));
			} catch (NumberFormatException ex){
				request.setAttribute("fouten", Collections.singletonMap("id", "Tik een getal"));
			}
		}
		request.getRequestDispatcher(VIEW).forward(request,response);
	}
}
