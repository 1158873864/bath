package bath.dataservice.user;

import bath.entity.user.Level;
import bath.exception.NotExistException;
import java.util.List;

public interface LevelDataService {

	void addLevel(Level level);

	Level getLevelByName(String name) throws NotExistException;

	List<Level> getAllLevels();

	void updateLevelByName(String name, double discountedRatio) throws NotExistException;

	void deleteLevelByName(String name) throws NotExistException;
}
