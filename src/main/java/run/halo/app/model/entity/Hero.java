package run.halo.app.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import javax.persistence.*;


@Entity
@Table(name = "hero")
@Data
@EqualsAndHashCode(callSuper = true)
public class Hero extends BaseEntity {

    @Id
    @GeneratedValue()
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "money", nullable = false)
    private Float money;

    @Column(name = "state", nullable = false)
    private Integer state;

    @Column(name = "advice")
    private String advice;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;


    @Override
    protected void prePersist() {
        super.prePersist();

    }
}
