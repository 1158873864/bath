package bath.data.user;

import bath.data.dao.user.LevelDao;
import bath.dataservice.user.LevelDataService;
import bath.entity.user.Level;
import bath.exception.NotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelDataServiceImpl implements LevelDataService {
	private final LevelDao levelDao;

	@Autowired
	public LevelDataServiceImpl(LevelDao levelDao) {
		this.levelDao = levelDao;
	}

	@Override
	public void addLevel(Level level) {
		levelDao.save(level);
	}

	@Override
	public Level getLevelByName(String name) throws NotExistException {
		Optional<Level> optionalLevel = levelDao.findById(name);
		if (optionalLevel.isPresent()) {
			return optionalLevel.get();
		} else {
			throw new NotExistException("Level name", name);
		}
	}

	@Override
	public List<Level> getAllLevels() {
		return levelDao.findAll();
	}

	@Override
	public void updateLevelByName(String name, double discountedRatio) throws NotExistException {
		Optional<Level> optionalLevel = levelDao.findById(name);
		if (optionalLevel.isPresent()) {
			Level level = optionalLevel.get();
			level.setDiscountedRatio(discountedRatio);
			levelDao.save(level);
		} else {
			throw new NotExistException("Level name", name);
		}
	}

	@Override
	public void deleteLevelByName(String name) throws NotExistException {
		if (levelDao.existsById(name)) {
			levelDao.deleteById(name);
		} else {
			throw new NotExistException("Level name", name);
		}
	}
}
