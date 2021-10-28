package run.halo.app.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import okhttp3.internal.Internal;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.sql.SQLException;


@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(callSuper=true)
public class Order extends BaseEntity{

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;
    @Column(name = "user_id",nullable = false)
    private Integer userId;
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "department")
    @ColumnDefault("计算机信息与工程学院")
    private String department ;

    @Column(name = "class_name")
    @ColumnDefault("电子信息")
    private String class_name ;
    @Column(name = "money",nullable = false)
    private Float money;
    @Column(name = "state",nullable = false)
    private Integer state;
    @Column(name = "device",nullable = false)
    private String device;
    @Column(name = "device_id",nullable = false)
    private Integer device_id;
    @Column(name = "student_num")
    private String student_num;





    @Override
    protected void prePersist() {
        super.prePersist();

    }
}
