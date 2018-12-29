package bath.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Level {
	@Id
	@Column
	private String name; //会员级别名

	@Column
	private double discountedRatio; //该会员级别的折扣率，价格是原价的discountedRatio倍


	public Level() {
	}

	public Level(String name, double discountedRatio) {
		this.name = name;
		this.discountedRatio=discountedRatio;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDiscountedRatio() {
		return discountedRatio;
	}

	public void setDiscountedRatio(double discountedRatio) {
		this.discountedRatio = discountedRatio;
	}
}
