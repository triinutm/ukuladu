package model.item.control;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.item.dao.HibernateUtil ; 

public class HibernateListener  implements ServletContextListener { 
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory().close(); // Free all resources 		
	}
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		HibernateUtil.getSessionFactory(); // Just call the static initializer of that class		
	} 
} 
