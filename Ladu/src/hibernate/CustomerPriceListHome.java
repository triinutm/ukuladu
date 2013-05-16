package hibernate;

// Generated May 16, 2013 2:46:12 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class CustomerPriceList.
 * @see hibernate.CustomerPriceList
 * @author Hibernate Tools
 */
public class CustomerPriceListHome {

	private static final Log log = LogFactory
			.getLog(CustomerPriceListHome.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext()
					.lookup("SessionFactory");
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(CustomerPriceList transientInstance) {
		log.debug("persisting CustomerPriceList instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(CustomerPriceList instance) {
		log.debug("attaching dirty CustomerPriceList instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CustomerPriceList instance) {
		log.debug("attaching clean CustomerPriceList instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(CustomerPriceList persistentInstance) {
		log.debug("deleting CustomerPriceList instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CustomerPriceList merge(CustomerPriceList detachedInstance) {
		log.debug("merging CustomerPriceList instance");
		try {
			CustomerPriceList result = (CustomerPriceList) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public CustomerPriceList findById(long id) {
		log.debug("getting CustomerPriceList instance with id: " + id);
		try {
			CustomerPriceList instance = (CustomerPriceList) sessionFactory
					.getCurrentSession().get("hibernate.CustomerPriceList", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(CustomerPriceList instance) {
		log.debug("finding CustomerPriceList instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("hibernate.CustomerPriceList")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
