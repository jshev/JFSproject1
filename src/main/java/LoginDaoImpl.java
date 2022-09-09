import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Iterator;
import java.util.List;

public class LoginDaoImpl implements LoginDao {

    private SessionFactory factory = HibernateConf.getFactory();

    public LoginDaoImpl() {
    }

    @Override
    public int getLoginIdByCredentials(String username, String password) {
        Transaction transaction = null;
        int id = 0;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        String hql = "Select log.loginId from Login log where log.username=:userName and log.password=:passWord";
        Query query = session.createQuery(hql);
        query.setParameter("userName", username);
        query.setParameter("passWord", password);
        List result = query.list();
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            id = (int) iterator.next();
        }
        transaction.commit();
        return id;
    }

    @Override
    public Login getLoginById(int id) {
        Transaction transaction = null;
        Login login = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        login = session.get(Login.class, id);
        transaction.commit();
        return login;
    }

    @Override
    public void updateLogin(Login login) {
        Transaction transaction = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(login);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Login> getAllLogin() {
        Transaction transaction = null;
        List<Login> logins = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        logins = session.createQuery("from Login").list();
        transaction.commit();
        return logins;
    }

    @Override
    public void addLogin(Login login) {
        Transaction transaction = null;
        Session session = factory.openSession();
        transaction = session.beginTransaction();
        session.save(login);
        transaction.commit();
        session.close();
    }
}
