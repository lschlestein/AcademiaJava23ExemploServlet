package com.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.Model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Repository.Repository;

@WebServlet(urlPatterns = { "/inserir", "/listar", "/home", "/add" })
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Repository r = new Repository("jdbc:mysql://localhost:3306/student", "root", "");

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		if (method.equals("POST")) {
			System.out.println("POST");
			doPost(request, response);
		} else if (method.equals("GET")) {
			System.out.println("GET");
			doGet(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/home")) {
			response.sendRedirect("index.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();

		if (path.equals("/add")) {
			response.sendRedirect("cadastrar.html");
		}

		if (path.equals("/inserir")) {
			Long id = Long.parseLong(request.getParameter("id"));
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			Pessoa p = new Pessoa(id,username, password, email);
			r.cadastrarPessoa(p);
			request.setAttribute("listaPessoas", listarPessoas());
			RequestDispatcher rd = request.getRequestDispatcher("pessoas.jsp");
			rd.forward(request, response);
		}

		if (path.equals("/listar")) {
			request.setAttribute("listaPessoas", listarPessoas());
			RequestDispatcher rd = request.getRequestDispatcher("pessoas.jsp");
			rd.forward(request, response);
		}
	}
	
	protected List<Pessoa>listarPessoas() {
		List<Pessoa> listaPessoas = new ArrayList<>();
		listaPessoas = r.consultarPessoas();
		return listaPessoas;
	}

}
