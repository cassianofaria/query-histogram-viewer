package models;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

public class Histogram {

	private List<String> bins;
	private List<Long> quantities;
	
	public Histogram() {
		this.bins = new ArrayList<>();
		this.quantities = new ArrayList<>();
	}

	public List<String> getBins() {
		return bins;
	}

	public void setBins(List<String> bins) {
		this.bins = bins;
	}
	
	public void addBin(String bin) {
		this.bins.add(bin);
	}

	public List<Long> getQuantities() {
		return quantities;
	}

	public void setQuantities(List<Long> quantities) {
		this.quantities = quantities;
	}
	
	public void addQuantity(Long quantity) {
		this.quantities.add(quantity);
	}

	public static Histogram get() {
		Histogram histogram = new Histogram();

		String sql = "SELECT bin_from, bin_to, bin_count FROM query_histogram() where bin_count <> 0";

		List<SqlRow> findList = Ebean.createSqlQuery(sql).findList();
		for (SqlRow sqlRow : findList) {
			Long from = sqlRow.getLong("bin_from");
			Long to = sqlRow.getLong("bin_to");
			Long count = sqlRow.getLong("bin_count");
			String toString = to == null ? ">" : to.toString();
			
			histogram.addBin(from.toString() + "-" + toString + " (ms)");
			histogram.addQuantity(count);
		}
		
		return histogram;
	}
	
	public static void reset() {
		String sql = "select query_histogram_reset()";
		Ebean.createSqlQuery(sql).findUnique();
	}

}
