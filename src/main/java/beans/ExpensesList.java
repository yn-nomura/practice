package beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ExpensesList {
	/**
	*データベースの文字数制限
	*/

	/**保持データ
	 *
	 */

	private int applicationId;
	private String applicationDate;
	private String name;
	private String expensesName;
	private String payment;
	private String amountOfMoney;
	private String statusName;
	private String updateId;
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
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
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
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	@Override
	public String toString() {
		return "ExpensesList [applicationId=" + applicationId + ", applicationDate=" + applicationDate + ", name="
				+ name + ", expensesName=" + expensesName + ", payment=" + payment + ", amountOfMoney=" + amountOfMoney
				+ ", statusName=" + statusName + ", updateId=" + updateId + "]";
	}
}
