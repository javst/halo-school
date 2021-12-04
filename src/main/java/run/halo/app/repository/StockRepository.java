package run.halo.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import run.halo.app.model.entity.Order;
import run.halo.app.model.entity.Stock;
import run.halo.app.repository.base.BaseRepository;
import java.util.List;

public interface StockRepository extends BaseRepository<Stock,Integer> {

    @Query("select stock from Stock stock")
    public List<Stock> findAllBy();

    @Query(value = "select * from instock where create_time >= :date ",nativeQuery = true)
    public List<Stock> findBy(@Param("date") String date);

    @Query("select count(stock) from Stock stock")
    public Integer countInStock();

    @Query("select stock from Stock stock where stock.importPeople = :importPeople")
    public List<Stock> findByImportPeople(@Param("importPeople") String importPeople);

    @Query(value = "select * from instock where create_time >= :date and import_people = :importPeople",nativeQuery = true)
    public List<Stock> findBy(@Param("importPeople") String importPeople,@Param("date") String date);

    @Query(value = "select * from instock limit :start,:top",nativeQuery = true)
    List<Stock> findLatest(int start, int top);
}
