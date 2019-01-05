package bath.dataservice.groupon;

import bath.entity.groupon.Groupon;
import bath.exception.NotExistException;

import java.util.List;


public interface GrouponDataService {
    void addGroupon(Groupon groupon);
    Groupon findById(String id) throws NotExistException;
    List<Groupon> getAll();
    void updateGroupon(Groupon groupon);
    void deleteGrouponById(String id) throws NotExistException;

}
