package bath.entity.coupon;

import bath.entity.user.User;
import javax.persistence.*;

@Entity
@Table
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="isUsed")
    private boolean isUsed;

    @Column(name="isValid")
    private boolean isValid;

    @Column(name="minusPrice")
    private double minusPrice;

    @Column(name="fullPrice")
    private double fullPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public double getMinusPrice() {
        return minusPrice;
    }

    public void setMinusPrice(double minusPrice) {
        this.minusPrice = minusPrice;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }
}
