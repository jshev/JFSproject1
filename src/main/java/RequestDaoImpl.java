import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class RequestDaoImpl implements RequestDao{

    private SessionFactory factory = HibernateConf.getFactory();

    public RequestDaoImpl() {}

    @Override
    public Request getRequestById(int id) {
        Transaction transaction = null;
        Request request = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        request = session.get(Request.class, id);
        transaction.commit();
        return request;
    }

    @Override
    public List<Request> getRequestsBySubmitter(String submitter) {
        Transaction transaction = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        //String hql = "Select * from Request where submitter=:subMitter";
        String hql = "from Request where submitter=:subMitter";
        Query query = session.createQuery(hql);
        query.setParameter("subMitter", submitter);
        List result = query.list();
        transaction.commit();
        return result;
    }

    @Override
    public List<Request> getRequestsByStatus(String status) {
        Transaction transaction = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        //String hql = "Select * from Request where status=:staTus";
        String hql = "from Request where status=:staTus";
        Query query = session.createQuery(hql);
        query.setParameter("staTus", status);
        List result = query.list();
        transaction.commit();
        return result;
    }

    @Override
    public void updateRequest(Request request) {
        Transaction transaction = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(request);
        transaction.commit();
        session.close();
    }

    @Override
    public void addRequest(Request request) {
        Transaction transaction = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        session.save(request);
        transaction.commit();
        session.close();
    }
}
