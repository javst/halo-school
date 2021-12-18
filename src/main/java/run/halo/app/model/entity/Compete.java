package run.halo.app.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "compete")
@Data
@EqualsAndHashCode(callSuper = true)
public class Compete extends BaseEntity{
    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "norm")
    private String norm;

    @Column(name = "link")
    private String link;

    @Column(name = "number")
    private Integer number;

    @Column(name = "username")
    private String username;

    @Column(name = "advice")
    private String advice;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "state")
    @ColumnDefault("0")
    private Integer state;
}
