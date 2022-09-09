public class LoginDaoFactory {
    private static LoginDao loginDao;
    private LoginDaoFactory(){}

    public static LoginDao getLoginDao() {
        if (loginDao == null) {
            loginDao = new LoginDaoImpl();
        }
        return loginDao;
    }

}
