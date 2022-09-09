public class RequestDaoFactory {
    private static RequestDao requestDao;
    private RequestDaoFactory(){}

    public static RequestDao getRequestDao() {
        if (requestDao == null) {
            requestDao = new RequestDaoImpl();
        }
        return requestDao;
    }
}
