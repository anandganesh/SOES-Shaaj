
public class Stockproblembean {
	private int id;
	private String side;
	private String company;
	private int quantity;


	public  Stockproblembean(int id, String side, String company, int quantity) {
		// TODO Auto-generated constructor stub
		this.setId(id);
		this.setSide(side);
		this.setCompany(company);
		this.setQuantity(quantity);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}



	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String  getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
}
