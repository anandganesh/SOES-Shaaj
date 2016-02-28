import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StockProblem {
	static int x = 1;
	static int b = 0;
	static int s = 0;
	static int a = 0;
	int c = 0;

	Stockproblembean bean;

	public static void main(String[] args) throws IOException {
		List<Stockproblembean> stp = new ArrayList<>();
		List<String> idsList = new ArrayList<>();
		List<String> companyList = new ArrayList<>();
		HashMap<Integer, String> sellerMap = new LinkedHashMap<Integer, String>(0);
		HashMap<Integer, String> buyerMap = new LinkedHashMap<Integer, String>(0);
		HashMap<Integer, Integer> idMap = new LinkedHashMap<Integer, Integer>(0);
		Integer val = null;
		File file = new File("C:\\Users\\anand\\Downloads\\SOES - Input.csv");

		try (BufferedReader br = Files.newBufferedReader(file.toPath(), StandardCharsets.US_ASCII)) {
			if (x == 1) {
				String line = br.readLine();
				System.out.println(line);
			}
			x = 0;
			String line = br.readLine();
			while (line != null) {

				String[] attributes = line.split(",");

				Stockproblembean st = calc(attributes);

				stp.add(st);

				line = br.readLine();
			}

			for (Stockproblembean stockproblembean : stp) {

				if (stockproblembean.getSide().equals("Buy")
						&& (!sellerMap.containsValue(stockproblembean.getCompany()))) {

					buyerMap.put(stockproblembean.getId(), stockproblembean.getCompany());
					idMap.put(stockproblembean.getId(), stockproblembean.getQuantity());
					System.out.println(stockproblembean.getId() + "," + stockproblembean.getSide() + ","
							+ stockproblembean.getCompany() + "," + stockproblembean.getQuantity() + "," + "0"
							+ " Closed");

				} else if (stockproblembean.getSide().equals("Buy")
						&& (sellerMap.containsValue(stockproblembean.getCompany()))) {

					for (Entry<Integer, String> entry : sellerMap.entrySet()) {

						if (entry.getValue().equals(stockproblembean.getCompany())) {
							val = entry.getKey();

						}
					}
					if (idMap.get(val) - stockproblembean.getQuantity() != 0) {

						if (idMap.get(val) < stockproblembean.getQuantity()) {

							a = Math.abs(idMap.get(val) - stockproblembean.getQuantity());

							System.out.println(stockproblembean.getId() + "," + stockproblembean.getSide() + ","
									+ stockproblembean.getCompany() + "," + stockproblembean.getQuantity() + "," + a
									+ " Open");

						} else {
							int x = idMap.get(val);

							idMap.put(val, idMap.get(val) - stockproblembean.getQuantity());
							a = 0;
							System.out.println(stockproblembean.getId() + "," + stockproblembean.getSide() + ","
									+ stockproblembean.getCompany() + "," + stockproblembean.getQuantity() + "," + a
									+ " Closed");

						}

					}

				} else if (stockproblembean.getSide().equals("Sell")
						&& (!buyerMap.containsValue(stockproblembean.getCompany()))) {
					sellerMap.put(stockproblembean.getId(), stockproblembean.getCompany());
					idMap.put(stockproblembean.getId(), stockproblembean.getQuantity());

					System.out.println(stockproblembean.getId() + "," + stockproblembean.getSide() + ","
							+ stockproblembean.getCompany() + "," + stockproblembean.getQuantity() + "," + "0"
							+ " Closed");

				} else if (stockproblembean.getSide().equals("Sell")
						&& (buyerMap.containsValue(stockproblembean.getCompany()))) {

					for (Entry<Integer, String> entry : buyerMap.entrySet()) {

						if (entry.getValue().equals(stockproblembean.getCompany())) {
							val = entry.getKey();

						}
					}
					if (idMap.get(val) - stockproblembean.getQuantity() != 0) {
						a = Math.abs(idMap.get(val) - stockproblembean.getQuantity());

						System.out.println(stockproblembean.getId() + "," + stockproblembean.getSide() + ","
								+ stockproblembean.getCompany() + "," + stockproblembean.getQuantity() + "," + a
								+ " Open");

					}

				}

			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	private static Stockproblembean calc(String[] attributes) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(attributes[0]);
		String side = attributes[1];
		String company = attributes[2];

		int quantity = Integer.parseInt(attributes[3]);

		return new Stockproblembean(id, side, company, quantity);
	}
}
