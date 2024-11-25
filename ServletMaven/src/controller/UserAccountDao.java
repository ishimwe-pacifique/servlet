package controller;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.UserAccount;
import util.HibernateUtil;

public class UserAccountDao {

	
	public String createAccount(UserAccount account) {
		
		try(Session session = HibernateUtil.getSession().openSession()){
			
			Transaction trans = session.beginTransaction();
			
			//this is like save
			session.persist(account);
			
			trans.commit();
			
			session.close();
			
			return "saved";
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}
	
}
