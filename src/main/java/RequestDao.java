import java.util.List;

public interface RequestDao {
    Request getRequestById(int id);

    List<Request> getRequestsBySubmitter(String submitter);

    List<Request> getRequestsByStatus(String status);

    void updateRequest(Request request);

    void addRequest(Request request);
}
