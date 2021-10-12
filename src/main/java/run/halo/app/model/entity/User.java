package run.halo.app.model.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import run.halo.app.model.enums.MFAType;
import run.halo.app.utils.DateUtils;

/**
 * User entity
 *
 * @author ryanwang
 * @date 2019-03-12
 */
@Data
@Entity
@Table(name = "users")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * User name.
     */
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    /**
     * User nick name,used to display on page.
     */
    @Column(name = "nickname", nullable = false)
    private String nickname;

    /**
     * Password.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * User email.
     */
    @Column(name = "email", length = 127)
    private String email;

    /**
     * User avatar.
     */
    @Column(name = "avatar", length = 1023)
    private String avatar;

    /**
     * User description.
     */
    @Column(name = "description", length = 1023)
    private String description;

    /**
     * Expire time.
     */
    @Column(name = "expire_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireTime;

    /**
     * mfa type (current: tfa)
     */
    @Column(name = "mfa_type", nullable = false)
    @ColumnDefault("0")
    private MFAType mfaType;

    /**
     * two factor auth key
     */
    @Column(name = "mfa_key", length = 64)
    private String mfaKey;

    /**
     * money
     */
    @Column(name = "money")
    @ColumnDefault("200")
    private Double money;

    /**
     * student_num
     */
    @Column(name = "sutdent_num", length = 25)
    @ColumnDefault("000000")
    private String student_num;


    @Column(name = "department")
    @ColumnDefault("计算机信息与工程学院")
    private String department ;

    @Column(name = "class_name")
    @ColumnDefault("电子信息")
    private String class_name ;

    @Column(name = "rule", length = 2)
    @ColumnDefault("0")
    private Integer rule;


    @Override
    public void prePersist() {
        super.prePersist();

        if (email == null) {
            email = "";
        }
        if (rule == null) {
            rule = 0;
        }

        if (money == null){
            money = 0.0;
        }

        if (student_num == null){
            student_num = "000000";
        }

        if (avatar == null) {
            avatar = "";
        }

        if (description == null) {
            description = "";
        }

        if (expireTime == null) {
            expireTime = DateUtils.now();
        }
    }
}
