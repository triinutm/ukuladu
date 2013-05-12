package hibernate;

// default package
// Generated May 12, 2013 3:56:00 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import javax.naming.InitialContext;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class PriceListStatusType.
 * @see .PriceListStatusType
 * @author Hibernate Tools
 */
public class PriceListStatusTypeHome {

	private static final Logger log = Logger
			.getLogger(PriceListStatusTypeHome.class);

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

	public void persist(PriceListStatusType transientInstance) {
		log.debug("persisting PriceListStatusType instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PriceListStatusType instance) {
		log.debug("attaching dirty PriceListStatusType instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PriceListStatusType instance) {
		log.debug("attaching clean PriceListStatusType instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(PriceListStatusType persistentInstance) {
		log.debug("deleting PriceListStatusType instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PriceListStatusType merge(PriceListStatusType detachedInstance) {
		log.debug("merging PriceListStatusType instance");
		try {
			PriceListStatusType result = (PriceListStatusType) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PriceListStatusType findById(long id) {
		log.debug("getting PriceListStatusType instance with id: " + id);
		try {
			PriceListStatusType instance = (PriceListStatusType) sessionFactory
					.getCurrentSession().get("PriceListStatusType", id);
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

	public List findByExample(PriceListStatusType instance) {
		log.debug("finding PriceListStatusType instance by example");
		try {
			List results = sessionFactory.getCurrentSession()
					.createCriteria("PriceListStatusType")
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