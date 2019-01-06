package bath.data.groupon;

import bath.data.dao.groupon.GrouponDao;
import bath.dataservice.groupon.GrouponDataService;
import bath.entity.groupon.Groupon;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrouponDataServiceImpl implements GrouponDataService {
    private final GrouponDao grouponDao;

    @Autowired
    public GrouponDataServiceImpl(GrouponDao grouponDao) {
        this.grouponDao = grouponDao;
    }

    @Override
    public void addGroupon(Groupon groupon) {
        grouponDao.save(groupon);
    }

    @Override
    public Groupon findById(String id) throws NotExistException {
        Optional<Groupon> optionalUser = grouponDao.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new NotExistException("团购openid", id);
        }
    }

    @Override
    public List<Groupon> getAll() {
        return grouponDao.findAll();
    }

    @Override
    public void updateGroupon(Groupon groupon) {
        grouponDao.save(groupon);

    }

    @Override
    public void deleteGrouponById(String id) throws NotExistException {
        findById(id);
        grouponDao.deleteById(id);
    }
}
