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
 * Home object for domain model class ItemPriceList.
 * @see hibernate.ItemPriceList
 * @author Hibernate Tools
 */
public class ItemPriceListHome {

	private static final Log log = LogFactory.getLog(ItemPriceListHome.class);

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

	public void persist(ItemPriceList transientInstance) {
		log.debug("persisting ItemPriceList instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(ItemPriceList instance) {
		log.debug("attaching dirty ItemPriceList instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ItemPriceList instance) {
		log.debug("attaching clean ItemPriceList instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(ItemPriceList persistentInstance) {
		log.debug("deleting ItemPriceList instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ItemPriceList merge(ItemPriceList detachedInstance) {
		log.debug("merging ItemPriceList instance");
		try {
			ItemPriceList result = (ItemPriceList) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ItemPriceList findById(long id) {
		log.debug("getting ItemPriceList instance with id: " + id);
		try {
			ItemPriceList instance = (ItemPriceList) sessionFactory
					.getCurrentSession().get("hibernate.ItemPriceList", id);
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

	public List findByExample(ItemPriceList instance) {
		log.debug("finding ItemPriceList instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("hibernate.ItemPriceList")
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
