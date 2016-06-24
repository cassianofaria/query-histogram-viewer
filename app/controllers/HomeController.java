package controllers;

import java.util.List;

import models.Histogram;
import models.HistogramDetail;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index() {
    	return redirect("/assets/index.html");
    }
    
    public Result histogram() {
    	Histogram histogram = Histogram.get();
		return ok(Json.toJson(histogram));
    }
    
    public Result detail() {
    	int from = Integer.parseInt(request().getQueryString("from"));
    	int to = Integer.parseInt(request().getQueryString("to"));
    	
    	List<HistogramDetail> details = HistogramDetail.getDetail(from, to);
		return ok(Json.toJson(details));
    }
    
    public Result reset() {
    	Histogram.reset();
		return ok();
    }


}
