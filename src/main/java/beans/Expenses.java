package beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Expenses {
	/**保持データ**/
	private int applicationId;
	private String applicationDate;
	private String updateDate;
	private String name;
	private String expensesName;
	private String amountOfMoney;
	private String statusName;
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExpensesName() {
		return expensesName;
	}
	public void setExpensesName(String expensesName) {
		this.expensesName = expensesName;
	}
	public String getAmountOfMoney() {
		return amountOfMoney;
	}
	public void setAmountOfMoney(String amountOfMoney) {
		this.amountOfMoney = amountOfMoney;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


}
