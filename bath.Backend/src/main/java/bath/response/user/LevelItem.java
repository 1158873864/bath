package bath.response.user;

import bath.entity.user.Level;

public class LevelItem {
	private String name; //会员级别名
	private double discountedRatio; //该会员级别的折扣率，价格是原价的discountedRatio倍

	public LevelItem(Level level) {
		this.name = level.getName();
		this.discountedRatio=level.getDiscountedRatio();
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
