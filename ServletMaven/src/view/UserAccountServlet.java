package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UserAccountDao;
import model.RoleType;
import model.UserAccount;

@WebServlet(urlPatterns = "/createAccount")
public class UserAccountServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) {

		String firstName = req.getParameter("firstName");

		String lastName = req.getParameter("lastName");

		String dob = req.getParameter("dob");

		String userName = req.getParameter("userName");

		String password = req.getParameter("password");

		String role = req.getParameter("role");
		 
		RequestDispatcher dispatcher;
		
		PrintWriter out;
		
		res.setContentType("text/html");

		try {

			UserAccount account = new UserAccount();
			
			UserAccountDao accountDao = new UserAccountDao();
			
			
			out = res.getWriter();

			SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-DD");

			account.setFirstName(firstName);
			account.setLastName(lastName);
			account.setDateOfBirth(smf.parse(dob));
			account.setUserName(userName);
			account.setPassword(password);
			
			if(role.equalsIgnoreCase("STUDENT")) {
				account.setRole(RoleType.STUDENT);
			}else if(role.equalsIgnoreCase("HOD")) {
				account.setRole(RoleType.HOD);
			}else if(role.equalsIgnoreCase("REGISTERER")) {
				account.setRole(RoleType.REGISTERER);
			}else if(role.equalsIgnoreCase("LECTURER")) {
				account.setRole(RoleType.LECTURER);
			}else if(role.equalsIgnoreCase("DEAN")) {
				account.setRole(RoleType.DEAN);
			}
			
			
			String saveUser = accountDao.createAccount(account);
			
			if(saveUser.equalsIgnoreCase("saved")) {
				
				out.println("<h1>Account create successfully </h1>");
				dispatcher = req.getRequestDispatcher("createAccount.html");
				dispatcher.include(req, res);
			}else {
				out.println("<h1>Account was not created </h1>");
				dispatcher = req.getRequestDispatcher("createAccount.html");
				dispatcher.include(req, res);
			}
		} catch (ParseException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
