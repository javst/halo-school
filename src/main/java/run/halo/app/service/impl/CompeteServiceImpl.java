package run.halo.app.service.impl;

import org.springframework.stereotype.Service;
import run.halo.app.model.entity.Compete;
import run.halo.app.repository.CompeteRepository;
import run.halo.app.repository.base.BaseRepository;
import run.halo.app.service.CompeteService;
import run.halo.app.service.base.AbstractCrudService;
import java.util.List;

@Service
public class CompeteServiceImpl extends AbstractCrudService<Compete, Integer> implements
    CompeteService {


    private CompeteRepository competeRepository;

    protected CompeteServiceImpl(CompeteRepository competeRepository) {
        super(competeRepository);
        this.competeRepository = competeRepository;
    }
    public List<Compete> findLatest(int start, int top) {
        final List<Compete> latest = competeRepository.findLatest(start, top);
        return latest;
    }



    public int countOrders() {
        final Integer count = competeRepository.countCompete();
        return count;
    }


    public List<Compete> findByCreateTime(String createTime) {
        return competeRepository.findByCreateTime(createTime);
    }

    public List<Compete> findByUsername(String username) {
        return competeRepository.findByUsername(username);
    }

    public List<Compete> findByUsernameAndCreateTime(String username, String createTime) {
        return competeRepository.findByCreateTimeAndUsername(createTime, username);
    }

    public List<Compete> findBuyUserId(Integer userId){
        return competeRepository.findByUserId(userId);
    }

    public Integer passOrder(Integer id, Integer state) {
        final Object i = competeRepository.passApply(id, state);
        if ((Integer) i > 0) {
            return 1;
        }
        return 0;
    }

    public Integer refuseApply(Integer id, Integer state, String advice) {
        final Object o = competeRepository.refuseApply(id, state, advice);
        if ((Integer) o > 0) {
            return 1;
        }
        return 0;
    }
}
