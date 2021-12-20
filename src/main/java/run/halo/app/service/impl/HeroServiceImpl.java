package run.halo.app.service.impl;


import org.springframework.stereotype.Service;
import run.halo.app.model.entity.Hero;
import run.halo.app.repository.HeroRepository;
import run.halo.app.repository.UserRepository;
import run.halo.app.service.HeroService;
import run.halo.app.service.base.AbstractCrudService;
import java.util.List;

@Service
public class HeroServiceImpl extends AbstractCrudService<Hero, Integer> implements HeroService {


    private final HeroRepository heroRepository;

    protected HeroServiceImpl(HeroRepository heroRepository, UserRepository userRepository) {
        super(heroRepository);
        this.heroRepository = heroRepository;

    }


    public List<Hero> findLatest(int start, int top) {
        final List<Hero> latest = heroRepository.findLatest(start, top);
        System.out.println(latest);
        return latest;
    }

    public List<Hero> findByState(int state) {
        final List<Hero> byState = heroRepository.findByState(state);
        return byState;
    }


    public int passOrder(Integer id, Integer state) {
        final Object i = heroRepository.passHero(id, state);
        if ((Integer) i > 0) {
            return 1;
        }
        return 0;

    }

    public int deleteOrder(Integer id) {
        final Object i = heroRepository.deleteHero(id);
        if ((Integer) i > 0) {
            return 1;
        }
        return 0;

    }

    public int countOrders() {
        final Integer count = heroRepository.countHero();
        return count;
    }


    public List<Hero> findByCreateTime(String createTime) {
        return heroRepository.findByCreateTime(createTime);
    }

    public List<Hero> findByUsername(String username) {
        return heroRepository.findByUsername(username);
    }

    public List<Hero> findByUsernameAndCreateTime(String username, String createTime) {
        return heroRepository.findByCreateTimeAndUsername(createTime, username);
    }

    public List<Hero> findByStateAndUsername(int state, String username) {
        return heroRepository.findByStateAndUsername(username, state);
    }

    public List<Hero> findByStateAndCreateTime(int state, String createTime) {
        return heroRepository.findByStateAndCreateTime(createTime, state);
    }

    public List<Hero> findByCreateTimeAndUsernameAndState(String username, String createTime,
        int state) {
        return heroRepository.findByCreateTimeAndUsernameAndState(createTime, username, state);
    }


    public Integer refuseApply(Integer id, Integer state, String advice) {
        final Object o = heroRepository.refuseApply(id, state, advice);
        if ((Integer) o > 0) {
            return 1;
        }
        return 0;
    }

    public List<Hero> findByUserId(Integer id) {
        return heroRepository.findByUserId(id);
    }
}
