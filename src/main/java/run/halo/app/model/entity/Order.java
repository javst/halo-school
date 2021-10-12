package run.halo.app.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity(name = "order")
@Table(name = "order")
@ToString(callSuper = true)
public class Order {

    @Id
    @GeneratedValue()
    private Integer id;


    @Column(name = "buy_date",nullable = false)
    private String buy_date;
    @Column(name = "user_id",nullable = false)
    private Integer user_id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "money",nullable = false)
    private Double money;





}
