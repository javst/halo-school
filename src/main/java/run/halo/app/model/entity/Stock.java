package run.halo.app.model.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "instock")
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String title;

    @Column
    private String norms;

    @Column
    private String deviceType;

    @Column
    private Float price;

    @Column
    private Integer stock;

    @Column
    private String categories;

    @Column
    private String deviceNum;

    @Column
    private String importPeople;

    @Column
    private Date createTime;

    public void postConvert(Post post){
        this.title = post.getTitle();
        this.norms = post.getNorms();
        this.createTime = post.getUpdateTime();
        this.deviceNum = post.getDeviceNum();
        this.deviceType = post.getDeviceType();
        this.price = post.getPrice();
        this.stock = post.getStock();
        this.importPeople = post.getImportPeople();
    }
}
