package models;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

public class HistogramDetail {

	private String query;
	private double duration;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public static List<HistogramDetail> getDetail(int from, int to) {
		List<HistogramDetail> details = new ArrayList<HistogramDetail>();

		String sql = "select query,duration_ms from query_histogram_detailed() where duration_ms between :from and :to order by duration_ms asc";

		List<SqlRow> findList = Ebean.createSqlQuery(sql)
				.setParameter("from", from)
				.setParameter("to", to)
				.findList();
		
		for (SqlRow sqlRow : findList) {
			
			String query = sqlRow.getString("query");
			double duration = sqlRow.getDouble("duration_ms");
			
			HistogramDetail detail = new HistogramDetail();
			detail.setQuery(query);
			detail.setDuration(duration);
			
			details.add(detail);
			
			
		}
		
		return details;
	}

}
