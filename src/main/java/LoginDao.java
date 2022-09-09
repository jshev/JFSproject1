import java.util.List;

public interface LoginDao {
    int getLoginIdByCredentials(String username, String password);

    Login getLoginById(int id);

    void updateLogin(Login login);

    List<Login> getAllLogin();

    void addLogin(Login login);
}
