package bath.entity.address;

import javax.persistence.*;

@Entity

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
    @Column(name="receiver")
    private String receiver;//收货人姓名

    @Column(name="phone")
    private String phone;//收货人电话

    @Column(name="zone")
    private String zone;//地区信息

    @Column(name="detailAddress")
    private String detailAddress;//详细地址

    @Column(name="postcode")
    private String postcode;//邮政编码

    public Address(){}

    public Address(String receiver,String phone,String zone,String detailAddress,String postcode){
        //this.user=user;
        this.receiver=receiver;
        this.phone=phone;
        this.zone=zone;
        this.detailAddress=detailAddress;
        this.postcode=postcode;
    }

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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
